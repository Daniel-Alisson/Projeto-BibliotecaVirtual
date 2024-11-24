package com.sistema.projetobibliotecavirtual.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrocarTelasController {
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
}
