package com.sistema.projetobibliotecavirtual.services;

import java.io.*;

public class SerializacaoService {
    public static void salvarObjeto(Object objeto, String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(objeto);
            System.out.println("Objeto salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o objeto: " + e.getMessage());
        }
    }

    // Método para carregar um objeto de um arquivo
    public static Object carregarObjeto(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            Object objeto = ois.readObject();
            System.out.println("Objeto carregado com sucesso!");
            return objeto;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o objeto: " + e.getMessage());
            return null;
        }
    }
}
