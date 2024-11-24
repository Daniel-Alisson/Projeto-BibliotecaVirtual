package com.sistema.projetobibliotecavirtual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // SO PRECISAR ESOLHER O NOME DA TELA PARA FAZER UM TESTE
        FXMLLoader fxmlLoader = new FXMLLoader(com.sistema.projetobibliotecavirtual.App.class.getResource("testeCadastro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 769);
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
