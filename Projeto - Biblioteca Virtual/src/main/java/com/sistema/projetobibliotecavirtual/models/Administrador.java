package com.sistema.projetobibliotecavirtual.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Administrador extends Pessoa {
    private String senhaHash;

    private static List<Administrador> listaAdministradores = new ArrayList<>();

    public Administrador(String nome, String cpf, String email, String senha, String telefone) {
        super(nome, cpf, email, telefone);
        setSenha(senha);
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenha(String senha) {
        this.senhaHash = gerarHash(senha);
    }

    private String gerarHash(String senha) {
        return Integer.toString(senha.hashCode());
    }

    public boolean verificarSenha(String senha) {
        return Objects.equals(this.senhaHash, gerarHash(senha));
    }

    public static List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public static void setListaAdministradores(List<Administrador> listaAdministradores) {
        Administrador.listaAdministradores = listaAdministradores;
    }

    public static int cadastrarAdministrador(Administrador administrador) {
        for(Administrador admin : listaAdministradores) {
            if(admin.getCpf().equals(administrador.getCpf())) {
                return 1;
            } if(admin.getEmail().equals(administrador.getEmail())) {
                return 2;
            }
        }
        listaAdministradores.add(administrador);
        return 0;
    }

    public static Administrador login(String email, String senha) {
        for(Administrador admin : listaAdministradores) {
            if(admin.getEmail().equals(email) && admin.verificarSenha(senha)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "FUNCIONÁRIO - " + getNome() + "\n\nNome: " + getNome() + "\nCpf: " + getCpf() +
                "\nEmail: " + getEmail() + "\nTelefone: " + getTelefone();
    }
}
