package com.gragas.gragas;

import com.gragas.gragas.metodos.Formatacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    @FXML
    private Pane FornecedorPane;

    @FXML
    private Pane FuncionarioPane;

    @FXML
    private Pane ProdutoPane;

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
    private Button voltarButton1;

    @FXML
    private Button voltarButton11;

    @FXML
    private Button CadProdutosButton1;

    @FXML
    private TextField FuncNomeTextField;

    @FXML
    private TextField CPFFuncTextField;

    @FXML
    private TextField FuncLogin;

    @FXML
    private PasswordField funcSenhaTextField;

    @FXML
    private Pane ClientePane;

    @FXML
    private TextField cleinteNomeTextField;

    @FXML
    private TextField clienteCPFTextField;

    @FXML
    private TextField clienteTelefoneTextField;

    @FXML
    private Button cadClienteButton;

    @FXML
    private Pane menuPane;

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
    void Voltar(ActionEvent event){
        HelloApplication.trocaTela("principal");
    }

    @FXML
    void VoltarMenu(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);

    }

    @FXML
    void EntrarProdutos(ActionEvent event) {
        ProdutoPane.setVisible(true);
        menuPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);
        FornecedorPane.setVisible(false);
    }

    @FXML
    void EntrarClientes(ActionEvent event) {
        ProdutoPane.setVisible(false);
        menuPane.setVisible(false);
        ClientePane.setVisible(true);
        FuncionarioPane.setVisible(false);
        FornecedorPane.setVisible(false);

    }

    @FXML
    void EntrarFuncionario(ActionEvent event) {
        ProdutoPane.setVisible(false);
        menuPane.setVisible(false);
        FuncionarioPane.setVisible(true);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);

    }

    @FXML
    void EntrarFornecedores(ActionEvent event) {
        FornecedorPane.setVisible(true);
        menuPane.setVisible(false);
        ProdutoPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //O método initialize() consegue dar Propriedades a elementos gráficos como uma formatação para um textfield
        Formatacao formatacao = new Formatacao();

    }



}
