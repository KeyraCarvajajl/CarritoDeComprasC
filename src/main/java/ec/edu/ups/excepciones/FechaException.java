package ec.edu.ups.excepciones;

/**
 * Excepción lanzada cuando la fecha ingresada es inválida o nula.
 */
public class FechaException extends Exception {

    public FechaException(String mensaje) {
        super(mensaje);
    }
}
