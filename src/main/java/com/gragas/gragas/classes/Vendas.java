package com.gragas.gragas.classes;

public class Vendas {

    String id_cliente;
    String id_funcionario;
    String id_produto;
    int quantidade;
    Double preco_total;

    public Vendas(String id_cliente, String id_funcionario, String id_produto, int quantidade, Double preco_total) {
        this.id_cliente = id_cliente;
        this.id_funcionario = id_funcionario;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public String getId_produto() {
        return id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Double getPreco_total() {
        return preco_total;
    }
}
