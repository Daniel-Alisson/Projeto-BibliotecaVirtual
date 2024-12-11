package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import com.sistema.projetobibliotecavirtual.models.Livro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.List;

public class PerfilController extends TrocarTelasController {
    // VARIAVEIS DA BARRA LATERAL
    @FXML
    private VBox barraLateral, conteudoBarra;
    private boolean barraExpandida = true;
    @FXML
    private Button botaoPrincipal;

    @FXML
    private GridPane gridAdmins;

    @FXML
    public void initialize() {
        barraLateral.setPrefWidth(200);
        conteudoBarra.setVisible(true);
        conteudoBarra.setManaged(true);
        barraExpandida = true;
        botaoPrincipal.setText("☰");
        if(gridAdmins == null) {
            System.out.println("gridAdmins é nulo!");
        } else {
            System.out.println("gridAdmins inicializado com sucesso.");
        }
        //listarAdministradores(Administrador.getListaAdministradores());
        gridAdmins.setHgap(100);
        gridAdmins.setVgap(10);
        gridAdmins.setStyle("-fx-padding: 10; -fx-alignment: top-center;");
    }

    private void listarAdministradores(List<Administrador> administradores) {
        gridAdmins.getChildren().clear();
        int coluna = 0;
        int linha = 0;

        for(Administrador administrador : administradores) {
            ImageView imagemCapa = new ImageView();
            imagemCapa.setImage(new Image(getClass().getResource("/imagens/aranha.png").toExternalForm()));
            imagemCapa.setFitWidth(150);
            imagemCapa.setFitHeight(190);

            Label nome = new Label("Nome: " + administrador.getNome());
            nome.setStyle("-fx-font-size: 14px; -fx-text-fill: white; -fx-font-weight: bold;");
            nome.setWrapText(true);

            Label idCpf = new Label("Cpf: " + administrador.getCpf());
            idCpf.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-font-style: italic;");

            VBox itemAdmin = new VBox(5, imagemCapa, nome, idCpf);
            itemAdmin.setStyle("-fx-alignment: center; -fx-background-color: #001A66; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-border-color: black; -fx-padding: 10;");
            itemAdmin.setSpacing(15);

            gridAdmins.add(itemAdmin, coluna, linha);

            coluna++;
            if (coluna == 5) { // VC PODE ALTERAR A QUANTIDADE DE LIVROS POR AQ, NESSE CASO SERÃO 5 LIVROS POR LINHA
                coluna = 0;
                linha++;
            }
        }
    }

    @FXML
    public void alternarBarra() {
        if (barraExpandida) {
            barraLateral.setPrefWidth(50);
            botaoPrincipal.setText("☰");
            conteudoBarra.setVisible(false);
            conteudoBarra.setManaged(false);
        } else {
            barraLateral.setPrefWidth(200);
            botaoPrincipal.setText("☰");
            conteudoBarra.setVisible(true);
            conteudoBarra.setManaged(true);
        }
        barraExpandida = !barraExpandida;
    }
}
