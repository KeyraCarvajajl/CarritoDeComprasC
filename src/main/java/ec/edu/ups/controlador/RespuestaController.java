package ec.edu.ups.controlador;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las respuestas
 * a preguntas de seguridad de los usuarios.
 * <p>
 * Esta clase sirve como intermediaria entre la lógica de negocio y el DAO que maneja
 * el almacenamiento de las respuestas. Permite registrar respuestas, consultarlas
 * por nombre de usuario y validarlas durante el proceso de recuperación de contraseña.
 * </p>
 *
 * @author Keyra Carvajal
 */

public class RespuestaController {

    private RespuestaDAO respuestaDAO;

    /**
     * Constructor del controlador que inicializa el DAO correspondiente.
     * <p>
     * Es necesario que {@code respuestaDAO} sea inyectado o inicializado externamente.
     * Actualmente, este constructor está mal implementado ya que no inicializa correctamente
     * el campo {@code respuestaDAO}.
     * </p>
     *
     * @deprecated El constructor actual no inicializa {@code respuestaDAO} adecuadamente.
     *             Se recomienda usar otro constructor con inyección o setear el DAO después.
     */

    public RespuestaController() {
        this.respuestaDAO = respuestaDAO;
    }

    /**
     * Guarda una respuesta de seguridad proporcionada por un usuario.
     *
     * @param respuesta Objeto {@link Respuesta} que contiene la pregunta, respuesta
     *                  y el nombre de usuario al que está asociada.
     */

    public void guardarRespuesta(Respuesta respuesta) {
        respuestaDAO.guardarRespuesta(respuesta);
    }

    /**
     * Obtiene todas las respuestas de seguridad registradas por un usuario específico.
     *
     * @param username El nombre de usuario asociado a las respuestas.
     * @return Lista de objetos {@link Respuesta} correspondientes al usuario.
     */

    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        return respuestaDAO.obtenerRespuestasPorUsuario(username);
    }

    /**
     * Valida si las respuestas proporcionadas coinciden con las almacenadas para el usuario.
     *
     * @param username  El nombre de usuario que está recuperando su contraseña.
     * @param respuestas Lista de respuestas ingresadas por el usuario.
     * @return {@code true} si todas las respuestas son correctas, {@code false} en caso contrario.
     */

    public boolean validarRespuestas(String username, List<Respuesta> respuestas) {
        return respuestaDAO.validarRespuestas(username, respuestas);
    }
}

