package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.gragas.gragas.metodos.Formatacao.LimitadorCaracteres;


public class LoginController implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private Button EntrarButton;

    @FXML
    private PasswordField senhaTextField;

    public static String nomeUser;
    public static int IDUser;
    public static boolean administrador;

    public static Connection conexao;



    @FXML
    void connect() {
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
    }

    @FXML
    void LoginEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            ResultSet resultSet;
            String username = usuarioTextField.getText();
            String password = senhaTextField.getText();

            /*
                Faz uma query de sql fazendo uma comparação de String para fazer o login,
                Caso o valor seja retornado, ele aceita o login.
            */
            String sql = "SELECT * FROM funcionario WHERE binary login = ? and senha = ?";

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
                    nomeUser = resultSet.getString("nome_funcionario");
                    String[] partes = nomeUser.split(" ", 2); // Divide a string em duas partes pelo primeiro espaço

                    nomeUser = partes[0];
                    IDUser = resultSet.getInt("id_funcionario");
                    administrador = resultSet.getBoolean("administrador");
                    System.out.println("Bem-Vindo!");
                    HelloApplication.trocaTela("principal");
                    usuarioTextField.clear();
                    senhaTextField.clear();

                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }


    @FXML
    void Login(){

        ResultSet resultSet;
        String username = usuarioTextField.getText();
        String password = senhaTextField.getText();

            /*
                Faz uma query de sql fazendo uma comparação de String para fazer o login,
                Caso o valor seja retornado, ele aceita o login.
            */
        String sql = "SELECT * FROM funcionario WHERE binary login = ? and senha = ?";

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
                nomeUser = resultSet.getString("nome_funcionario");

                String[] partes = nomeUser.split(" ", 2); // Divide a string em duas partes pelo primeiro espaço

                nomeUser = partes[0];
                IDUser = resultSet.getInt("id_funcionario");
                administrador = resultSet.getBoolean("administrador");
                System.out.println("Bem-Vindo!");
                HelloApplication.trocaTela("principal");
                usuarioTextField.clear();
                senhaTextField.clear();


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connect();
        LimitadorCaracteres(usuarioTextField,16);
        LimitadorCaracteres(senhaTextField,16);

    }
}
