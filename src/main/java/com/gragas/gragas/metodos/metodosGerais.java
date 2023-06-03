package com.gragas.gragas.metodos;

import com.gragas.gragas.CadastroController;
import com.gragas.gragas.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;

public class metodosGerais {

    public static String valor = null;


    public static void TentarLogin(TextField usuarioTextField, PasswordField senhaTextField) {


    }


    /*MÉTODO PARA SIMPLIFICAR O USO DE ALERT BOX*/
    public static void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


    /*MÉTODO PARA VERIFICAR SE HÁ 2 NÚMERO APÓS O VIRGULA*/
    public static boolean isTextFieldValueValido(TextField textField) {
        String value = textField.getText();

        // Remove o prefixo "R$ " e quaisquer espaços em branco antes ou depois
        value = value.replace("R$", "").trim();

        // Verifica se o valor restante possui uma vírgula e se a parte após a vírgula está no formato correto
        if (value.contains(",")) {
            int commaIndex = value.indexOf(",");
            String decimalPart = value.substring(commaIndex + 1);

            // Verifica se a parte após a vírgula contém apenas dígitos e tem no máximo dois caracteres
            if (decimalPart.matches("\\d{2}")) {
                return true;
            }
        }

        return false;
    }
}