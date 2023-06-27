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

    public void setIDFornecedorClass(int IDFornecedorClass) {
        this.IDFornecedorClass = IDFornecedorClass;
    }

    public void setNomeFornecedorClass(String nomeFornecedorClass) {
        this.nomeFornecedorClass = nomeFornecedorClass;
    }

    public void setEnderecoFornecedorClass(String enderecoFornecedorClass) {
        this.enderecoFornecedorClass = enderecoFornecedorClass;
    }

    public void setCNPJFornecedorClass(String CNPJFornecedorClass) {
        this.CNPJFornecedorClass = CNPJFornecedorClass;
    }

    public void setTelefoneFornecedorClass(String telefoneFornecedorClass) {
        this.telefoneFornecedorClass = telefoneFornecedorClass;
    }
}