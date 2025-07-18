package ec.edu.ups.excepciones;

/**
 * Excepción lanzada cuando el formato del correo electrónico es inválido.
 */
public class CorreoException extends Exception {

    public CorreoException(String mensaje) {
        super(mensaje);
    }
}
