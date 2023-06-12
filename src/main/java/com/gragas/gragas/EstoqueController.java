package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class EstoqueController implements Initializable {

    @FXML
    private Button voltarButton;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
