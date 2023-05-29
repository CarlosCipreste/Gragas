package com.gragas.gragas.metodos;

import com.gragas.gragas.HelloApplication;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.*;

public class metodosGerais {
    public static Connection conexao = null;
    public static String valor = null;


    public static void TentarLogin(TextField usuarioTextField, PasswordField senhaTextField) {

        String url = "jdbc:mysql://localhost:3306/login";
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
            //Caso contrário, aqui ficará o código que será passado pra próxima tela
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

    public static void CadastrarProduto(TextField nomeTF, TextField precoTF, CheckBox alcool_Sim, CheckBox alcool_Nao, ChoiceBox dest_ou_ferm, ChoiceBox suco_ou_refri, TextField validadeTF, TextField quantidadeTF){

        String nomeProd = nomeTF.getText();
        String precoProd = nomeTF.getText();
        boolean alcoolicoSim = alcool_Sim.isSelected();
        boolean alcoolicoNao = alcool_Nao.isSelected();
        String tipo_alcoolico = dest_ou_ferm.getItems().toString();
        String tipo_nao_alcoolico = suco_ou_refri.getItems().toString();
        String validadeProd = validadeTF.getText();
        int validade = Integer.parseInt(validadeTF.getText());
        int quantidade = Integer.parseInt(quantidadeTF.getText());

        System.out.println(nomeProd);
        System.out.println(precoProd);
        System.out.println(alcoolicoSim);
        System.out.println(alcoolicoNao);
        System.out.println(tipo_alcoolico);
        System.out.println(tipo_nao_alcoolico);
        System.out.println(validadeProd);
        System.out.println(validade);
        System.out.println(quantidade);
    }

    public static void CadastrarCliente(){

    }

    public static void CadastrarFuncionario(){

    }

    public static void CadastrarFornecedor(){

    }
}