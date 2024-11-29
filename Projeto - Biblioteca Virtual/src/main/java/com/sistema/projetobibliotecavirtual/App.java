package com.sistema.projetobibliotecavirtual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.sistema.projetobibliotecavirtual.App.class.getResource("telaCadastro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 769);
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
        // AINDA N ESTÁ FUNCIONAL
        // TELA: CADASTRO, LOGIN, ALUNO E LIVRO ESTÃO FUNCIONANDO
        // 1- PRECISA MELHORAR O CÓDIGO DE REALIZAR EMPRESTIMO, DEVOLUCAO E MENU PARA OS CARAS
        // 2- ADICIONAR VERIFICAÇÕES E REVISAR OS CÓDIGOS ANTERIORES
        // 3- COLOCAR SERIALIZACAO E LOG PARA SALVAR OS DADOS E PESQUISAR DPS PARA USAR ALGUM BANCO
        //                                      (SE DER TEMPO)
        // 4- ADICIONAR OUTRAS FUNCIONALIDADES COMO LISTAR, REMOVER E ATUALIZAR, E QUEM SABE RELATORIOS
    }

    public static void main(String[] args) {
        launch();
    }
}
