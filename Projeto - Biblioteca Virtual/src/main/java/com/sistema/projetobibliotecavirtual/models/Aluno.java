package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String matricula;
    private String turma;
    private String nome;
    private String telefone;

    // LISTA DE ALUNOS
    private static List<Aluno> listaAlunos = new ArrayList<>();

    public Aluno(String nome, String turma, String matricula, String telefone) {
        this.matricula = matricula;
        this.turma = turma;
        this.nome = nome;
        this.telefone = telefone;
    }

    public static List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public static void setListaAlunos(List<Aluno> listaAlunos) {
        Aluno.listaAlunos = listaAlunos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // METODO PARA CADASTRAR ALUNOS
    public int cadastrarAluno(Aluno aluno) {
        for(Aluno alunoNovo : listaAlunos) {
            if(alunoNovo.getMatricula().equals(aluno.getMatricula())) {
                return 1;
            }
        }
        listaAlunos.add(aluno);
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
