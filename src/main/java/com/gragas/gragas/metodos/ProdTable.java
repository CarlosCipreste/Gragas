package com.gragas.gragas.metodos;

public class ProdTable {

    String nomeProdClass;
    int qtdProdClass;

    public ProdTable(String nomeProdClass, int qtdProdClass) {
        this.nomeProdClass = nomeProdClass;
        this.qtdProdClass = qtdProdClass;
    }

    public String getNomeProdClass() {
        return nomeProdClass;
    }

    public int getQtdProdClass() {
        return qtdProdClass;
    }
}
