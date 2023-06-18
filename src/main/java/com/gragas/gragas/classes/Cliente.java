package com.gragas.gragas.classes;

public class Cliente {

    int IDClienteClass;
    String nomeClienteClass;
    String CPFClienteClass;
    String telefoneClienteClass;

    public Cliente(int IDCLienteClass, String nomeClienteClass, String CPFClienteClass, String telefoneClienteClass) {
        this.IDClienteClass = IDCLienteClass;
        this.nomeClienteClass = nomeClienteClass;
        this.CPFClienteClass = CPFClienteClass;
        this.telefoneClienteClass = telefoneClienteClass;
    }

    public int getIDClienteClass() {
        return IDClienteClass;
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
