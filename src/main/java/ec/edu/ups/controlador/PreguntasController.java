package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;
import java.util.List;

public class PreguntasController {

    private final CuestionarioView recuperarContraseniaView;
    private final CambiarContraseniaView cambiarContraseniaView;
    private final PreguntasDAO preguntasDAO;
    private final MensajeInternacionalizacionHandler mensajeHandler;
    private final JDesktopPane desktopPane;

    private List<Preguntas> preguntasUsuario;

    public PreguntasController(CuestionarioView recuperarContraseniaView,
                               CambiarContraseniaView cambiarContraseniaView,
                               PreguntasDAO preguntasDAO,
                               MensajeInternacionalizacionHandler mensajeHandler,
                               JDesktopPane desktopPane) {
        this.recuperarContraseniaView = recuperarContraseniaView;
        this.cambiarContraseniaView = cambiarContraseniaView;
        this.preguntasDAO = preguntasDAO;
        this.mensajeHandler = mensajeHandler;
        this.desktopPane = desktopPane;
        configurarEventos();
    }

    private void configurarEventos() {
        recuperarContraseniaView.getBtnValidar().addActionListener(e -> {
            String username = recuperarContraseniaView.getTxtUsuario().getText().trim();
            String respuestaIngresada = recuperarContraseniaView.getTxtRespuesta().getText().trim();

            if (username.isEmpty() || respuestaIngresada.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.campos.vacios"));
                return;
            }

            preguntasUsuario = preguntasDAO.buscarPorUsername(username);
            if (preguntasUsuario == null || preguntasUsuario.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.usuario.noexiste"));
                return;
            }

            boolean esCorrecta = validarRespuesta(respuestaIngresada);
            // Mostrar en una nueva ventana independiente
            if (!cambiarContraseniaView.isVisible()) {
                desktopPane.add(cambiarContraseniaView);
                cambiarContraseniaView.setVisible(true);
            } else {
                cambiarContraseniaView.toFront();
            }

            recuperarContraseniaView.dispose();

            cambiarContraseniaView.getBtnCancelar().addActionListener(actionEvent -> {
                cambiarContraseniaView.dispose(); // Cierra solo la ventana de cambiar contraseña
            });

        });
    }



    public boolean validarRespuesta(String respuestaIngresada) {
        int index = recuperarContraseniaView.getCbxPreguntas().getSelectedIndex();
        if (index >= 0 && index < preguntasUsuario.size()) {
            Preguntas preguntaSeleccionada = preguntasUsuario.get(index);
            return preguntaSeleccionada.getRespuesta().equalsIgnoreCase(respuestaIngresada.trim());
        }
        return false;
    }

}
