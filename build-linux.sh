#!/bin/bash
set -e

echo "🧹 Limpiando..."
mvn clean package

echo "🔧 Creando runtime con jlink..."
jlink \
  --module-path target/todolist-1.0-SNAPSHOT-jar-with-dependencies.jar:javafx-jmods-24/linux \
  --add-modules java.base,javafx.controls,javafx.fxml \
  --output runtime-TodoList \
  --strip-debug \
  --compress=2 \
  --no-header-files \
  --no-man-pages

echo "📦 Empaquetando AppImage con jpackage..."
jpackage \
  --type app-image \
  --name TodoList \
  --input target \
  --main-jar todolist-1.0-SNAPSHOT-jar-with-dependencies.jar \
  --main-class com.todo.javiergrande.Main \
  --runtime-image runtime-TodoList \
  --dest dist/linux

echo "✅ App creada en dist/linux/TodoList"
