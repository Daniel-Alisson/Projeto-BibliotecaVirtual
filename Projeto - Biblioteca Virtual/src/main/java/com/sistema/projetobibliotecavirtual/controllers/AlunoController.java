package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Aluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AlunoController extends TrocarTelasController {
    // CAMPOS PARA CADASTRO
    @FXML
    TextField campoNome, campoTelefone, campoMatricula, campoTurma;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS CADASTRADOS
    @FXML
    Label alerta;

    // CADASTRAR ALUNOS
    @FXML
    private void cadastrarAlunos(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String matricula = campoMatricula.getText().trim();
        String turma = campoTurma.getText().trim();

        if(nome.isEmpty() || matricula.isEmpty() || turma.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if(!matricula.matches("\\d+")) {
            alerta.setText("Matrícula inválida! Deve conter apenas números.");
            return;
        }

        Aluno alunoNovo = new Aluno(nome, turma, matricula, telefone);
        int op = alunoNovo.cadastrarAluno(alunoNovo);

        switch(op) {
            case 0:
                alerta.setText("Aluno cadastrado com sucesso!");
                limparCampos();
                break;
            case 1:
                alerta.setText("Já existe um aluno cadastrado com essa Matrícula");
                break;
        }
    }

    // METODO PARA LIMPAR CAMPOS
    private void limparCampos() {
        campoNome.clear();
        campoTelefone.clear();
        campoMatricula.clear();
        campoTurma.clear();
    }
}
