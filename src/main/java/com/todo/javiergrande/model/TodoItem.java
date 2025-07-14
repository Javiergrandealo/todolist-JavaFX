package com.todo.javiergrande.model;

/**
 * Clase que repesenta una tarea en el modelo
 */
public class TodoItem {
    String content;
    boolean completed;

    /**
     * Constructor de la clase
     * @param content: descripción del contenido de la tarea que se mostrara
     */
    public TodoItem(String content) {
        this.content = content;
        this.completed = false;
    }
    public String getContent() {
        return content;
    }

    /**
     * Marca una tarea como completada
     * @return booleano del estado de la tarea (completado o no)
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Metodo para marcar como completado una tarea
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * Metodo que cambia el contenido de la tarea en caso de querer modificarlo
     * @param nuevoContenido: descripción nueva a mostrar de la tarera
     */
    public void setContent(String nuevoContenido) {
        this.content = nuevoContenido;
    }

    /**
     * Marca la tarea como no completada
     */
    public void unmarkCompleted() {
        this.completed = false;
    }
}
