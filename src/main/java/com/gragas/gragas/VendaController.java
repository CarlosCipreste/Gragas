package com.gragas.gragas;

import com.gragas.gragas.classes.ProdVenda;
import com.gragas.gragas.metodos.metodosGerais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Label valorTotalLabel;

    @FXML
    private Pane notaFiscalPane;

    @FXML
    private Label nomeClienteLabel;

    @FXML
    private Label dataCompraLabel;
    @FXML
    private TableView<ProdVenda> notaFiscalTableView;

    @FXML
    private TableColumn<ProdVenda, String> produtoNotaTableCell;

    @FXML
    private TableColumn<ProdVenda, Integer> qtdNotaTableCell;

    @FXML
    private TableColumn<ProdVenda, Double> precoNotaTableCell;

    @FXML
    private Label valorTotalNotaLabel;

    private ObservableList<ProdVenda> valoresNotaFiscal = FXCollections.observableArrayList();

    private ObservableList<String> lista;
    public int idcliente;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
        clearAll(vendaClienteTextField,vendaProdutosChoiceBox,vendaQTDTextField,vendaListaTableView);
    }

    public void setvendaChoiceBoxValues(List<String> list) {


        // Criar a conexão com o banco de dados
        try {
            // Criar a consulta SQL
            String sql = "SELECT nome_produto FROM produto where ativo = true";

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
                    int idcliente= resultSet.getInt("id_cliente");
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

            double totalVenda = 0.0;

            for (ProdVenda produto : valoresProdVenda) {
                double precoProduto = produto.getPrecoProdClass();
                int quantidade = produto.getQtdProdClass();
                totalVenda += precoProduto * quantidade;
            }

            valorTotalLabel.setText("R$ "+totalVenda);
        }
        else{
            exibirAlerta(Alert.AlertType.ERROR,"Selecione um Produto!","Selecione um produto primeiro para apagar.");
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
            querySelect = "select quantidade from produto where id_produto = ? and ativo = true";

            try(PreparedStatement statement = conexao.prepareStatement(querySelect)){
                statement.setInt(1,IDProd);
                ResultSet resultSet = statement.executeQuery();

                if( resultSet.next()){
                    int qtdQuery = resultSet.getInt("quantidade");
                    if(qtdQuery < qtd){
                        exibirAlerta(Alert.AlertType.ERROR,"Erro","Quantidade inseria é maior da qu há em estoque");
                    }
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

            double totalVenda = 0.0;

            for (ProdVenda produto : valoresProdVenda) {
                double precoProduto = produto.getPrecoProdClass();
                int quantidade = produto.getQtdProdClass();
                totalVenda += precoProduto * quantidade;
            }

            valorTotalLabel.setText("R$ "+totalVenda);

            System.out.println("Adicionado");

            // Verifica se a lista está vazia
            if (valoresNotaFiscal == null) {
                valoresNotaFiscal = FXCollections.observableArrayList(
                        new ProdVenda(IDProd, nomeProd, qtdProd, precoProd)
                );
            }else {
                    valoresNotaFiscal.add(new ProdVenda(IDProd,nomeProd, qtdProd,precoProd));
                }
                notaFiscalTableView.setItems(valoresProdVenda);

        }
    }



    @FXML
    public void FinalizarVenda(ActionEvent event) {

        ObservableList<ProdVenda> listaProdVenda = FXCollections.observableArrayList(vendaListaTableView.getItems());

        int totalProdutos = listaProdVenda.size();
        int contador = 0;
        int idcliente = 0; // Valor padrão para o idcliente


        for (ProdVenda produto : listaProdVenda) {
            contador++;

            Integer idProduto = produto.getIDProdClass();
            Integer qtd = produto.getQtdProdClass();
            Double preco = produto.getPrecoProdClass();
            int idfuncionario = LoginController.IDUser;
            Double precoTotal = qtd * preco;
            BigDecimal precoBigDecimal = new BigDecimal(precoTotal);
            Timestamp horarioCompra = Timestamp.valueOf(LocalDateTime.now());

            // Executando a atualização para a nova quantidade de produtos
            String sqlUpdate = "UPDATE produto SET quantidade = quantidade - ?  WHERE id_produto = ?";
            try (PreparedStatement statement = conexao.prepareStatement(sqlUpdate)) {
                statement.setInt(1, qtd); // Define o novo valor do nome
                statement.setInt(2, idProduto); // Define o ID para a atualização

                statement.executeUpdate();

                System.out.println("Registro atualizado");
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            // Verificação se há o cliente selecionado no banco
            String querySelect = "SELECT id_cliente, nome_cliente FROM cliente WHERE nome_cliente = ?";
            String nomecliente = vendaClienteTextField.getText();
            try (PreparedStatement statement = conexao.prepareStatement(querySelect)) {
                statement.setString(1, nomecliente);
                ResultSet resultSet = statement.executeQuery();

                // Caso não haja o cliente definido, é criado um cliente AVULSO no DB
                if (!resultSet.next()) {

                    String queryInsert = "INSERT INTO cliente(nome_cliente) VALUES (?)";
                    try (PreparedStatement insert = conexao.prepareStatement(queryInsert)) {
                        insert.setString(1, nomecliente);
                        int linhasAfetadas = insert.executeUpdate();
                        System.out.println("Linhas Afetadas = " + linhasAfetadas);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        return;
                    }

                    String querySelect1 = "SELECT id_cliente FROM cliente WHERE nome_cliente = ?";
                    try (PreparedStatement insert = conexao.prepareStatement(querySelect1)) {
                        insert.setString(1, nomecliente);
                        ResultSet resultSet1 = insert.executeQuery();

                        if (resultSet1.next()) {
                            idcliente = resultSet1.getInt("id_cliente");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Caso haja um cliente com este nome, o ID é retornado
                    idcliente = resultSet.getInt("id_cliente");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Insert na tabela venda
            String queryInsert = "INSERT INTO venda (id_cliente, id_funcionario, id_produto, quantidade, preco_total, horario_compra)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            try (PreparedStatement statement = conexao.prepareStatement(queryInsert)) {

                statement.setInt(1, idcliente); // Substitui o primeiro parâmetro com o valor real do ID do cliente
                statement.setInt(2, idfuncionario); // Substitui o segundo parâmetro com o valor real do ID do funcionário
                statement.setInt(3, idProduto); // Substitui o terceiro parâmetro com o valor real do ID do produto
                statement.setInt(4, qtd); // Substitui o quarto parâmetro com o valor real da quantidade
                statement.setBigDecimal(5, precoBigDecimal); // Substitui o quinto parâmetro com o valor real do preço total
                statement.setTimestamp(6, horarioCompra);

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


                String nomeNota = vendaClienteTextField.getText();
                Date data = Date.valueOf(LocalDate.now());
                double totalVenda = 0.0;

                for (ProdVenda precoNota : valoresProdVenda) {
                    double precoProduto = precoNota.getPrecoProdClass();
                    int quantidade = precoNota.getQtdProdClass();
                    totalVenda += precoProduto * quantidade;
                }

                vendaListaTableView.setItems(valoresProdVenda);

                nomeClienteLabel.setText(nomeNota);
                dataCompraLabel.setText(data.toString());
                valorTotalNotaLabel.setText("R$ "+totalVenda);

                //Mostrar Nota Fiscal
                notaFiscalPane.setVisible(true);



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void sair(ActionEvent event){
        metodosGerais.clearAll(vendaClienteTextField,vendaProdutosChoiceBox,vendaQTDTextField,vendaListaTableView);
        HelloApplication.trocaTela("principal");
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

            produtoNotaTableCell.setCellValueFactory(new PropertyValueFactory<ProdVenda, String>("nomeProdClass"));
            qtdNotaTableCell.setCellValueFactory(new PropertyValueFactory<ProdVenda, Integer>("qtdProdClass"));
            precoNotaTableCell.setCellValueFactory(new PropertyValueFactory<ProdVenda, Double>("precoProdClass"));

        }


    }