package com.gragas.gragas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene LoginPage;
    private static Scene PrincipalPage;
    private static Scene EstoquePane;
    private static Scene VendaPage;
    private static Scene CadastroPage;
    private static Scene RegistroPage;

    private static Stage stage;


    @Override
    public void start(Stage primarystage) throws IOException {

        stage = primarystage;

        //Cache de paginas FXML
        FXMLLoader loginfxml = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        LoginPage = new Scene(loginfxml.load());

        FXMLLoader principalfxml = new FXMLLoader(HelloApplication.class.getResource("principal.fxml"));
        PrincipalPage = new Scene(principalfxml.load());

        FXMLLoader cadastrofxml = new FXMLLoader(HelloApplication.class.getResource("cadastro.fxml"));
        CadastroPage = new Scene(cadastrofxml.load());



        stage.setTitle("Taverna do Gragas");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(LoginPage);
        stage.show();

    }

    public static void trocaTela(String nomeTela) {

        switch (nomeTela) {
            case "login":

                //Chamando a tela que será exibida caso chame "login"
                stage.setScene(LoginPage);
                //Centralizando a tela depois de chama-la
                stage.centerOnScreen();
                break;

            case "principal":

                stage.setScene(PrincipalPage);
                stage.centerOnScreen();
                break;

            case "cadastro":
                stage.setScene(CadastroPage);
                stage.centerOnScreen();
                break;

            case "venda":
                try{
                FXMLLoader vendafxml = new FXMLLoader(HelloApplication.class.getResource("venda.fxml"));
                VendaPage = new Scene(vendafxml.load());
                stage.setScene(VendaPage);
                stage.centerOnScreen();}catch(IOException e){e.printStackTrace();}
                break;

            case "registro":
                try{
                    FXMLLoader registrofxml = new FXMLLoader(HelloApplication.class.getResource("registro.fxml"));
                    RegistroPage = new Scene(registrofxml.load());
                    stage.setScene(RegistroPage);
                    stage.centerOnScreen();}catch(IOException e){e.printStackTrace();}
                break;

            case "estoque":
                try{
                FXMLLoader estoquefxml = new FXMLLoader(HelloApplication.class.getResource("estoque.fxml"));
                EstoquePane = new Scene(estoquefxml.load());
                stage.setScene(EstoquePane);
                stage.centerOnScreen();}catch(IOException e){e.printStackTrace();}
            break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}