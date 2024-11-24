package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class LivroController extends TrocarTelasController {
    @FXML
    TextField campoId, campoTitulo, campoAutor, campoEditora, campoEstoque;
    @FXML
    private CheckBox campoDisponivel;
    @FXML
    private ImageView foto;
    @FXML
    private Button carregar, salvar;

    private String caminhoImagem;

    @FXML
    private void escolherImagem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        File arquivo = fileChooser.showOpenDialog(null);
        if(arquivo != null) {
            caminhoImagem = arquivo.getAbsolutePath();
            Image imagem = new Image(arquivo.toURI().toString());
            foto.setImage(imagem);
        }
    }

    @FXML
    private void salvarLivro() {
        try {
            String id = campoId.getText();
            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            String editora = campoEditora.getText();
            int estoque = Integer.parseInt(campoEstoque.getText());
            boolean disponivel = campoDisponivel.isSelected();

            Livro livro = new Livro(id, titulo, autor, editora, estoque, disponivel, caminhoImagem);

            System.out.println("Livro cadastrado com sucesso: " + livro.getTitulo());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erro de Validação");
            alert.setContentText("Por favor, preencha todos os campos corretamente.");
            alert.showAndWait();
        }
    }
}
