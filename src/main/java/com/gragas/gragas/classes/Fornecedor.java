package com.gragas.gragas.classes;

public class Fornecedor {

    String nomeFornecedorClass;
    String enderecoFornecedorClass;
    String CNPJFornecedorClass;
    String telefoneFornecedorClass;

    public Fornecedor(String nomeFornecedorClass, String enderecoFornecedorClass, String CNPJFornecedorClass, String telefoneFornecedorClass) {
        this.nomeFornecedorClass = nomeFornecedorClass;
        this.enderecoFornecedorClass = enderecoFornecedorClass;
        this.CNPJFornecedorClass = CNPJFornecedorClass;
        this.telefoneFornecedorClass = telefoneFornecedorClass;
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
