package com.gragas.gragas;

import com.gragas.gragas.classes.loginClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.SQLException;

public class PrincipalController {

    @FXML
    private Label nomeLogon;

    @FXML
    private Button atualizarEstoqueButton;

    @FXML
    private Button ConsultarFornecedoresButton;

    @FXML
    private Button sairContaButton;

    @FXML
    private Button cadastrarUsuarioButton;

    @FXML
    private Button prepararPedidoButton;

    @FXML
    private Button ConsultaProdutosButton;

    @FXML
    private Button RegistroButton;


    @FXML
    void Logout(ActionEvent event) throws SQLException {
        HelloApplication.trocaTela("login");
        loginClass.conexao.close();
    }

}
