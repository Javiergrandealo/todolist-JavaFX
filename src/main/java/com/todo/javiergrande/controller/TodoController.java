package com.todo.javiergrande.controller;

import com.todo.javiergrande.model.*;
import com.todo.javiergrande.persistence.TaskStorage;

import java.io.IOException;
import java.util.List;
/**
 * Representa una clase controladora encargada de la gestón de datos relacionada con las tareas
 */

public class TodoController {
    private final TodoModel modeloPrincipal;
    private final CompletedModel modeloCompletado;

    private static final String ARCHIVO_ACTIVAS = "tareas_activas.enc";
    private static final String ARCHIVO_COMPLETADAS = "tareas_completadas.enc";

    /**
     * Crea un nuevo controlador con los 2 modelos de tareas, el normal y el de tareas completadas.
     * Carga los datos almacenados si los hay
     */
    public TodoController() {
        this.modeloPrincipal = TodoModel.getInstance();
        this.modeloCompletado = CompletedModel.getInstance();
        cargarDatos();
    }

    /**
     * Metdo utilizado para añadir una tarea al modelo principal
     * @param texto: texto descriptivo de la tarea a realizar
     */
    public void agregarTarea(String texto) {
        modeloPrincipal.addItem(new TodoItem(texto));
    }

    /**
     * Marca como realizada una tarea y la mueve al modelo de tereas realizadas
     * @param index: posición de la tarea en la lista de tareas
     */
    public void marcarTareaComoCompletada(int index) {
        TodoItem item = modeloPrincipal.getItems().get(index);
        modeloPrincipal.removeItem(item);
        modeloCompletado.addItem(item);
    }

    /**
     * Mueve una teara del modelo de tareas completado al modelo de tareas principal
     * @param index: posicion de la tarea en el modelo de tareas completadas
     */
    public void restaurarTarea(int index) {
        TodoItem item = modeloCompletado.getItems().get(index);
        modeloCompletado.restoreItem(item);
    }

    /**
     * Elimina por completo una tarea del modelo de tareas completadas
     * @param index: posicion de la tarea en el modelo de tareas completadas
     */

    public void eliminarTareaCompletada(int index) {
        TodoItem item = modeloCompletado.getItems().get(index);
        modeloCompletado.removeItem(item);
    }

    /**
     * Modifica el texto de una tarea
     * @param index: posición de la teare en el modelo
     * @param nuevoTexto: descripción nueva de la tarea a modificar
     */
    public void editarTarea(int index, String nuevoTexto) {
        modeloPrincipal.getItems().get(index).setContent(nuevoTexto);
    }

    public TodoModel getModeloPrincipal() {
        return modeloPrincipal;
    }

    public CompletedModel getModeloCompletado() {
        return modeloCompletado;
    }

    /**
     * Guarda los datos de las tareas de ambos modelos en archivos independientes para su posterior recuperación
     */

    public void guardarDatos() {
        try {
            TaskStorage.save(modeloPrincipal.getItems(), ARCHIVO_ACTIVAS);
            TaskStorage.save(modeloCompletado.getItems(), ARCHIVO_COMPLETADAS);
        } catch (IOException e) {
            System.err.println("Error guardando tareas: " + e.getMessage());
        }
    }

    /**
     * Carga los datos de los archivos para que sean mostrados (normalmente al abrir la aplicación)
     */
    private void cargarDatos() {
        try {
            List<TodoItem> activas = TaskStorage.load(ARCHIVO_ACTIVAS);
            List<TodoItem> completadas = TaskStorage.load(ARCHIVO_COMPLETADAS);

            modeloPrincipal.getItems().addAll(activas);
            modeloCompletado.getItems().addAll(completadas);
        } catch (IOException e) {
            System.err.println("No se pudieron cargar las tareas: " + e.getMessage());
        }
    }
}