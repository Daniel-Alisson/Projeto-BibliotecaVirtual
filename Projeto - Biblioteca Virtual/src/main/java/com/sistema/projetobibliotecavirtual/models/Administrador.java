package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Pessoa implements Serializable {
    private String senha;

    // LISTA PARA GUARDAR ADMINS
    private static List<Administrador> listaAdministradores = new ArrayList<>();

    public Administrador(String nome, String cpf, String email, String senha) {
        super(nome, cpf, email);
        this.senha = senha;
    }

    public static List<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public static void setListaAdministradores(List<Administrador> listaAdministradores) {
        Administrador.listaAdministradores = listaAdministradores;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // METODO PARA CADASTRO DE ADMINISTRADORES
    public int cadastrarAdministrador(Administrador administrador) {
        for(Administrador testeAdmin : listaAdministradores) {
            if(testeAdmin.getCpf().equals(administrador.getCpf())) {
                return 1;
            }
            if(testeAdmin.getEmail().equals(administrador.getEmail())) {
                return 2;
            }
        }
        listaAdministradores.add(administrador);
        return 0;
    }

    // METODO PARA REALIAR O LOGIN DO ADMIN
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
            // testes
            Administrador admin = new Administrador("Daniel", "00000000000", "daniel@gmail.com", "1");
            listaAdministradores.add(admin);
            List<Administrador> carregarAdmins = (List<Administrador>) SerializacaoService.carregarObjeto("administradores.txt");
            if(carregarAdmins != null) {
                listaAdministradores = carregarAdmins;
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
