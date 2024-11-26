package com.sistema.projetobibliotecavirtual.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LogService {
    private static final Path LOG_FILE = Path.of("log.txt");

    public static void registrarLog(String mensagem) {
        try {
            String log = java.time.LocalDateTime.now() + " - " + mensagem + "\n";
            Files.write(LOG_FILE, log.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
