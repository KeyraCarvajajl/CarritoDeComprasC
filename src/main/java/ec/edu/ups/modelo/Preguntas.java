package ec.edu.ups.modelo;

/**
 * Clase que representa una pregunta de seguridad asociada a un usuario.
 * Se utiliza tanto para el registro como para la recuperación de contraseña.
 *
 * Cada objeto almacena el nombre de usuario, la pregunta y la respuesta.
 *
 * @author Keyra
 */
public class Preguntas {

    /**
     * Nombre de usuario al que pertenece la pregunta.
     */
    private String username;

    /**
     * Texto de la pregunta de seguridad.
     */
    private String pregunta;

    /**
     * Respuesta a la pregunta de seguridad.
     */
    private String respuesta;

    /**
     * Constructor que recibe el nombre de usuario y la pregunta.
     *
     * @param username Nombre del usuario.
     * @param pregunta Pregunta de seguridad asociada.
     */
    public Preguntas(String username, String pregunta) {
        this.username = username;
        this.pregunta = pregunta;
    }

    /**
     * Devuelve el nombre de usuario.
     *
     * @return Username del usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Asigna el nombre de usuario.
     *
     * @param username Nombre del usuario a asignar.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve la pregunta de seguridad.
     *
     * @return Pregunta del usuario.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Asigna la pregunta de seguridad.
     *
     * @param pregunta Texto de la pregunta.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Devuelve la respuesta asignada a la pregunta.
     *
     * @return Respuesta del usuario.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Asigna la respuesta a la pregunta.
     *
     * @param respuesta Texto de la respuesta.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
