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

    public void setIDVendas(int IDVendas) {
        this.IDVendas = IDVendas;
    }

    public void setNomeClienteVenda(String nomeClienteVenda) {
        this.nomeClienteVenda = nomeClienteVenda;
    }

    public void setNomeFuncionarioVenda(String nomeFuncionarioVenda) {
        this.nomeFuncionarioVenda = nomeFuncionarioVenda;
    }

    public void setNomeProdutoVenda(String nomeProdutoVenda) {
        this.nomeProdutoVenda = nomeProdutoVenda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void setHorarioCompra(Timestamp horarioCompra) {
        this.horarioCompra = horarioCompra;
    }
}

