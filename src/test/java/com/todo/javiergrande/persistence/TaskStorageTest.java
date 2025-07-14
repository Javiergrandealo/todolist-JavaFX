package com.todo.javiergrande.persistence;

import com.todo.javiergrande.model.TodoItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStorageTest {

    private static final String TEST_FILE = "test_guardado.enc";

    @AfterEach
    void cleanup() throws IOException {
        // Borra el archivo de test después de cada prueba
        Path file = Paths.get("userfiles", TEST_FILE);
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }

    @Test
    void testGuardarYCargarTareas() throws IOException {
        List<TodoItem> tareasOriginales = Arrays.asList(
                new TodoItem("Leer"),
                new TodoItem("Estudiar")
        );

        // Guardar
        TaskStorage.save(tareasOriginales, TEST_FILE);

        // Cargar
        List<TodoItem> cargadas = TaskStorage.load(TEST_FILE);

        assertEquals(2, cargadas.size());
        assertEquals("Leer", cargadas.get(0).getContent());
        assertEquals("Estudiar", cargadas.get(1).getContent());
    }

    @Test
    void testGuardarListaVacia() throws IOException {
        List<TodoItem> vacia = List.of();
        TaskStorage.save(vacia, TEST_FILE);

        List<TodoItem> cargadas = TaskStorage.load(TEST_FILE);
        assertTrue(cargadas.isEmpty());
    }

    @Test
    void testArchivoInexistente() throws IOException {
        List<TodoItem> cargadas = TaskStorage.load("archivo_que_no_existe.enc");
        assertNotNull(cargadas);
        assertTrue(cargadas.isEmpty());
    }
}