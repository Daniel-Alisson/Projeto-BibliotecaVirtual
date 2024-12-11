package com.sistema.projetobibliotecavirtual;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import com.sistema.projetobibliotecavirtual.models.Aluno;
import com.sistema.projetobibliotecavirtual.models.Emprestimo;
import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.sistema.projetobibliotecavirtual.App.class.getResource("AdminLoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1536, 816);
        stage.setTitle("Biblioteca Virtual");
        carregarDados();
        stage.setScene(scene);
        stage.show();
        // RESOLUCAO DO MEU NOTEBOOK É 1536.0 x 816.0
        // 1- PESQUISAR SOBRE OS BANCOS E PARA TRANSFORMAR EM .exe
    }

    public static void main(String[] args) {
        launch();
    }

    public void carregarDados() {
        Administrador.carregarListaAdministradores("administradores.ser");
        Aluno.carregarListaAlunos("alunos.ser");
        Livro.carregarListaLivros("livros.ser");
        Emprestimo.carregarListaEmprestimosAtivos("emprestimosAtivos.ser");
    }
}
