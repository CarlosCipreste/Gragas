package com.gragas.gragas.metodos;

import com.gragas.gragas.CadastroController;
import com.gragas.gragas.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

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

    public static Optional<ButtonType> exibirAlertaConfirmacao(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        return alerta.showAndWait();
    }

    //MÉTODO PARA SIPLIFICAR O USO DO ALERT BOX DE CONFIRMAÇÃO
    public static int showConfirmationDialog(String title, String message) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(message);

        // Configurando os botões do diálogo
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancelar");

        confirmationDialog.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Exibindo o diálogo e aguardando a resposta
        ButtonType result = confirmationDialog.showAndWait().orElse(buttonTypeCancel);

        // Verificando qual botão foi pressionado
        if (result == buttonTypeOK) {
            System.out.println("Botão OK pressionado");
            // Faça algo aqui se o botão OK for pressionado
        } else if (result == buttonTypeCancel) {
            System.out.println("Botão Cancelar pressionado");
            // Faça algo aqui se o botão Cancelar for pressionado
            // Retorna um valor específico (nesse exemplo, -1)
            // Aqui você pode chamar um método que utiliza o valor retornado
            int resultado = -1;
            System.out.println("Resultado: " + resultado);
            return resultado;
        }

        return 0;
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
    public static void clearAll(TextField nomeProd, TextField precoProd, CheckBox alcoolico, ChoiceBox dest_ou_ferm, CheckBox nalcoolico, ChoiceBox suco_ou_refri, DatePicker validade, TextField quantidadeProd) {
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

    public static void clearAll(TextField nomeFunc, TextField CPFFunc, TextField login, PasswordField senha) {
        //Clear para a Tela de Funcionarios
        nomeFunc.clear();
        CPFFunc.clear();
        login.clear();
        senha.clear();
    }

    public static void clearAll(TextField nomeCliente, TextField CPFCliente, TextField telefoneCliente) {
        //Clear para a tela de Cliente
        nomeCliente.clear();
        CPFCliente.clear();
        telefoneCliente.clear();
    }

    public static void clearAll(TextField nomeFornecedor, TextField enderecoFornecedor, TextField CNPJFornecedor, TextField telefoneFornecedor) {
        //Clear para a tela de Fornecedores
        nomeFornecedor.clear();
        enderecoFornecedor.clear();
        CNPJFornecedor.clear();
        telefoneFornecedor.clear();
    }

    public static void clearAll(TextField vendaCliente, ChoiceBox<String> produtoChoiceBox, TextField quantidadeProduto, TableView vendaTableView) {
        vendaCliente.clear();
        produtoChoiceBox.getSelectionModel().clearSelection();
        quantidadeProduto.clear();
        vendaTableView.getItems().clear();
    }

    public static void clearAll(TextField nomeProd, TextField precoProd, CheckBox alcoolico, ChoiceBox dest_ou_ferm, CheckBox nalcoolico, ChoiceBox suco_ou_refri, TextField quantidadeProd) {
        //Clear para a tela de Produtos
        nomeProd.clear();
        precoProd.clear();
        alcoolico.setSelected(false);
        dest_ou_ferm.setValue(null);
        nalcoolico.setSelected(false);
        suco_ou_refri.setValue(null);
        quantidadeProd.clear();
    }
}