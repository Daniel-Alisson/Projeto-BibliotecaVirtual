package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AdministradorController extends TrocarTelasController {
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoSenha, campoSenha2, campoTelefone;

    @FXML
    Label alerta;

    // AREA DE TESTE
    // AQ JÁ TEM ALGUNS ADMINS CADASTRADOS OU SE PRECISAR ADICIONAR MAIS
    public void initialize() {
        Administrador admin = new Administrador("dan", "1", "dan1", "1", "123");
        Administrador admin2 = new Administrador("dan", "2", "dan2", "1", "123");
        Administrador admin3 = new Administrador("dan", "3", "dan3", "1", "123");
        Administrador.cadastrarAdministrador(admin);
        Administrador.cadastrarAdministrador(admin2);
        Administrador.cadastrarAdministrador(admin3);
    }

    // METODO PARA CADASTRAR UM ADMINISTRADOR
    @FXML
    private void cadastrarAdministrador(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().replaceAll("\\.|-", "").trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();
        String senha2 = campoSenha2.getText().trim();
        String telefone = campoTelefone.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || senha2.isEmpty() || telefone.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if (!validarCPF(cpf)) {
            alerta.setText("CPF inválido");
            return;
        } if (!email.contains("@") || !email.contains(".")) {
            alerta.setText("E-mail inválido");
            return;
        } if (!senha.equals(senha2)) {
            alerta.setText("As senhas são diferentes");
            return;
        }

        Administrador admin = new Administrador(nome, cpf, email, senha, telefone);
        int op = Administrador.cadastrarAdministrador(admin);

        switch (op) {
            case 0:
                alerta.setText("Administrador cadastrado com sucesso!");
                registrarLog("Cadastro realizado: " + nome + ", CPF: " + cpf);
                limparCampos();
                break;
            case 1:
                alerta.setText("Já existe um administrador cadastrado com esse CPF");
                break;
            case 2:
                alerta.setText("Já existe um administrador cadastrado com esse E-mail");
                break;
        }
    }

    // METODO PARA REALIZAR LOGIN DE UM ADMINISTRADOR
    @FXML
    private void loginAdministrador(ActionEvent event) {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        Administrador adminLogado = Administrador.login(email, senha);

        if(adminLogado != null) {
            alerta.setText("Login realizado com sucesso!");
            registrarLog("Login realizado: E-mail: " + email);
            mudarTelalogin(event);
        } else {
            alerta.setText("Email ou senha inválidos!");
        }
    }

    // METODO PARA LIMPAR CAMPOS
    @FXML
    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoSenha.clear();
        campoSenha2.clear();
        campoTelefone.clear();
    }

    // METODO PARA REGISTRAR UM ARQUIVO DE LOG, PARA REGISTRAR AS FUNCIONALIDADES REALIZAS
    private void registrarLog(String mensagem) {
        try {
            String log = java.time.LocalDateTime.now() + " - " + mensagem + "\n";
            Files.write(Path.of("log.txt"), log.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            alerta.setText("Erro ao registrar o log.");
        }
    }

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }
}
