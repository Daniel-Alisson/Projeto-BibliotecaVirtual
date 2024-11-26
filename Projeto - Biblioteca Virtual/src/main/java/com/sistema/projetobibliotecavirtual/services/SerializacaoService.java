package com.sistema.projetobibliotecavirtual.services;

import java.io.*;
import java.util.List;

public class SerializacaoService {
    public static void salvarObjeto(Object objeto, String caminhoArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(objeto);
        }
    }

    public static Object carregarObjeto(String caminhoArquivo) throws IOException, ClassNotFoundException {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return ois.readObject();
        }
    }
}
