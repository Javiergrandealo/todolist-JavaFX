package com.todo.javiergrande.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.todo.javiergrande.model.TodoItem;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase con la persistncia para el guardado de archivos al cerrar y abrir el programa utilizando la libreria Gson que
 * transforma de JSON a objeto y de objeto a JSON
 * Los archivos guardados de encriptan para mayor seguridad
 */
public class TaskStorage {
    private static final Gson gson = new Gson();
    private static final Type LIST_TYPE = new TypeToken<List<TodoItem>>() {}.getType();
    private static final Path USER_DIR = Paths.get("userfiles");
    static {
        try {
            Files.createDirectories(USER_DIR); // crea la carpeta si no existe
        } catch (IOException e) {
            System.err.println("No se pudo crear la carpeta 'userfiles': " + e.getMessage());
        }
    }

    /**
     * Guarda las tareas en un archivo y lo encripta
     * @param tareas: lista de tareas a guardar
     * @param fileName: normbre del archivo
     * @throws IOException: excepción de lectura o escritura de archivos
     */
    public static void save(List<TodoItem> tareas, String fileName) throws IOException {
        String json = gson.toJson(tareas, LIST_TYPE);
        byte[] cifrado = EncryptionUtil.encrypt(json);
        Path filePath = USER_DIR.resolve(fileName);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(cifrado);
        }
    }

    /**
     * Metodo para cargar los archivos y los desencripta
     * @param fileName: nombre del archivo que hay que leer y desencriptar
     * @return lista de objetos de TodoItem listos para la isnerción en un modelo
     * @throws IOException: excepción de lectura o escritura de archivos
     */
    public static List<TodoItem> load(String fileName) throws IOException {
        Path filePath = USER_DIR.resolve(fileName);

        // Asegurarse de que la carpeta existe
        Files.createDirectories(USER_DIR);

        File file = filePath.toFile();
        if (!file.exists()) return new ArrayList<>();

        byte[] cifrado = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(cifrado);
        }

        String json = EncryptionUtil.decrypt(cifrado);
        List<TodoItem> lista = gson.fromJson(json, LIST_TYPE);
        return lista != null ? lista : new ArrayList<>();
    }
}