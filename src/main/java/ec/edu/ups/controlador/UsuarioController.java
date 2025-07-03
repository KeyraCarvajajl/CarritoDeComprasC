package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.preguntas.CuestionarioRecuView;
import ec.edu.ups.vista.usuario.LoginView;
import ec.edu.ups.vista.usuario.RegistrarseView;
import ec.edu.ups.vista.usuario.UsuarioEliminarView;
import ec.edu.ups.vista.usuario.UsuarioListaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private RegistrarseView registrarseView;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioEliminarView usuarioEliminarView;
    private UsuarioListaView usuarioListarView;
    private MensajeInternacionalizacionHandler mensajeI;
    private CuestionarioView recuperarContraseniaView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, RegistrarseView registrarseView, MensajeInternacionalizacionHandler mensajeI) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.registrarseView = registrarseView;
        this.mensajeI = mensajeI;
        configurarEventosLogin();
    }

    public void setRecuperarContraseniaView(CuestionarioView recuperarContraseniaView) {
        this.recuperarContraseniaView = recuperarContraseniaView;
        configurarEventoOlvidoContrasena();
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
        configurarEventosRegistro();
    }

    public void setUsuarioListarView(UsuarioListaView usuarioListarView) {
        this.usuarioListarView = usuarioListarView;
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
        usuarioListarView.getBtnBuscar().addActionListener(e -> buscarUsuarios());
        usuarioListarView.getBtnListar().addActionListener(e -> listarUsuarios());
    }

    private void configurarEventosEliminar() {
        usuarioEliminarView.getBtnEliminarUsuario().addActionListener(e -> eliminarUsuario());
    }

    private void configurarEventoCerrarSesion() {
        menuPrincipalView.getMenuItemCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void configurarEventosRegistro() {
        registrarseView.getBtnRegistrarse().addActionListener(e -> crear());
    }

    private void configurarEventosLogin() {
        loginView.getBtnIniciarSesion().addActionListener(e -> autenticar());
        loginView.getBtnRegistrarse().addActionListener(e -> {
            if (registrarseView != null) {
                registrarseView.setVisible(true);
            }
        });
    }

    private void buscarUsuarios() {
        String nombre = usuarioListarView.getTxtNombre().getText().trim();
        DefaultTableModel modelo = usuarioListarView.getModelo();
        modelo.setRowCount(0);

        if (nombre.isEmpty()) {
            usuarioListarView.mostrarMensaje("Ingrese un nombre de usuario para buscar.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(nombre);
        if (usuario != null) {
            modelo.addRow(new Object[]{usuario.getUsername(), usuario.getRol().name()});
        } else {
            usuarioListarView.mostrarMensaje("Usuario no encontrado.");
        }
    }

    private void listarUsuarios() {
        List<Usuario> lista = usuarioDAO.listarTodos();
        DefaultTableModel modelo = usuarioListarView.getModelo();
        modelo.setRowCount(0);
        for (Usuario u : lista) {
            modelo.addRow(new Object[]{u.getUsername(), u.getRol().name()});
        }
    }

    private void eliminarUsuario() {
        String usernameEliminar = usuarioEliminarView.getTxtNombre().getText();
        String contraseniaAdmin = new String(usuarioEliminarView.getTxtContraseña().getPassword());

        if (getUsuarioAutenticado() == null || getUsuarioAutenticado().getRol() != Rol.ADMINISTRADOR) {
            usuarioEliminarView.mostrarMensaje("No tienes permisos para eliminar usuarios.");
            return;
        }

        if (!getUsuarioAutenticado().getContrasenia().equals(contraseniaAdmin)) {
            usuarioEliminarView.mostrarMensaje("Contraseña incorrecta.");
            return;
        }

        Usuario usuarioAEliminar = usuarioDAO.buscarPorUsername(usernameEliminar);
        if (usuarioAEliminar == null) {
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado.");
            return;
        }

        if (usuarioAEliminar.getUsername().equals(getUsuarioAutenticado().getUsername())) {
            usuarioEliminarView.mostrarMensaje("No puedes eliminar tu propia cuenta.");
            return;
        }

        usuarioDAO.eliminar(usernameEliminar);
        usuarioEliminarView.mostrarMensaje("Usuario eliminado con éxito.");
    }

    private void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(menuPrincipalView, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            menuPrincipalView.setVisible(false);
            menuPrincipalView = null;
            loginView.setVisible(true);
            this.usuario = null;
        }
    }

    private void crear() {
        String usuarioT = registrarseView.getTxtUsuario().getText();
        String contrasenia = new String(registrarseView.getTxtContrasenia().getPassword());
        String confirmarContrasenia = new String(registrarseView.getTxtConfirmarContrasenia().getPassword());

        if (usuarioT.isEmpty() || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
            registrarseView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            registrarseView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(usuarioT) != null) {
            registrarseView.mostrarMensaje("Ya existe un usuario con ese nombre.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(usuarioT, contrasenia);
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
                                         CuestionarioRecuView cuestionarioRecuView,
                                         PreguntasDAO preguntasDAO,
                                         MensajeInternacionalizacionHandler mensajeHandler) {
        this.recuperarContraseniaView = cuestionarioView;
        this.mensajeI = mensajeHandler;
        // Puedes guardar los demás si los necesitas en atributos
    }

}

