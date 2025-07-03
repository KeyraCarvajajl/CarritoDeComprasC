package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import java.util.List;

public class PreguntasController {

    private final CuestionarioView recuperarContraseniaView;
    private final PreguntasDAO preguntasDAO;
    private final MensajeInternacionalizacionHandler mensajeHandler;

    private List<Preguntas> preguntasUsuario;

    public PreguntasController(CuestionarioView recuperarContraseniaView,
                               PreguntasDAO preguntasDAO,
                               MensajeInternacionalizacionHandler mensajeHandler) {
        this.recuperarContraseniaView = recuperarContraseniaView;
        this.preguntasDAO = preguntasDAO;
        this.mensajeHandler = mensajeHandler;
        configurarEventos();
    }

    private void configurarEventos() {
        recuperarContraseniaView.getBtnValidar().addActionListener(e -> {
            String username = recuperarContraseniaView.getTxtUsuario().getText().trim();

            if (username.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.usuario.vacio"));
                return;
            }

            preguntasUsuario = preguntasDAO.buscarPorUsername(username);
            recuperarContraseniaView.getCbxPreguntas().removeAllItems();

            if (preguntasUsuario == null || preguntasUsuario.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.usuario.noexiste"));
            } else {
                for (Preguntas p : preguntasUsuario) {
                    recuperarContraseniaView.getCbxPreguntas().addItem(p.getPregunta());
                }
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.usuario.encontrado"));
            }
        });

        recuperarContraseniaView.getBtnCancelar().addActionListener(e -> {
            recuperarContraseniaView.dispose();
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
