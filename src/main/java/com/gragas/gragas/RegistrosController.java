package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegistrosController {

    @FXML
    private Button voltarButton;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

}
