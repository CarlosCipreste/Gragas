package com.gragas.gragas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene loginPage;
    private static Scene principalPage;

    private static Stage stage;


    @Override
    public void start(Stage primarystage) throws IOException {

        stage = primarystage;

        //Cache de paginas FXML
        FXMLLoader loginfxml = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        loginPage = new Scene(loginfxml.load());

        FXMLLoader principalfxml = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
        principalPage = new Scene(principalfxml.load());

        stage.setTitle("Taverna do Gragas");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(loginPage);
        stage.show();

    }

    public static void trocaTela(String nomeTela) {

        switch (nomeTela) {
            case "login":

                //Chamando a tela que será exibida caso chame "login"
                stage.setScene(loginPage);
                //Centralizando a tela depois de chama-la
                stage.centerOnScreen();
                break;

            case "principal":

                stage.setScene(principalPage);
                stage.centerOnScreen();
                break;

        }
    }

    public static void main(String[] args) {
        launch();
    }
}