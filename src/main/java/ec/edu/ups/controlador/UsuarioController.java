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

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private RegistrarseView registrarseView;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioEliminarView usuarioEliminarView;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private CuestionarioView recuperarContraseniaView;
    private CarritoDAO carritoDAO;
    private CarritoModificarView carritoModificarView;
    private UsuarioModificarView usuarioModificarView;
    private UsuarioListaView usuarioListaView;

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


    public void setRecuperarContraseniaView(CuestionarioView recuperarContraseniaView) {
        this.recuperarContraseniaView = recuperarContraseniaView;
        configurarEventoOlvidoContrasena();
        configurarEventosRecuperacion();
    }

    public void setUsuarioEliminarView(UsuarioEliminarView usuarioEliminarView) {
        this.usuarioEliminarView = usuarioEliminarView;
        configurarEventosEliminar();
    }

    public void setMenuPrincipalView(MenuPrincipalView menuPrincipalView) {
        this.menuPrincipalView = menuPrincipalView;
        configurarEventoCerrarSesion();
    }

    public void setRegistrarseView(RegistrarseView registrarseView) {
        this.registrarseView = registrarseView;
        inicializarListenersRegistro();
    }

    public void setUsuarioListarView(UsuarioListaView usuarioListarView) {
        this.usuarioListaView = usuarioListarView;
        configurarEventosListaUsuarios();
    }

    private void configurarEventoOlvidoContrasena() {
        loginView.getBtnOlvidarContrasenia().addActionListener(e -> {
            if (recuperarContraseniaView != null) {
                recuperarContraseniaView.setVisible(true);
            } else {
                System.out.println("recuperarContraseniaView es null");
            }
        });
    }

    private void configurarEventosListaUsuarios() {
        usuarioListaView.getBtnBuscar().addActionListener(e -> buscarUsuarios());
    }

    private void configurarEventosEliminar() {
        usuarioEliminarView.getBtnEliminarUsuario().addActionListener(e -> eliminarUsuario());
    }

    private void configurarEventoCerrarSesion() {
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void configurarEventosLogin() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
        loginView.getBtnRegistrarse().addActionListener(e -> {
            if (registrarseView != null) {
                registrarseView.setVisible(true);
            }
        });
    }

    public void inicializarListenersRegistro() {
        registrarseView.getBtnRegistro().addActionListener(e -> crear());

        registrarseView.getBtnCancelar().addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(registrarseView.getContentPane());
            if (frame != null) {
                frame.dispose();
            }
        });
    }

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

    private void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listarTodos();
        DefaultTableModel modelo = usuarioListaView.getModelo();
        modelo.setRowCount(0);
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol().name()});
        }
    }

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



    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(menuPrincipalView, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            menuPrincipalView.setVisible(false);
            menuPrincipalView = null;
            loginView.setVisible(true);
            this.usuario = null;
        }
    }

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

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

    public void setPreguntasDependencias(CuestionarioView cuestionarioView,
                                         CambiarContraseniaView cuestionarioRecuView,
                                         PreguntasDAO preguntasDAO,
                                         MensajeInternacionalizacionHandler mensajeHandler) {
        this.recuperarContraseniaView = cuestionarioView;
        this.mensajeHandler = mensajeHandler;
    }

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

    public void setUsuarioModificarView(UsuarioModificarView usuarioModificarView) {
        this.usuarioModificarView = usuarioModificarView;
        configurarEventosModificar();
    }

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
