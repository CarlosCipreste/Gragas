package com.gragas.gragas.classes;

import java.util.Date;

public class ProdEstoque {

    int id;
    String nome;
    String preco;
    boolean alcoolico;
    String tipo;
    Date validade;
    int quantidade;

    public ProdEstoque(int id, String nome, String preco, boolean alcoolico, String tipo, Date validade, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.alcoolico = alcoolico;
        this.tipo = tipo;
        this.validade = validade;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }

    public boolean isAlcoolico() {
        return alcoolico;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getValidade() {
        return validade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
