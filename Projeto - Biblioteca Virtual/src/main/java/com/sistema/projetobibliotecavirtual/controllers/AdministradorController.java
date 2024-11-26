package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdministradorController extends TrocarTelasController {
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoTelefone, campoSenha, campoSenha1;

    @FXML
    Label alerta;

    @FXML
    public void initialize() {
        Administrador.carregarAdministradores();
    }

    @FXML
    private void cadastrarAdministrador(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String senha = campoSenha.getText().trim();
        String senha1 = campoSenha1.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            alerta.setText("Preencha todos os campos nescessários");
            return;
        } if(!cpf.matches("\\d{11}")) {
            alerta.setText("CPF inválido!");
            return;
        } if(!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            alerta.setText("Email inválido!");
            return;
        } if(!senha.equals(senha1)) {
            alerta.setText("Senhas diferentes!");
        }

        Administrador adminNovo = new Administrador(nome, cpf, email, telefone, senha);
        int op = Administrador.cadastrarAdministrador(adminNovo);

        switch(op) {
            case 0:
                alerta.setText("Administrador cadastrado com sucesso!");
                limparCampos();
                mudarTelalogin(event);
                break;
            case 1:
                alerta.setText("Já existe um administrador cadastrado com esse CPF");
                break;
            case 2:
                alerta.setText("Já existe um administrador cadastrado com esse E-mail");
                break;
        }
    }

    @FXML
    private void loginAdministrador(ActionEvent event) {
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();

        if(email.isEmpty() || senha.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        }

        Administrador admin = Administrador.realizarLogin(email, senha);

        if(admin != null) {
            alerta.setText("Login realizado com sucesso!");
            mudarTelaCadastro(event);
        } else {
            alerta.setText("Email ou senha incorretos!");
        }
    }

    @FXML
    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
        campoSenha.clear();
        campoSenha1.clear();
    }
}
