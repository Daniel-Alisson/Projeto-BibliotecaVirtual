package com.sistema.projetobibliotecavirtual.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {
    private int codigo;
    private Aluno aluno;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    private static List<Emprestimo> emprestimosAtivos = new ArrayList<>();

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

    public static boolean registrarDevolucao(int codigo) {
        Emprestimo emprestimoEncontrado = null;

        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.getCodigo() == codigo) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado != null) {
            Livro livro = emprestimoEncontrado.getLivro();
            livro.setEstoque(livro.getEstoque() + 1);
            emprestimosAtivos.remove(emprestimoEncontrado);
            System.out.println("Devolução registrada com sucesso.");
            return true;
        }

        System.out.println("Empréstimo não encontrado.");
        return false;
    }

    public static List<Emprestimo> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    @Override
    public String toString() {
        return "EMPRÉSTIMO - " + aluno.getNome() + "\n\nCódigo: " + codigo +
                "\nLivro: " + livro.getTitulo() + "\nData Empréstimo: " + dataEmprestimo +
                "\nData Devolução: " + dataDevolucao;
    }
}
