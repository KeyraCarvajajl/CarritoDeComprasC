package ec.edu.ups.excepciones;

/**
 * Excepción lanzada cuando la cédula ingresada es inválida.
 */
public class CedulaException extends Exception {

    public CedulaException(String mensaje) {
        super(mensaje);
    }
}
