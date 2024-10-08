package com.gragas.gragas;

import com.gragas.gragas.metodos.*;
import com.gragas.gragas.metodos.metodosGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.conexao;
import static com.gragas.gragas.metodos.metodosGerais.*;

public class CadastroController implements Initializable {

    @FXML
    private Pane FornecedorPane;

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
    private Pane ClientePane;

    @FXML
    private TextField clienteNomeTextField;

    @FXML
    private TextField clienteCPFTextField;

    @FXML
    private TextField clienteEnderecoTextField;

    @FXML
    private TextField clienteTelefoneTextField;

    @FXML
    private TextField clienteCelularTextField;

    @FXML
    private Button VoltarClienteButton;

    @FXML
    private Button cadClienteButton;

    @FXML
    private Pane FuncionarioPane;

    @FXML
    private Button voltarFuncionarioButton;

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
    private Pane ProdutoPane;

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
    private Button voltarProdutoButton;

    @FXML
    private Button CadProdutoButton;

    @FXML
    private DatePicker validadeDatePicker;

    @FXML
    private CheckBox administradorCheckBox;

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
    public void VoltarMenuProd(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);

        clearAll(nomeProdutoTextField,precoTextField,alcoolicoCheckBox,nalcoolicoChoiceBox,NAlcoolicoCheckBox,nalcoolicoChoiceBox,validadeDatePicker,quantidadeTextField);

    }
    @FXML
    public void VoltarMenuCliente(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);


        clearAll(clienteNomeTextField,clienteCPFTextField,clienteTelefoneTextField);

    }

    @FXML
    public void VoltarMenuFuncionario(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);

        clearAll(FuncNomeTextField,CPFFuncTextField,FuncLogin,funcSenhaTextField);

    }

    @FXML
    public void VoltarMenuFornecedor(ActionEvent event) {
        menuPane.setVisible(true);
        ProdutoPane.setVisible(false);
        FornecedorPane.setVisible(false);
        ClientePane.setVisible(false);
        FuncionarioPane.setVisible(false);

        clearAll(fornecedorNomeTextField,fornecedorEnderecoTextField,fornecedorCNPJTextField,fornecedorCNPJTextField);

    }

    @FXML
    void EntrarProdutos(ActionEvent event) {
        if(LoginController.administrador) {
            ProdutoPane.setVisible(true);
            menuPane.setVisible(false);
            ClientePane.setVisible(false);
            FuncionarioPane.setVisible(false);
            FornecedorPane.setVisible(false);
        }
        else {
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Você não possui Permissão para adicionar Produtos");
        }

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
        if(LoginController.administrador) {
            ProdutoPane.setVisible(false);
            menuPane.setVisible(false);
            FuncionarioPane.setVisible(true);
            FornecedorPane.setVisible(false);
            ClientePane.setVisible(false);
        }
        else {
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Você não possui Permissão para adicionar Funcionários");

        }
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

        //VERIFICAÇÃO
        try {
            // Verifica se todos os TextField possuem conteúdo
            if (nomeProdutoTextField.getText().isEmpty() || precoTextField.getText().isEmpty() || quantidadeTextField.getText().isEmpty()) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Preencha todos os campos!");
                return;
            }

            // Verifica se um dos CheckBox está selecionado
            if (!(alcoolicoCheckBox.isSelected() || NAlcoolicoCheckBox.isSelected())) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma opção para a bebida!");
                return;
            }

            // Verifica se um valor dos dois ChoiceBox está selecionado
            if (alcoolicoChoiceBox.getSelectionModel().isEmpty() && nalcoolicoChoiceBox.getSelectionModel().isEmpty()) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma opção para o tipo de bebida bebida");
                return;
            }
            // Verifica se há uma data no DatePicker
            if (validadeDatePicker.getValue() == null) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma Data de validade para o Produto");
                return;
            }
            // Verifica se o valor de preço possui 2 números após a virgula
            if (isTextFieldValueValido(precoTextField)) {

            } else {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "O preço precisa ter 2 algarismo após a vírgula");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Caso nao haja nenhum erro com os componentes
         * O sistema é liberado para fazer o cadastro*/

        //Variaveis para a Query de mySQL
        String nomeProduto = nomeProdutoTextField.getText().toLowerCase();
        String precoProduto = precoTextField.getText();

        //Definindo o valor do booleano com base da escolha do ChoiceBox
        boolean Alcoolico_S_N;
        if(alcoolicoCheckBox.isSelected()) {
            Alcoolico_S_N = true;
        }
        else{
            Alcoolico_S_N = false;
        }

        String tipo;
        if(alcoolicoCheckBox.isSelected() || alcoolicoChoiceBox.getValue() == "Destilado"){
            tipo = "destilado";
        }
        else{
            tipo = "fermentado";
        }

        if(NAlcoolicoCheckBox.isSelected() || nalcoolicoChoiceBox.getValue() == "Suco"){
            tipo = "suco";
        }
        else{
            tipo = "refrigerante";
        }

        LocalDate validadeProduto = validadeDatePicker.getValue();

        int quantidadeProduto = Integer.parseInt(quantidadeTextField.getText());
        try{
            String query = "select * from produto WHERE nome_produto = ? and preco_produto = ? and alcoolico_S_N = ? and tipo = ? and validade = ? and quantidade = ?";

//          Posicionando as Variáveis dentro da STRING de QUERY
            PreparedStatement select = conexao.prepareStatement(query);
            select.setString(1,nomeProduto);
            select.setString(2,precoProduto);
            select.setBoolean(3,Alcoolico_S_N);
            select.setString(4,tipo);
            select.setObject(5,validadeProduto);
            select.setInt(6,quantidadeProduto);
            ResultSet resultSetSelect = select.executeQuery();

            if (resultSetSelect.next()) {
                exibirAlerta(Alert.AlertType.ERROR,"Erro!","Produto já existe no Banco de Dados");
                return;
            } else {
                //Query para Cadastrar o Produto no sistema
                String queryCadastro = "insert into produto(nome_produto,preco_produto,alcoolico_S_N,tipo,validade,quantidade)values (?,?,?,?,?,?)";

                PreparedStatement insert = conexao.prepareStatement(queryCadastro);
                insert.setString(1,nomeProduto);
                insert.setString(2,precoProduto);
                insert.setBoolean(3,Alcoolico_S_N);
                insert.setString(4,tipo);
                insert.setObject(5,validadeProduto);
                insert.setInt(6,quantidadeProduto);

                int rowsInserted = insert.executeUpdate();

                if (rowsInserted > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION,"Produto Cadastrado","Produto foi Cadastrado com Sucesso!");
                    menuPane.setVisible(true);
                    ProdutoPane.setVisible(false);
                    FornecedorPane.setVisible(false);
                    ClientePane.setVisible(false);
                    FuncionarioPane.setVisible(false);
                    clearAll(nomeProdutoTextField,precoTextField,alcoolicoCheckBox,nalcoolicoChoiceBox,NAlcoolicoCheckBox,nalcoolicoChoiceBox,validadeDatePicker,quantidadeTextField);

                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }    }




    @FXML
    void cadCliente(ActionEvent event) {
        //VERIFICAÇÃO
        if(clienteNomeTextField.getText().isEmpty()){
           exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }
        if(clienteCPFTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }
        if(clienteEnderecoTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }


        String cliente = clienteNomeTextField.getText().toLowerCase();
        String CPF = clienteCPFTextField.getText();
        String endereco = clienteEnderecoTextField.getText();
        String telefone = clienteTelefoneTextField.getText();

        try {
            // Criação da consulta SQL
            String querySelect = "SELECT * FROM cliente WHERE cpf_cliente = ?";

            // Preparação da instrução SQL
            PreparedStatement select = conexao.prepareStatement(querySelect);
            select.setString(1, CPF);

            // Execução da consulta
            ResultSet resultSet1 = select.executeQuery();

            // Verifica se o CPF existe no banco de dados
            if (resultSet1.next()) {
                exibirAlerta(Alert.AlertType.ERROR,"CPF Existente","Este CPF já está Cadastrado!");
            } else {

                String queryInsert = "insert into cliente (nome_cliente, cpf_cliente, endereco_cliente, telefone_cliente) values (?,?,?,?);";
                PreparedStatement insert = conexao.prepareStatement(queryInsert);
                insert.setString(1,cliente);
                insert.setString(2,CPF);
                insert.setString(3,endereco);
                insert.setString(4,telefone);

                int linhasInseridas = insert.executeUpdate();

                if(linhasInseridas > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Cliente Cadastrado", "Informações do cliente foram inseridas com sucesso!");
                    menuPane.setVisible(true);
                    ProdutoPane.setVisible(false);
                    FornecedorPane.setVisible(false);
                    ClientePane.setVisible(false);
                    FuncionarioPane.setVisible(false);
                    clearAll(clienteNomeTextField,clienteCPFTextField,clienteEnderecoTextField,clienteTelefoneTextField);

                }
                // Fechando recurso
                insert.close();

            }
            // Fechando recursos

            resultSet1.close();
            select.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }




    @FXML
    void cadFornecedor(){
        //VERIFICAÇÂO
        if(fornecedorNomeTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }
        if(fornecedorEnderecoTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }
        if(fornecedorCNPJTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }
        if(FornecedorTelefoneTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
            return;
        }

        String fornecedor = fornecedorNomeTextField.getText().toLowerCase();
        String endereco = fornecedorEnderecoTextField.getText().toLowerCase();
        String CNPJ = fornecedorCNPJTextField.getText();
        String telefone = FornecedorTelefoneTextField.getText();


        try {
            // Criação da consulta SQL
            String querySelect = "SELECT * FROM fornecedor WHERE cnpj_fornecedor = ?";

            // Preparação da instrução SQL
            PreparedStatement select = conexao.prepareStatement(querySelect);
            select.setString(1, CNPJ);

            // Execução da consulta
            ResultSet resultSet1 = select.executeQuery();

            // Verifica se o CPF existe no banco de dados
            if (resultSet1.next()) {
                exibirAlerta(Alert.AlertType.ERROR,"CNPJ Existente","Este CNPJ já está Cadastrado!");
            } else {

                String queryInsert = "insert into fornecedor (nome_fornecedor, endereco_fornecedor, CNPJ_fornecedor, telefone_fornecedor) values (?,?,?,?);";
                PreparedStatement insert = conexao.prepareStatement(queryInsert);
                insert.setString(1,fornecedor);
                insert.setString(2,endereco);
                insert.setString(3,CNPJ);
                insert.setString(4,telefone);

                int linhasInseridas = insert.executeUpdate();

                if(linhasInseridas > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Fornecedor Cadastrado", "Informações do Fornecedor foram inseridas com sucesso!");
                    menuPane.setVisible(true);
                    ProdutoPane.setVisible(false);
                    FornecedorPane.setVisible(false);
                    ClientePane.setVisible(false);
                    FuncionarioPane.setVisible(false);
                    clearAll(fornecedorNomeTextField,fornecedorEnderecoTextField,fornecedorCNPJTextField,fornecedorCNPJTextField);

                }
                // Fechando recurso
                insert.close();

            }
            // Fechando recursos

            resultSet1.close();
            select.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }




@FXML
    void cadFuncionario(){
    //VERIFICAÇÃO
    if(FuncNomeTextField.getText().isEmpty()){
        exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
        return;
    }
    if(CPFFuncTextField.getText().isEmpty()){
        exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
        return;
    }
    if(FuncLogin.getText().isEmpty()){
        exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
        return;
    }
    if(funcSenhaTextField.getText().isEmpty()){
        exibirAlerta(Alert.AlertType.ERROR,"Preencham os Campos","Preencha os campos corretamente");
        return;
    }


        String funcionario = FuncNomeTextField.getText().toLowerCase();
        String CPF = CPFFuncTextField.getText();
        String login = FuncLogin.getText();
        String senha = funcSenhaTextField.getText();
        boolean adm = administradorCheckBox.isSelected();

    try {
            // Criação da consulta SQL
            String querySelect = "SELECT * FROM funcionario WHERE cpf_funcionario = ?";

            // Preparação da instrução SQL
            PreparedStatement select = conexao.prepareStatement(querySelect);
            select.setString(1, CPF);

            // Execução da consulta
            ResultSet resultSet1 = select.executeQuery();

            // Verifica se o CPF existe no banco de dados
            if (resultSet1.next()) {
                exibirAlerta(Alert.AlertType.ERROR,"Funcionario Existente","Este Funcionario já está Cadastrado!");
            } else {

                String queryInsert = "insert into funcionario (nome_funcionario,cpf_funcionario,login,senha) values (?,?,?,?);";
                PreparedStatement insert = conexao.prepareStatement(queryInsert);
                insert.setString(1,funcionario);
                insert.setString(2,CPF);
                insert.setString(3,login);
                insert.setString(4,senha);

                int linhasInseridas = insert.executeUpdate();

                if(linhasInseridas > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Funcionário Cadastrado", "Informações do funcionário foram inseridas com sucesso!");
                    menuPane.setVisible(true);
                    ProdutoPane.setVisible(false);
                    FornecedorPane.setVisible(false);
                    ClientePane.setVisible(false);
                    FuncionarioPane.setVisible(false);
                    clearAll(FuncNomeTextField,CPFFuncTextField,FuncLogin,funcSenhaTextField);

                }
                // Fechando recurso
                insert.close();

            }
            // Fechando recursos

            resultSet1.close();
            select.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //O método initialize() consegue dar Propriedades a elementos gráficos como uma formatação para um textfield

        nalcoolicoChoiceBox.getItems().addAll(nAlcoolicoValues);
        alcoolicoChoiceBox.getItems().addAll(AlcoolicoValues);


        //Formatando os TextField para CPF
        Formatacao.formataCPFEnquantoDigita(CPFFuncTextField);
        Formatacao.formataCPFEnquantoDigita(clienteCPFTextField);

        //Formatando os TextField para CNPJ
        Formatacao.formataCNPJDinamico(fornecedorCNPJTextField);

        //Formatando os TextField para Número de Celular
        Formatacao.formataTelefoneDinamico(FornecedorTelefoneTextField);
        Formatacao.formataTelefoneDinamico(clienteTelefoneTextField);

        //Formatador de Preço
        Formatacao.formataPrecoEnquantoDigita(precoTextField);

        //Limitando a quantidade caracteres dos TextFields que nao recebem os Formatadores anteriores
        Formatacao.LimitadorCaracteres(FuncNomeTextField, 60);
        Formatacao.LimitadorCaracteres(FuncLogin, 16);
        Formatacao.LimitadorCaracteres(funcSenhaTextField, 16);

        Formatacao.LimitadorCaracteres(fornecedorNomeTextField, 60);
        Formatacao.LimitadorCaracteres(fornecedorEnderecoTextField, 60);

        Formatacao.LimitadorCaracteres(nomeProdutoTextField, 60);
        Formatacao.LimitadorCaracteres(quantidadeTextField, 3);
        Formatacao.LimitadorCaracteres(clienteNomeTextField, 60);

        //Transformando os TextField para receberem apenas Letras
        Formatacao.ApenasLetras(FuncNomeTextField);

        //Formatando para Receber apenas números
        Formatacao.ApenasNumeros(quantidadeTextField);

    }


}
