package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdEstoque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.conexao;

public class EstoqueController implements Initializable {

    @FXML
    private TableView<ProdEstoque> EstoqueTableVIew;
    @FXML
    private TableColumn<ProdEstoque, Integer> estoqueIDTC;

    @FXML
    private TableColumn<ProdEstoque, String> estoqueNomeTC;

    @FXML
    private TableColumn<ProdEstoque, String> estoquePrecoTC;

    @FXML
    private TableColumn<ProdEstoque, Boolean> estoqueAlcoolicoTC;

    @FXML
    private TableColumn<ProdEstoque, String> estoqueTipoTC;

    @FXML
    private TableColumn<ProdEstoque, Date> estoqueValidadeTC;

    @FXML
    private TableColumn<ProdEstoque, Integer> estoqueQTDTC;
    private ObservableList<ProdEstoque> values = FXCollections.observableArrayList();

    @FXML
    private Button voltarButton;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

    void setupEstoqueValues() {
        String querySelect = "select * from produto;";
        System.out.println("Tentativa de estoque");

        try (PreparedStatement statement = conexao.prepareStatement(querySelect)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_produto");
                String nome = resultSet.getString("nome_produto");
                String preco = resultSet.getString("preco_produto");
                boolean alcool_S_N = resultSet.getBoolean("alcoolico_S_N");
                String tipo = resultSet.getString("tipo");
                Date validade = resultSet.getDate("validade");
                int quantidade = resultSet.getInt("quantidade");

                values.add(
                        new ProdEstoque(id, nome, preco, alcool_S_N, tipo, validade, quantidade)
                );
                EstoqueTableVIew.setItems(values);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupEstoqueValues();
    }
}