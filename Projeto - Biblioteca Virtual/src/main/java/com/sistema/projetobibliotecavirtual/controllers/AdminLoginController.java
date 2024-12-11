package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminLoginController extends TrocarTelasController {
    // CAMPOS DO CADASTRO
    @FXML
    TextField campoEmail, campoSenha;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS CADASTRADOS
    @FXML
    Label alerta;

    // METODO PARA INICIAR O LOGIN DO ADMIN
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
            menuLivros(event);
        } else {
            alerta.setText("Email ou senha incorretos!");
        }
    }

    // METODO PARA LIMPAR CAMPOS
    @FXML
    private void limparCampos() {
        campoEmail.clear();
        campoSenha.clear();
    }
}
