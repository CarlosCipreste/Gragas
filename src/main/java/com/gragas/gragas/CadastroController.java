package com.gragas.gragas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CadastroController {

    @FXML
    private Button CadUsuariosButton;

    @FXML
    private Button CadClientesButton;

    @FXML
    private Button CadFornecedorButton;

    @FXML
    private Button CadProdutosButton;

    @FXML
    private Button voltarButton;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }

}
