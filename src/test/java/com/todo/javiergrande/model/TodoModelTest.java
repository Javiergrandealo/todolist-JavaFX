package com.todo.javiergrande.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoModelTest {

    private TodoModel modelo;
    private TodoItem item1;
    private TodoItem item2;

    @BeforeEach
    void setUp() {
        modelo = TodoModel.getInstance();
        modelo.getItems().clear(); // aseguramos que no haya otro puesto que es un singleton

        item1 = new TodoItem("Tarea 1");
        item2 = new TodoItem("Tarea 2");
    }

    @Test
    void testAgregarTarea() {
        modelo.addItem(item1);
        List<TodoItem> tareas = modelo.getItems();
        assertEquals(1, tareas.size());
        assertEquals("Tarea 1", tareas.get(0).getContent());
    }

    @Test
    void testEliminarTarea() {
        modelo.addItem(item1);
        modelo.removeItem(item1);
        assertTrue(modelo.getItems().isEmpty());
    }

    @Test
    void testMarcarTareaComoCompletada() {
        modelo.addItem(item2);
        modelo.markItemCompleted(item2);
        assertTrue(item2.isCompleted());
    }
}