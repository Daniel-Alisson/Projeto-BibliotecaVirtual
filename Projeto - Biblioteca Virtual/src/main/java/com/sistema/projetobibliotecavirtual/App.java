package com.sistema.projetobibliotecavirtual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // NO CÓDIGO ABAIXO TEM O CAMPO PARA TROCAR A TELA - "testeCadastro.fxml" VC PODE TROCAR POR QUALQUER OUTRA TELA
        FXMLLoader fxmlLoader = new FXMLLoader(com.sistema.projetobibliotecavirtual.App.class.getResource("testeLivro.fxml"));
        // PROPORÇÕES DA TELA, VCS QUE ESCOLHEM
        Scene scene = new Scene(fxmlLoader.load(), 1024, 769);
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
        // REVISAR A FUNCIONALIDADE DE CADASTRO DE LIVROS
    }

    public static void main(String[] args) {
        launch();
    }
}
