package com.gragas.gragas.classes;

public class Cliente {

    int IDClienteClass;
    String nomeClienteClass;
    String CPFClienteClass;
    String enderecoClienteClass;
    String telefoneClienteClass;

    public Cliente(int IDClienteClass, String nomeClienteClass, String CPFClienteClass, String enderecoClienteClass, String telefoneClienteClass) {
        this.IDClienteClass = IDClienteClass;
        this.nomeClienteClass = nomeClienteClass;
        this.CPFClienteClass = CPFClienteClass;
        this.enderecoClienteClass = enderecoClienteClass;
        this.telefoneClienteClass = telefoneClienteClass;
    }

    public int getIDClienteClass() {
        return IDClienteClass;
    }

    public void setIDClienteClass(int IDClienteClass) {
        this.IDClienteClass = IDClienteClass;
    }

    public void setNomeClienteClass(String nomeClienteClass) {
        this.nomeClienteClass = nomeClienteClass;
    }

    public void setCPFClienteClass(String CPFClienteClass) {
        this.CPFClienteClass = CPFClienteClass;
    }

    public void setEnderecoClienteClass(String enderecoClienteClass) {
        this.enderecoClienteClass = enderecoClienteClass;
    }

    public void setTelefoneClienteClass(String telefoneClienteClass) {
        this.telefoneClienteClass = telefoneClienteClass;
    }

    public String getNomeClienteClass() {
        return nomeClienteClass;
    }

    public String getCPFClienteClass() {
        return CPFClienteClass;
    }

    public String getEnderecoClienteClass() {
        return enderecoClienteClass;
    }

    public String getTelefoneClienteClass() {
        return telefoneClienteClass;
    }
}
