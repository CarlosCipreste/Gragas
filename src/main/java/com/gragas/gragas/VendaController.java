package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.*;
import static com.gragas.gragas.metodos.metodosGerais.clearAll;
import static com.gragas.gragas.metodos.metodosGerais.exibirAlerta;

public class VendaController implements Initializable {

    @FXML
    private TableColumn<ProdTable, String> ID;

    @FXML
    private ChoiceBox<String> vendaProdutosChoiceBox;
    @FXML
    private ObservableList<ProdTable> valoresProdTable = FXCollections.observableArrayList();

    @FXML
    private TextField vendaClienteTextField;

    @FXML
    private TextField vendaProdutoTextField;

    @FXML
    private TextField vendaQTDTextField;

    @FXML
    private Button vendaAdicionarProdButton;

    @FXML
    private TableView<ProdTable> vendaListaTableView;

    @FXML
    private TableColumn<ProdTable, String> nome;

    @FXML
    private TableColumn<ProdTable, Integer> qtd;

    @FXML
    private Button vendaFinalizarVendaButton;

    @FXML
    private Button voltarButton;
    @FXML
    private ObservableList<String> lista;
    @FXML
    private int idcliente;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
        clearAll(vendaClienteTextField,vendaProdutosChoiceBox,vendaQTDTextField,vendaListaTableView);
    }

    public void setvendaChoiceBoxValues(List<String> list) {


        // Criar a conexão com o banco de dados
        try {
            // Criar a consulta SQL
            String sql = "SELECT nome_produto FROM produto";

            // Criar o statement
            try (Statement statement = conexao.createStatement()) {
                // Executar a consulta e obter o resultado
                ResultSet resultSet = statement.executeQuery(sql);

                // Percorrer o resultado da consulta e adicionar os valores à ObservableList existente
                while (resultSet.next()) {
                    String nomeProduto = resultSet.getString("nome_produto");
                    list.add(nomeProduto);
                }
                // Definir a ObservableList como a fonte de itens do ChoiceBox
                vendaProdutosChoiceBox.getItems().setAll(list);
            }
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR,"ERRO","ERRO");
            e.printStackTrace();
        }
    }
    
    


    private void setupClienteValues(ObservableList<String> lista, TextField textField){
        try {
            String sql = "SELECT id_cliente,nome_cliente FROM cliente";
            try (Statement statement = conexao.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String nomeProduto = resultSet.getString("nome_cliente");
                    idcliente= resultSet.getInt("id_cliente");
                    lista.add(nomeProduto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AutoCompletionBinding<String> autoComplete = TextFields.bindAutoCompletion(textField,lista);

        autoComplete.setOnAutoCompleted((AutoCompletionBinding.AutoCompletionEvent<String> event) -> {
            String sugestaoSelecionada = event.getCompletion();
            System.out.println("Sugestão selecionada: " + sugestaoSelecionada);
        });

    }

    @FXML
    void ApagarProdutoSelecionado(ActionEvent event) {
        ProdTable itemSelecionado = vendaListaTableView.getSelectionModel().getSelectedItem();

        // Verifica se um item está selecionado
        if (itemSelecionado != null) {
            // Remove o item da lista de itens da tabela
            valoresProdTable.remove(itemSelecionado);
        }
    }

    @FXML
    void AdicionarProdutonaVenda(ActionEvent event) {
        String querySelect = "select id_produto from produto where nome_produto = ?";

        int IDProd = 0;
        String nomeProd = vendaProdutosChoiceBox.getValue();
        int qtdProd = Integer.parseInt(vendaQTDTextField.getText());

        try(PreparedStatement statement = conexao.prepareStatement(querySelect)){
            statement.setString(1,nomeProd);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                IDProd = resultSet.getInt("id_produto");
            }
        }catch(SQLException e){e.printStackTrace();}

        // Verifica se a lista está vazia
        if (valoresProdTable == null) {
            valoresProdTable = FXCollections.observableArrayList(
                    new ProdTable(IDProd,nomeProd, qtdProd)
            );
        } else {
            // Verifica se o produto já está presente na lista
            boolean produtoJaPresente = false;
            for (ProdTable produto : valoresProdTable) {
                //Se o produto já estiver na lista uma variavel booleana é utilizada para lançar um erro
                if (produto.getNomeProdClass().equals(nomeProd)) {
                    produtoJaPresente = true;
                    break;
                }
            }

            int qtd = Integer.parseInt(vendaQTDTextField.getText());

            querySelect = "select quantidade from produto where quantidade < ?";

            try(PreparedStatement statement = conexao.prepareStatement(querySelect)){
                statement.setInt(1,qtd);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    exibirAlerta(Alert.AlertType.ERROR,"Valor inválido!","Valor acima do que há no estoque");
                    return;
                }
            }catch (SQLException e){e.printStackTrace();}

            //Lançamento do erro
            if (produtoJaPresente) {
                exibirAlerta(Alert.AlertType.ERROR,"ERRO","O produto já está na tabela.");
                return;
            } else {
                valoresProdTable.add(new ProdTable(IDProd,nomeProd, qtdProd));
            }
            vendaListaTableView.setItems(valoresProdTable);
            System.out.println("Adicionado");
        }

    }

    @FXML
    public void FinalizarVenda(ActionEvent event) {

        ObservableList<ProdTable> listaProdTable = vendaListaTableView.getItems();
        int totalProdutos = listaProdTable.size();
        int contador = 0;

        for (ProdTable produto : listaProdTable) {
            contador++;

            Integer idProduto = produto.getIDProdClass();
            Integer qtd = produto.getQtdProdClass();
            int idfuncionario = LoginController.IDUser;

            // Execute a atualização para o ID e novo nome
            String sqlUpdate = "UPDATE produto SET quantidade = quantidade - ?  WHERE id_produto = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sqlUpdate)) {
                statement.setInt(1, qtd); // Define o novo valor do nome
                statement.setInt(2, idProduto); // Define o ID para a atualização

                statement.executeUpdate();

                System.out.println("Registro atualizado");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String queryInsert = "insert into venda(id_cliente,id_produto,id_funcionario)values(?,?,?)";
            try (PreparedStatement statement = conexao.prepareStatement(queryInsert)) {
                statement.setInt(1, idcliente);
                statement.setInt(2, idProduto);
                statement.setInt(3, idfuncionario);
                statement.executeUpdate();

                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("A atualização foi bem-sucedida. Linhas afetadas: " + linhasAfetadas);
                } else {
                    exibirAlerta(Alert.AlertType.ERROR, "Atualização mal-sucedida", "");
                    return;
                }

                if (contador == totalProdutos) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Venda finalizada", "Todos os produtos foram atualizados e a venda foi registrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


        @Override
        public void initialize (URL location, ResourceBundle resources){


            List<String> list = new ArrayList<String>();
            setvendaChoiceBoxValues(list);

            lista = FXCollections.observableArrayList();
            setupClienteValues(lista, vendaClienteTextField);

            ID.setCellValueFactory(new PropertyValueFactory<ProdTable, String>("IDProdClass"));
            nome.setCellValueFactory(new PropertyValueFactory<ProdTable, String>("nomeProdClass"));
            qtd.setCellValueFactory(new PropertyValueFactory<ProdTable, Integer>("qtdProdClass"));

        }


    }