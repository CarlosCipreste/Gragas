package com.gragas.gragas.classes;

import java.math.BigDecimal;

public class Venda {

    String nomeClienteVenda;
    String nomeFuncionarioVenda;
    String nomeProdutoVenda;
    int quantidade;
    BigDecimal precoTotal;

    public Venda(String nomeClienteVenda, String nomeFuncionarioVenda, String nomeProdutoVenda, int quantidade, BigDecimal precoTotal) {
        this.nomeClienteVenda = nomeClienteVenda;
        this.nomeFuncionarioVenda = nomeFuncionarioVenda;
        this.nomeProdutoVenda = nomeProdutoVenda;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public String getNomeClienteVenda() {
        return nomeClienteVenda;
    }

    public String getNomeFuncionarioVenda() {
        return nomeFuncionarioVenda;
    }

    public String getNomeProdutoVenda() {
        return nomeProdutoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }
}
