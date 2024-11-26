package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Aluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlunoController extends TrocarTelasController {
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoTelefone, campoMatricula, campoTurma;

    @FXML
    Label alerta;

    public void initialize() {
        Aluno.carregarAlunos();
    }

    @FXML
    private void cadastrarAlunos(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String matricula = campoMatricula.getText().trim();
        String turma = campoTurma.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || telefone.isEmpty() || matricula.isEmpty() || turma.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if(!cpf.matches("\\d{11}")) {
            alerta.setText("CPF inválido! Deve conter exatamente 11 dígitos numéricos.");
            return;
        } if(!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            alerta.setText("Email inválido! Verifique o formato.");
            return;
        } if(!matricula.matches("\\d+")) {
            alerta.setText("Matrícula inválida! Deve conter apenas números.");
            return;
        }

        Aluno alunoNovo = new Aluno(nome, cpf, email, telefone, matricula, turma);
        int op = Aluno.cadastrarAluno(alunoNovo);

        switch (op) {
            case 0:
                alerta.setText("Aluno cadastrado com sucesso!");
                limparCampos();
                break;
            case 1:
                alerta.setText("Já existe um aluno cadastrado com esse CPF");
                break;
            case 2:
                alerta.setText("Já existe um aluno cadastrado com esse E-mail");
                break;
            case 3:
                alerta.setText("Já existe um aluno cadastrado com essa Matrícula");
                break;
        }
    }

    private void limparCampos() {
        campoNome.clear();
        campoCpf.clear();
        campoEmail.clear();
        campoTelefone.clear();
        campoMatricula.clear();
        campoTurma.clear();
    }
}
