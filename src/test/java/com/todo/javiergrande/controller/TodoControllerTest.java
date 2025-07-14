package com.todo.javiergrande.controller;

import com.todo.javiergrande.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoControllerTest {

    private TodoController controlador;

    @BeforeEach
    void setUp() {
        controlador = new TodoController();

        // Limpiar los modelos antes de cada test (por ser singletons)
        controlador.getModeloPrincipal().getItems().clear();
        controlador.getModeloCompletado().getItems().clear();
    }

    @Test
    void testAgregarTarea() {
        controlador.agregarTarea("Aprender JavaFX");
        List<TodoItem> activas = controlador.getModeloPrincipal().getItems();

        assertEquals(1, activas.size());
        assertEquals("Aprender JavaFX", activas.get(0).getContent());
        assertFalse(activas.get(0).isCompleted());
    }

    @Test
    void testMarcarComoCompletada() {
        controlador.agregarTarea("Practicar tests");
        controlador.marcarTareaComoCompletada(0);

        assertTrue(controlador.getModeloPrincipal().getItems().isEmpty());

        List<TodoItem> completadas = controlador.getModeloCompletado().getItems();
        assertEquals(1, completadas.size());
        assertTrue(completadas.get(0).isCompleted());
    }

    @Test
    void testRestaurarTarea() {
        controlador.agregarTarea("Volver a repasar");
        controlador.marcarTareaComoCompletada(0);
        controlador.restaurarTarea(0);

        List<TodoItem> activas = controlador.getModeloPrincipal().getItems();
        List<TodoItem> completadas = controlador.getModeloCompletado().getItems();

        assertEquals(1, activas.size());
        assertEquals("Volver a repasar", activas.get(0).getContent());
        assertFalse(activas.get(0).isCompleted());
        assertTrue(completadas.isEmpty());
    }

    @Test
    void testEliminarCompletada() {
        controlador.agregarTarea("Tarea inútil");
        controlador.marcarTareaComoCompletada(0);
        controlador.eliminarTareaCompletada(0);

        assertTrue(controlador.getModeloCompletado().getItems().isEmpty());
    }

    @Test
    void testEditarTarea() {
        controlador.agregarTarea("Viejo texto");
        controlador.editarTarea(0, "Nuevo texto");

        TodoItem item = controlador.getModeloPrincipal().getItems().get(0);
        assertEquals("Nuevo texto", item.getContent());
    }
}