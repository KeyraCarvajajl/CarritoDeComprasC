package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.carrito.CarritoEliminarView;
import ec.edu.ups.vista.carrito.CarritoModificarView;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.usuario.*;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase UsuarioController
 * ------------------------
 * Controlador principal encargado de gestionar toda la lógica relacionada con la autenticación,
 * registro, modificación, eliminación, listado, filtrado y recuperación de contraseña de usuarios.
 * Aplica el patrón MVC, separando la lógica del negocio de las vistas.
 *
 * Esta clase hace uso de:
 * - Vistas: LoginView, RegistrarseView, MenuPrincipalView, UsuarioModificarView, UsuarioEliminarView, UsuarioListaView, CuestionarioView
 * - DAOs: UsuarioDAO, PreguntasDAO, CarritoDAO
 * - Modelos: Usuario, Carrito
 * - Utilidades: MensajeInternacionalizacionHandler
 *
 * Autor: Keyra Carvajal
 */

public class UsuarioController {

    /**
     * Usuario actualmente autenticado en el sistema.
     */

    private Usuario usuario;

    /**
     * Objeto DAO para manejar operaciones de persistencia relacionadas con usuarios.
     */

    private final UsuarioDAO usuarioDAO;

    /**
     * Vista de inicio de sesión donde el usuario ingresa sus credenciales.
     */

    private final LoginView loginView;

    /**
     * Vista para registrar nuevos usuarios en el sistema.
     */

    private RegistrarseView registrarseView;

    /**
     * Vista del menú principal mostrada después del inicio de sesión exitoso.
     */

    private MenuPrincipalView menuPrincipalView;

    /**
     * Vista que permite eliminar un usuario del sistema.
     */

    private UsuarioEliminarView usuarioEliminarView;

    /**
     * Manejador para la internacionalización de mensajes y etiquetas en diferentes idiomas.
     */

    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Vista para la recuperación de contraseña mediante preguntas de seguridad.
     */

    private CuestionarioView recuperarContraseniaView;

    /**
     * DAO para la gestión de carritos, usado en funciones auxiliares como modificar fecha.
     */

    private CarritoDAO carritoDAO;

    /**
     * Vista para modificar la fecha de creación de un carrito.
     */

    private CarritoModificarView carritoModificarView;

    /**
     * Vista para modificar los datos de un usuario existente.
     */

    private UsuarioModificarView usuarioModificarView;

    /**
     * Vista para listar todos los usuarios registrados, con opción de búsqueda y filtrado.
     */

    private UsuarioListaView usuarioListaView;

    /**
     * Constructor del controlador de usuarios. Inicializa las vistas y el DAO de usuario,
     * y configura los eventos correspondientes para el login y el registro de usuario.
     *
     * @param usuarioDAO Objeto DAO para la gestión de usuarios.
     * @param loginView Vista para el inicio de sesión del usuario.
     * @param registrarseView Vista para registrar nuevos usuarios.
     * @param usuarioListaView Vista para listar usuarios registrados.
     * @param usuarioModificarView Vista para modificar los datos de un usuario.
     * @param usuarioEliminarView Vista para eliminar usuarios del sistema.
     * @param mensajeHandler Manejador para internacionalización de textos y mensajes.
     */

    public UsuarioController(
            UsuarioDAO usuarioDAO,
            LoginView loginView,
            RegistrarseView registrarseView,
            UsuarioListaView usuarioListaView,
            UsuarioModificarView usuarioModificarView,
            UsuarioEliminarView usuarioEliminarView,
            MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.registrarseView = registrarseView;
        this.usuarioListaView = usuarioListaView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.menuPrincipalView = menuPrincipalView;
        this.mensajeHandler = mensajeHandler;

        configurarEventosLogin();
        inicializarListenersRegistro();
    }

    /**
     * Establece la vista de recuperación de contraseña y configura los eventos
     * necesarios para el proceso de recuperación (preguntas de seguridad y validación).
     *
     * @param recuperarContraseniaView Vista del cuestionario de recuperación de contraseña.
     */

    public void setRecuperarContraseniaView(CuestionarioView recuperarContraseniaView) {
        this.recuperarContraseniaView = recuperarContraseniaView;
        configurarEventoOlvidoContrasena();
        configurarEventosRecuperacion();
    }

