package com.gragas.gragas.metodos;

import java.util.Date;

public class ProdEstoque {

    String nome;
    String preco;
    boolean alcoolico;
    String tipo;
    String validade;
    int quantidade;

    public ProdEstoque(String nome, String preco, boolean alcoolico, String tipo, String validade, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.alcoolico = alcoolico;
        this.tipo = tipo;
        this.validade = validade;
        this.quantidade = quantidade;
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

    public String getValidade() {
        return validade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
