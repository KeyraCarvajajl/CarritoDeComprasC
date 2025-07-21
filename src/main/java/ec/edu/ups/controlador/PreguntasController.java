package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;

import javax.swing.*;
import java.util.List;

/**
 * Controlador encargado de manejar la lógica relacionada con la recuperación de contraseña
 * mediante preguntas de seguridad.
 *
 * Administra los eventos generados en las vistas {@link CuestionarioView} y {@link CambiarContraseniaView},
 * y utiliza el {@link PreguntasDAO} para obtener las preguntas y respuestas del usuario.
 *
 * @author Keyra
 */

public class PreguntasController {

    /**
     * Vista donde el usuario responde a las preguntas de seguridad para recuperar su contraseña.
     */

    private final CuestionarioView recuperarContraseniaView;

    /**
     * Vista que permite al usuario ingresar una nueva contraseña tras validar correctamente su respuesta.
     */

    private final CambiarContraseniaView cambiarContraseniaView;

    /**
     * Objeto DAO que proporciona acceso a las preguntas de seguridad almacenadas para cada usuario.
     */

    private final PreguntasDAO preguntasDAO;

    /**
     * Manejador de mensajes que permite la carga dinámica de textos en diferentes idiomas.
     * Es utilizado para soportar internacionalización en las vistas.
     */

    private final MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Panel principal donde se agregan las vistas internas tipo JInternalFrame.
     * Permite organizar múltiples ventanas dentro del mismo marco principal.
     */

    private final JDesktopPane desktopPane;

    /**
     * Lista de preguntas de seguridad asociadas al usuario que intenta recuperar su contraseña.
     * Se utiliza para validar la respuesta ingresada.
     */

    private List<Preguntas> preguntasUsuario;

    /**
     * Constructor de la clase PreguntasController.
     *
     * @param recuperarContraseniaView Vista de recuperación de contraseña (preguntas).
     * @param cambiarContraseniaView Vista para cambiar la contraseña si la respuesta es válida.
     * @param preguntasDAO DAO que permite acceder a las preguntas almacenadas.
     * @param mensajeHandler Manejador para los mensajes internacionalizados.
     * @param desktopPane Contenedor MDI donde se agregan las vistas internas.
     */

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

    /**
     * Configura los eventos de la vista de recuperación de contraseña,
     * específicamente la validación de la respuesta ingresada por el usuario.
     */

    private void configurarEventos() {
        recuperarContraseniaView.getBtnValidar().addActionListener(e -> {
            String username = recuperarContraseniaView.getTxtUsuario().getText().trim();
            String respuestaIngresada = recuperarContraseniaView.getTxtRespuesta().getText().trim();

            /**
             * Validación de campos vacíos
             */

            if (username.isEmpty() || respuestaIngresada.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.campos.vacios"));
                return;
            }

            /**
             * Buscar preguntas de usuario
             */

            preguntasUsuario = preguntasDAO.buscarPorUsername(username);
            if (preguntasUsuario == null || preguntasUsuario.isEmpty()) {
                recuperarContraseniaView.mostrarMensaje(mensajeHandler.get("validacion.usuario.noexiste"));
                return;
            }

            /**
             * Validar respuesta
             */

            boolean esCorrecta = validarRespuesta(respuestaIngresada);

            /**
             * Abrir vista para camabia la contraseña si se validó
             */

            if (!cambiarContraseniaView.isVisible()) {
                desktopPane.add(cambiarContraseniaView);
                cambiarContraseniaView.setVisible(true);
            } else {
                cambiarContraseniaView.toFront();
            }

            /**
             * Evento para cancelar el cambio de contraseña
             */
            recuperarContraseniaView.dispose();

            cambiarContraseniaView.getBtnCancelar().addActionListener(actionEvent -> {
                cambiarContraseniaView.dispose(); // Cierra solo la ventana de cambiar contraseña
            });

        });
    }

    /**
     * Valida si la respuesta ingresada por el usuario corresponde a la pregunta seleccionada.
     *
     * @param respuestaIngresada Respuesta escrita por el usuario.
     * @return true si la respuesta es correcta, false si es incorrecta o no se seleccionó pregunta.
     */

    public boolean validarRespuesta(String respuestaIngresada) {
        int index = recuperarContraseniaView.getCbxPreguntas().getSelectedIndex();
        if (index >= 0 && index < preguntasUsuario.size()) {
            Preguntas preguntaSeleccionada = preguntasUsuario.get(index);
            return preguntaSeleccionada.getRespuesta().equalsIgnoreCase(respuestaIngresada.trim());
        }
        return false;
    }
}
