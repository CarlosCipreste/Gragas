package com.gragas.gragas;

import com.gragas.gragas.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.conexao;
import static com.gragas.gragas.metodos.metodosGerais.exibirAlerta;

public class RegistrosController implements Initializable {

    @FXML
    private TableView<Funcionario> funcionarioTableView;
        @FXML
        private TableColumn<Funcionario, Integer> IDFuncionarios;

        @FXML
        private TableColumn<Funcionario, String> nomeFuncionarios;

        @FXML
        private TableColumn<Funcionario, String> CPFFuncionarios;

        @FXML
        private TableColumn<Funcionario,String> usuarioFuncionarios;

    @FXML
    private TableView<Cliente> clienteTableView;

        @FXML
        private TableColumn<Cliente,Integer> IDClientes;

        @FXML
        private TableColumn<Cliente, String> nomeClientes;

        @FXML
        private TableColumn<Cliente, String> CPFClientes;

        @FXML
        private TableColumn<Cliente, String> telefoneClientes;

    @FXML
    private TableView<Fornecedor> fornecedorTableView;

        @FXML
        private TableColumn<Fornecedor, Integer> fornecedorID;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorNome;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorEndereco;

        @FXML
        private TableColumn<Fornecedor, String> CNPJFornecedor;

        @FXML
        private TableColumn<Fornecedor, String> telefoneFornecedor;

    @FXML
    private TableView<Venda> vendasTableView;

        @FXML
        private TableColumn<Venda,Integer> IDVenda;
        @FXML
        private TableColumn<Venda, String> clienteNomeVendas;

        @FXML
        private TableColumn<Venda, String> funcionarioNomeVendas;

        @FXML
        private TableColumn<Venda, String> produtoNomeVendas;

        @FXML
        private TableColumn<Venda, Integer> quantidadeProdutoVendas;

        @FXML
        private TableColumn<Venda, Double> precoVendas;
        @FXML
        private TableColumn<Venda, Timestamp> horarioCompra;

    @FXML
    private Pane FuncionarioPane;


    @FXML
    private Button voltarButton;

    private ObservableList funcionarioValues = FXCollections.observableArrayList();
    private ObservableList clienteValues = FXCollections.observableArrayList();
    private ObservableList fornecedorValues = FXCollections.observableArrayList();
    private ObservableList vendaValues = FXCollections.observableArrayList();


    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

    @FXML
    void OpenClientesTable(ActionEvent event) {
        clienteTableView.setVisible(true);
        clienteTableView.toFront();
    }

    @FXML
    void OpenFornecedorTable(ActionEvent event) {
        fornecedorTableView.setVisible(true);
        fornecedorTableView.toFront();
    }

    @FXML
    void OpenFuncionarioTable(ActionEvent event) {
        funcionarioTableView.setVisible(true);
        funcionarioTableView.toFront();
    }

    @FXML
    void OpenVendasTable(ActionEvent event) {
        vendasTableView.setVisible(true);
        vendasTableView.toFront();
    }

    @FXML
    void enterAtualizarFuncionario(ActionEvent event){

    }

    @FXML
    void ApagarFuncionario(){
        Funcionario itemSelecionado = funcionarioTableView.getSelectionModel().getSelectedItem();
        // Verifica se um item está selecionado

        if (itemSelecionado != null) {
            exibirAlerta(Alert.AlertType.CONFIRMATION,"Tem Certeza?","Tem Certeza que quer APAGAR um Produto?");

            String queryDelete = "UPDATE funcionario\n" +
                    "SET ativo = FALSE\n" +
                    "WHERE id_produto = ?;";

            try(PreparedStatement statement = conexao.prepareStatement(queryDelete)){
                statement.setInt(1,itemSelecionado.getIDFuncionarioClass());
                int linhasAfetadas = statement.executeUpdate();

                if(linhasAfetadas > 0){
                    exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso","Produto Apagado com Sucesso!");
                }
            }catch(SQLException e){
                e.printStackTrace();}
        }else {
            exibirAlerta(Alert.AlertType.INFORMATION,"Informe o Produto","Primeiro você precisa selecionar um Produto");
        }
    }

