package com.gragas.gragas;

import com.gragas.gragas.classes.*;
import com.gragas.gragas.metodos.Formatacao;
import com.gragas.gragas.metodos.metodosGerais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.controlsfx.control.action.Action;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.gragas.gragas.LoginController.conexao;
import static com.gragas.gragas.metodos.Formatacao.LimitadorCaracteres;
import static com.gragas.gragas.metodos.metodosGerais.exibirAlerta;

public class RegistrosController implements Initializable {

    @FXML
    private TableView<Funcionario> funcionarioTableView;
        @FXML
        private TableColumn<Funcionario, Integer> IDFuncionarios;

        @FXML
        private TableColumn<Funcionario, String> nomeFuncionarios;

        @FXML
        private TableColumn<Funcionario, String> CPFFuncionarios;

        @FXML
        private TableColumn<Funcionario,String> usuarioFuncionarios;

    @FXML
    private TableView<Cliente> clienteTableView;

        @FXML
        private TableColumn<Cliente,Integer> IDClientes;

        @FXML
        private TableColumn<Cliente, String> nomeClientes;

        @FXML
        private TableColumn<Cliente, String> CPFClientes;

        @FXML
        private TableColumn<Cliente, String> enderecoCliente;

        @FXML
        private TableColumn<Cliente, String> telefoneClientes;

    @FXML
    private TableView<Fornecedor> fornecedorTableView;

        @FXML
        private TableColumn<Fornecedor, Integer> fornecedorID;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorNome;

        @FXML
        private TableColumn<Fornecedor, String> fornecedorEndereco;

        @FXML
        private TableColumn<Fornecedor, String> CNPJFornecedor;

        @FXML
        private TableColumn<Fornecedor, String> telefoneFornecedor;

    @FXML
    private TableView<Venda> vendasTableView;

        @FXML
        private TableColumn<Venda,Integer> IDVenda;
        @FXML
        private TableColumn<Venda, String> clienteNomeVendas;

        @FXML
        private TableColumn<Venda, String> funcionarioNomeVendas;

        @FXML
        private TableColumn<Venda, String> produtoNomeVendas;

        @FXML
        private TableColumn<Venda, Integer> quantidadeProdutoVendas;

        @FXML
        private TableColumn<Venda, Double> precoVendas;
        @FXML
        private TableColumn<Venda, Timestamp> horarioCompra;

    @FXML
    private Button voltarButton;

    @FXML
    private Button AtualizarFuncionarioButton;

    @FXML
    private Button ApagarFuncionarioButton;

    @FXML
    private Button AtualizarClienteButton;

    @FXML
    private Button ApagarClienteButton;

    @FXML
    private Button AtualizarFornecedorButton;

    @FXML
    private Button ApagarFornecedorButton;

    @FXML
    private Pane AtualizarFuncionarioPane;

        @FXML
        private TextField FuncNomeTextField;

        @FXML
        private TextField CPFFuncTextField;

        @FXML
        private TextField FuncLogin;

        @FXML
        private PasswordField funcSenhaTextField;

    @FXML
    private Pane AtualizarClientePane;

        @FXML
        private TextField clienteNomeTextFIeld;

        @FXML
        private TextField CPFClienteTextField;

        @FXML
        private TextField clienteEnderecoTextField;

        @FXML
        private TextField clienteTelefoneTextField;

    @FXML
    private Pane AtualizarFornecedorPane;

        @FXML
        private TextField fornecedorNomeTextField;

        @FXML
        private TextField fornecedorCNPJTextField;

        @FXML
        private TextField fornecedorEnderecoTextField;

        @FXML
        private TextField fornecedorTelefoneTextField;


    private ObservableList funcionarioValues = FXCollections.observableArrayList();
    private ObservableList clienteValues = FXCollections.observableArrayList();
    private ObservableList fornecedorValues = FXCollections.observableArrayList();
    private ObservableList vendaValues = FXCollections.observableArrayList();


    @FXML
    void Voltar(ActionEvent event) {
        HelloApplication.trocaTela("principal");
    }


