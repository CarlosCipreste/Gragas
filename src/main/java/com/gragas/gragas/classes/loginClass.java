package com.gragas.gragas.classes;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginClass {
    static Connection conexao = null;

    public static void TentarLogin(TextField usuarioTextField, PasswordField senhaTextField){

        String url = "jdbc:mysql://localhost:3306/login";
        String usuario = "root";
        String senha = "";



        try {
            System.out.println("Tentativa de Conexão");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão Com o Banco de dados bem-Sucedida");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());

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
            declaracao.setString(1,username);
            declaracao.setString(2,password);
            resultSet = declaracao.executeQuery();

            //Caso não haja Resultado é lançado um print avisando
            if(!resultSet.next()){
                System.out.println("Usuário ou senha Incorreto");

            }
            //Caso contrário, aqui ficará o código que será passado pra próxima tela
            else{
                System.out.println("Bem-Vindo!");
                try{
                    //trocarParaPrincipal(event);
                    System.out.println("FOI");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    }
}