    /**
     * Establece la vista de eliminación de usuario y configura los eventos
     * relacionados con el proceso de eliminación de cuentas.
     *
     * @param usuarioEliminarView Vista que permite al usuario eliminar su cuenta.
     */

    public void setUsuarioEliminarView(UsuarioEliminarView usuarioEliminarView) {
        this.usuarioEliminarView = usuarioEliminarView;
        configurarEventosEliminar();
    }

    /**
     * Establece la vista del menú principal del sistema y configura
     * el evento para cerrar sesión desde dicha vista.
     *
     * @param menuPrincipalView Vista principal del sistema una vez que el usuario ha iniciado sesión.
     */

    public void setMenuPrincipalView(MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalView = menuPrincipalView;
        configurarEventoCerrarSesion();
    }

    /**
     * Establece la vista de registro de usuario y configura los eventos
     * necesarios para registrar un nuevo usuario en el sistema.
     *
     * @param registrarseView Vista del formulario de registro de nuevos usuarios.
     */

    public void setRegistrarseView(RegistrarseView registrarseView) {
        this.registrarseView = registrarseView;
        inicializarListenersRegistro();
    }

    /**
     * Establece la vista de listado de usuarios y configura los eventos
     * necesarios para realizar búsquedas de usuarios desde dicha vista.
     *
     * @param usuarioListarView Vista donde se listan y buscan usuarios.
     */

    public void setUsuarioListarView(UsuarioListaView usuarioListarView) {
        this.usuarioListaView = usuarioListarView;
        configurarEventosListaUsuarios();
    }

    /**
     * Configura el evento del botón "¿Olvidaste tu contraseña?" en la vista de login.
     * Cuando se hace clic, se muestra la vista de recuperación de contraseña si está disponible.
     */

    private void configurarEventoOlvidoContrasena() {
        loginView.getBtnOlvidarContrasenia().addActionListener(e -> {
            if (recuperarContraseniaView != null) {
                recuperarContraseniaView.setVisible(true);
            } else {
                System.out.println("recuperarContraseniaView es null");
            }
        });
    }

    /**
     * Configura el evento del botón "Buscar" en la vista de listado de usuarios.
     * Ejecuta la lógica para buscar usuarios según el criterio ingresado.
     */

    private void configurarEventosListaUsuarios() {
        usuarioListaView.getBtnBuscar().addActionListener(e -> buscarUsuarios());
    }

    /**
     * Configura el evento del botón "Eliminar Usuario" en la vista de eliminación de usuarios.
     * Ejecuta la lógica necesaria para validar y eliminar al usuario del sistema.
     */

    private void configurarEventosEliminar() {
        usuarioEliminarView.getBtnEliminarUsuario().addActionListener(e -> eliminarUsuario());
    }

    /**
     * Configura el evento para cerrar sesión desde el menú principal.
     * Al seleccionar la opción, se cierra la ventana del menú y se regresa a la vista de login.
     */

    private void configurarEventoCerrarSesion() {
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    /**
     * Configura los eventos de la vista de inicio de sesión.
     * Incluye los botones para iniciar sesión y abrir la vista de registro.
     */

    private void configurarEventosLogin() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
        loginView.getBtnRegistrarse().addActionListener(e -> {
            if (registrarseView != null) {
                registrarseView.setVisible(true);
            }
        });
    }

    /**
     * Inicializa los listeners de los botones en la vista de registro de usuario.
     * Configura la acción para registrar un nuevo usuario y para cancelar el registro.
     */

