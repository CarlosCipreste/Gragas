package com.gragas.gragas.metodos;

public class ProdTable {

    int IDProdClass;
    String nomeProdClass;
    int qtdProdClass;

    public String getNomeProdClass() {
        return nomeProdClass;
    }



    public ProdTable(int IDProdClass, String nomeProdClass, int qtdProdClass) {
        this.IDProdClass = IDProdClass;
        this.nomeProdClass = nomeProdClass;
        this.qtdProdClass = qtdProdClass;
    }

    public int getQtdProdClass() {
        return qtdProdClass;
    }
    public int getIDProdClass() {
        return IDProdClass;
    }

}
