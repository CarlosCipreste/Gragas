package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.*;

public class VendaController implements Initializable {

    @FXML
    private static ChoiceBox<String> vendaProdutosChoiceBox;

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
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }
    @FXML
    ObservableList<ProdTable> valoresProdTable = FXCollections.observableArrayList(
            new ProdTable("Batata",4),
            new ProdTable("Banana",2)
    );

    public void setChoiceBoxValues(ObservableList items) {


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
                    items.add(nomeProduto);
                }

                // Definir a ObservableList como a fonte de itens do ChoiceBox
                vendaProdutosChoiceBox.setItems(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> items = FXCollections.observableArrayList();
        vendaProdutosChoiceBox.getItems().addListener();
        setChoiceBoxValues(items);

        nome.setCellValueFactory(new PropertyValueFactory<ProdTable,String>("nomeProdClass"));
        qtd.setCellValueFactory(new PropertyValueFactory<ProdTable,Integer>("qtdProdClass"));

        vendaListaTableView.setItems(valoresProdTable);
    }


}
