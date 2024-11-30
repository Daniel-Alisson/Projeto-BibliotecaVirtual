package com.sistema.projetobibliotecavirtual.models;

import com.sistema.projetobibliotecavirtual.services.LogService;
import com.sistema.projetobibliotecavirtual.services.SerializacaoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aluno implements Serializable {
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
        salvarListaAlunos("alunos.ser");
        return 0;
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

    @Override
    public String toString() {
        return nome;
    }
}
