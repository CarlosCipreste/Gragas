package com.gragas.gragas.metodos;


import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Formatacao {


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Formata o CPF enquanto digita - TextField
    public void formataCPFEnquantoDigita(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String digits = newValue.replaceAll("[^0-9]", "");

            StringBuilder formatted = new StringBuilder();
            if (digits.length() > 0) {
                formatted.append(digits.substring(0, Math.min(3, digits.length()))).append(".");
            }
            if (digits.length() > 3) {
                formatted.append(digits.substring(3, Math.min(6, digits.length()))).append(".");
            }
            if (digits.length() > 6) {
                formatted.append(digits.substring(6, Math.min(9, digits.length()))).append("-");
            }
            if (digits.length() > 9) {
                formatted.append(digits.substring(9, Math.min(11, digits.length())));
            }

            textField.setText(formatted.toString());
        });

        // Adiciona um listener para escutar o evento de tecla pressionada
        textField.setOnKeyPressed(event -> {
            // Verifica se a tecla pressionada foi o BACKSPACE
            if (event.getCode() == KeyCode.BACK_SPACE) {
                // Limpa o conteúdo do JFXTextField
                textField.clear();
            }
        });
    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Formata o celular enquanto digita - TextField
    public void formataCelularDinamico(TextField textField) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String digits = newValue.replaceAll("[^0-9]", "");

            StringBuilder formatted = new StringBuilder();
            if (digits.length() > 0) {
                formatted.append("(").append(digits.charAt(0));
            }
            if (digits.length() > 1) {
                formatted.append(digits.charAt(1)).append(") ");
            }
            if (digits.length() > 2) {
                formatted.append(digits.substring(2, Math.min(3, digits.length()))).append("-");
            }
            if (digits.length() > 3) {
                formatted.append(digits.substring(3, Math.min(7, digits.length()))).append("-");
            }
            if (digits.length() > 7) {
                formatted.append(digits.substring(7, Math.min(11, digits.length())));
            }

            textField.setText(formatted.toString());
        });


        // Adiciona um listener para escutar o evento de tecla pressionada
        textField.setOnKeyPressed(event -> {

            // Verifica se a tecla pressionada foi o BACKSPACE - Pois o cliente não consegue apagar as barras e parenteses que adicionei
            if (event.getCode() == KeyCode.BACK_SPACE) {
                // Limpa o conteúdo do JFXTextField
                textField.clear();
            }

        });
    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Formata o TextField de CNPJ Dinâmicamente
    public void formataCNPJDinamico(TextField textField) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String digits = newValue.replaceAll("[^0-9]", "");

            StringBuilder formatted = new StringBuilder();
            if (digits.length() > 0) {
                formatted.append(digits.substring(0, Math.min(2, digits.length())));
            }
            if (digits.length() > 2) {
                formatted.append(".").append(digits.substring(2, Math.min(5, digits.length())));
            }
            if (digits.length() > 5) {
                formatted.append(".").append(digits.substring(5, Math.min(8, digits.length())));
            }
            if (digits.length() > 8) {
                formatted.append("/").append(digits.substring(8, Math.min(12, digits.length())));
            }
            if (digits.length() > 12) {
                formatted.append("-").append(digits.substring(12, Math.min(14, digits.length())));
            }

            // Adiciona um listener para escutar o evento de tecla pressionada
            textField.setOnKeyPressed(event -> {

                // Verifica se a tecla pressionada foi o BACKSPACE - Pois o cliente não consegue apagar os pontos e traços que adicionei
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    // Limpa o conteúdo do JFXTextField
                    textField.clear();
                }

            });

            textField.setText(formatted.toString());
        });
    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Formata o TextFIeld de preço Dinamicamente
    public void formataPrecoEnquantoDigita(TextField textField) {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            String digits = newValue.replaceAll("[^0-9,]", "");

            // Limita o número de caracteres após a vírgula para no máximo 2
            int commaIndex = digits.indexOf(",");
            if (commaIndex != -1 && digits.length() - commaIndex > 3) {
                digits = digits.substring(0, commaIndex + 3);
            }

            StringBuilder formatted = new StringBuilder();
            if (digits.length() > 0) {
                formatted.append("R$ ");
                formatted.append(digits);
            }

            textField.setText(formatted.toString());
            textField.setOnKeyPressed(event -> {

                // Verifica se a tecla pressionada foi o BACKSPACE - Pois o cliente não consegue apagar os pontos e traços que adicionei
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    // Limpa o conteúdo do JFXTextField
                    textField.clear();
                }

            });
        });
    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Faz que ele apenas ative retorne uma String quando o length do texto for maior que 1
    public String toString(String value) {

        if (value == null || value.length() < 1) {
            return "";
        }
        if (value.length() < 11) {
            value = String.format("%011d", Long.valueOf(value));
        }
        return String.format("%s.%s.%s-%s", value.substring(0, 3), value.substring(3, 6), value.substring(6, 9), value.substring(9, 11));
    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Limita o TextField a ter apenas Números
    public static void ApenasNumeros(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("\\d*", newText)) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);

    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Limita o TextField a ter apenas Letras
    public static void ApenasLetras(TextField textField) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("[a-zA-Z]*", newText)) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);

    }




    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Limita a quantidade de caracteres no TextField
    public static void LimitadorCaracteres(TextField textField, int limite) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();

            if (newText.length() <= limite) {
                return change;
            }

            return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        textField.setTextFormatter(textFormatter);
    }

}
