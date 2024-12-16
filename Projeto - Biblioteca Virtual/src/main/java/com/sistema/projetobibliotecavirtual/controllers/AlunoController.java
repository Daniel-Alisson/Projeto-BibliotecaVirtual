package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Aluno;
import com.sistema.projetobibliotecavirtual.services.SerializacaoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class AlunoController extends TrocarTelasController {
    // CAMPOS PARA CADASTRO
    @FXML
    TextField campoNome, campoTelefone, campoMatricula, campoTurma;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS CADASTRADOS
    @FXML
    Label alerta;

    // LISTA DE ALUNOS
    private static List<Aluno> listaAlunos = new ArrayList<>();

    public static List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public static void setListaAlunos(List<Aluno> listaAlunos) {
        AlunoController.listaAlunos = listaAlunos;
    }

    // CADASTRAR ALUNOS
    @FXML
    private void cadastrarAlunos(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String matricula = campoMatricula.getText().trim();
        String turma = campoTurma.getText().trim();

        if (nome.isEmpty() || matricula.isEmpty() || turma.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if (!matricula.matches("\\d+")) {
            alerta.setText("Matrícula inválida! Deve conter apenas números.");
            return;
        }

        for (Aluno aluno : listaAlunos) {
            if (aluno.getMatricula().equals(matricula)) {
                alerta.setText("Já existe um aluno cadastrado com essa Matrícula");
                return;
            }
        }
        Aluno alunoNovo = new Aluno(nome, turma, matricula, telefone);
        listaAlunos.add(alunoNovo);
        salvarListaAlunos("alunos.ser");
        alerta.setText("Aluno cadastrado com sucesso!");
        limparCampos();
    }
    public static void salvarListaAlunos(String caminhoArquivo) {
        SerializacaoService.salvarObjeto(listaAlunos, caminhoArquivo);
    }

    public static void carregarListaAlunos(String caminhoArquivo) {
        List<Aluno> listaCarregada = (List<Aluno>) SerializacaoService.carregarObjeto(caminhoArquivo);
        if (listaCarregada != null) {
            listaAlunos = listaCarregada;
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
