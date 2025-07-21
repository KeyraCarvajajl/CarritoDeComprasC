package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;

import java.util.List;

/**
 * Interfaz DAO para gestionar las respuestas de seguridad asociadas a los usuarios.
 *
 * <p>Define los métodos necesarios para guardar, recuperar y validar respuestas
 * proporcionadas por los usuarios como parte del proceso de recuperación de contraseña.
 *
 * <p>Las implementaciones de esta interfaz pueden manejar el almacenamiento en memoria,
 * archivos o base de datos.
 *
 * @author Keyra
 */

public interface RespuestaDAO {

    /**
     * Guarda una respuesta de seguridad proporcionada por el usuario.
     *
     * @param respuesta la respuesta que se desea guardar.
     */

    void guardarRespuesta(Respuesta respuesta);

    /**
     * Obtiene todas las respuestas de seguridad registradas para un usuario específico.
     *
     * @param username el nombre de usuario para el cual se buscan las respuestas.
     * @return una lista de objetos {@code Respuesta} asociados al usuario.
     */

    List<Respuesta>obtenerRespuestasPorUsuario(String username);
    List<Respuesta> listarTodos();


    /**
     * Valida si las respuestas proporcionadas por un usuario coinciden con las almacenadas.
     *
     * @param username  el nombre del usuario a validar.
     * @param respuestas las respuestas ingresadas por el usuario.
     * @return {@code true} si todas las respuestas son correctas, {@code false} en caso contrario.
     */

    boolean validarRespuestas(String username, List<Respuesta> respuestas);
}
