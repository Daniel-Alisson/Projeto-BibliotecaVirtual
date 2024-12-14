package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Pessoa implements Serializable {
    private String senha;
    private transient Administrador usuarioLogado;

    // LISTA PARA GUARDAR ADMINS

    public Administrador(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email);
        this.senha = senha;
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
}
