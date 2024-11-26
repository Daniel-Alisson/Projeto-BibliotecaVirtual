package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Pessoa implements Serializable {
    private String senha;
    private static List<Administrador> listaAdministradores = new ArrayList<>();

    public Administrador(String nome, String cpf, String email, String telefone, String senha) {
        super(nome, cpf, email, telefone);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public static void setListaAdministradores(List<Administrador> listaAdministradores) {
        Administrador.listaAdministradores = listaAdministradores;
    }

    public static int cadastrarAdministrador(Administrador administrador) {
        for(Administrador admin : listaAdministradores) {
            if (admin.getCpf().equals(administrador.getCpf())) {
                return 1; // CPF já cadastrado
            }
            if (admin.getEmail().equals(administrador.getEmail())) {
                return 2; // E-mail já cadastrado
            }
        }
        listaAdministradores.add(administrador);
        //salvarAdministradores();
        return 0;
    }

    public static Administrador realizarLogin(String email, String senha) {
        for(Administrador admin : listaAdministradores) {
            if(admin.getEmail().equals(email) && admin.getSenha().equals(senha)) {
                return admin;
            }
        }
        return null;
    }

    public static void salvarAdministradores() {
        try {
            SerializacaoService.salvarObjeto(listaAdministradores, "administradores.txt");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarAdministradores() {
        try {
            List<Administrador> carregarAdmins = (List<Administrador>) SerializacaoService.carregarObjeto("administradores.txt");
            if(carregarAdmins != null) {
                listaAdministradores = carregarAdmins;
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
