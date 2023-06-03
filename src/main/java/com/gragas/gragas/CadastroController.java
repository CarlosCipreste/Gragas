package com.gragas.gragas;

import com.gragas.gragas.metodos.Formatacao;
import com.gragas.gragas.metodos.metodosGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    @FXML
    private static Pane menuPane;

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
    private static Pane ClientePane;

    @FXML
    private TextField clienteNomeTextField;

    @FXML
    private TextField clienteCPFTextField;

    @FXML
    private TextField clienteTelefoneTextField;

    @FXML
    private Button voltarButton12;

    @FXML
    private Button cadClienteButton;

    @FXML
    private static Pane FuncionarioPane;

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
    private static Pane FornecedorPane;

    @FXML
    private Button voltarButton13;

    @FXML
    private TextField fornecedorNomeTextField;

    @FXML
    private TextField fornecedorEnderecoTextField;

    @FXML
    private TextField fornecedorCNPJTextField;

    @FXML
    private TextField FornecedorTelefoneTextField;

    @FXML
    private static Pane ProdutoPane;

    @FXML
    private CheckBox alcoolicoCheckBox;

    @FXML
    private TextField nomeProdutoTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private ChoiceBox<String> nalcoolicoChoiceBox;

    @FXML
    private ChoiceBox<String> alcoolicoChoiceBox;

    @FXML
    private CheckBox NAlcoolicoCheckBox;

    @FXML
    private TextField quantidadeTextField;
    @FXML
    private DatePicker validadeDatePicker;

    @FXML
    private TextField validadeTextField;
    @FXML
    private Button voltarButton1;

    @FXML
    private Button CadProdutoButton;

    String[] nAlcoolicoValues = {"Suco", "Refrigerante"};
    String[] AlcoolicoValues = {"Destilado", "Fermentado"};

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

    @FXML
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
    }

    @FXML
    void SelectedNAlcoolicoCheckBox(ActionEvent event) {
        if (NAlcoolicoCheckBox.isSelected()) {
            nalcoolicoChoiceBox.setVisible(true);
            alcoolicoCheckBox.setSelected(false);
            alcoolicoChoiceBox.setVisible(false);
            alcoolicoChoiceBox.getSelectionModel().clearSelection();

        }
        if (!NAlcoolicoCheckBox.isSelected()) {
            nalcoolicoChoiceBox.setVisible(false);
        }
    }

    @FXML
    public static void VoltarMenu(ActionEvent event) {
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
    void cadProduto(ActionEvent event) {
        metodosGerais.CadastrarProduto(nomeProdutoTextField, precoTextField, alcoolicoCheckBox, NAlcoolicoCheckBox, alcoolicoChoiceBox, nalcoolicoChoiceBox, validadeDatePicker, quantidadeTextField);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //O método initialize() consegue dar Propriedades a elementos gráficos como uma formatação para um textfield

        nalcoolicoChoiceBox.getItems().addAll(nAlcoolicoValues);
        alcoolicoChoiceBox.getItems().addAll(AlcoolicoValues);

        Formatacao formatacao = new Formatacao();

        //Formatando os TextField para CPF
        formatacao.formataCPFEnquantoDigita(CPFFuncTextField);
        formatacao.formataCPFEnquantoDigita(clienteCPFTextField);

        //Formatando os TextField para CNPJ
        formatacao.formataCNPJDinamico(fornecedorCNPJTextField);

        //Formatando os TextField para Número de Celular
        formatacao.formataCelularDinamico(FornecedorTelefoneTextField);
        formatacao.formataCelularDinamico(clienteTelefoneTextField);

        //Formatador de Preço
        formatacao.formataPrecoEnquantoDigita(precoTextField);

        //Limitando a quantidade caracteres dos TextFields que nao recebem os Formatadores anteriores
        formatacao.LimitadorCaracteres(FuncNomeTextField, 60);
        formatacao.LimitadorCaracteres(FuncLogin, 16);
        formatacao.LimitadorCaracteres(funcSenhaTextField, 16);

        formatacao.LimitadorCaracteres(fornecedorNomeTextField, 60);
        formatacao.LimitadorCaracteres(fornecedorEnderecoTextField, 60);

        formatacao.LimitadorCaracteres(nomeProdutoTextField, 60);
        formatacao.LimitadorCaracteres(quantidadeTextField, 3);
        formatacao.LimitadorCaracteres(clienteNomeTextField, 60);

        //Transformando os TextField para receberem apenas Letras
        formatacao.ApenasLetras(FuncNomeTextField);

        //Formatando para Receber apenas números
        formatacao.ApenasNumeros(quantidadeTextField);
    }


}
