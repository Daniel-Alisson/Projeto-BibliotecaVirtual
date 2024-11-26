package com.sistema.projetobibliotecavirtual.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private int estoque;
    private boolean disponivel;

    private static List<Livro> listaLivros = new ArrayList<>();

    public Livro(String id, String titulo, String autor, String editora, int estoque) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.estoque = estoque;
        this.disponivel = estoque > 0;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getEstoque() {
        return estoque;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
        this.disponivel = estoque > 0;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Livro> getListaLivros() {
        return listaLivros;
    }

    public static void setListaLivros(List<Livro> listaLivros) {
        Livro.listaLivros = listaLivros;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static boolean adicionarLivro(Livro livro) {
        for (Livro l : listaLivros) {
            if (l.getId().equals(livro.getId())) {
                return false;
            }
        }
        listaLivros.add(livro);
        //salvarLivrosEmArquivo();
        return true;
    }

    @Override
    public String toString() {
        return "LIVRO - " + titulo + "\n\nId: " + id + "\nAutor: " + autor +
                "\nEditora: " + editora + "\nEstoque: " + estoque +
                "\nDisponibilidade: " + disponivel;
    }
}
