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
            String id = campoId.getText().trim();
            String titulo = campoTitulo.getText().trim();
            String autor = campoAutor.getText().trim();
            String editora = campoEditora.getText().trim();
            String estoqueTexto = campoEstoque.getText().trim();

            if (id.isEmpty() || titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || estoqueTexto.isEmpty() || caminhoImagem == null) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Preencha todos os campos corretamente.");
                return;
            }

            if (!estoqueTexto.matches("\\d+")) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Estoque' deve conter apenas números.");
                return;
            }

            int estoque = Integer.parseInt(estoqueTexto);
            boolean disponivel = campoDisponivel.isSelected();

            Livro livro = new Livro(id, titulo, autor, editora, estoque, disponivel, caminhoImagem);
            if (Livro.adicionarLivro(livro)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Livro cadastrado com sucesso!");
                limparCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Já existe um livro cadastrado com esse ID.");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao cadastrar o livro.");
        }
    }

    private void limparCampos() {
        campoId.clear();
        campoTitulo.clear();
        campoAutor.clear();
        campoEditora.clear();
        campoEstoque.clear();
        campoDisponivel.setSelected(false);
        foto.setImage(null);
        caminhoImagem = null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
