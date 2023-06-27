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

    public void setIDProdClass(int IDProdClass) {
        this.IDProdClass = IDProdClass;
    }

    public void setNomeProdClass(String nomeProdClass) {
        this.nomeProdClass = nomeProdClass;
    }

    public void setQtdProdClass(int qtdProdClass) {
        this.qtdProdClass = qtdProdClass;
    }

    public void setPrecoProdClass(Double precoProdClass) {
        this.precoProdClass = precoProdClass;
    }
}
