package com.todo.javiergrande.model;

import com.todo.javiergrande.model.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemTest {

    private TodoItem item;

    @BeforeEach
    void setUp() {
        item = new TodoItem("Prueba");
    }

    @Test
    void testCreacion() {
        assertEquals("Prueba", item.getContent());
        assertFalse(item.isCompleted());
    }

    @Test
    void testMarcarComoCompletado() {
        item.markCompleted();
        assertTrue(item.isCompleted());
    }

    @Test
    void testDesmarcarComoCompletado() {
        item.markCompleted();
        item.unmarkCompleted();
        assertFalse(item.isCompleted());
    }

    @Test
    void testSetContent() {
        item.setContent("Prueba2");
        assertEquals("Prueba2", item.getContent());
    }
}