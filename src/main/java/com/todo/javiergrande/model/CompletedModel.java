package com.todo.javiergrande.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un modelo de tareas completadas
 */
//Igual que en TodoModel he decidido que siga el patrón Singleton
public class CompletedModel implements TaskModel {
    private static CompletedModel instance;

    private List<TodoItem> items;

    /**
     * Constructor privado de la clase (Patron Singleton)
     */
    private CompletedModel() {
        this.items = new ArrayList<>();
    }

    /**
     * Metodo que devuele la única instancia posble creada de la clase
     * @return modelo de tareas completadas
     */
    public static CompletedModel getInstance() {
        if (instance == null) {
            instance = new CompletedModel();
        }
        return instance;
    }

    /**
     * Metodo que devuelve todos los items del modelo
     * @return items del modelo
     */
    @Override
    public List<TodoItem> getItems() {
        return items;
    }

    /**
     * Metodo que añade un item al modelo de tareas completadas
     * @param item: tarea a añadir
     */
    @Override
    public void addItem(TodoItem item) {
        item.markCompleted();
        items.add(item);
    }

    /**
     * Metodo usado para eliminar por completo un item del modelo de tareas completadas
     * @param item: tarea a eliminar
     */
    @Override
    public void removeItem(TodoItem item) {
        items.remove(item);
    }

    /**
     * Metodo para restaurar un intem completado al modelo de items sin completar
     * @param item: tarea a restaurar
     */
    public void restoreItem(TodoItem item) {
        item.unmarkCompleted();
        removeItem(item);
        TodoModel.getInstance().addItem(item);
    }
}