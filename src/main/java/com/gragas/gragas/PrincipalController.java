package com.gragas.gragas;

import com.gragas.gragas.metodos.metodosGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class PrincipalController {

    @FXML
    public static Label nomeLogon;

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
        metodosGerais.conexao.close();
        System.out.println("Conexão Fechada!");
    }


}
