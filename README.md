# 🛒 Sistema de Gestión de Compras - Java Swing

Este proyecto es una aplicación de escritorio desarrollada en Java usando **Swing** que permite la gestión de productos, carritos y usuarios, con funcionalidades como autenticación, roles, recuperación de contraseña mediante preguntas de seguridad e internacionalización dinámica (Español, Inglés y Francés).

## 📦 Estructura del Proyecto

src/
├── controlador/
│ ├── CarritoController.java
│ ├── ProductoController.java
│ └── UsuarioController.java
│ └── RecuperacionController.java
├── dao/
│ ├── ProductoDAO.java
│ ├── CarritoDAO.java
│ ├── UsuarioDAO.java
│ ├── PreguntasDAO.java
│ └── impl/
│ ├── ProductoDAOMemoria.java
│ ├── CarritoDAOMemoria.java
│ ├── UsuarioDAOMemoria.java
│ └── PreguntasDAOMemoria.java
├── modelo/
│ ├── Producto.java
│ ├── Carrito.java
│ ├── Usuario.java
│ ├── Pregunta.java
│ ├── Respuesta.java
│ └── Rol.java
├── util/
│ └── MensajeInternacionalizacionHandler.java
├── vista/
│ ├── InicioDeSesion/
│ │ ├── LoginView.java
│ │ ├── RegistrarseView.java
│ │ ├── RecuperarContraseñaView.java
│ │ └── CambiarContraseñaView.java
│ ├── producto/
│ ├── carrito/
│ ├── usuario/
│ └── MenuPrincipalView.java
└── Main.java


## 🧠 Principios SOLID Aplicados

- **S** - **Responsabilidad única**: Cada clase cumple una función clara (controlador, modelo, vista).
- **O** - **Abierto/Cerrado**: Las vistas admiten internacionalización sin modificar su lógica.
- **L** - **Sustitución de Liskov**: DAO e interfaces pueden ser intercambiables sin romper el sistema.
- **I** - **Segregación de interfaces**: Cada DAO tiene métodos específicos según el modelo que maneja.
- **D** - **Inversión de dependencias**: Controladores dependen de interfaces DAO.

## 🌐 Internacionalización

El sistema soporta tres idiomas:
- Español (`mensajes_es.properties`)
- Inglés (`mensajes_en.properties`)
- Francés (`mensajes_fr.properties`)

Todos los textos visibles son dinámicamente actualizados en las vistas al cambiar el idioma desde el menú.

## 🔐 Autenticación y Roles

- **ADMINISTRADOR**: puede registrar, eliminar, modificar y listar usuarios, productos y carritos.
- **USUARIO**: puede iniciar sesión, ver su carrito y modificar sus datos.

## 🔁 Recuperación de Contraseña

El sistema permite recuperar la contraseña a través de preguntas de seguridad. El usuario debe responder correctamente a 3 preguntas que seleccionó durante el registro.

## 🧪 Tecnologías

- Java 17+
- Java Swing (GUI)
- DAO Pattern
- MVC Architecture
- ResourceBundle para i18n
- `DefaultTableModel` para mostrar datos
- `JInternalFrame` y `JDesktopPane` para una interfaz MDI

## 📷 Capturas de Pantalla

- Inicio de sesión multilenguaje  
- Registro con preguntas de seguridad  
- CRUD de productos y carritos  
- Ventana principal MDI

## 📄 UML

El proyecto incluye un **diagrama UML** detallado con clases, atributos y relaciones entre controladores, modelos, vistas y DAOs.

## 🚀 Cómo ejecutar

1. Abre el proyecto en IntelliJ IDEA o NetBeans.
2. Ejecuta la clase `Main.java`.
3. Inicia sesión con usuario administrador (`admin`, `admin`) o regístrate como nuevo usuario.

## 🔑 Credenciales por defecto

Diagrama UML
![carritodecompras](https://github.com/user-attachments/assets/8c2e2b94-a056-4625-8c1c-20f4f2cf5ed9)

 ## Video demostrativo del programa
 Link del video: https://youtu.be/4KObdqtos3U
