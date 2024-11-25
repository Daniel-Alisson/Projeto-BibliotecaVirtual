package com.sistema.projetobibliotecavirtual.models;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String matricula;
    private String turma;

    // POR ENQUANTO A LISTA DE ALUNOS SERÁ ARMAZENADA EM ARRAYLIST
    private static List<Aluno> listaAlunos = new ArrayList<>();

    // CONSTRUTOR
    public Aluno(String nome, String cpf, String email, String telefone, String matricula, String turma) {
        super(nome, cpf, email, telefone);
        this.matricula = matricula;
        this.turma = turma;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    // MÉTODO CADASTRAR ALUNO
    public static int cadastrarAluno(Aluno aluno) {
        for(Aluno alunoNovo : listaAlunos) {
            if(alunoNovo.getCpf().equals(aluno.getCpf())) {
                return 1;
            } if(alunoNovo.getEmail().equals(aluno.getEmail())) {
                return 2;
            } if(alunoNovo.getMatricula().equals(aluno.getMatricula())) {
                return 3;
            }
        }
        listaAlunos.add(aluno);
        return 0;
    }
}
