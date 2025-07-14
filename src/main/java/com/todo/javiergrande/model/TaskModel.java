package com.todo.javiergrande.model;

import java.util.List;

/**
 * Interfaz comun a los 2 modelos con los metodos basicos de funcionamineto
 */
public interface TaskModel {
    List<TodoItem> getItems();
    void addItem(TodoItem item);
    void removeItem(TodoItem item);
}