package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;

public class MenuController extends TrocarTelasController {
    @FXML
    private TextField campoBusca;
    @FXML
    private GridPane gridLivros;

    @FXML
    public void initialize() {
        listarLivros(Livro.getListaLivros());
    }

    // METODO PARA LISTAR LIVROS NO GRID
    private void listarLivros(List<Livro> livros) {
        gridLivros.getChildren().clear();
        int coluna = 0;
        int linha = 0;

        for (Livro livro : livros) {
            ImageView imagemCapa = new ImageView();
            File arquivoCapa = new File(livro.getCapa());
            if (arquivoCapa.exists()) {
                imagemCapa.setImage(new Image(arquivoCapa.toURI().toString()));
            } else {
                imagemCapa.setImage(new Image("default_capa.png")); // Imagem padrão
            }
            imagemCapa.setFitWidth(150);
            imagemCapa.setFitHeight(200);

            //
            Label titulo = new Label(livro.getTitulo());
            titulo.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            titulo.setWrapText(true);

            Label idLivro = new Label("ID: " + livro.getId()); // Exibe o ID do livro
            idLivro.setStyle("-fx-font-size: 12px; -fx-font-style: italic;");

            VBox itemLivro = new VBox(10, imagemCapa, titulo, idLivro);
            itemLivro.setStyle("-fx-alignment: center;");
            itemLivro.setSpacing(15);

            gridLivros.add(itemLivro, coluna, linha);

            coluna++;
            if (coluna == 5) { // VC PODE ALTERAR A QUANTIDADE DE LIVROS POR AQ, NESSE CASO SERÃO 5 LIVROS POR LINHA
                coluna = 0;
                linha++;
            }
        }
    }

    // METODO PARA BUSCAR LIVROS POR ID
    @FXML
    private void buscarLivro() {
        String idTexto = campoBusca.getText().trim();
        if (idTexto.isEmpty()) {
            listarLivros(Livro.getListaLivros());
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            List<Livro> livrosFiltrados = Livro.getListaLivros().stream().filter(livro -> livro.getId() == id).toList();
            if (livrosFiltrados.isEmpty()) {
                System.out.println("Nenhum livro encontrado com o ID: " + id);
            }
            listarLivros(livrosFiltrados);
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um ID válido.");
        }
    }
}