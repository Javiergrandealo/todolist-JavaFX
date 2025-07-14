package com.todo.javiergrande.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CompletedModelTest {

    private CompletedModel completadas;
    private TodoModel activas;
    private TodoItem tarea;

    @BeforeEach
    void setUp() {
        completadas = CompletedModel.getInstance();
        activas = TodoModel.getInstance();

        completadas.getItems().clear(); //lo de los Singleton
        activas.getItems().clear();

        tarea = new TodoItem("Tarea completada");
        tarea.markCompleted();
    }

    @Test
    void testAgregarTareaCompletada() {
        completadas.addItem(tarea);
        assertEquals(1, completadas.getItems().size());
        assertTrue(completadas.getItems().get(0).isCompleted());
    }

    @Test
    void testEliminarTareaCompletada() {
        completadas.addItem(tarea);
        completadas.removeItem(tarea);
        assertTrue(completadas.getItems().isEmpty());
    }

    @Test
    void testRestaurarTarea() {
        completadas.addItem(tarea);
        completadas.restoreItem(tarea);

        assertTrue(activas.getItems().contains(tarea));
        assertFalse(tarea.isCompleted());
        assertFalse(completadas.getItems().contains(tarea));
    }
}