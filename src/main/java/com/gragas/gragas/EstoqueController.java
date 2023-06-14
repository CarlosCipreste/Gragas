package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdEstoque;
import com.gragas.gragas.metodos.ProdTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.conexao;
import static com.gragas.gragas.metodos.metodosGerais.exibirAlerta;
import static com.gragas.gragas.metodos.metodosGerais.isTextFieldValueValido;

public class EstoqueController implements Initializable {

    @FXML
    private Pane EstoqueViewPane;
    @FXML
    private Pane AtualizarPane;

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
    private Button EnterAtualizarButton;

    @FXML
    private Button voltarButton;

    @FXML
    private CheckBox atualizaralcoolicoCheckBox;

    @FXML
    private TextField atualizarNomeTextField;

    @FXML
    private TextField atualizarPrecoTextField;

    @FXML
    private ChoiceBox<String> atualizarNAlcoolicoChoiceBox;

    @FXML
    private ChoiceBox<String> atualizarAcoolicoChoiceBox;

    @FXML
    private CheckBox atualizarNAlcoolicoCheckBox;

    @FXML
    private TextField atualizarQuantidadeTextField;

    @FXML
    private Button voltarEstoqueButton;

    @FXML
    private Button AtualizarProdutoButton;

    String[] nAlcoolicoValues = {"Suco", "Refrigerante"};
    String[] AlcoolicoValues = {"Destilado", "Fermentado"};

    /*@FXML
    void SelectedAlcoolicoCheckBox(ActionEvent event) {
        if (alcoolicoCheckBox.isSelected()) {
            alcoolicoChoiceBox.setVisible(true);
            NAlcoolicoCheckBox.setSelected(false);
            nalcoolicoChoiceBox.setVisible(false);
            nalcoolicoChoiceBox.getSelectionModel().clearSelection();
        }
        if (!alcoolicoCheckBox.isSelected()) {
            alcoolicoChoiceBox.setVisible(false);
        }
    }*/



    @FXML
    void enterAtualizar(ActionEvent event) {
        ProdEstoque itemSelecionado = EstoqueTableVIew.getSelectionModel().getSelectedItem();

        // Verifica se um item está selecionado
        if (itemSelecionado != null) {
            // Entra na tela de Atualizar os produtos
            AtualizarPane.setVisible(true);
            EstoqueViewPane.setVisible(false);



            atualizarNomeTextField.setText(itemSelecionado.getNome());
            atualizarPrecoTextField.setText(itemSelecionado.getPreco());
            atualizarAcoolicoChoiceBox.getItems().addAll(AlcoolicoValues);
            atualizarNAlcoolicoChoiceBox.getItems().addAll(nAlcoolicoValues);
            atualizarQuantidadeTextField.setText(Integer.toString(itemSelecionado.getQuantidade()));
        }

        
    }
    @FXML
    void AtualizarProduto(ActionEvent event) {

        //Variaveis para a Query de mySQL
        String nomeProduto = atualizarNomeTextField.getText().toLowerCase();
        String precoProduto = atualizarPrecoTextField.getText();

        //Definindo o valor do booleano com base da escolha do ChoiceBox
        boolean Alcoolico_S_N;
        if(atualizaralcoolicoCheckBox.isSelected()) {
            Alcoolico_S_N = true;
        }
        else{
            Alcoolico_S_N = false;
        }
        String tipo;
        if(atualizaralcoolicoCheckBox.isSelected() || atualizarAcoolicoChoiceBox.getValue() == "Destilado"){
            tipo = "destilado";
        }
        else{
            tipo = "fermentado";
        }

        if(atualizarNAlcoolicoCheckBox.isSelected() || atualizarNAlcoolicoChoiceBox.getValue() == "Suco"){
            tipo = "suco";
        }
        else{
            tipo = "refrigerante";
        }
        int quantidadeProduto = Integer.parseInt(atualizarQuantidadeTextField.getText());

    }

    @FXML
    void SelectedAlcoolicoCheckBox(ActionEvent event) {
        if(atualizaralcoolicoCheckBox.isSelected()) {
            atualizarNAlcoolicoCheckBox.setSelected(false);
            atualizarAcoolicoChoiceBox.setVisible(true);
            atualizarNAlcoolicoChoiceBox.setVisible(false);
            atualizarNAlcoolicoChoiceBox.getSelectionModel().clearSelection();

        }
        if (!atualizaralcoolicoCheckBox.isSelected()) {
            atualizarAcoolicoChoiceBox.setVisible(false);
        }
    }

    @FXML
    void SelectedNAlcoolicoCheckBox(ActionEvent event) {
        if(atualizarNAlcoolicoCheckBox.isSelected()) {
            atualizaralcoolicoCheckBox.setSelected(false);
            atualizarAcoolicoChoiceBox.setVisible(false);
            atualizarNAlcoolicoChoiceBox.setVisible(true);
            atualizarAcoolicoChoiceBox.getSelectionModel().clearSelection();

        }
        if (!atualizarNAlcoolicoCheckBox.isSelected()) {
            atualizarNAlcoolicoChoiceBox.setVisible(false);
        }
    }
    @FXML
    void voltarEstoque(ActionEvent event) {
        AtualizarPane.setVisible(false);
        EstoqueViewPane.setVisible(true);
    }

    @FXML
    void Voltar(){
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

        estoqueIDTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, Integer>("id"));
        estoqueNomeTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, String>("nome"));
        estoquePrecoTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, String>("preco"));
        estoqueAlcoolicoTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, Boolean>("alcoolico"));
        estoqueTipoTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, String>("tipo"));
        estoqueValidadeTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, Date>("validade"));
        estoqueQTDTC.setCellValueFactory(new PropertyValueFactory<ProdEstoque, Integer>("quantidade"));

        setupEstoqueValues();
    }
}