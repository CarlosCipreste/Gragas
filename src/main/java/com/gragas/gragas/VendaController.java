package com.gragas.gragas;

import com.gragas.gragas.metodos.ProdTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VendaController implements Initializable {

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
    ObservableList<ProdTable> valoresProdTable = FXCollections.observableArrayList(
            new ProdTable("Batata",4),
            new ProdTable("Banana",2)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nome.setCellValueFactory(new PropertyValueFactory<ProdTable,String>("nomeProdClass"));
        qtd.setCellValueFactory(new PropertyValueFactory<ProdTable,Integer>("qtdProdClass"));

        vendaListaTableView.setItems(valoresProdTable);
    }
}
