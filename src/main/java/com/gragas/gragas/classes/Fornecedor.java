package com.gragas.gragas.classes;

public class Fornecedor {

    int IDFornecedorClass;
    String nomeFornecedorClass;
    String enderecoFornecedorClass;
    String CNPJFornecedorClass;
    String telefoneFornecedorClass;

    public Fornecedor(int IDFornecedorClass, String nomeFornecedorClass, String enderecoFornecedorClass, String CNPJFornecedorClass, String telefoneFornecedorClass) {
        this.IDFornecedorClass = IDFornecedorClass;
        this.nomeFornecedorClass = nomeFornecedorClass;
        this.enderecoFornecedorClass = enderecoFornecedorClass;
        this.CNPJFornecedorClass = CNPJFornecedorClass;
        this.telefoneFornecedorClass = telefoneFornecedorClass;
    }

    public int getIDFornecedorClass() {
        return IDFornecedorClass;
    }

    public String getNomeFornecedorClass() {
        return nomeFornecedorClass;
    }

    public String getEnderecoFornecedorClass() {
        return enderecoFornecedorClass;
    }

    public String getCNPJFornecedorClass() {
        return CNPJFornecedorClass;
    }

    public String getTelefoneFornecedorClass() {
        return telefoneFornecedorClass;
    }
}