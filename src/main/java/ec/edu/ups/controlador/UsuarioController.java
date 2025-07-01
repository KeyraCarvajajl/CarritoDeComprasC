package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.Principal;
import ec.edu.ups.vista.usuario.UsuarioRegistroView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final UsuarioRegistroView registrarseView;

    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView, UsuarioRegistroView registrarseView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.registrarseView = registrarseView;
        this.usuario = null;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarseView.setVisible(true);
            }
        });

        registrarseView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        loginView.getBtnOlvidarContrasenia().addActionListener(e -> {
            RecuperarContrasenaView recuperarView = new RecuperarContrasenaView(mensajeHandler);
            recuperarView.setVisible(true);
        });

    }

    private void autenticar() {
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
        } else {
            loginView.mostrarMensaje("Inicio de sesión exitoso.");
            loginView.dispose();
            Principal ventanaPrincipal = new Principal();
            ventanaPrincipal.mostrarUsuario(usuario.getUsername());
            ventanaPrincipal.setVisible(true);
        }
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }

    private void registrarUsuario() {
        String username = registrarseView.getTxtUsuario().getText();
        String contrasenia = registrarseView.getContraseniaComoTexto();
        String confirmar = registrarseView.getConfirmarContraseniaComoTexto();

        if (username.isEmpty() || contrasenia.isEmpty() || confirmar.isEmpty()) {
            registrarseView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        if (!contrasenia.equals(confirmar)) {
            registrarseView.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            registrarseView.mostrarMensaje("El usuario ya existe.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(username, contrasenia, Rol.USUARIO);
        usuarioDAO.crear(nuevoUsuario);

        registrarseView.mostrarMensaje("Usuario registrado exitosamente.");
        registrarseView.limpiarCampos();
        registrarseView.setVisible(false);
    }
}
