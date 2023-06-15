package com.gragas.gragas.classes;

public class ProdVenda {

    int IDProdClass;
    String nomeProdClass;
    int qtdProdClass;
    Double precoProdClass;

    public String getNomeProdClass() {
        return nomeProdClass;
    }

    public ProdVenda(int IDProdClass, String nomeProdClass, int qtdProdClass, Double precoProdClass) {
        this.IDProdClass = IDProdClass;
        this.nomeProdClass = nomeProdClass;
        this.qtdProdClass = qtdProdClass;
        this.precoProdClass = precoProdClass;
    }

    public int getIDProdClass() {
        return IDProdClass;
    }

    public int getQtdProdClass() {
        return qtdProdClass;
    }

    public Double getPrecoProdClass() {
        return precoProdClass;
    }
}
