package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Emprestimo;
import com.sistema.projetobibliotecavirtual.models.Livro;
import com.sistema.projetobibliotecavirtual.services.LogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.format.DateTimeFormatter;

public class DevolucaoController extends TrocarTelasController {
    @FXML
    private TextField campoBuscaId;
    @FXML
    private TableView<Emprestimo> tabelaEmprestimos;
    @FXML
    private TableColumn<Emprestimo, Integer> colunaCodigo;
    @FXML
    private TableColumn<Emprestimo, String> colunaAluno;
    @FXML
    private TableColumn<Emprestimo, String> colunaLivro;
    @FXML
    private TableColumn<Emprestimo, String> colunaDataEmprestimo;
    @FXML
    private TableColumn<Emprestimo, String> colunaDataDevolucao;
    @FXML
    private Label alerta;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // ATUALIZA OS DADOS DA TABELA AO ENTRAR NA TELA
    @FXML
    private void initialize() {
        carregarTabelaEmprestimos();
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        colunaLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        colunaDataEmprestimo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataEmprestimo().format(formato)));
        colunaDataDevolucao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataDevolucao().format(formato)));
        colunaCodigo.setStyle("-fx-alignment: CENTER;");
        colunaAluno.setStyle("-fx-alignment: CENTER;");
        colunaLivro.setStyle("-fx-alignment: CENTER;");
        colunaDataDevolucao.setStyle("-fx-alignment: CENTER;");
        colunaDataEmprestimo.setStyle("-fx-alignment: CENTER;");
    }

    // CARREGA AS TABELAS
    private void carregarTabelaEmprestimos() {
        tabelaEmprestimos.getItems().clear();
        tabelaEmprestimos.getItems().addAll(Emprestimo.getEmprestimosAtivos());
    }

    // METODO PARA BUSCAR EMPRESTIMO POR ID
    @FXML
    private void buscarEmprestimoPorId() {
        String idBusca = campoBuscaId.getText().trim();
        if(idBusca.isEmpty()) {
            alerta.setText("Digite um ID para buscar.");
            return;
        }

        Emprestimo emprestimoEncontrado = null;

        for(Emprestimo emprestimo : Emprestimo.getEmprestimosAtivos()) {
            if(String.valueOf(emprestimo.getCodigo()).equals(idBusca)) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if(emprestimoEncontrado != null) {
            tabelaEmprestimos.getItems().clear();
            tabelaEmprestimos.getItems().add(emprestimoEncontrado);
        } else {
            alerta.setText("Empréstimo não encontrado.");
        }
    }

    // METODO PARA REGISTRAR A DEVOLUÇÃO DE UM LIVRO
    @FXML
    private void registrarDevolucao() {
        Emprestimo emprestimoSelecionado = tabelaEmprestimos.getSelectionModel().getSelectedItem();
        if(emprestimoSelecionado == null) {
            alerta.setText("Selecione um empréstimo para devolução.");
            return;
        }

        Emprestimo.getEmprestimosAtivos().remove(emprestimoSelecionado);
        Livro livroDevolvido = emprestimoSelecionado.getLivro();
        Livro.getListaLivros().add(livroDevolvido);
        alerta.setText("Devolução realizada com sucesso!");
        Emprestimo.salvarListaEmprestimoAtivos("emprestimosAtivos.ser");
        Livro.salvarListaLivros("livros.ser");
        carregarTabelaEmprestimos();
    }
}
