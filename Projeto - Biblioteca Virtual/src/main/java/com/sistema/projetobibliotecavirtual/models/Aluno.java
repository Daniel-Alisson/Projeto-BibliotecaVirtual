package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa implements Serializable {
    private String matricula;
    private String turma;

    private static List<Aluno> listaAlunos = new ArrayList<>();

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

    public static List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public static void setListaAlunos(List<Aluno> listaAlunos) {
        Aluno.listaAlunos = listaAlunos;
    }

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
        //salvarAlunos();
        return 0;
    }

    public static void salvarAlunos() {
        try {
            SerializacaoService.salvarObjeto(listaAlunos, "alunos.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarAlunos() {
        try {
            List<Aluno> alunosCarregados = (List<Aluno>) SerializacaoService.carregarObjeto("alunos.txt");
            if (alunosCarregados != null) {
                listaAlunos = alunosCarregados;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
