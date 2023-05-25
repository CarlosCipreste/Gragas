package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static com.gragas.gragas.metodos.metodosGerais.TentarLogin;


public class LoginController {

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

}
