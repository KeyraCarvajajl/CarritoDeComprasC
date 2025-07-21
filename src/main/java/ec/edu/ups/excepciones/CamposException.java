package ec.edu.ups.excepciones;

/**
 * Excepción lanzada cuando uno o más campos obligatorios están vacíos o mal formateados.
 */
public class CamposException extends Exception {

    public CamposException(String mensaje) {
        super(mensaje);
    }
}
