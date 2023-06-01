package com.gragas.gragas;

import com.gragas.gragas.metodos.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.gragas.gragas.metodos.Formatacao.ApenasNumeros;

public class CadastroController implements Initializable {

    @FXML
    private Pane FuncionarioPane;

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
    private Pane FornecedorPane;

    @FXML
    private Button voltarButton13;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private TextField usuarioTextField1;

    @FXML
    private TextField usuarioTextField2;

    @FXML
    private TextField usuarioTextField3;

    @FXML
    private Pane ClientePane;

    @FXML
    private TextField cleinteNomeTextField;

    @FXML
    private TextField clienteCPFTextField;

    @FXML
    private TextField clienteTelefoneTextField;

    @FXML
    private Button voltarButton12;

    @FXML
    private Button cadClienteButton;

    @FXML
    private Pane ProdutoPane;

    @FXML
    private CheckBox alcoolicoCheckBox;

    @FXML
    private CustomTextField nomeProdutoTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private ChoiceBox<String> nalcoolicoChoiceBox;
    String[] nAlcoolicoValues = {"Suco","Refrigerante"};
    @FXML
    private ChoiceBox<String> alcoolicoChoiceBox;
    String[] AlcoolicoValues = {"Destilado","Fermentado"};

    @FXML
    private CheckBox NAlcoolicoCkeckBox;

    @FXML
    private TextField validadeTextField;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private Button voltarButton1;

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
    @FXML
    void cadProduto(ActionEvent event){
        metodosGerais.CadastrarProduto(nomeProdutoTextField,precoTextField,alcoolicoCheckBox,NAlcoolicoCkeckBox,alcoolicoChoiceBox,nalcoolicoChoiceBox,validadeTextField,quantidadeTextField);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nalcoolicoChoiceBox.getItems().addAll(nAlcoolicoValues);
        alcoolicoChoiceBox.getItems().addAll(AlcoolicoValues);


        //O método initialize() consegue dar Propriedades a elementos gráficos como uma formatação para um textfield
        Formatacao formatacao = new Formatacao();
        formatacao.ApenasNumeros(nomeProdutoTextField);

    }



}
