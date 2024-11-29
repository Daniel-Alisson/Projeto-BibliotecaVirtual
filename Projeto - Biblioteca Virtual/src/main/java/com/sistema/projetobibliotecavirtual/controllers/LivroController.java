package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LivroController extends TrocarTelasController {
    // CAMPOS DO CADASTRO
    @FXML
    private TextField campoId, campoTitulo, campoAutor, campoEditora, campoEstoque, campoGenero;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS CADASTRADOS
    @FXML
    Label alerta;

    // METODO PARA CADASTRAR LIVROS
    @FXML
    private void cadastrarLivro() {
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            String titulo = campoTitulo.getText().trim();
            String autor = campoAutor.getText().trim();
            String editora = campoEditora.getText().trim();
            int estoque = Integer.parseInt(campoEstoque.getText().trim());
            String genero = campoGenero.getText().trim();

            if(titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || campoId.getText().trim().isEmpty() || campoEstoque.getText().trim().isEmpty()) {
                alerta.setText("Preencha todos os campos");
                return;
            }

            Livro livroNovo = new Livro(id, titulo, autor, editora, estoque, genero);

            int op = livroNovo.adicionarLivro(livroNovo);

            switch(op) {
                case 0:
                    alerta.setText("Livro cadastrado com sucesso!");
                    limparCampos();
                    break;
                case 1:
                    alerta.setText("Já existe um livro cadastrado com esse ID");
                    break;
                case 2:
                    alerta.setText("Já existe um livro cadastrado com esse Titulo");
                    // adicinar funcionalidade para adicionar estoque
                    break;
            }
        } catch(Exception e) {
            alerta.setText("Ocorreu um erro ao cadastrar o livro.");
        }
    }

    // METODO PARA LIMPAR CAMPOS
    private void limparCampos() {
        campoId.clear();
        campoTitulo.clear();
        campoAutor.clear();
        campoEditora.clear();
        campoEstoque.clear();
    }
}
