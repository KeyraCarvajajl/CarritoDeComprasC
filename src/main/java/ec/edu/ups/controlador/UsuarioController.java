package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.Principal;
import ec.edu.ups.vista.RecuperarContraseniaView;
import ec.edu.ups.vista.usuario.UsuarioRegistroView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final LoginView loginView;
    private final UsuarioRegistroView registroView;
    private final MensajeInternacionalizacionHandler mensajeHandler;

    private Usuario usuario;

    public UsuarioController(UsuarioDAO usuarioDAO, PreguntaDAO preguntaDAO,
                             LoginView loginView, UsuarioRegistroView registroView,
                             MensajeInternacionalizacionHandler mensajeHandler) {
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.loginView = loginView;
        this.registroView = registroView;
        this.mensajeHandler = mensajeHandler;

        configurarEventos();
    }

    private void configurarEventos() {
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });

        loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registroView.setVisible(true);
            }
        });

        loginView.getBtnOlvidarContrasenia().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecuperarContraseniaView recuperarView = new RecuperarContraseniaView(mensajeHandler, preguntaDAO);
                // 👇 Agregamos el controlador correctamente
                RecuperarContraseniaController recuperarController = new RecuperarContraseniaController(
                        recuperarView, usuarioDAO, mensajeHandler
                );
                recuperarView.setVisible(true);
            }
        });

        registroView.getBtnRegistrarse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
    }


    private void autenticar() {
        String username = loginView.getTxtUsername().getText().trim();
        String contrasenia = new String(loginView.getTxtContrasenia().getPassword()).trim();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if (usuario == null) {
            loginView.mostrarMensaje(mensajeHandler.get("login.error"));
        } else {
            loginView.mostrarMensaje(mensajeHandler.get("login.exito"));
            loginView.dispose();

            Principal principal = new Principal(usuario, mensajeHandler, usuarioDAO);
            principal.setVisible(true);
        }
    }

    private void registrarUsuario() {
        String usuarioNombre = registroView.getTxtUsuario().getText().trim();
        String contrasenia = new String(registroView.getTxtContrasenia().getPassword()).trim();
        String confirmar = new String(registroView.getTxtConfirmarContrasenia().getPassword()).trim();
        String nombreCompleto = registroView.getTxtNombreCompleto().getText().trim();
        String correo = registroView.getTxtCorreo().getText().trim();
        String telefono = registroView.getTxtTelefono().getText().trim();
        String fechaNacimiento = registroView.getTxtFechaNacimiento().getText().trim();

        if (usuarioNombre.isEmpty() || contrasenia.isEmpty() || confirmar.isEmpty()
                || nombreCompleto.isEmpty() || correo.isEmpty() || telefono.isEmpty()
                || fechaNacimiento.isEmpty()) {
            registroView.mostrarMensaje(mensajeHandler.get("registro.campos.vacios"));
            return;
        }

        if (!contrasenia.equals(confirmar)) {
            registroView.mostrarMensaje(mensajeHandler.get("registro.contrasena.no.coincide"));
            return;
        }

        if (usuarioDAO.buscarPorUsername(usuarioNombre) != null) {
            registroView.mostrarMensaje(mensajeHandler.get("registro.usuario.ya.existe"));
            return;
        }

        LocalDate fechaNac;
        try {
            fechaNac = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            registroView.mostrarMensaje(mensajeHandler.get("registro.fecha.invalida"));
            return;
        }

        Usuario nuevoUsuario = new Usuario(usuarioNombre, contrasenia, Rol.USUARIO);
        nuevoUsuario.setNombreCompleto(nombreCompleto);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setFechaNacimiento(fechaNac);

        usuarioDAO.crear(nuevoUsuario);

        // Guardar preguntas
        Pregunta p1 = new Pregunta(usuarioNombre,
                (String) registroView.getCbxPregunta1().getSelectedItem(),
                registroView.getTxtPregunta1().getText().trim());

        Pregunta p2 = new Pregunta(usuarioNombre,
                (String) registroView.getCbxPregunta2().getSelectedItem(),
                registroView.getTxtPregunta2().getText().trim());

        Pregunta p3 = new Pregunta(usuarioNombre,
                (String) registroView.getCbxPregunta3().getSelectedItem(),
                registroView.getTxtPregunta3().getText().trim());

        preguntaDAO.guardarPregunta(p1);
        preguntaDAO.guardarPregunta(p2);
        preguntaDAO.guardarPregunta(p3);

        registroView.mostrarMensaje(mensajeHandler.get("registro.exito"));
        registroView.limpiarCampos();
        registroView.setVisible(false);
    }

    public Usuario getUsuarioAutenticado() {
        return usuario;
    }
}
