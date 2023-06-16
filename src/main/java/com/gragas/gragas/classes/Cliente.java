package com.gragas.gragas.classes;

public class Cliente {

    String nomeClienteClass;
    String CPFClienteClass;
    String telefoneClienteClass;

    public Cliente(String nomeClienteClass, String CPFClienteClass, String telefoneClienteClass) {
        this.nomeClienteClass = nomeClienteClass;
        this.CPFClienteClass = CPFClienteClass;
        this.telefoneClienteClass = telefoneClienteClass;
    }

    public String getNomeClienteClass() {
        return nomeClienteClass;
    }

    public String getCPFClienteClass() {
        return CPFClienteClass;
    }

    public String getTelefoneClienteClass() {
        return telefoneClienteClass;
    }
}
