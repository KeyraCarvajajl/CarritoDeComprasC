package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.RecuperarContraseniaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class RecuperarContraseniaController {

    private final RecuperarContraseniaView view;
    private final UsuarioDAO usuarioDAO;
    private final MensajeInternacionalizacionHandler mensajeHandler;

    public RecuperarContraseniaController(RecuperarContraseniaView view,
                                          UsuarioDAO usuarioDAO,
                                          MensajeInternacionalizacionHandler mensajeHandler) {
        this.view = view;
        this.usuarioDAO = usuarioDAO;
        this.mensajeHandler = mensajeHandler;

        configurarEventos();
    }

    private void configurarEventos() {
        view.getBtnValidar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarRespuestas();
            }
        });

        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void validarRespuestas() {
        String username = view.getTxtUsuario().getText().trim();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario == null) {
            view.mostrarMensaje(mensajeHandler.get("recuperar.usuario.no.encontrado"));
            return;
        }

        String respuestaIngresada1 = view.getTxtRespuesta1().getText().trim();
        String respuestaIngresada2 = view.getTxtRespuesta2().getText().trim();
        String respuestaIngresada3 = view.getTxtRespuesta3().getText().trim();

        boolean respuestasCorrectas =
                respuestaIngresada1.equalsIgnoreCase(usuario.getRespuesta1()) &&
                        respuestaIngresada2.equalsIgnoreCase(usuario.getRespuesta2()) &&
                        respuestaIngresada3.equalsIgnoreCase(usuario.getRespuesta3());

        if (respuestasCorrectas) {
            view.mostrarMensaje(mensajeHandler.get("recuperar.validacion.exito"));
        } else {
            view.mostrarMensaje(mensajeHandler.get("recuperar.validacion.fallo"));
        }
    }
}
