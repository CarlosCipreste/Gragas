package com.gragas.gragas.classes;

public class Funcionario {

    String nomeFuncionarioClass;
    String CPFFuncionarioClass;
    String usuarioFuncionarioClass;

    public Funcionario(String nomeFuncionarioClass, String CPFFuncionarioClass, String usuarioFuncionarioClass) {
        this.nomeFuncionarioClass = nomeFuncionarioClass;
        this.CPFFuncionarioClass = CPFFuncionarioClass;
        this.usuarioFuncionarioClass = usuarioFuncionarioClass;
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
