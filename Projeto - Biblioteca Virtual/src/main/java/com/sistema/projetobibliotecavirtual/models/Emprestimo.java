package com.sistema.projetobibliotecavirtual.models;

import java.time.LocalDate;

public class Emprestimo {
    private int codigo;
    private Aluno aluno;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(int codigo, Aluno aluno, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.codigo = codigo;
        this.aluno = aluno;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    // ADICIONAR O METODO DE EMPRESTIMO AQ TAMBÉM

    @Override
    public String toString() {
        return "EMPRESTIMO - " + codigo + "\n\nCodigo: " + codigo + "\n Aluno: " + aluno.getNome() +
                "\nNº de matricula: " + aluno.getMatricula() + "\nLivro: " + livro.getTitulo() +
                "\nData de Emprestimo: " + dataEmprestimo + "\n Data de Devolução: " + (dataDevolucao != null ? dataDevolucao : "Ainda não foi devolvido");
    }
}
