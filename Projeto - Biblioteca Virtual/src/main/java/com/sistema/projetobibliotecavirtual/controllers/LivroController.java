package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class LivroController extends TrocarTelasController {
    @FXML
    private TextField campoId, campoTitulo, campoAutor, campoEditora, campoEstoque;

    @FXML
    private void salvarLivro() {
        try {
            String id = campoId.getText().trim();
            String titulo = campoTitulo.getText().trim();
            String autor = campoAutor.getText().trim();
            String editora = campoEditora.getText().trim();
            String estoqueTexto = campoEstoque.getText().trim();

            if(id.isEmpty() || titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || estoqueTexto.isEmpty()) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Preencha todos os campos corretamente.");
                return;
            }

            if(!estoqueTexto.matches("\\d+")) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "O campo 'Estoque' deve conter apenas números.");
                return;
            }

            int estoque = Integer.parseInt(estoqueTexto);
            Livro livro = new Livro(id, titulo, autor, editora, estoque);

            boolean livroExistente = false;
            for (Livro livro2 : Livro.getListaLivros()) {
                if (livro2.getId().equals(id) || livro2.getTitulo().equals(titulo)) {
                    livroExistente = true;
                    break;
                }
            }
            if(livroExistente) {
                Alert alertConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                alertConfirmacao.setTitle("Livro Existente");
                alertConfirmacao.setHeaderText("Livro com o mesmo ID ou título já existe!");
                alertConfirmacao.setContentText("Deseja adicionar mais exemplares deste livro ao estoque?");

                alertConfirmacao.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        for (Livro livro2 : Livro.getListaLivros()) {
                            if (livro2.getId().equals(id) || livro2.getTitulo().equals(titulo)) {
                                int novoEstoque = livro2.getEstoque() + estoque;
                                livro2.setEstoque(novoEstoque);
                                mostrarAlerta(Alert.AlertType.INFORMATION, "Estoque Atualizado",
                                        "Estoque do livro '" + livro2.getTitulo() + "' foi atualizado!");
                                break;
                            }
                        }
                    } else {
                        mostrarAlerta(Alert.AlertType.INFORMATION, "Operação Cancelada", "Nenhum livro foi adicionado.");
                    }
                });
            } else {
                if (Livro.adicionarLivro(livro)) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Livro cadastrado com sucesso!");
                    limparCampos();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao adicionar o livro.");
                }
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
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
