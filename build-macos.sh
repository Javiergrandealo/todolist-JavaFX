#!/bin/bash

# Configuración
APP_NAME="TodoList"
MAIN_CLASS="com.todo.javiergrande.Main"
JAR_NAME="todolist-1.0-SNAPSHOT-jar-with-dependencies.jar"
ICON_PATH="src/main/resources/icon.icns"
JAVA_VERSION="17"
RUNTIME_DIR="runtime-$APP_NAME"
JMODS_PATH="/Users/javiergrande/gluon/javafx-jmods-24"
OUTPUT_DIR="dist/mac"

# Limpieza
echo "🧹 Limpiando..."
rm -rf "$RUNTIME_DIR" "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR"

# Crear runtime con jlink
echo "🔧 Creando runtime con jlink..."
jlink \
  --module-path "$JMODS_PATH" \
  --add-modules java.base,javafx.controls,javafx.fxml \
  --output "$RUNTIME_DIR" \
  --strip-debug --compress=2 --no-header-files --no-man-pages

# Empaquetar la aplicación con jpackage
echo "📦 Empaquetando .app con jpackage..."
jpackage \
  --type app-image \
  --input target \
  --name "$APP_NAME" \
  --main-jar "$JAR_NAME" \
  --main-class "$MAIN_CLASS" \
  --runtime-image "$RUNTIME_DIR" \
  --icon "$ICON_PATH" \
  --dest "$OUTPUT_DIR"

echo "✅ App creada en $OUTPUT_DIR/$APP_NAME.app"