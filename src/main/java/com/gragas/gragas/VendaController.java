package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.*;
import static com.gragas.gragas.metodos.metodosGerais.clearAll;
import static com.gragas.gragas.metodos.metodosGerais.exibirAlerta;

public class VendaController implements Initializable {

    @FXML
    private ChoiceBox<String> vendaProdutosChoiceBox;
    @FXML
    private ObservableList<ProdTable> valoresProdTable = FXCollections.observableArrayList();

    @FXML
    private TextField vendaClienteTextField;

    @FXML
    private TextField vendaProdutoTextField;

    @FXML
    private TextField vendaQTDTextField;

    @FXML
    private Button vendaAdicionarProdButton;

    @FXML
    private TableView<ProdTable> vendaListaTableView;

    @FXML
    private TableColumn<ProdTable, String> nome;

    @FXML
    private TableColumn<ProdTable, Integer> qtd;

    @FXML
    private Button vendaFinalizarVendaButton;

    @FXML
    private Button voltarButton;
    @FXML
    private ObservableList<String> lista;

    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
        clearAll(vendaClienteTextField,vendaProdutosChoiceBox,vendaQTDTextField,vendaListaTableView);
    }

    public void setvendaChoiceBoxValues(List<String> list) {


        // Criar a conexão com o banco de dados
        try {
            // Criar a consulta SQL
            String sql = "SELECT nome_produto FROM produto";

            // Criar o statement
            try (Statement statement = conexao.createStatement()) {
                // Executar a consulta e obter o resultado
                ResultSet resultSet = statement.executeQuery(sql);

                // Percorrer o resultado da consulta e adicionar os valores à ObservableList existente
                while (resultSet.next()) {
                    String nomeProduto = resultSet.getString("nome_produto");
                    list.add(nomeProduto);
                }
                // Definir a ObservableList como a fonte de itens do ChoiceBox
                vendaProdutosChoiceBox.getItems().setAll(list);
            }
        } catch (SQLException e) {
            exibirAlerta(Alert.AlertType.ERROR,"ERRO","ERRO");
            e.printStackTrace();
        }
    }
    
    


    private void setupClienteValues(ObservableList<String> lista, TextField textField){
        try {
            String sql = "SELECT nome_cliente FROM cliente";
            try (Statement statement = conexao.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String nomeProduto = resultSet.getString("nome_cliente");
                    lista.add(nomeProduto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AutoCompletionBinding<String> autoComplete = TextFields.bindAutoCompletion(textField,lista);

        autoComplete.setOnAutoCompleted((AutoCompletionBinding.AutoCompletionEvent<String> event) -> {
            String sugestaoSelecionada = event.getCompletion();
            System.out.println("Sugestão selecionada: " + sugestaoSelecionada);
        });

    }

    @FXML
    void AdicionarProdutonaVenda(ActionEvent event) {

        String nomeProd = vendaProdutosChoiceBox.getValue();
        int qtdProd = Integer.parseInt(vendaQTDTextField.getText());

        if(nomeProd == ){}

        //Se a lista estiver vazia é criada uma nova
        if (valoresProdTable == null) {
            valoresProdTable = FXCollections.observableArrayList(
                    new ProdTable(nomeProd,qtdProd)
            );
            vendaListaTableView.setItems(valoresProdTable);
        }
        //Se ela existir o produto é adicionado ao table view
        else {
            valoresProdTable.add( new ProdTable(nomeProd, qtdProd));
            vendaListaTableView.setItems(valoresProdTable);

        }
        System.out.println("Adicionado");
    }

    @FXML
    public void FinalizarVenda(ActionEvent event){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<String> list = new ArrayList<String>();
        setvendaChoiceBoxValues(list);

        lista = FXCollections.observableArrayList();
        setupClienteValues(lista, vendaClienteTextField);

        nome.setCellValueFactory(new PropertyValueFactory<ProdTable,String>("nomeProdClass"));
        qtd.setCellValueFactory(new PropertyValueFactory<ProdTable,Integer>("qtdProdClass"));

    }


}