    void setupRegistrosValues(){

        //Setando valores para FUNCIONARIOS

        String selectFuncionario = "select * from funcionario";

        try (PreparedStatement statement = conexao.prepareStatement(selectFuncionario)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_funcionario");
                String nome = resultSet.getString("nome_funcionario");
                String CPF = resultSet.getString("cpf_funcionario");
                String usuario = resultSet.getString("login");

                funcionarioValues.add(
                        new Funcionario(ID,nome,CPF,usuario)
                );
                funcionarioTableView.setItems(funcionarioValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String selectCliente = "select * from cliente";

        try (PreparedStatement statement = conexao.prepareStatement(selectCliente)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_cliente");
                String nome = resultSet.getString("nome_cliente");
                String CPF = resultSet.getString("cpf_cliente");
                String telefone = resultSet.getString("telefone_cliente");

                clienteValues.add(
                        new Cliente(ID,nome,CPF,telefone)
                );
                clienteTableView.setItems(clienteValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectFornecedor = "select * from fornecedor";

        try (PreparedStatement statement = conexao.prepareStatement(selectFornecedor)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_fornecedor");
                String nome = resultSet.getString("nome_fornecedor");
                String endereco = resultSet.getString("endereco_fornecedor");
                String CNPJ = resultSet.getString("CNPJ_fornecedor");
                String telefone = resultSet.getString("telefone_fornecedor");

                fornecedorValues.add(
                        new Fornecedor(ID,nome,endereco,CNPJ,telefone)
                );
                fornecedorTableView.setItems(fornecedorValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String vendaSelect = "SELECT venda.id_venda,cliente.nome_cliente, funcionario.nome_funcionario, produto.nome_produto, venda.quantidade, venda.preco_total, venda.horario_compra\n"+
        "FROM venda\n"+
        "JOIN funcionario ON venda.id_funcionario = funcionario.id_funcionario\n"+
        "JOIN produto ON venda.id_produto = produto.id_produto\n"+
        "JOIN cliente ON venda.id_cliente = cliente.id_cliente\n";

        try (PreparedStatement statement = conexao.prepareStatement(vendaSelect)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int IDVenda = resultSet.getInt("id_venda");
                String nomeCliente = resultSet.getString("nome_cliente");
                String nomeFuncionario = resultSet.getString("nome_funcionario");
                String nomeProduto= resultSet.getString("nome_produto");
                int quantidadeComprada = resultSet.getInt("quantidade");
                BigDecimal precototal =  resultSet.getBigDecimal("preco_total");
                Timestamp horarioCompra = resultSet.getTimestamp("horario_compra");


                vendaValues.add(
                        new Venda(IDVenda,nomeCliente, nomeFuncionario,nomeProduto,quantidadeComprada,precototal,horarioCompra)
                );
                System.out.println("bruh");
                vendasTableView.setItems(vendaValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SETANDO OS VALORES E ESPECIFICAÇÃO DAS COLUNAs NA TABLE VIEW

        //Table View de Funcionarios
        IDFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("IDFuncionarioClass"));
        nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nomeFuncionarioClass"));
        CPFFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("CPFFuncionarioClass"));
        usuarioFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("usuarioFuncionarioClass"));
        //Table view de Clientes
        IDClientes.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("IDClienteClass"));
        nomeClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nomeClienteClass"));
        CPFClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPFClienteClass"));
        telefoneClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefoneClienteClass"));
        //Table View de Clientes
        fornecedorID.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("IDFornecedorClass"));
        fornecedorNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedorClass"));
        fornecedorEndereco.setCellValueFactory(new PropertyValueFactory<>("enderecoFornecedorClass"));
        CNPJFornecedor.setCellValueFactory(new PropertyValueFactory<>("CNPJFornecedorClass"));
        telefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("telefoneFornecedorClass"));
        //Table View de Vendas
        IDVenda.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("IDVendas"));
        clienteNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeClienteVenda"));
        funcionarioNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeFuncionarioVenda"));
        produtoNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeProdutoVenda"));
        quantidadeProdutoVendas.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("quantidade"));
        precoVendas.setCellValueFactory(new PropertyValueFactory<Venda, Double>("precoTotal"));
        horarioCompra.setCellValueFactory(new PropertyValueFactory<Venda, Timestamp>("horarioCompra"));

        setupRegistrosValues();
    }
}
