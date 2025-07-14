package com.todo.javiergrande.model;

import java.util.ArrayList;
import java.util.List;

//He decidio aplicar el patron singleton ya que solo habrá un modelo por sesión.

/**
 * Clase que represneta la lista de tareas por completar sigueindo el patrón Singleton
 */
public class TodoModel implements TaskModel {
    private static TodoModel instancia; // instancia única
    private List<TodoItem> items;

    /**
     * Contructor privado de la clase
     */
    private TodoModel() {
        this.items = new ArrayList<>();
    }

    /**
     * Metodo utilzado para obtener o crear la unica instancia de la clase
     * @return la instancia de TodoModel
     */
    public static TodoModel getInstance() {
        if (instancia == null) {
            instancia = new TodoModel();
        }
        return instancia;
    }

    @Override
    public List<TodoItem> getItems() {
        return items;
    }

    /**
     * Metodo para añadir un item a la lista de tareas
     * @param item
     */
    @Override
    public void addItem(TodoItem item) {
        items.add(item);
    }

    /**
     * Metodo para quitar un item de la lista de tareas a completar
     * @param item: tarea a quitar de la lista
     */
    @Override
    public void removeItem(TodoItem item) {
        items.remove(item);
    }

    /**
     * Metodo para marcar como completado un item de la lista de tareas a completar
     * @param item: tarea que se va a completar
     */
    public void markItemCompleted(TodoItem item) {
        if (items.contains(item)) {
            item.markCompleted();
        }
    }
}