    @FXML
    void OpenClientesTable(ActionEvent event) {
        //Visibilidade dos botões e tabelas que vão ser vistas
        clienteTableView.setVisible(true);
        AtualizarClienteButton.setVisible(true);
        ApagarClienteButton.setVisible(true);
        clienteTableView.toFront();

        //ao serem ativadas, outros botões são desativados
        AtualizarFornecedorButton.setVisible(false);
        ApagarFornecedorButton.setVisible(false);
        AtualizarFuncionarioButton.setVisible(false);
        ApagarFuncionarioButton.setVisible(false);

        //Outras tabelas são desativadas
        funcionarioTableView.setVisible(false);
        fornecedorTableView.setVisible(false);
        vendasTableView.setVisible(false);
    }

    @FXML
    void OpenFornecedorTable(ActionEvent event) {
        //Visibilidade dos botões e tabelas que vão ser vistas
        fornecedorTableView.setVisible(true);
        AtualizarFornecedorButton.setVisible(true);
        ApagarFornecedorButton.setVisible(true);
        fornecedorTableView.toFront();
        //ao serem ativadas, outros botões são desativados
        AtualizarClienteButton.setVisible(false);
        ApagarClienteButton.setVisible(false);
        AtualizarFuncionarioButton.setVisible(false);
        ApagarFuncionarioButton.setVisible(false);
        //Outras tabelas são desativadas
        funcionarioTableView.setVisible(false);
        clienteTableView.setVisible(false);
        vendasTableView.setVisible(false);
    }

    @FXML
    void OpenFuncionarioTable(ActionEvent event) {
        //Visibilidade dos botões e tabelas que vão ser vistas
        funcionarioTableView.setVisible(true);
        AtualizarFuncionarioButton.setVisible(true);
        ApagarFuncionarioButton.setVisible(true);
        funcionarioTableView.toFront();

        //ao serem ativadas, outros botões são desativados
        AtualizarClienteButton.setVisible(false);
        ApagarClienteButton.setVisible(false);
        AtualizarFornecedorButton.setVisible(false);
        ApagarFornecedorButton.setVisible(false);

        //Outras tabelas são desativadas
        clienteTableView.setVisible(false);
        fornecedorTableView.setVisible(false);
        vendasTableView.setVisible(false);
    }

    @FXML
    void OpenVendasTable(ActionEvent event) {

        vendasTableView.setVisible(true);
        vendasTableView.toFront();

        //Todos os botões são desativados pois essa tabela não é ossivel apagar
        AtualizarFuncionarioButton.setVisible(false);
        ApagarFuncionarioButton.setVisible(false);
        AtualizarClienteButton.setVisible(false);
        ApagarClienteButton.setVisible(false);
        AtualizarFornecedorButton.setVisible(false);
        ApagarFornecedorButton.setVisible(false);

        //Outras tabelas são desativadas
        clienteTableView.setVisible(false);
        funcionarioTableView.setVisible(false);
        fornecedorTableView.setVisible(false);
    }



