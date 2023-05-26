package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CadastroController {

    @FXML
    private Pane menuPane;

    @FXML
    private Pane ProdutoPane;

    @FXML
    private Button voltarButton;

    @FXML
    private Button CadUsuariosButton;

    @FXML
    private Button CadClientesButton;

    @FXML
    private Button CadFornecedorButton;

    @FXML
    private Button CadProdutosButton;

    @FXML
    private CheckBox alcoolicoCheckBox;

    @FXML
    private TextField nomeProdutoTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private ChoiceBox<?> nalcoolicoChoiceBox;

    @FXML
    private ChoiceBox<?> alcoolicoChoiceBox;

    @FXML
    private CheckBox NAlcoolicoCkeckBox;

    @FXML
    private TextField validadeTextField;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    void Voltar(ActionEvent event){
        HelloApplication.trocaTela("principal");
    }

    @FXML
    void VoltarMenu(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
//        FornecedorPane.setVisible(false);
//        ClientePane.setVisible(false);
//        UsuarioPane.setVisible(false);

    }

    @FXML
    void EntrarProdutos(ActionEvent event) {
        ProdutoPane.setVisible(true);
        menuPane.setVisible(false);

    }

    /*@FXML
    void EntrarClientes(ActionEvent event) {
        ProdutoPane.setVisible(true);
        menuPane.setVisible(false);

    }

    @FXML
    void EntrarUsuarios(ActionEvent event) {
        ProdutoPane.setVisible(true);
        menuPane.setVisible(false);

    }

    @FXML
    void EntrarFornecedores(ActionEvent event) {
        ProdutoPane.setVisible(true);
        menuPane.setVisible(false);

    }*/



}
