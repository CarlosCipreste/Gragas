package com.gragas.gragas.metodos;

import com.gragas.gragas.HelloApplication;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;

public class metodosGerais {
    public static Connection conexao = null;
    public static String valor = null;


    public static void TentarLogin(TextField usuarioTextField, PasswordField senhaTextField) {

        String url = "jdbc:mysql://localhost:3306/distribuidora";
        String usuario = "root";
        String senha = "";


        try {
            System.out.println("Tentativa de Conexão");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão Com o Banco de dados bem-Sucedida");

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um Erro!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

            /*
            variáveis necessaria para a query e retorno  de resultado.
             As variávei possuem os métodos para poder retornar o valor
             colocado no componente TextField e retornar um String
            */

        ResultSet resultSet = null;
        String username = usuarioTextField.getText().toString();
        String password = senhaTextField.getText().toString();

            /*
                Faz uma query de sql fazendo uma comparação de String para fazer o login,
                Caso o valor seja retornado, ele aceita o login.
            */
        String sql = "SELECT * FROM conexao WHERE binary username = ? and senha = ?";

        try {
            PreparedStatement declaracao = conexao.prepareStatement(sql);
            declaracao.setString(1, username);
            declaracao.setString(2, password);
            resultSet = declaracao.executeQuery();

            //Caso não haja Resultado é lançado um print avisando
            if (!resultSet.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Ocorreu um Erro!");
                alert.setContentText("Usuário ou senha Incorretos");
                alert.showAndWait();

            }
            //Caso contrário, aqui ficará o código que será passado para próxima tela
            else {
                System.out.println("Bem-Vindo!");
                HelloApplication.trocaTela("principal");
                usuarioTextField.clear();
                senhaTextField.clear();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void CadastrarCliente() {

    }

    public static void CadastrarFuncionario() {

    }

    public static void CadastrarFornecedor() {

    }

    public static void CadastrarProduto(TextField nomeTF, TextField precoTF, CheckBox alcool_Sim, CheckBox alcool_Nao, ChoiceBox<String> dest_ou_ferm, ChoiceBox<String> suco_ou_refri, DatePicker validade, TextField quantidadeTF) {
        /*Verificação de todos os Componentes do Pane de Cadastro de Produtos
        * A verificação checa se todos os componentes estão preenchidos de acordo
        * Com o que o banco deve receber como valores para cadastrar o Produto*/

        try {
            // Verifica se todos os TextField possuem conteúdo
            if (nomeTF.getText().isEmpty() || precoTF.getText().isEmpty() || quantidadeTF.getText().isEmpty()) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Preencha todos os campos!");
                return;
            }

            // Verifica se um dos CheckBox está selecionado
            if (!(alcool_Sim.isSelected() || alcool_Nao.isSelected())) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma opção para a bebida!");
                return;
            }

            // Verifica se um valor dos dois ChoiceBox está selecionado
            if (dest_ou_ferm.getSelectionModel().isEmpty() && suco_ou_refri.getSelectionModel().isEmpty()) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma opção para o tipo de bebida bebida");
                return;
            }
            // Verifica se há uma data no DatePicker
            if (validade.getValue() == null) {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Selecione uma Data de validade para o Produto");
                return;
            }
            // Verifica se o valor de preço possui 2 números após a virgula
            if (isTextFieldValueValid(precoTF)) {

            } else {
                exibirAlerta(Alert.AlertType.ERROR, "Erro de Validação", "O preço precisa ter 2 algarismo após a vírgula");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        /*Caso nao haja nenhum erro com os componentes
         * O sistema é liberado para fazer o cadastro*/

        //Variaveis para a Query de mySQL
        String nomeProduto = nomeTF.getText();
        String precoProduto = precoTF.getText();

        boolean Alcoolico_S_N;
        if(alcool_Sim.isSelected()){Alcoolico_S_N = true;}
        else{Alcoolico_S_N = false;}

        String tipo;
        if(alcool_Sim.isSelected() || dest_ou_ferm.getValue() == "Destilado"){
            tipo = "Destilado";
        }
        else{
            tipo = "Fermentado";
        }

        if(alcool_Nao.isSelected() || suco_ou_refri.getValue() == "Suco"){
            tipo = "Suco";
        }
        else{
            tipo = "Refrigerante";
        }

        LocalDate validadeProduto = validade.getValue();

        int quantidadeProduto = Integer.parseInt(quantidadeTF.getText());
        try{
            String query = "select * from produto WHERE nome_produto = ? and preco_produto = ? and alcoolico_S_N = ? and tipo = ? and validade = ? and quantidade = ?";

            PreparedStatement statement = conexao.prepareStatement(query);
            statement.setString(1,nomeProduto);
            statement.setString(2,precoProduto);
            statement.setBoolean(3,Alcoolico_S_N);
            statement.setString(4,tipo);
            statement.setObject(5,validadeProduto);
            statement.setInt(6,quantidadeProduto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                exibirAlerta(Alert.AlertType.ERROR,"Erro!","Produto já existe no Banco de Dados");

            } else {
                exibirAlerta(Alert.AlertType.CONFIRMATION,"Produto Cadastrado","Produto foi Cadastrado com Sucesso!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static boolean isTextFieldValueValid(TextField textField) {
        String value = textField.getText();

        // Remove o prefixo "R$ " e quaisquer espaços em branco antes ou depois
        value = value.replace("R$", "").trim();

        // Verifica se o valor restante possui uma vírgula e se a parte após a vírgula está no formato correto
        if (value.contains(",")) {
            int commaIndex = value.indexOf(",");
            String decimalPart = value.substring(commaIndex + 1);

            // Verifica se a parte após a vírgula contém apenas dígitos e tem no máximo dois caracteres
            if (decimalPart.matches("\\d{2}")) {
                return true;
            }
        }

        return false;
    }
}