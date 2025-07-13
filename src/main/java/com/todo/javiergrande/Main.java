package com.todo.javiergrande;

import com.todo.javiergrande.model.TodoItem;
import com.todo.javiergrande.model.TodoModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.geometry.Insets;

public class Main extends Application {
    private TodoModel modelo = new TodoModel("prueba", "prueba de descripcion");
    private ListView<String> listaTareas = new ListView<>();
    private ObservableList<String> tareasVisibles = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        TextField campoTexto = new TextField();
        campoTexto.setPromptText("Nueva tarea...");

        Button botonAgregar = new Button("Agregar");

        botonAgregar.setOnAction(e -> {
            String descripcion = campoTexto.getText().trim();
            if (!descripcion.isEmpty()) {
                modelo.addItem(new TodoItem(descripcion));
                actualizarLista();
                campoTexto.clear();
            }
        });

        listaTareas.setItems(tareasVisibles);

        Button botonCompletar = new Button("Marcar como completada");

        botonCompletar.setOnAction(e -> {
            if(){
            int indice = listaTareas.getSelectionModel().getSelectedIndex();
            if (indice >= 0) {
                modelo.markItemCompleted(modelo.getItems().get(indice));
                actualizarLista();
            }
        }});
    
    

        VBox layout = new VBox(10, campoTexto, botonAgregar, listaTareas, botonCompletar);
        layout.setPadding(new Insets(10));

        Scene escena = new Scene(layout, 400, 300);
        primaryStage.setScene(escena);
        primaryStage.setTitle("To-Do App");
        primaryStage.show();
    }

    private void actualizarLista() {
        tareasVisibles.clear();
        for (TodoItem item : modelo.getItems()) {
            String texto = (item.isCompleted() ? "[✓] " : "[ ] ") + item.getContent();
            tareasVisibles.add(texto);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}