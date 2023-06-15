package com.gragas.gragas;

import com.gragas.gragas.metodos.metodosGerais;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    @FXML
    private Label nomeLogon;

    @FXML
    private Button sairContaButton;

    @FXML
    private Button CadastroButton;

    @FXML
    private Button RegistroButton;

    @FXML
    private Button VendaButton;

    @FXML
    void setNomeLogon(){
        nomeLogon.setText(LoginController.nomeUser);
    }

    @FXML
    void enterRegistro(){
        HelloApplication.trocaTela("registro");
        System.out.println("Troca de tela");
    }

    @FXML
    void EnterCadastrar(ActionEvent event) {
        HelloApplication.trocaTela("cadastro");
        System.out.println("Troca de tela");
    }
    @FXML
    void EnterEstoque(ActionEvent event) {
        HelloApplication.trocaTela("estoque");
        System.out.println("Troca de tela");
    }

    @FXML
    void EnterVenda(ActionEvent event){
        HelloApplication.trocaTela("venda");
        System.out.println("Troca de tela");
    }

    @FXML
    void Logout(ActionEvent event) throws SQLException {
        HelloApplication.trocaTela("login");
        System.out.println("Conexão Fechada!");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNomeLogon();
        System.out.println(nomeLogon.toString());

    }
}
