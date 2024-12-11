package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminCadastroController extends TrocarTelasController {
    // CAMPOS DO CADASTRO
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoSenha, campoSenhaNovamente;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS CADASTRADOS
    @FXML
    Label alerta;

    // METODO PARA CADASTRAR ADMINISTRADORES
    @FXML
    private void cadastrarAdministrador(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();
        String senhaNovamente = campoSenhaNovamente.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if(!cpf.matches("\\d{11}")) {
            alerta.setText("CPF inválido!");
            return;
        } if(!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            alerta.setText("Email inválido!");
            return;
        } if(!senha.equals(senhaNovamente)) {
            alerta.setText("Senhas não correspondem");
            return;
        }

        Administrador adminNovo = new Administrador(nome, cpf, email, senha);
        int op = adminNovo.cadastrarAdministrador(adminNovo);

        switch(op) {
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
    }

    // METODO PARA LIMPAR CAMPOS
    @FXML
    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoSenha.clear();
        campoSenhaNovamente.clear();
    }
}
