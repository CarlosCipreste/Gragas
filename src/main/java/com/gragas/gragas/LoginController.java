package com.gragas.gragas;

import com.gragas.gragas.metodos.*;
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

import static com.gragas.gragas.metodos.metodosGerais.TentarLogin;


public class LoginController implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private Button EntrarButton;

    @FXML
    private PasswordField senhaTextField;

    public static Connection conexao = null;
    @FXML
    void EntrarEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TentarLogin(usuarioTextField, senhaTextField);
        }
    }

    @FXML
    void Login(ActionEvent event) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Formatacao formatacao = new Formatacao();

        formatacao.LimitadorCaracteres(usuarioTextField,16);
        formatacao.LimitadorCaracteres(senhaTextField,16);

    }
}
