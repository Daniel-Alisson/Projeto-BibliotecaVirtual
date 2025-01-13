package com.sistema.projetobibliotecavirtual.models;

import java.io.Serializable;

public class Administrador extends Pessoa implements Serializable {
    private String senha;
    private transient Administrador usuarioLogado;
    private boolean status;

    public Administrador(String nome, String cpf, String email, String senha, boolean status) {
        super(nome, cpf, email);
        this.senha = senha;
        this.status = false;
    }

    public Administrador getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Administrador usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
