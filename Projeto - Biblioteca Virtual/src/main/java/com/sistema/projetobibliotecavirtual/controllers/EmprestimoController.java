package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Aluno;
import com.sistema.projetobibliotecavirtual.models.Emprestimo;
import com.sistema.projetobibliotecavirtual.models.Livro;
import com.sistema.projetobibliotecavirtual.services.LogService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

public class EmprestimoController extends TrocarTelasController {
    @FXML
    private ComboBox<Aluno> comboAlunos;
    @FXML
    private TextField campoIdLivro;
    @FXML
    private DatePicker campoDataEmprestimo;
    @FXML
    private DatePicker campoDataDevolucao;
    @FXML
    private ImageView imagemCapa;
    @FXML
    private Label tituloLivro;
    @FXML
    private Label alerta;

    private Livro livroSelecionado;

    // INICIALIZAR A LISTA DE ALUNOS NA COMBOBOX
    @FXML
    private void initialize() {
        ObservableList<Aluno> alunos = FXCollections.observableArrayList(Aluno.getListaAlunos());
        comboAlunos.setItems(alunos);
    }

    // METODO PARA BUSCAR LIVROS POR ID PARA CARREGAR IMAGEM
    @FXML
    private void buscarLivro() {
        String idLivro = campoIdLivro.getText().trim();
        if(idLivro.isEmpty()) {
            alerta.setText("Por favor, insira um ID válido.");
            return;
        }

        livroSelecionado = buscarLivroPorId(idLivro);
        if(livroSelecionado != null) {
            tituloLivro.setText(livroSelecionado.getTitulo());
            imagemCapa.setImage(new Image("file:///" + livroSelecionado.getCapa()));
            alerta.setText("Livro encontrado");
        } else {
            alerta.setText("Livro não encontrado");
        }
    }

    // METODO PARA REALIZAR EMPRESTIMO
    @FXML
    private void realizarEmprestimo() {
        try {
            if(livroSelecionado == null) {
                alerta.setText("Selecione um livro antes de realizar o empréstimo.");
                return;
            }

            Aluno alunoSelecionado = comboAlunos.getValue();

            if(alunoSelecionado == null) {
                alerta.setText("Selecione um aluno.");
                return;
            }

            LocalDate dataEmprestimo = campoDataEmprestimo.getValue();
            LocalDate dataDevolucao = campoDataDevolucao.getValue();

            if(dataEmprestimo == null || dataDevolucao == null) {
                alerta.setText("Preencha as datas de empréstimo e devolução.");
                return;
            }

            Emprestimo emprestimo = new Emprestimo(gerarCodigoEmprestimo(), alunoSelecionado, livroSelecionado, dataEmprestimo, dataDevolucao);
            Emprestimo.getEmprestimosAtivos().add(emprestimo);
            Livro.getListaLivros().remove(livroSelecionado);
            alerta.setText("Empréstimo realizado com sucesso!");
            Emprestimo.salvarListaEmprestimoAtivos("emprestimosAtivos.ser");
            Livro.salvarListaLivros("livros.ser");
            limparCampos();
        } catch (Exception e) {
            alerta.setText("Erro ao realizar o empréstimo.");
        }
    }

    // METODO PARA BUSCAR LIVRO POR ID PADRAO
    private Livro buscarLivroPorId(String id) {
        for(Livro livro : Livro.getListaLivros()) {
            if(String.valueOf(livro.getId()).equals(id)) {
                return livro;
            }
        }
        return null;
    }

    // GERAR CODIGO SIMPLES
    private int gerarCodigoEmprestimo() {
        return Emprestimo.getEmprestimosAtivos().size() + 1;
    }

    // METODO PARA LIMPAR CAMPOS
    @FXML
    private void limparCampos() {
        comboAlunos.getSelectionModel().clearSelection();
        campoIdLivro.clear();
        campoDataEmprestimo.setValue(null);
        campoDataDevolucao.setValue(null);
        imagemCapa.setImage(null);
        tituloLivro.setText("");
        alerta.setText("");
    }
}
