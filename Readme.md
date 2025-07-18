===============================
PROYECTO FINAL – CARRO DE COMPRAS
===============================

📌 Estudiante: Keyra
📌 Materia: Programación Orientada a Objetos
📌 Universidad: Universidad Politécnica Salesiana
📌 Fecha: 18/07/2025
📌 Docente: PhD. Gabriel Alejandro León Paredes

===================================================================================
🔧 REQUISITOS DEL SISTEMA
===================================================================================

✔ Java Development Kit (JDK) 17 o superior instalado
✔ Sistema operativo Windows, macOS o Linux
✔ 512 MB de RAM o superior
✔ IntelliJ IDEA (recomendado para desarrollo)

===================================================================================
🚀 INSTRUCCIONES PARA EJECUTAR EL ARCHIVO .JAR
===================================================================================

1. Abre una terminal (CMD, PowerShell, Terminal Bash).
2. Navega hasta la carpeta donde se encuentra el archivo `CarritoFinal.jar`.

   Ejemplo (Windows):
   > cd C:\Users\Keyra\Desktop\EntregaFinal

3. Ejecuta el siguiente comando:
   > java -jar CarritoFinal.jar

4. La interfaz gráfica (ventana de Login) se abrirá automáticamente.

   ❗ Si no tienes `java` en tu PATH, deberás agregarlo o ejecutar desde IntelliJ.

===================================================================================
📂 ESTRUCTURA DE ARCHIVOS Y CARPETAS
===================================================================================

📦 CarritoFinal/
├── CarritoFinal.jar              → Ejecutable compilado
├── README.txt                    → Este archivo con información técnica
├── data/                         → Archivos de almacenamiento (según configuración)
│   ├── usuarios.txt / usuarios.bin
│   ├── productos.txt / productos.bin
│   ├── carritos.txt / carritos.bin
│   ├── preguntas.txt
│   ├── respuestas.txt / respuestas.bin
├── resources/
│   ├── mensajes_es_EC.properties
│   ├── mensajes_en_US.properties
│   ├── mensajes_fr_FR.properties

Nota: Los archivos `.txt` y `.bin` se generan automáticamente si no existen.

===================================================================================
🧠 FUNCIONALIDADES PRINCIPALES
===================================================================================

🔐 **Autenticación y roles:**
- Usuarios con roles `ADMINISTRADOR` y `USUARIO`
- Registro seguro con validaciones y roles predefinidos

👤 **Gestión de usuarios:**
- Registro con validación de cédula ecuatoriana
- Validación avanzada de contraseña
- Modificación y eliminación de usuarios
- Filtro por nombre, correo, rol, etc.

🛒 **Gestión de productos:**
- Crear, listar, modificar, eliminar
- Búsqueda por nombre o código
- Visualización con formato regional de precio

🛍 **Gestión de carritos:**
- Añadir productos a carritos
- Listar carritos con botón de “ver detalles”
- Modificación y eliminación de carritos

💾 **Persistencia configurable:**
- Modo MEMORIA (sin archivo)
- Modo TEXTO (archivos `.txt`)
- Modo BINARIO (archivos `.bin`)
- Configurable desde el `main`

🌍 **Internacionalización (i18n):**
- Soporte multilenguaje: Español, Inglés, Francés
- Cambio de idioma en tiempo real desde menú

🛡 **Recuperación de contraseña:**
- Mediante validación de preguntas de seguridad
- Se deben acertar al menos 3 respuestas

🎨 **Interfaz Gráfica Swing (MDI):**
- Ventanas internas (`JInternalFrame`) dentro de un `JDesktopPane`
- Íconos personalizados en botones
- Color base uniforme (RGB 250, 222, 212)
- Layout manual para control visual total

📄 **Formato regional:**
- Fechas con `DateFormat`
- Precios con `NumberFormat`

===================================================================================
🔒 VALIDACIONES Y SEGURIDAD
===================================================================================

✔ Validación de campos obligatorios
✔ Validación de cédula ecuatoriana real (módulo 10)
✔ Validación de contraseña segura (longitud, mayúscula, número, símbolo)
✔ Validación de fecha con excepciones personalizadas
✔ Manejo de errores con mensajes GUI y consola

===================================================================================
📤 INSTRUCCIONES DE ENTREGA
===================================================================================

✅ Archivo ejecutable: `CarritoFinal.jar`
✅ Carpeta `data/` con archivos `.txt` y `.bin`
✅ Archivos `.properties` en carpeta `resources/`
✅ Archivo `README.txt` con todas las instrucciones
✅ Capturas de pantalla para informe (no incluidas aquí)
✅ Javadoc generado (opcionalmente en carpeta `/docs`)
✅ Archivo Word/PDF con explicación técnica e informe

===================================================================================
📝 OBSERVACIONES
===================================================================================

⚠ Si ejecutas el `.jar` fuera del IDE, asegúrate de tener permisos de escritura
⚠ Las rutas relativas se usan para lectura/escritura de archivos
⚠ El sistema es sensible a errores de edición manual en los `.txt`


───────────────────────────────
📫 Contacto del estudiante:
───────────────────────────────
✉ Correo: kcarvajalc5@est.ups.edu.ec
