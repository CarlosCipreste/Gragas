package com.gragas.gragas;

import com.gragas.gragas.classes.ProdVenda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
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
    private TableColumn<ProdVenda, String> ID;

    @FXML
    private ChoiceBox<String> vendaProdutosChoiceBox;
    @FXML
    private ObservableList<ProdVenda> valoresProdVenda = FXCollections.observableArrayList();

    @FXML
    private TextField vendaClienteTextField;

    @FXML
    private TextField vendaProdutoTextField;

    @FXML
    private TextField vendaQTDTextField;

    @FXML
    private Button vendaAdicionarProdButton;

    @FXML
    private TableView<ProdVenda> vendaListaTableView;

    @FXML
    private TableColumn<ProdVenda, String> nome;

    @FXML
    private TableColumn<ProdVenda, Integer> qtd;
    @FXML
    private TableColumn<ProdVenda, Double> preco;


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
        ProdVenda itemSelecionado = vendaListaTableView.getSelectionModel().getSelectedItem();

        // Verifica se um item está selecionado
        if (itemSelecionado != null) {
            // Remove o item da lista de itens da tabela
            valoresProdVenda.remove(itemSelecionado);
        }
    }

    @FXML
    void AdicionarProdutonaVenda(ActionEvent event) {
        String querySelect = "select id_produto,preco_produto from produto where nome_produto = ?";

        int IDProd = 0;
        String nomeProd = vendaProdutosChoiceBox.getValue();
        int qtdProd = Integer.parseInt(vendaQTDTextField.getText());
        String precoString = "";
        Double precoProd = 0.0;

        try(PreparedStatement statement = conexao.prepareStatement(querySelect)){
            statement.setString(1,nomeProd);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                IDProd = resultSet.getInt("id_produto");
                precoString = resultSet.getString("preco_produto");
                precoString = precoString.replace("R$"," ").trim();
                precoString = precoString.replace(",", ".");
                precoProd = Double.parseDouble(precoString);

            }
        }catch(SQLException e){e.printStackTrace();}

        // Verifica se a lista está vazia
        if (valoresProdVenda == null) {
            valoresProdVenda = FXCollections.observableArrayList(
                    new ProdVenda(IDProd,nomeProd, qtdProd,precoProd)
            );
        } else {
            // Verifica se o produto já está presente na lista
            boolean produtoJaPresente = false;
            for (ProdVenda produto : valoresProdVenda) {
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
                valoresProdVenda.add(new ProdVenda(IDProd,nomeProd, qtdProd,precoProd));
            }
            vendaListaTableView.setItems(valoresProdVenda);
            System.out.println("Adicionado");
        }

    }

    @FXML
    public void FinalizarVenda(ActionEvent event) {

        ObservableList<ProdVenda> listaProdVenda = vendaListaTableView.getItems();
        int totalProdutos = listaProdVenda.size();
        int contador = 0;

        for (ProdVenda produto : listaProdVenda) {
            contador++;

            Integer idProduto = produto.getIDProdClass();
            Integer qtd = produto.getQtdProdClass();
            Double preco = produto.getPrecoProdClass();
            int idfuncionario = LoginController.IDUser;
            Double precoTotal = qtd * preco;
            BigDecimal precoBigDecimal = new BigDecimal(precoTotal);

            // Executando a atualização para a nova quantidade de produtos
            String sqlUpdate = "UPDATE produto SET quantidade = quantidade - ?  WHERE id_produto = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sqlUpdate)) {
                statement.setInt(1, qtd); // Define o novo valor do nome
                statement.setInt(2, idProduto); // Define o ID para a atualização

                statement.executeUpdate();

                System.out.println("Registro atualizado");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Insert na tabela venda
            String queryInsert = "INSERT INTO venda (id_cliente, id_funcionario, id_produto, quantidade, preco_total)\n" +
                    "VALUES (?, ?, ?, ?, ?);";
            try (PreparedStatement statement = conexao.prepareStatement(queryInsert)) {
                statement.setInt(1, idcliente); // Substitui o primeiro parâmetro com o valor real do ID do cliente
                statement.setInt(2, idfuncionario); // Substitui o segundo parâmetro com o valor real do ID do funcionário
                statement.setInt(3, idProduto); // Substitui o terceiro parâmetro com o valor real do ID do produto
                statement.setInt(4, qtd); // Substitui o quarto parâmetro com o valor real da quantidade
                statement.setBigDecimal(5, precoBigDecimal); // Substitui o quinto parâmetro com o valor real do preço total
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

            ID.setCellValueFactory(new PropertyValueFactory<ProdVenda, String>("IDProdClass"));
            nome.setCellValueFactory(new PropertyValueFactory<ProdVenda, String>("nomeProdClass"));
            qtd.setCellValueFactory(new PropertyValueFactory<ProdVenda, Integer>("qtdProdClass"));
            preco.setCellValueFactory(new PropertyValueFactory<ProdVenda, Double>("precoProdClass"));

        }


    }