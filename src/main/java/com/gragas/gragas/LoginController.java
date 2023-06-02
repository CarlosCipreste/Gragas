package com.gragas.gragas;

import com.gragas.gragas.metodos.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static com.gragas.gragas.metodos.metodosGerais.TentarLogin;


public class LoginController implements Initializable {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private Button EntrarButton;

    @FXML
    private PasswordField senhaTextField;

    @FXML
    void EntrarEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            TentarLogin(usuarioTextField, senhaTextField);
        }
    }

    @FXML
    void Login(ActionEvent event) {
        TentarLogin(usuarioTextField, senhaTextField);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Formatacao formatacao = new Formatacao();

        formatacao.LimitadorCaracteres(usuarioTextField,16);
        formatacao.LimitadorCaracteres(senhaTextField,16);

    }
}
