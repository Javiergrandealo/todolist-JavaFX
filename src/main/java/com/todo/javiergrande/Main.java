package com.todo.javiergrande;

import com.todo.javiergrande.view.TodoView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        TodoView vista = new TodoView();
        vista.start(primaryStage);

        // Guardar al cerrar
        primaryStage.setOnCloseRequest(event -> {
            vista.getControlador().guardarDatos();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}