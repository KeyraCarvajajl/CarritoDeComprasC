package ec.edu.ups.modelo;

/**
 * Clase que representa una respuesta a una pregunta de seguridad registrada por un usuario.
 *
 * Esta clase se utiliza para validar la identidad del usuario durante el proceso de recuperación
 * de contraseña, comparando las respuestas ingresadas con las previamente almacenadas.
 *
 * Cada objeto contiene el nombre de usuario, la pregunta y la respuesta asociada.
 *
 * @author Keyra
 */
public class Respuesta {

    /**
     * Nombre de usuario asociado a la respuesta.
     */
    private String username;

    /**
     * Pregunta de seguridad respondida por el usuario.
     */
    private String pregunta;

    /**
     * Respuesta proporcionada por el usuario.
     */
    private String respuesta;

    /**
     * Constructor principal que inicializa los atributos con los valores especificados.
     *
     * @param username Nombre de usuario.
     * @param pregunta Pregunta de seguridad.
     * @param respuesta Respuesta a la pregunta.
     */
    public Respuesta(String username, String pregunta, String respuesta) {
        this.username = username;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene el nombre de usuario asociado.
     *
     * @return Nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username Nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la pregunta de seguridad.
     *
     * @return Pregunta de seguridad.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta de seguridad.
     *
     * @param pregunta Pregunta de seguridad.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Obtiene la respuesta proporcionada por el usuario.
     *
     * @return Respuesta a la pregunta.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta del usuario.
     *
     * @param respuesta Respuesta a la pregunta de seguridad.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