    void setupRegistrosValues(){

        //Setando valores para a tela de Registros

        String selectFuncionario = "select * from funcionario where ativo = true";

        try (PreparedStatement statement = conexao.prepareStatement(selectFuncionario)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_funcionario");
                String nome = resultSet.getString("nome_funcionario");
                String CPF = resultSet.getString("cpf_funcionario");
                String usuario = resultSet.getString("login");

                funcionarioValues.add(
                        new Funcionario(ID,nome,CPF,usuario)
                );
                funcionarioTableView.setItems(funcionarioValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String selectCliente = "select * from cliente where ativo = true";

        try (PreparedStatement statement = conexao.prepareStatement(selectCliente)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_cliente");
                String nome = resultSet.getString("nome_cliente");
                String CPF = resultSet.getString("cpf_cliente");
                String endereco = resultSet.getString("endereco_cliente");
                String telefone = resultSet.getString("telefone_cliente");

                clienteValues.add(
                        new Cliente(ID,nome,CPF,endereco,telefone)
                );
                clienteTableView.setItems(clienteValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String selectFornecedor = "select * from fornecedor";

        try (PreparedStatement statement = conexao.prepareStatement(selectFornecedor)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("id_fornecedor");
                String nome = resultSet.getString("nome_fornecedor");
                String endereco = resultSet.getString("endereco_fornecedor");
                String CNPJ = resultSet.getString("CNPJ_fornecedor");
                String telefone = resultSet.getString("telefone_fornecedor");

                fornecedorValues.add(
                        new Fornecedor(ID,nome,endereco,CNPJ,telefone)
                );
                fornecedorTableView.setItems(fornecedorValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String vendaSelect = "SELECT venda.id_venda,cliente.nome_cliente, funcionario.nome_funcionario, produto.nome_produto, venda.quantidade, venda.preco_total, venda.horario_compra\n"+
        "FROM venda\n"+
        "JOIN funcionario ON venda.id_funcionario = funcionario.id_funcionario\n"+
        "JOIN produto ON venda.id_produto = produto.id_produto\n"+
        "JOIN cliente ON venda.id_cliente = cliente.id_cliente\n";

        try (PreparedStatement statement = conexao.prepareStatement(vendaSelect)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int IDVenda = resultSet.getInt("id_venda");
                String nomeCliente = resultSet.getString("nome_cliente");
                String nomeFuncionario = resultSet.getString("nome_funcionario");
                String nomeProduto= resultSet.getString("nome_produto");
                int quantidadeComprada = resultSet.getInt("quantidade");
                BigDecimal precototal =  resultSet.getBigDecimal("preco_total");
                Timestamp horarioCompra = resultSet.getTimestamp("horario_compra");


                vendaValues.add(
                        new Venda(IDVenda,nomeCliente, nomeFuncionario,nomeProduto,quantidadeComprada,precototal,horarioCompra)
                );
                System.out.println("bruh");
                vendasTableView.setItems(vendaValues);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @FXML
    void AtualizarFuncionario(ActionEvent event) {
        Funcionario itemSelecionado = funcionarioTableView.getSelectionModel().getSelectedItem();

        if(FuncNomeTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }
        if(CPFClienteTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }
        if(usuarioFuncionarios.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }
        if(funcSenhaTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }

        int id = itemSelecionado.getIDFuncionarioClass();
        String nome = FuncNomeTextField.getText();
        String cpf = CPFFuncTextField.getText();
        String usuario = usuarioFuncionarios.getText();
        String senha = funcSenhaTextField.getText();

        String queryUpdate = "update funcionario " +
                "set nome_funcionario = ?, " +
                "cpf_funcionario = ?, " +
                "login = ?, " +
                "senha = ? " +
                "where id_funcionario = ?";

        try(PreparedStatement statement = conexao.prepareStatement(queryUpdate)){
            statement.setString(1,nome);
            statement.setString(2,cpf);
            statement.setString(3,usuario);
            statement.setString(4,senha);
            statement.setInt(5,id);

            int linhasAfetadas = statement.executeUpdate();

            if(linhasAfetadas>0){
                exibirAlerta(Alert.AlertType.INFORMATION,"Mensagem","Funcionario Atualizado com sucesso");

                // Obtém a lista de itens da tabela
                ObservableList<Funcionario> listaFuncionarios = funcionarioTableView.getItems();

                // Obtém o índice do item selecionado
                int indiceSelecionado = listaFuncionarios.indexOf(itemSelecionado);

                // Atualiza o item selecionado com os novos valores
                itemSelecionado.setNomeFuncionarioClass(nome);
                itemSelecionado.setCPFFuncionarioClass(cpf);
                itemSelecionado.setUsuarioFuncionarioClass(usuario);

                // Atualiza o item selecionado na lista
                listaFuncionarios.set(indiceSelecionado, itemSelecionado);
            }
            else{
                exibirAlerta(Alert.AlertType.ERROR,"ERRO","Atualização Mal-Sucedida");
            }
        }catch(SQLException e){e.printStackTrace();}
    }

    @FXML
    void ApagarFuncionario(ActionEvent event) {
        Funcionario itemSelecionado = funcionarioTableView.getSelectionModel().getSelectedItem();
        // Verifica se um item está selecionado

        if (itemSelecionado != null) {
            exibirAlerta(Alert.AlertType.CONFIRMATION,"Tem Certeza?","Tem Certeza que quer APAGAR um Produto?");

            String queryDelete = "UPDATE funcionario\n" +
                    "SET ativo = FALSE\n" +
                    "WHERE id_produto = ?;";

            try(PreparedStatement statement = conexao.prepareStatement(queryDelete)){
                statement.setInt(1,itemSelecionado.getIDFuncionarioClass());
                int linhasAfetadas = statement.executeUpdate();

                if(linhasAfetadas > 0){
                    exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso","Produto Apagado com Sucesso!");
                    funcionarioTableView.getItems().remove(itemSelecionado);
                }
            }catch(SQLException e){
                e.printStackTrace();}
        }else {
            exibirAlerta(Alert.AlertType.INFORMATION,"Informe o Produto","Primeiro você precisa selecionar um Produto");
        }
    }

    @FXML
    void enterAtualizarFuncionario(ActionEvent event) {
        Funcionario itemSelecionado = funcionarioTableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            // Entra na tela de Atualizar os produtos
            AtualizarFuncionarioPane.setVisible(true);
            //Adiciona os valores existentes para serem atualizados
            FuncNomeTextField.setText(itemSelecionado.getNomeFuncionarioClass());
            CPFFuncTextField.setText(itemSelecionado.getCPFFuncionarioClass());
            FuncLogin.setText(itemSelecionado.getUsuarioFuncionarioClass());
        } else {
            exibirAlerta(Alert.AlertType.INFORMATION, "Informe o Produto", "Primeiro você precisa selecionar um Produto");

        }
    }

    @FXML
    void VoltarFuncionario(ActionEvent event){
        AtualizarFuncionarioPane.setVisible(false);
        metodosGerais.clearAll(FuncNomeTextField,CPFFuncTextField,FuncLogin,funcSenhaTextField);
    }




    @FXML
    void enterAtualizarCliente(ActionEvent event){
        Cliente itemSelecionado = clienteTableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            // Entra na tela de Atualizar os produtos
            AtualizarClientePane.setVisible(true);
            //Adiciona os valores existentes para serem atualizados
            clienteNomeTextFIeld.setText(itemSelecionado.getNomeClienteClass());
            CPFClienteTextField.setText(itemSelecionado.getCPFClienteClass());
            clienteEnderecoTextField.setText(itemSelecionado.getEnderecoClienteClass());
            clienteTelefoneTextField.setText(itemSelecionado.getTelefoneClienteClass());

        } else {
            exibirAlerta(Alert.AlertType.INFORMATION, "Informe o Cliente", "Primeiro você precisa selecionar um cliente");

        }
    }

    @FXML
    void AtualizarCliente(ActionEvent event) {
        if(clienteNomeTextFIeld.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }
        if(CPFClienteTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }
        if(clienteEnderecoTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR, "Preencha os campos!","Preencha os campos corretamente");
            return;
        }


        Cliente itemSelecionado = clienteTableView.getSelectionModel().getSelectedItem();

        int id = itemSelecionado.getIDClienteClass();
        String nome = clienteNomeTextFIeld.getText();
        String cpf = CPFClienteTextField.getText();
        String endereco = clienteEnderecoTextField.getText();
        String telefone = clienteTelefoneTextField.getText();

        String queryUpdate;
        if (clienteNomeTextFIeld != null) {
            queryUpdate = "update cliente " +
                    "set nome_cliente = ?, " +
                    "cpf_cliente = ?, " +
                    "endereco_cliente = ?," +
                    "telefone_cliente = ? " +
                    "where id_cliente = ?";

            try (PreparedStatement statement = conexao.prepareStatement(queryUpdate)) {
                statement.setString(1, nome);
                statement.setString(2, cpf);
                statement.setString(3,endereco);
                statement.setString(4, telefone);
                statement.setInt(5,id);


                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Mensagem", "Cliente Atualizado com sucesso");

                    // Obtém a lista de itens da tabela
                    ObservableList<Cliente> listaClientes = clienteTableView.getItems();

                    // Obtém o índice do item selecionado
                    int indiceSelecionado = listaClientes.indexOf(itemSelecionado);

                    // Atualiza o item selecionado com os novos valores
                    itemSelecionado.setNomeClienteClass(nome);
                    itemSelecionado.setCPFClienteClass(cpf);
                    itemSelecionado.setEnderecoClienteClass(endereco);
                    itemSelecionado.setTelefoneClienteClass(telefone);

                    // Atualiza o item selecionado na lista
                    listaClientes.set(indiceSelecionado, itemSelecionado);
                } else {
                    exibirAlerta(Alert.AlertType.ERROR, "ERRO", "Atualização Mal-Sucedida");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void ApagarCliente(ActionEvent event) {
        Cliente itemSelecionado = clienteTableView.getSelectionModel().getSelectedItem();
        // Verifica se um item está selecionado

        if (itemSelecionado != null) {
            exibirAlerta(Alert.AlertType.CONFIRMATION,"Tem Certeza?","Tem Certeza que quer APAGAR um Cliente?");

            String queryDelete = "UPDATE cliente\n" +
                                "SET ativo = FALSE\n" +
                                "WHERE id_cliente = ?";

            try(PreparedStatement statement = conexao.prepareStatement(queryDelete)){
                statement.setInt(1,itemSelecionado.getIDClienteClass());
                int linhasAfetadas = statement.executeUpdate();

                if(linhasAfetadas > 0){
                    exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso","Cliente Apagado com Sucesso!");
                    clienteTableView.getItems().remove(itemSelecionado);
                }
            }catch(SQLException e){
                e.printStackTrace();}
        }else {
            exibirAlerta(Alert.AlertType.INFORMATION,"Informe o cliente","Primeiro você precisa selecionar um Cliente");
        }
    }

    @FXML
    void VoltarCliente(ActionEvent event){
        AtualizarClientePane.setVisible(false);
        metodosGerais.clearAll(clienteNomeTextFIeld,CPFClienteTextField,clienteTelefoneTextField);
    }



    @FXML
    void enterAtualizarFornecedor(ActionEvent event){
        Fornecedor itemSelecionado = fornecedorTableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            // Entra na tela de Atualizar os produtos
            AtualizarFornecedorPane.setVisible(true);
            //Adiciona os valores existentes para serem atualizados
            fornecedorNomeTextField.setText(itemSelecionado.getNomeFornecedorClass());
            fornecedorEnderecoTextField.setText(itemSelecionado.getEnderecoFornecedorClass());
            fornecedorCNPJTextField.setText(itemSelecionado.getCNPJFornecedorClass());
            fornecedorTelefoneTextField.setText(itemSelecionado.getTelefoneFornecedorClass());

        } else {
            exibirAlerta(Alert.AlertType.INFORMATION, "Informe o Fornecedor", "Primeiro você precisa selecionar um Fornecedor");

        }
    }

    @FXML
    void AtualizarFornecedor(ActionEvent event) {
        if(fornecedorNomeTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Preencha os campos corretamente!");
            return;
        }

        if(fornecedorEnderecoTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Preencha os campos corretamente!");
            return;
        }
        if(fornecedorCNPJTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Preencha os campos corretamente!");
            return;
        }
        if(fornecedorTelefoneTextField.getText().isEmpty()){
            exibirAlerta(Alert.AlertType.ERROR,"Erro","Preencha os campos corretamente!");
            return;
        }

        Fornecedor itemSelecionado = fornecedorTableView.getSelectionModel().getSelectedItem();

        int id = itemSelecionado.getIDFornecedorClass();
        String nome = fornecedorNomeTextField.getText();
        String endereco = fornecedorEndereco.getText();
        String cnpj = fornecedorCNPJTextField.getText();
        String telefone = fornecedorTelefoneTextField.getText();

        String queryUpdate;
        if (clienteNomeTextFIeld != null) {
            queryUpdate = "update fornecedor " +
                        "set nome_fornecedor = ?, " +
                        "endereco_fornecedor = ?, " +
                        "CNPJ_fornecedor = ?," +
                        "telefone_fornecedor = ?, " +
                        "where id_fornecedor = ?";

            try (PreparedStatement statement = conexao.prepareStatement(queryUpdate)) {
                statement.setString(1, nome);
                statement.setString(2, endereco);
                statement.setString(3, cnpj);
                statement.setString(4, telefone);
                statement.setInt(5,id);


                int linhasAfetadas = statement.executeUpdate();

                if (linhasAfetadas > 0) {
                    exibirAlerta(Alert.AlertType.INFORMATION, "Mensagem", "Cliente Atualizado com sucesso");

                    // Obtém a lista de itens da tabela
                    ObservableList<Fornecedor> listaFornecedor = fornecedorTableView.getItems();

                    // Obtém o índice do item selecionado
                    int indiceSelecionado = listaFornecedor.indexOf(itemSelecionado);

                    // Atualiza o item selecionado com os novos valores
                    itemSelecionado.setNomeFornecedorClass(nome);
                    itemSelecionado.setEnderecoFornecedorClass(endereco);
                    itemSelecionado.setCNPJFornecedorClass(cnpj);
                    itemSelecionado.setTelefoneFornecedorClass(telefone);

                    // Atualiza o item selecionado na lista
                    listaFornecedor.set(indiceSelecionado, itemSelecionado);

                } else {
                    exibirAlerta(Alert.AlertType.ERROR, "ERRO", "Atualização Mal-Sucedida");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void ApagarFornecedor(ActionEvent event) {
        Fornecedor itemSelecionado = fornecedorTableView.getSelectionModel().getSelectedItem();
        // Verifica se um item está selecionado

        if (itemSelecionado != null) {
            exibirAlerta(Alert.AlertType.CONFIRMATION,"Tem Certeza?","Tem Certeza que quer APAGAR um Fornecedor?");

            String queryDelete = "UPDATE fornecedor\n" +
                                "SET ativo = FALSE\n" +
                                "WHERE id_fornecedor = ?;";

            try(PreparedStatement statement = conexao.prepareStatement(queryDelete)){
                statement.setInt(1,itemSelecionado.getIDFornecedorClass());
                int linhasAfetadas = statement.executeUpdate();

                if(linhasAfetadas > 0){
                    exibirAlerta(Alert.AlertType.INFORMATION,"Sucesso","Fornecedor Apagado com Sucesso!");
                    fornecedorTableView.getItems().remove(itemSelecionado);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else {
            exibirAlerta(Alert.AlertType.INFORMATION,"Selecione o Fornecedor","Primeiro você precisa selecionar um Fornecedor");
        }
    }

    @FXML
    void VoltarFornecedor(ActionEvent event){
        AtualizarFornecedorPane.setVisible(false);
        metodosGerais.clearAll(fornecedorNomeTextField,fornecedorCNPJTextField,fornecedorEnderecoTextField,fornecedorTelefoneTextField);
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SETANDO OS VALORES E ESPECIFICAÇÃO DAS COLUNAs NA TABLE VIEW

        //Table View de Funcionarios
        IDFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("IDFuncionarioClass"));
        nomeFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nomeFuncionarioClass"));
        CPFFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("CPFFuncionarioClass"));
        usuarioFuncionarios.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("usuarioFuncionarioClass"));
        //Table view de Clientes
        IDClientes.setCellValueFactory(new PropertyValueFactory<Cliente,Integer>("IDClienteClass"));
        nomeClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nomeClienteClass"));
        CPFClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("CPFClienteClass"));
        enderecoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("enderecoClienteClass"));
        telefoneClientes.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefoneClienteClass"));
        //Table View de Fornecedor
        fornecedorID.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("IDFornecedorClass"));
        fornecedorNome.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedorClass"));
        fornecedorEndereco.setCellValueFactory(new PropertyValueFactory<>("enderecoFornecedorClass"));
        CNPJFornecedor.setCellValueFactory(new PropertyValueFactory<>("CNPJFornecedorClass"));
        telefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("telefoneFornecedorClass"));
        //Table View de Vendas
        IDVenda.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("IDVendas"));
        clienteNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeClienteVenda"));
        funcionarioNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeFuncionarioVenda"));
        produtoNomeVendas.setCellValueFactory(new PropertyValueFactory<Venda, String>("nomeProdutoVenda"));
        quantidadeProdutoVendas.setCellValueFactory(new PropertyValueFactory<Venda, Integer>("quantidade"));
        precoVendas.setCellValueFactory(new PropertyValueFactory<Venda, Double>("precoTotal"));
        horarioCompra.setCellValueFactory(new PropertyValueFactory<Venda, Timestamp>("horarioCompra"));

        //Limitando os caracteres dos textfields

        //Pane de atualização de Funcionarios
        Formatacao.ApenasLetras(FuncNomeTextField);
        Formatacao.LimitadorCaracteres(FuncNomeTextField,60);
        Formatacao.formataCPFEnquantoDigita(CPFFuncTextField);
        Formatacao.LimitadorCaracteres(FuncLogin,16);
        Formatacao.LimitadorCaracteres(funcSenhaTextField,16);

        //Pane de atualização de Clientes
        Formatacao.ApenasLetras(clienteNomeTextFIeld);
        Formatacao.LimitadorCaracteres(clienteNomeTextFIeld,60);
        Formatacao.formataCPFEnquantoDigita(CPFClienteTextField);
        Formatacao.formataTelefoneDinamico(clienteTelefoneTextField);

        //Pane de atualização de fornecedor
        Formatacao.LimitadorCaracteres(fornecedorNomeTextField,60);
        Formatacao.LimitadorCaracteres(fornecedorEnderecoTextField,70);
        Formatacao.formataCNPJDinamico(fornecedorCNPJTextField);
        Formatacao.formataTelefoneDinamico(fornecedorTelefoneTextField);

        setupRegistrosValues();
    }
}
