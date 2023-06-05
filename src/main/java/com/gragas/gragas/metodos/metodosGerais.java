package com.gragas.gragas.metodos;

import com.gragas.gragas.CadastroController;
import com.gragas.gragas.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.*;
import java.time.LocalDate;

public class metodosGerais {

    public static String valor = null;





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

    //SOBRECARGA DO MÉTODO CLEAR
    public static void clearAll(TextField nomeProd, TextField precoProd, CheckBox alcoolico, ChoiceBox dest_ou_ferm, CheckBox nalcoolico, ChoiceBox suco_ou_refri, DatePicker validade, TextField quantidadeProd){
        //Clear para a tela de Produtos
        nomeProd.clear();
        precoProd.clear();
        alcoolico.setSelected(false);
        dest_ou_ferm.setValue(null);
        nalcoolico.setSelected(false);
        suco_ou_refri.setValue(false);
        validade.setValue(null);
        quantidadeProd.clear();
    }

    public static void clearAll(TextField nomeFunc, TextField CPFFunc, TextField login, PasswordField senha){
        //Clear para a Tela de Funcionarios
        nomeFunc.clear();
        CPFFunc.clear();
        login.clear();
        senha.clear();
    }

    public static void clearAll(TextField nomeCliente, TextField CPFCliente, TextField telefoneCliente){
        //Clear para a tela de Cliente
        nomeCliente.clear();
        CPFCliente.clear();
        telefoneCliente.clear();
    }

    public static void clearAll(TextField nomeFornecedor, TextField enderecoFornecedor, TextField CNPJFornecedor, TextField telefoneFornecedor){
        //Clear para a tela de Fornecedores
        nomeFornecedor.clear();
        enderecoFornecedor.clear();
        CNPJFornecedor.clear();
        telefoneFornecedor.clear();
    }
}