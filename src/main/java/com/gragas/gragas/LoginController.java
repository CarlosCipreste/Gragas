package com.gragas.gragas;

import com.gragas.gragas.classes.loginClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;


public class LoginController extends loginClass {

    @FXML
    private TextField usuarioTextField;

    @FXML
    private Button EntrarButton;

    @FXML
    private PasswordField senhaTextField;

    @FXML
    void Login(ActionEvent event) {
        TentarLogin(usuarioTextField,senhaTextField);
    }

}
