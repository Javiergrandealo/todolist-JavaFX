package com.todo.javiergrande.view;

import com.todo.javiergrande.controller.TodoController;
import com.todo.javiergrande.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Clase vista relacionado con todos los elementos graficos y lo que vera el usuario
 */
public class TodoView {
    private final TodoController controlador = new TodoController();
    private final ObservableList<String> tareasVisiblesActivas = FXCollections.observableArrayList();
    private final ObservableList<String> tareasVisiblesCompletadas = FXCollections.observableArrayList();

    public TodoController getControlador() {
        return controlador;
    }

    /**
     * Metodo de inicio de la interfaz grafica del programa con todos los elementos de la ventana asi como su inicialización
     * @param primaryStage: Escenario principal
     */
    public void start(Stage primaryStage) {
        // === TAB 1: TAREAS ACTIVAS ===
        TextField campoTexto = new TextField();
        campoTexto.setPromptText("Nueva tarea...");

        Button botonAgregar = new Button("Agregar");
        ListView<String> listaActivas = new ListView<>(tareasVisiblesActivas);
        listaActivas.setEditable(true);
        listaActivas.setCellFactory(TextFieldListCell.forListView());

        botonAgregar.setOnAction(e -> {
            String descripcion = campoTexto.getText().trim();
            if (!descripcion.isEmpty()) {
                controlador.agregarTarea(descripcion);
                campoTexto.clear();
                actualizarListas();
            }
        });

        listaActivas.setOnEditCommit(event -> {
            int index = event.getIndex();
            String nuevoTexto = event.getNewValue();
            if (!nuevoTexto.trim().isEmpty()) {
                controlador.editarTarea(index, nuevoTexto.trim());
                actualizarListas();
            }
        });

        Button botonCompletar = new Button("Marcar como completada");
        botonCompletar.setOnAction(e -> {
            int index = listaActivas.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                controlador.marcarTareaComoCompletada(index);
                actualizarListas();
            }
        });

        VBox layoutActivas = new VBox(10, campoTexto, botonAgregar, listaActivas, botonCompletar);
        layoutActivas.setPadding(new Insets(10));

        // === TAB 2: TAREAS COMPLETADAS ===
        ListView<String> listaCompletadas = new ListView<>(tareasVisiblesCompletadas);

        Button botonEliminar = new Button("Eliminar");
        botonEliminar.setOnAction(e -> {
            int index = listaCompletadas.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                controlador.eliminarTareaCompletada(index);
                actualizarListas();
            }
        });

        Button botonRestaurar = new Button("Descompletar");
        botonRestaurar.setOnAction(e -> {
            int index = listaCompletadas.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                controlador.restaurarTarea(index);
                actualizarListas();
            }
        });

        VBox layoutCompletadas = new VBox(10, listaCompletadas, botonRestaurar, botonEliminar);
        layoutCompletadas.setPadding(new Insets(10));

        // === TabPane ===
        Tab tabActivas = new Tab("Tareas activas", layoutActivas);
        Tab tabCompletadas = new Tab("Tareas completadas", layoutCompletadas);
        tabActivas.setClosable(false);
        tabCompletadas.setClosable(false);

        TabPane tabPane = new TabPane(tabActivas, tabCompletadas);

        // === Escena ===
        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("To-Do App");
        primaryStage.setScene(scene);
        primaryStage.show();

        actualizarListas();
    }

    /**
     * Metodo para mostar el recuadro de completado o no completado en las listas de tareas. Recorre las listas y imprime el
     * recuadro antes de cada item en cada uno
     */
    private void actualizarListas() {
        tareasVisiblesActivas.clear();
        for (TodoItem item : controlador.getModeloPrincipal().getItems()) {
            tareasVisiblesActivas.add("[ ] " + item.getContent());
        }

        tareasVisiblesCompletadas.clear();
        for (TodoItem item : controlador.getModeloCompletado().getItems()) {
            tareasVisiblesCompletadas.add("[✓] " + item.getContent());
        }
    }
}