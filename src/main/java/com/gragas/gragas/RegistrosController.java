package com.gragas.gragas;

import com.gragas.gragas.classes.Cliente;
import com.gragas.gragas.classes.Fornecedor;
import com.gragas.gragas.classes.Funcionario;
import com.gragas.gragas.classes.Vendas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrosController implements Initializable {

    @FXML
    private TableView<Funcionario> funcionarioTableView;

        @FXML
        private TableColumn<Funcionario, String> nomeFuncionarios;

        @FXML
        private TableColumn<Funcionario, String> CPFFuncionarios;

        @FXML
        private TableColumn<Funcionario,String> usuarioFuncionarios;

    @FXML
    private TableView<Cliente> clienteTableView;

        @FXML
        private TableColumn<Cliente, String> nomeClientes;

        @FXML
        private TableColumn<Cliente, String> CPFClientes;

        @FXML
        private TableColumn<Cliente, String> telefoneClientes;

    @FXML
    private TableView<Fornecedor> fornecedorTableView;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorNome;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorEndereço;

        @FXML
        private TableColumn<Fornecedor, String> CNPJFornecedor;

        @FXML
        private TableColumn<Fornecedor, String> telefoneFornecedor;

    @FXML
    private TableView<Vendas> vendasTableView;

        @FXML
        private TableColumn<Vendas, String> clienteNomeVendas;

        @FXML
        private TableColumn<Vendas, String> funcionarioNomeVendas;

        @FXML
        private TableColumn<Vendas, String> produtoNomeVendas;

        @FXML
        private TableColumn<Vendas, String> quantidadeProdutoVendas;

        @FXML
        private TableColumn<Vendas, String> precoVendas;
    @FXML
    private Button voltarButton;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

    @FXML
    void OpenClientesTable(ActionEvent event) {

    }

    @FXML
    void OpenFornecedorTable(ActionEvent event) {

    }

    @FXML
    void OpenFuncionarioTable(ActionEvent event) {

    }

    @FXML
    void OpenVendasTable(ActionEvent event) {

    }

    void setupRegistrosValues(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario>());
        CPFFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario>());
        usuarioFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario>());

        nomeClientes.setCellValueFactory(new PropertyValueFactory<Cliente>());
        CPFClientes.setCellValueFactory(new PropertyValueFactory<Cliente>());
        telefoneClientes.setCellValueFactory(new PropertyValueFactory<Cliente>());

        fornecedorNome.setCellValueFactory(new PropertyValueFactory<Fornecedor>());
        fornecedorEndereço.setCellValueFactory(new PropertyValueFactory<Fornecedor>());
        CNPJFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor>());
        telefoneFornecedor.setCellValueFactory(new PropertyValueFactory<Fornecedor>());

        clienteNomeVendas.setCellValueFactory(new PropertyValueFactory<Vendas>());
        funcionarioNomeVendas.setCellValueFactory(new PropertyValueFactory<Vendas>());
        produtoNomeVendas.setCellValueFactory(new PropertyValueFactory<Vendas>());
        quantidadeProdutoVendas.setCellValueFactory(new PropertyValueFactory<Vendas>());
        precoVendas.setCellValueFactory(new PropertyValueFactory<Vendas>());

        setupRegistrosValues();
    }
}
