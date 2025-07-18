package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria del DAO para respuestas de seguridad.
 *
 * <p>Esta clase gestiona una lista de respuestas asociadas a usuarios para
 * recuperación de contraseñas mediante preguntas de seguridad.</p>
 *
 * <p>Permite:
 * <ul>
 *   <li>Guardar nuevas respuestas de seguridad.</li>
 *   <li>Obtener respuestas por nombre de usuario.</li>
 *   <li>Validar si las respuestas ingresadas por el usuario coinciden con las almacenadas.</li>
 * </ul>
 * </p>
 *
 * <p>Uso exclusivo en almacenamiento temporal sin persistencia en archivos o base de datos.</p>
 *
 * @author Keyra
 */

public class RespuestaDAOMemoria implements RespuestaDAO {

    /** Lista de respuestas de seguridad almacenadas. */
    private List<Respuesta> respuestas = new ArrayList<>();

    /**
     * Guarda una respuesta de seguridad asociada a un usuario.
     *
     * @param respuesta Objeto {@link Respuesta} a guardar.
     */
    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    /**
     * Retorna todas las respuestas de seguridad almacenadas para un usuario específico.
     *
     * @param username Nombre de usuario.
     * @return Lista de respuestas asociadas al usuario.
     */
    @Override
    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        List<Respuesta> result = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsername().equals(username)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<Respuesta> listarTodos() {
        return List.of();
    }

    /**
     * Valida si las respuestas proporcionadas por un usuario coinciden con las almacenadas.
     * Se compara tanto la pregunta como la respuesta (ignorando mayúsculas/minúsculas).
     *
     * @param username            Usuario al que pertenecen las respuestas.
     * @param respuestasUsuario   Respuestas ingresadas por el usuario.
     * @return {@code true} si todas las respuestas coinciden; {@code false} en caso contrario.
     */
    @Override
    public boolean validarRespuestas(String username, List<Respuesta> respuestasUsuario) {
        List<Respuesta> almacenadas = obtenerRespuestasPorUsuario(username);
        int coincidencias = 0;

        for (Respuesta rUsuario : respuestasUsuario) {
            for (Respuesta rAlmacenada : almacenadas) {
                if (rUsuario.getPregunta().equals(rAlmacenada.getPregunta())
                        && rUsuario.getRespuesta().equalsIgnoreCase(rAlmacenada.getRespuesta())) {
                    coincidencias++;
                }
            }
        }

        return coincidencias == respuestasUsuario.size();
    }
}
