package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaController {

    @FXML
    private TextField campoBusca;
    @FXML
    private TableView<Livro> tabelaLivros;
    @FXML
    private TableColumn<Livro, String> colunaId;
    @FXML
    private TableColumn<Livro, String> colunaTitulo;
    @FXML
    private TableColumn<Livro, String> colunaAutor;
    @FXML
    private TableColumn<Livro, String> colunaDisponibilidade;
    @FXML
    private Pagination paginacao;
    @FXML
    private Button botaoDetalhes;


    private ObservableList<Livro> listaLivros = FXCollections.observableArrayList();
    private static final int ITENS_POR_PAGINA = 10;

    @FXML
    public void initialize() {
        // TESTES
        listaLivros.addAll(
                new Livro("1", "PJ", "TESTE1", "true", 1),
                new Livro("2", "PVT LIDER", "TESTE2", "true", 1),
                new Livro("3", "TAMECA", "TESTE3", "true", 1)
        );

        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colunaDisponibilidade.setCellValueFactory(livro ->
                new SimpleStringProperty(livro.getValue().isDisponivel() ? "Disponível" : "Indisponível")
        );
        configurarPaginacao();
        configurarBusca();
    }

    private void configurarPaginacao() {
        int totalPaginas = (int) Math.ceil((double) listaLivros.size() / ITENS_POR_PAGINA);
        paginacao.setPageCount(totalPaginas);

        paginacao.setPageFactory(this::criarPagina);
    }

    private TableView<Livro> criarPagina(int pagina) {
        int inicio = pagina * ITENS_POR_PAGINA;
        int fim = Math.min(inicio + ITENS_POR_PAGINA, listaLivros.size());
        tabelaLivros.setItems(FXCollections.observableArrayList(listaLivros.subList(inicio, fim)));
        return tabelaLivros;
    }

    private void configurarBusca() {
        FilteredList<Livro> livrosFiltrados = new FilteredList<>(listaLivros, livro -> true);

        campoBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            livrosFiltrados.setPredicate(livro -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String buscaMinuscula = newValue.toLowerCase();
                return livro.getTitulo().toLowerCase().contains(buscaMinuscula) ||
                        livro.getAutor().toLowerCase().contains(buscaMinuscula);
            });

            configurarPaginacao();
        });
    }

    @FXML
    private void verDetalhesLivro() {
        Livro livroSelecionado = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado != null) {
            Alert detalhes = new Alert(Alert.AlertType.INFORMATION);
            detalhes.setHeaderText("Detalhes do Livro");
            detalhes.setContentText("Título: " + livroSelecionado.getTitulo() +
                    "\nAutor: " + livroSelecionado.getAutor() +
                    "\nDisponível: " + (livroSelecionado.isDisponivel() ? "Sim" : "Não"));
            detalhes.showAndWait();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Nenhum livro selecionado");
            alerta.setContentText("Por favor, selecione um livro antes de clicar no botão.");
            alerta.showAndWait();
        }
    }
}
