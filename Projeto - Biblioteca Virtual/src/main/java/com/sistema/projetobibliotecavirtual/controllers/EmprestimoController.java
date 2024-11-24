package com.sistema.projetobibliotecavirtual.controllers;


import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class EmprestimoController {
    @FXML
    private TextField campoBuscaLivro;
    @FXML
    private Button botaoBuscar, botaoRealizarEmprestimo;
    @FXML
    private Label textoTitulo, textoAutor, textoDisponibilidade, textoDataEmprestimo;
    @FXML
    private ComboBox<String> comboAluno;
    @FXML
    private DatePicker dataDevolucao1;

    private String livroSelecionado;

    // TENTAR BOTAR A LISTA NA CLASSE EMPRESTIMO OU LIVRO
    private ObservableList<Livro> listaLivros = FXCollections.observableArrayList();

    @FXML
    private ImageView imgLivro;

    @FXML
    public void initialize() {
        textoDataEmprestimo.setText(LocalDate.now().toString());
        dataDevolucao1.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        // TESTES
        comboAluno.getItems().addAll("Aluno 1", "Aluno 2", "Aluno 3");
        comboAluno.setPromptText("Selecione um aluno");
        listaLivros.addAll(
                new Livro("1", "teste1", "PVT LIDER", "true", 1, false, "imagens/imagem1.jpeg"),
                new Livro("2", "1984", "PJ", "", 1, true, "imagens/imagem2.jpg"),
                new Livro("3", "Teste3", "TAMECA", "true", 1, true, "imagens/imagem3.jpg")
        );
    }

    @FXML
    private void buscarLivro() {
        String busca = campoBuscaLivro.getText();
        // MELHORAR ISSO, ELE PROCULA POR ID OU TITULO, POREM TEM Q BOTAR O NOME INTEIRO
        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equalsIgnoreCase(busca) || livro.getId().equals(busca)) {
                livroSelecionado = livro.getTitulo();
                textoTitulo.setText(livro.getTitulo());
                textoAutor.setText(livro.getAutor());
                textoDisponibilidade.setText(livro.isDisponivel() ? "Disponível" : "Indisponível");
                try {
                    Image imagem = new Image(getClass().getResourceAsStream("/" + livro.getCaminhoFoto()));
                    imgLivro.setImage(imagem);
                } catch (NullPointerException e) {
                    imgLivro.setImage(null);
                    System.out.println("Imagem não encontrada: " + livro.getCaminhoFoto());
                }
                return;
            }
        }
        textoTitulo.setText("Livro não encontrado");
        textoAutor.setText("");
        textoDisponibilidade.setText("");
        imgLivro.setImage(null);
    }

    @FXML
    private void confirmarEmprestimo() {
        if (livroSelecionado == null || comboAluno.getValue() == null || dataDevolucao1.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos incompletos");
            alert.setContentText("Preencha todos os campos antes de confirmar o empréstimo.");
            alert.showAndWait();
        } else {
            String aluno = comboAluno.getValue();
            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucao = dataDevolucao1.getValue();

            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Livro: " + livroSelecionado);
            System.out.println("Aluno: " + aluno);
            System.out.println("Data de Empréstimo: " + dataEmprestimo);
            System.out.println("Data de Devolução: " + dataDevolucao);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sucesso");
            alert.setContentText("O empréstimo foi registrado com sucesso.");
            alert.showAndWait();
        }
    }
}
