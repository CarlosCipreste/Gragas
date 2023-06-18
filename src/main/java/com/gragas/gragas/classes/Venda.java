package com.gragas.gragas.classes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Venda {

    int IDVendas;
    String nomeClienteVenda;
    String nomeFuncionarioVenda;
    String nomeProdutoVenda;
    int quantidade;
    BigDecimal precoTotal;
    Timestamp horarioCompra;

    public Venda(int IDVendas, String nomeClienteVenda, String nomeFuncionarioVenda, String nomeProdutoVenda, int quantidade, BigDecimal precoTotal, Timestamp horarioCompra) {
        this.IDVendas = IDVendas;
        this.nomeClienteVenda = nomeClienteVenda;
        this.nomeFuncionarioVenda = nomeFuncionarioVenda;
        this.nomeProdutoVenda = nomeProdutoVenda;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.horarioCompra = horarioCompra;
    }

    public int getIDVendas() {
        return IDVendas;
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

    public Timestamp getHorarioCompra() {
        return horarioCompra;
    }
}

