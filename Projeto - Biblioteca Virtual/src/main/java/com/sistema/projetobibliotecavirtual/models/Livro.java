package com.sistema.projetobibliotecavirtual.models;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private int estoque;
    private boolean disponivel;
    private String caminhoFoto;

    private static List<Livro> listaLivros = new ArrayList<>();

    public Livro(String id, String titulo, String autor, String editora, int estoque, boolean disponivel, String caminhoFoto) {
        this.autor = autor;
        this.disponivel = disponivel;
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.estoque = estoque;
        this.caminhoFoto = caminhoFoto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    // ADICIONAR O METODO DE CADASTRAR AQ NA CLASSE

    @Override
    public String toString() {
        return "LIVRO - " + titulo + "\n\nId: " + id + "\nAutor: " + autor +
                "\nEditora: " + editora + "\nEstoque: " + estoque +
                "\nDisponibilidade: " + disponivel;
    }
}
