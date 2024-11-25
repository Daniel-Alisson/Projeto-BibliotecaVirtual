package com.sistema.projetobibliotecavirtual.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
    // ESSA VAI SER A CLASSE PAI DAS OUTRAS CLASSES CONTROLADORAS, REDUZIR UM POUCO A REPETICAO DE METODOS
public class TrocarTelasController {
    // TODOS OS METODOS E ATRIBUTOS DEVEM TER ESSA OBSERVACAO @FXML PARA SINALIZAR QUE SÃO RELATIVOS DO JAVAFX
    // OS METODOS DESSA CLASSE SÃO DE MUDANCA DE TELA E TALVEZ PARA CONFIGURAR A BARRA LATERAL, VOU TESTAR DPS

    @FXML
    public void mudarTelaCadastro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeCadastro.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarTelalogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeLogin.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarTelaAluno(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeAluno.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarTelaLivro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeLivro.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarTelaMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeMenu.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mudarTelaEmprestimo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/projetobibliotecavirtual/testeEmprestimo.fxml"));
            Parent newScreenRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(newScreenRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