    public void inicializarListenersRegistro() {
        registrarseView.getBtnRegistro().addActionListener(e -> crear());

        registrarseView.getBtnCancelar().addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(registrarseView.getContentPane());
            if (frame != null) {
                frame.dispose();
            }
        });
    }

    /**
     * Realiza la búsqueda de un usuario en base al nombre ingresado en la vista de listado.
     * Si se encuentra el usuario, lo muestra en la tabla; si no, muestra un mensaje de error.
     */

    private void buscarUsuarios() {
        String nombre = usuarioListaView.getTxtNombre().getText().trim();
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0);

        if (nombre.isEmpty()) {
            usuarioListaView.mostrarMensaje("Ingrese un nombre de usuario para buscar.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(nombre);
        if (usuario != null) {
            modelo.addRow(new Object[]{usuario.getUsername(), usuario.getRol().name()});
        } else {
            usuarioListaView.mostrarMensaje("Usuario no encontrado.");
        }
    }

    /**
     * Lista todos los usuarios registrados en el sistema y los muestra en la tabla
     * de la vista de listado de usuarios.
     */

    private void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listarTodos();
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0);
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol().name()});
        }
    }

    /**
     * Elimina un usuario del sistema validando el nombre de usuario y su contraseña.
     * Primero comprueba que las contraseñas coincidan y luego valida que el usuario exista
     * y que la contraseña ingresada sea correcta.
     */

    private void eliminarUsuario() {
        String nombre = usuarioEliminarView.getTxtNombre().getText();
        String contrasenia = new String(usuarioEliminarView.getTxtContraseña().getPassword());
        String confirmar = new String(usuarioEliminarView.getTxtConfirmarContrasenia().getPassword());

        if (!contrasenia.equals(confirmar)) {
            usuarioEliminarView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        Usuario u = usuarioDAO.buscarPorUsername(nombre);
        if (u == null) {
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado.");
            return;
        }

        if (!u.getContrasenia().equals(contrasenia)) {
            usuarioEliminarView.mostrarMensaje("Contraseña incorrecta.");
            return;
        }

        usuarioDAO.eliminar(u.getCodigo());
        usuarioEliminarView.mostrarMensaje("Usuario eliminado correctamente.");

        usuarioEliminarView.getTxtNombre().setText("");
        usuarioEliminarView.getTxtContraseña().setText("");
        usuarioEliminarView.getTxtConfirmarContrasenia().setText("");
    }

    /**
     * Cierra la sesión actual del usuario autenticado.
     * Muestra un cuadro de confirmación antes de cerrar el menú principal
     * y regresar a la ventana de login.
     */

    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(menuPrincipalView, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            menuPrincipalView.setVisible(false);
            menuPrincipalView = null;
            loginView.setVisible(true);
            this.usuario = null;
        }
    }

    /**
     * Registra un nuevo usuario con los datos ingresados en el formulario de registro.
     * Valida que todos los campos estén completos, que las contraseñas coincidan,
     * que el nombre de usuario no esté duplicado, y que la fecha de nacimiento sea válida.
     * Si todo es correcto, crea el usuario, lo guarda en el DAO y cierra la vista de registro.
     */

    private void crear() {
        String nombreCompleto = registrarseView.getTxtNombreCompleto().getText();
        String username = registrarseView.getTxtUsuario().getText();
        String contrasenia = new String(registrarseView.getTxtContrasenia().getPassword());
        String confirmarContrasenia = new String(registrarseView.getTxtConfirmarContrasenia().getPassword());
        String fechaNacimiento = registrarseView.getTxtFechaNacimiento().getText();
        String correo = registrarseView.getTxtCorreo().getText();
        String telefono = registrarseView.getTxtTelefono().getText();

        String pregunta1 = (String) registrarseView.getCbxPregunta1().getSelectedItem();
        String respuesta1 = registrarseView.getTxtPregunta1().getText();
        String pregunta2 = (String) registrarseView.getCbxPregunta2().getSelectedItem();
        String respuesta2 = registrarseView.getTxtPregunta2().getText();
        String pregunta3 = (String) registrarseView.getCbxPregunta3().getSelectedItem();
        String respuesta3 = registrarseView.getTxtPregunta3().getText();

        if (username.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()
                || nombreCompleto.isEmpty() || fechaNacimiento.isEmpty()
                || correo.isEmpty() || telefono.isEmpty()
                || respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty()) {
            registrarseView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            registrarseView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registrarseView.mostrarMensaje("Ya existe un usuario con ese nombre.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia);
        DateTimeFormatter formatter;
        try {
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);
            nuevoUsuario.setFechaNacimiento(fecha);
        } catch (DateTimeParseException ex) {
            registrarseView.mostrarMensaje("La fecha debe tener el formato dd-MM-yyyy y ser válida.");
            return;
        }
        nuevoUsuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));
        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setPregunta1(pregunta1);
        nuevoUsuario.setRespuesta1(respuesta1);
        nuevoUsuario.setPregunta2(pregunta2);
        nuevoUsuario.setRespuesta2(respuesta2);
        nuevoUsuario.setPregunta3(pregunta3);
        nuevoUsuario.setRespuesta3(respuesta3);

        usuarioDAO.crear(nuevoUsuario);
        registrarseView.mostrarMensaje("Usuario registrado con éxito.");
        registrarseView.dispose();
    }

    /**
     * Autentica al usuario utilizando las credenciales ingresadas en la vista de login.
     * Si la autenticación es exitosa, se guarda el usuario autenticado y se cierra la vista de login.
     * Si las credenciales son incorrectas, se muestra un mensaje de error.
     */

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        } else {
            loginView.mostrarMensaje("Bienvenido al sistema: " + username);
            loginView.dispose();
        }
    }

    /**
     * Retorna el usuario autenticado actualmente en el sistema.
     *
     * @return Usuario autenticado, o null si no hay sesión activa.
     */

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

    /**
     * Configura las dependencias necesarias para la recuperación de contraseña,
     * incluyendo la vista de cuestionario, la vista de cambio de contraseña,
     * el DAO de preguntas y el manejador de internacionalización.
     *
     * @param cuestionarioView Vista para ingresar respuestas a preguntas de seguridad.
     * @param cuestionarioRecuView Vista para cambiar la contraseña.
     * @param preguntasDAO DAO que gestiona las preguntas de seguridad.
     * @param mensajeHandler Manejador de internacionalización de mensajes.
     */

    public void setPreguntasDependencias(CuestionarioView cuestionarioView,
                                         CambiarContraseniaView cuestionarioRecuView,
                                         PreguntasDAO preguntasDAO,
                                         MensajeInternacionalizacionHandler mensajeHandler) {
        this.recuperarContraseniaView = cuestionarioView;
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Configura los eventos para la vista de recuperación de contraseña.
     * Incluye la carga dinámica de preguntas de seguridad al ingresar el usuario
     * y la validación de la respuesta ingresada.
     */

    private void configurarEventosRecuperacion() {
        recuperarContraseniaView.getTxtUsuario().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                String username = recuperarContraseniaView.getTxtUsuario().getText().trim();
                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario != null) {
                    JComboBox<String> cbx = recuperarContraseniaView.getCbxPreguntas();
                    cbx.removeAllItems();
                    cbx.addItem(usuario.getPregunta1());
                    cbx.addItem(usuario.getPregunta2());
                    cbx.addItem(usuario.getPregunta3());
                }
            }
        });

        recuperarContraseniaView.getBtnValidar().addActionListener(e -> {
            String username = recuperarContraseniaView.getTxtUsuario().getText().trim();
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            if (usuario == null) {
                recuperarContraseniaView.mostrarMensaje("Usuario no válido.");
                return;
            }

            String preguntaSeleccionada = (String) recuperarContraseniaView.getCbxPreguntas().getSelectedItem();
            String respuestaIngresada = recuperarContraseniaView.getTxtRespuesta().getText().trim();

            if (preguntaSeleccionada == null || respuestaIngresada.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje("Por favor seleccione una pregunta y escriba una respuesta.");
                return;
            }

            boolean esCorrecta = (
                    (preguntaSeleccionada.equals(usuario.getPregunta1()) && respuestaIngresada.equalsIgnoreCase(usuario.getRespuesta1())) ||
                            (preguntaSeleccionada.equals(usuario.getPregunta2()) && respuestaIngresada.equalsIgnoreCase(usuario.getRespuesta2())) ||
                            (preguntaSeleccionada.equals(usuario.getPregunta3()) && respuestaIngresada.equalsIgnoreCase(usuario.getRespuesta3()))
            );

            if (esCorrecta) {
                recuperarContraseniaView.mostrarMensaje("Respuesta correcta. Ahora puedes cambiar tu contraseña.");
            } else {
                recuperarContraseniaView.mostrarMensaje("Respuesta incorrecta.");
            }
        });
    }

    /**
     * Modifica la fecha de creación del carrito al momento actual.
     * Busca el carrito por su código desde la vista y actualiza su fecha.
     * Muestra un mensaje de éxito o error según el resultado.
     */

    private void modificarFecha() {
        int codigo = Integer.parseInt(carritoModificarView.getTxtCodigo().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);
        if (c != null) {
            c.setFechaCreacion(new Date());
            carritoDAO.actualizar(c); // <-- ESTA LÍNEA ES IMPORTANTE
            carritoModificarView.mostrarMensaje("Fecha modificada correctamente.");
        } else {
            carritoModificarView.mostrarMensaje("Carrito no encontrado.");
        }
    }

    /**
     * Modifica los datos del usuario (nombre, correo, teléfono y fecha de nacimiento)
     * si el usuario existe y la fecha es válida.
     * Muestra mensajes en la vista si hay errores o si la operación se realiza correctamente.
     */

    private void modificarUsuario() {
        String usuarioBuscar = usuarioModificarView.getTxtBuscarUsuario().getText();
        Usuario usuario = usuarioDAO.buscarPorUsername(usuarioBuscar);
        if (usuario == null) {
            usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
            return;
        }

        String nombre = usuarioModificarView.getTxtNombre().getText();
        String correo = usuarioModificarView.getTxtCorreo().getText();
        String telefono = usuarioModificarView.getTxtTelefono().getText();
        String fechaStr = usuarioModificarView.getTxtFecha().getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);

            usuario.setNombreCompleto(nombre);
            usuario.setCorreo(correo);
            usuario.setTelefono(telefono);
            usuario.setFechaNacimiento(fechaNacimiento);

            usuarioModificarView.mostrarMensaje("Usuario modificado con éxito.");
        } catch (Exception ex) {
            usuarioModificarView.mostrarMensaje("Error al modificar: " + ex.getMessage());
        }
    }

    /**
     * Configura los eventos de la vista de modificación de usuarios.
     * Incluye la búsqueda de usuarios y la modificación de sus datos,
     * además de la acción del botón Cancelar para limpiar y cerrar la ventana.
     */

    public void configurarEventosModificar() {
        usuarioModificarView.getBtnBuscar().addActionListener(e -> {
            String username = usuarioModificarView.getTxtBuscarUsuario().getText();
            Usuario usuario = usuarioDAO.buscarPorUsername(username);

            if (usuario != null) {
                usuarioModificarView.getTxtNombre().setText(usuario.getNombreCompleto());
                usuarioModificarView.getTxtCorreo().setText(usuario.getCorreo());
                usuarioModificarView.getTxtTelefono().setText(usuario.getTelefono());

                // Convertir LocalDate a String para el campo de texto
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaTexto = usuario.getFechaNacimiento().format(formatter);
                usuarioModificarView.getTxtFecha().setText(fechaTexto);

            } else {
                usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
            }
        });

        usuarioModificarView.getBtnModificarUsuario().addActionListener(e -> {
            String username = usuarioModificarView.getTxtBuscarUsuario().getText();
            Usuario usuario = usuarioDAO.buscarPorUsername(username);

            if (usuario != null) {
                String nombre = usuarioModificarView.getTxtNombre().getText();
                String correo = usuarioModificarView.getTxtCorreo().getText();
                String telefono = usuarioModificarView.getTxtTelefono().getText();
                String fechaTexto = usuarioModificarView.getTxtFecha().getText();

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fecha = LocalDate.parse(fechaTexto, formatter);

                    usuario.setNombreCompleto(nombre);
                    usuario.setCorreo(correo);
                    usuario.setTelefono(telefono);
                    usuario.setFechaNacimiento(fecha);

                    usuarioModificarView.mostrarMensaje("Usuario modificado correctamente.");
                    usuarioModificarView.limpiarCampos();
                } catch (Exception ex) {
                    usuarioModificarView.mostrarMensaje("Formato de fecha incorrecto. Usa yyyy-MM-dd");
                }
            } else {
                usuarioModificarView.mostrarMensaje("No se pudo modificar. Usuario no encontrado.");
            }
        });

        usuarioModificarView.getBtnCancelar().addActionListener(e -> {
            usuarioModificarView.limpiarCampos();
            usuarioModificarView.dispose();
        });
    }

    /**
     * Establece la vista de modificación de usuario y llama a la configuración de sus eventos.
     * @param usuarioModificarView Vista usada para modificar usuarios
     */

    public void setUsuarioModificarView(UsuarioModificarView usuarioModificarView) {
        this.usuarioModificarView = usuarioModificarView;
        configurarEventosModificar();
    }

    /**
     * Configura los eventos para filtrar usuarios en la vista de listado según el filtro seleccionado
     * (nombre, correo, rol o código). Actualiza la tabla con los resultados encontrados.
     */

    public void configurarEventosLista() {
        usuarioListaView.getBtnBuscar().addActionListener(e -> {
            String filtro = (String) usuarioListaView.getCbxFiltro().getSelectedItem();
            String valor = usuarioListaView.getTxtNombre().getText().trim().toLowerCase();

            List<Usuario> filtrados = usuarioDAO.obtenerTodos().stream()
                    .filter(u -> {
                        switch (filtro.toLowerCase()) {
                            case "nombre":
                                return u.getNombreCompleto().toLowerCase().contains(valor);
                            case "correo":
                                return u.getCorreo().toLowerCase().contains(valor);
                            case "rol":
                                return u.getRol().name().toLowerCase().contains(valor);
                            case "código":
                                return String.valueOf(u.getCodigo()).contains(valor);
                            default:
                                return false;
                        }
                    }).toList();

            DefaultTableModel modelo = usuarioListaView.getModelo();
            modelo.setRowCount(0);

            for (Usuario u : filtrados) {
                modelo.addRow(new Object[]{
                        u.getCodigo(),
                        u.getNombreCompleto(),
                        u.getCorreo(),
                        u.getTelefono(),
                        u.getRol().name()
                });
            }
        });

        usuarioListaView.getBtnCerrar().addActionListener(e -> usuarioListaView.dispose());
    }

    /**
     * Actualiza la tabla de la vista de usuarios con todos los usuarios registrados en el sistema.
     * Obtiene los datos desde el DAO y los carga en el modelo de la tabla.
     */

    public void actualizarTablaUsuarios() {
        List<Usuario> lista = usuarioDAO.obtenerTodos();
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0); // Limpia la tabla

        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                    u.getCodigo(),
                    u.getNombreCompleto(),
                    u.getCorreo(),
                    u.getTelefono(),
                    u.getRol().name()
            });
        }
    }

    /**
     * Filtra la lista de usuarios mostrada en la tabla según el criterio seleccionado en el combo box
     * (nombre, correo, rol o código) y el texto ingresado por el usuario.
     * Solo se muestran los usuarios que coincidan parcial o totalmente con el texto ingresado.
     */

    public void filtrarUsuarios() {
        String filtro = usuarioListaView.getCbxFiltro().getSelectedItem().toString();
        String texto = usuarioListaView.getTxtNombre().getText().trim().toLowerCase();

        usuarioListaView.getBtnBuscar().addActionListener(e -> filtrarUsuarios());
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0);

        List<Usuario> usuarios = usuarioDAO.obtenerTodos();

        for (Usuario usuario : usuarios) {
            boolean coincide = false;
            switch (filtro) {
                case "Nombre":
                    coincide = usuario.getNombreCompleto().toLowerCase().contains(texto);
                    break;
                case "Correo":
                    coincide = usuario.getCorreo().toLowerCase().contains(texto);
                    break;
                case "Rol":
                    coincide = usuario.getRol().toString().toLowerCase().contains(texto);
                    break;
                case "Código":
                    coincide = String.valueOf(usuario.getCodigo()).contains(texto);
                    break;
            }

            if (coincide) {
                modelo.addRow(new Object[]{
                        usuario.getCodigo(),
                        usuario.getNombreCompleto(),
                        usuario.getCorreo(),
                        usuario.getTelefono(),
                        usuario.getRol().toString()
                });
            }
        }
    }

    /**
     * Busca y retorna una lista de usuarios que coincidan con el filtro proporcionado.
     * @param filtro Criterio por el cual se va a buscar (nombre, correo, rol o código)
     * @param valor Texto a comparar contra el campo correspondiente de cada usuario
     * @return Lista de usuarios que cumplen con el filtro
     */

    private List<Usuario> buscarUsuariosPor(String filtro, String valor) {
        return usuarioDAO.obtenerTodos().stream()
                .filter(u -> {
                    switch (filtro.toLowerCase()) {
                        case "nombre": return u.getNombreCompleto().toLowerCase().contains(valor.toLowerCase());
                        case "correo": return u.getCorreo().toLowerCase().contains(valor.toLowerCase());
                        case "rol": return u.getRol().name().toLowerCase().contains(valor.toLowerCase());
                        case "codigo": return String.valueOf(u.getCodigo()).contains(valor);
                        default: return false;
                    }
                }).toList();
    }
}
