package com.sistema.projetobibliotecavirtual.controllers;

import com.sistema.projetobibliotecavirtual.models.Administrador;
import com.sistema.projetobibliotecavirtual.models.Livro;
import com.sistema.projetobibliotecavirtual.services.SerializacaoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AdministradorController extends TrocarTelasController {
    // CAMPOS
    @FXML
    TextField campoNome, campoCpf, campoEmail, campoSenha, campoSenhaNovamente;
    @FXML
    TextField campoNovoNome, campoNovoCpf, campoNovoEmail, campoNovaSenha, campoNovaSenhaNovamente, campoBuscaCpf;
    // EXIBE A MENSAGEM DE ALERTA, OU DE DIVERGENCIA DOS DADOS
    @FXML
    Label alerta;
    @FXML
    Button botaoEditar, botaoSelecao;
    @FXML
    GridPane gridAdmins;

    private static List<Administrador> listaAdministradores = new ArrayList<>();
    Administrador logado = null;

    @FXML
    private void initialize() {
        if (gridAdmins != null) {
            listarAdministradores(listaAdministradores);
            gridAdmins.setHgap(100);
            gridAdmins.setVgap(10);
            gridAdmins.setStyle("-fx-padding: 10; -fx-alignment: top-center;");
        } if (botaoEditar != null) {
            botaoEditar.setVisible(false);
        }

        for (Administrador admin : listaAdministradores) {
            if (admin.getUsuarioLogado() != null) {
                logado = admin.getUsuarioLogado();
                break;
            }
        } if (logado != null) {
            campoNovoNome.setText(logado.getNome());
            campoNovoCpf.setText(logado.getCpf());
            campoNovoEmail.setText(logado.getEmail());
            campoNovaSenha.setText(logado.getSenha());
            campoNovaSenhaNovamente.setText(logado.getSenha());
            campoNovoNome.setDisable(true);
            campoNovoEmail.setDisable(true);
            campoNovoCpf.setDisable(true);
            campoNovaSenha.setDisable(true);
            campoNovaSenhaNovamente.setDisable(true);
            System.out.println("ESCONDIDO DE INICIO");
        }
    }

    // METODO PARA CADASTRAR ADMINISTRADORES
    @FXML
    private void cadastrarAdministrador(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();
        String senhaNovamente = campoSenhaNovamente.getText().trim();

        if(nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if(!cpf.matches("\\d{11}")) {
            alerta.setText("CPF inválido!");
            return;
        } if(!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            alerta.setText("Email inválido!");
            return;
        } if(!senha.equals(senhaNovamente)) {
            alerta.setText("Senhas não correspondem");
            return;
        }

        for(Administrador admin : listaAdministradores) {
            if(admin.getCpf().equals(cpf)) {
                alerta.setText("Já existe um administrador cadastrado com esse CPF");
                return;
            } if(admin.getEmail().equals(email)) {
                alerta.setText("Já existe um administrador cadastrado com esse E-mail");
                return;
            }
        }
        Administrador adminNovo = new Administrador(nome, cpf, email, senha);
        listaAdministradores.add(adminNovo);
        alerta.setText("Administrador cadastrado com sucesso!");
        limparCampos();
        salvarListaAdministradores("administradores.ser");
    }

    // METODO PARA LIMPAR CAMPOS
    @FXML
    private void limparCampos() {
        if (campoNome != null) {
            campoNome.clear();
        } if (campoCpf != null) {
            campoCpf.clear();
        } if (campoEmail != null) {
            campoEmail.clear();
        } if (campoSenha != null) {
            campoSenha.clear();
        } if (campoSenhaNovamente != null) {
            campoSenhaNovamente.clear();
        }
    }

    // METODO PARA INICIAR O LOGIN DO ADMIN
    @FXML
    private void loginAdministrador(ActionEvent event) {
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();

        if(email.isEmpty() || senha.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        }

        for (Administrador admin : listaAdministradores) {
            if (admin.getEmail().equals(email) && admin.getSenha().equals(senha)) {
                admin.setUsuarioLogado(admin);
                alerta.setText("Login realizado com sucesso!");
                menuLivros(event);
            }
        }
        alerta.setText("Email ou senha incorretos!");
    }

    @FXML
    private void editarAdministrador(ActionEvent event) {
        String novoNome = campoNovoNome.getText().trim();
        String novoEmail = campoNovoEmail.getText().trim();
        String novaSenha = campoNovaSenha.getText().trim();
        String novaSenhaNovamente = campoNovaSenhaNovamente.getText().trim();

        if (novoNome.isEmpty() || novoEmail.isEmpty() || novaSenha.isEmpty() || novaSenhaNovamente.isEmpty()) {
            alerta.setText("Preencha todos os campos");
            return;
        } if (!novoEmail.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            alerta.setText("E-mail inválido");
            return;
        } if (!novaSenha.equals(novaSenhaNovamente)) {
            alerta.setText("As senhas não correspondem");
            return;
        }

        Administrador logado = null;
        for (Administrador admin : listaAdministradores) {
            if (admin.getUsuarioLogado() != null) {
                logado = admin;
                break;
            }
        }

        for (Administrador admin : listaAdministradores) {
            if (!admin.equals(logado) && admin.getEmail().equals(novoEmail)) {
                alerta.setText("Este e-mail já está em uso por outro administrador");
                return;
            }
        }
        logado.setNome(novoNome);
        logado.setEmail(novoEmail);
        logado.setSenha(novaSenha);
        salvarListaAdministradores("administradores.ser");
        alerta.setText("Dados atualizados com sucesso!");
        abrirEdicao(event);
        limparCampos();
    }

    @FXML
    private void abrirEdicao(ActionEvent event) {
        if (!campoNovoNome.isDisable()) {
            campoNovoNome.setText(logado.getNome());
            campoNovoCpf.setText(logado.getCpf());
            campoNovoEmail.setText(logado.getEmail());
            campoNovaSenha.setText(logado.getSenha());
            campoNovaSenhaNovamente.setText(logado.getSenha());
            campoNovoNome.setDisable(true);
            campoNovoEmail.setDisable(true);
            campoNovoCpf.setDisable(true);
            campoNovaSenha.setDisable(true);
            campoNovaSenhaNovamente.setDisable(true);
            //System.out.println("teste aberto");
            botaoEditar.setVisible(false);
            botaoSelecao.setText("Editar");
        } else {
            campoNovoNome.setText(logado.getNome());
            campoNovoCpf.setText(logado.getCpf());
            campoNovoEmail.setText(logado.getEmail());
            campoNovaSenha.setText(logado.getSenha());
            campoNovaSenhaNovamente.setText(logado.getSenha());
            campoNovoNome.setDisable(false);
            campoNovoEmail.setDisable(false);
            campoNovoCpf.setDisable(true);
            campoNovaSenha.setDisable(false);
            campoNovaSenhaNovamente.setDisable(false);
            //System.out.println("teste fechado");
            botaoSelecao.setText("Cancelar");
            botaoEditar.setVisible(true);
        }
    }

    @FXML
    private void listarAdministradores(List<Administrador> administradores) {
        gridAdmins.getChildren().clear();
        int coluna = 0;
        int linha = 0;

        for(Administrador administrador : administradores) {
            ImageView imagemCapa = new ImageView();
            imagemCapa.setImage(new Image(getClass().getResource("/imagens/Perfil.png").toExternalForm()));
            imagemCapa.setFitWidth(190);
            imagemCapa.setFitHeight(190);

            Label nome = new Label("Nome: " + administrador.getNome());
            nome.setStyle("-fx-font-size: 14px; -fx-text-fill: white; -fx-font-weight: bold;");
            nome.setWrapText(true);

            Label cpf = new Label("Cpf: " + administrador.getCpf());
            cpf.setStyle("-fx-font-size: 12px; -fx-text-fill: white; -fx-font-style: italic;");

            Button remover = new Button("Remover");
            remover.setStyle("-fx-font-size: 14px; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #FF3B3B; -fx-cursor:hand");
            remover.setOnAction(event -> removerAdministrador(administrador));
            VBox itemAdmin = new VBox(5, imagemCapa, nome, cpf, remover);
            itemAdmin.setStyle("-fx-alignment: center; -fx-background-color: #001A66; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-border-color: black; -fx-padding: 10;");
            itemAdmin.setSpacing(15);

            gridAdmins.add(itemAdmin, coluna, linha);

            coluna++;
            if (coluna == 5) {
                coluna = 0;
                linha++;
            }
        }
    }

    @FXML
    private void removerAdministrador(Administrador administrador) {
        if (logado == administrador) {
            alerta.setText("Não pode remover o Administrador logado");
            return;
        }
        listaAdministradores.remove(administrador);
        salvarListaAdministradores("administradores.ser");
        listarAdministradores(listaAdministradores);
        alerta.setText("Administrador removido com sucesso!");
    }

    @FXML
    private void buscaAdmin() {
        String cpf = campoBuscaCpf.getText().trim();

        if (cpf.isEmpty()) {
            listarAdministradores(listaAdministradores);
            return;
        }

        List<Administrador> adminsFiltrados = listaAdministradores.stream().filter(admin -> admin.getCpf().equals(cpf)).toList();
        if (adminsFiltrados.isEmpty()) {
            System.out.println("Nenhum administrador encontrado com o CPF: " + cpf);
        }
        listarAdministradores(adminsFiltrados);
    }


    public static void salvarListaAdministradores(String caminhoArquivo) {
        SerializacaoService.salvarObjeto(listaAdministradores, caminhoArquivo);
    }

    public static void carregarListaAdministradores(String caminhoArquivo) {
        List<Administrador> listaCarregada = (List<Administrador>) SerializacaoService.carregarObjeto(caminhoArquivo);
        if (listaCarregada != null) {
            listaAdministradores = listaCarregada;
        }
    }
}
