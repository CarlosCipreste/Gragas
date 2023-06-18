package com.gragas.gragas.classes;

public class Funcionario {

    int IDFuncionarioClass;
    String nomeFuncionarioClass;
    String CPFFuncionarioClass;
    String usuarioFuncionarioClass;

    public Funcionario(int IDFuncionarioClass, String nomeFuncionarioClass, String CPFFuncionarioClass, String usuarioFuncionarioClass) {
        this.IDFuncionarioClass = IDFuncionarioClass;
        this.nomeFuncionarioClass = nomeFuncionarioClass;
        this.CPFFuncionarioClass = CPFFuncionarioClass;
        this.usuarioFuncionarioClass = usuarioFuncionarioClass;
    }

    public int getIDFuncionarioClass() {
        return IDFuncionarioClass;
    }

    public String getNomeFuncionarioClass() {
        return nomeFuncionarioClass;
    }

    public String getCPFFuncionarioClass() {
        return CPFFuncionarioClass;
    }

    public String getUsuarioFuncionarioClass() {
        return usuarioFuncionarioClass;
    }
}