#!/bin/bash

# Ruta de entrada
INPUT_IMAGE="icon.png"
OUTPUT_DIR="src/main/resources/icons"

# Verifica que exista la imagen base
if [ ! -f "$INPUT_IMAGE" ]; then
  echo "❌ No se encuentra '$INPUT_IMAGE'. Coloca la imagen base (1024x1024) en el mismo directorio."
  exit 1
fi

mkdir -p "$OUTPUT_DIR"

echo "🖼 Generando iconos en: $OUTPUT_DIR"

# ------------------------
# macOS: Generar icon.icns
# ------------------------
if [[ "$OSTYPE" == "darwin"* ]]; then
  echo "🍏 Generando .icns para macOS..."
  ICONSET="icon.iconset"
  mkdir -p $ICONSET

  sips -z 16 16     "$INPUT_IMAGE" --out $ICONSET/icon_16x16.png
  sips -z 32 32     "$INPUT_IMAGE" --out $ICONSET/icon_16x16@2x.png
  sips -z 32 32     "$INPUT_IMAGE" --out $ICONSET/icon_32x32.png
  sips -z 64 64     "$INPUT_IMAGE" --out $ICONSET/icon_32x32@2x.png
  sips -z 128 128   "$INPUT_IMAGE" --out $ICONSET/icon_128x128.png
  sips -z 256 256   "$INPUT_IMAGE" --out $ICONSET/icon_128x128@2x.png
  sips -z 256 256   "$INPUT_IMAGE" --out $ICONSET/icon_256x256.png
  sips -z 512 512   "$INPUT_IMAGE" --out $ICONSET/icon_256x256@2x.png
  sips -z 512 512   "$INPUT_IMAGE" --out $ICONSET/icon_512x512.png
  cp "$INPUT_IMAGE" $ICONSET/icon_512x512@2x.png

  iconutil -c icns $ICONSET -o "$OUTPUT_DIR/icon.icns"
  rm -r $ICONSET
fi

# ------------------------
# Windows: Generar .ico
# ------------------------
if command -v convert &> /dev/null; then
  echo "🪟 Generando .ico para Windows..."
  convert "$INPUT_IMAGE" -define icon:auto-resize=256,128,64,48,32,16 "$OUTPUT_DIR/icon.ico"
else
  echo "⚠️ 'convert' (ImageMagick) no está instalado. Saltando .ico."
fi

# ------------------------
# Linux: Copiar .png
# ------------------------
echo "🐧 Copiando .png para Linux..."
cp "$INPUT_IMAGE" "$OUTPUT_DIR/icon.png"

echo "✅ Iconos generados correctamente en $OUTPUT_DIR"
