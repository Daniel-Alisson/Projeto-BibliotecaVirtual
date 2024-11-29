package com.sistema.projetobibliotecavirtual.models;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int estoque;
    private String genero;

    // LISTA DE LIVROS
    private static List<Livro> listaLivros = new ArrayList<>();

    public Livro(int id, String titulo, String autor, String editora, int estoque, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.estoque = estoque;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public static List<Livro> getListaLivros() {
        return listaLivros;
    }

    public static void setListaLivros(List<Livro> listaLivros) {
        Livro.listaLivros = listaLivros;
    }

    // METODO PARA ADICIONAR LIVRS NA LISTA
    public int adicionarLivro(Livro livro) {
        for(Livro livroNovo : listaLivros) {
            if(livroNovo.getId() == livro.getId()) {
                return 1;
            } if(livroNovo.getTitulo().equals(livro.titulo)) {
                return 2;
            }
        }
        listaLivros.add(livro);
        return 0;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "autor='" + autor + '\'' +
                ", id=" + id +
                ", titulo='" + titulo + '\'' +
                ", editora='" + editora + '\'' +
                ", estoque=" + estoque +
                ", genero='" + genero + '\'' +
                '}';
    }
}
