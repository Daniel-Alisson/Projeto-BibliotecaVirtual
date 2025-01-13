package com.sistema.projetobibliotecavirtual;

import com.sistema.projetobibliotecavirtual.controllers.AdministradorController;
import com.sistema.projetobibliotecavirtual.controllers.AlunoController;
import com.sistema.projetobibliotecavirtual.models.Administrador;
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
        carregarDados();
        FXMLLoader fxmlLoader = new FXMLLoader(com.sistema.projetobibliotecavirtual.App.class.getResource("/com/sistema/projetobibliotecavirtual/view/AdminLoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1536, 816);
        stage.setTitle("Biblioteca Virtual");
        stage.setScene(scene);
        stage.show();
        for (Administrador admin : AdministradorController.getListaAdministradores()) {
            if (admin.isStatus()) {
                admin.setUsuarioLogado(admin);
                AdministradorController controlador = fxmlLoader.getController();
                controlador.menuLivrosDireto(stage);
                return;
            }
        }
        // RESOLUCAO DO MEU NOTEBOOK É 1536.0 x 816.0
    }

    public void carregarDados() {
        AdministradorController.carregarListaAdministradores("administradores.ser");
        AlunoController.carregarListaAlunos("alunos.ser");
        Livro.carregarListaLivros("livros.ser");
        Emprestimo.carregarListaEmprestimosAtivos("emprestimosAtivos.ser");
    }
}
