package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdministradorController extends TrocarTelasController {
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoSenha, campoSenha2, campoTelefone;

    @FXML
    Label alerta;

    // TESTES
    public void initialize() {
        Administrador admin = new Administrador("dan", "1", "dan1", "1", "123");
        Administrador admin2 = new Administrador("dan", "2", "dan2", "1", "123");
        Administrador admin3 = new Administrador("dan", "3", "dan3", "1", "123");
        Administrador.cadastrarAdministrador(admin);
        Administrador.cadastrarAdministrador(admin2);
        Administrador.cadastrarAdministrador(admin3);
    }

    @FXML
    private void cadastrarAdministrador(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();
        String senha2 = campoSenha2.getText().trim();
        String telefone = campoTelefone.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || senha2.isEmpty() || telefone.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        }

        if(!senha.equals(senha2)) {
            alerta.setText("As senhas são diferentes");
            return;
        }

        Administrador admin = new Administrador(nome, cpf, email, senha, telefone);
        int op = Administrador.cadastrarAdministrador(admin);

        switch (op) {
            case 0:
                alerta.setText("Administrador cadastrado com sucesso!");
                limparCampos();
                break;
            case 1:
                alerta.setText("Já existe um administrador cadastrado com esse CPF");
                break;
            case 2:
                alerta.setText("Já existe um administrador cadastrado com esse E-mail");
                break;
        }
        /*
        SEM VERIFICADOR
        Administrador adminNovo = new Administrador(nome, cpf, email, senha, telefone);
        if(Administrador.cadastrarAdministrador(adminNovo)) {
            alerta.setText("Administrador cadastrado com sucesso!");
            limparCampos();
        } else {
            alerta.setText("Já existe um administrador cadastrado com esse cpf");
        }
        */
    }

    @FXML
    private void loginAdministrador(ActionEvent event) {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        Administrador adminLogado = Administrador.login(email, senha);

        if(adminLogado != null) {
            alerta.setText("Login realizado com sucesso!");
            mudarTelalogin(event);
        } else {
            alerta.setText("Email ou senha inválidos!");
        }
    }

    @FXML
    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoSenha.clear();
        campoSenha2.clear();
        campoTelefone.clear();
    }
}
