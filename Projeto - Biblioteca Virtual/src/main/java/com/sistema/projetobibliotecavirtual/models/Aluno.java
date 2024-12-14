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

    public Aluno(String nome, String turma, String matricula, String telefone) {
        this.matricula = matricula;
        this.turma = turma;
        this.nome = nome;
        this.telefone = telefone;
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

    @Override
    public String toString() {
        return nome;
    }
}
