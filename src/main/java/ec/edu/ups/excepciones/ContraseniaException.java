package ec.edu.ups.excepciones;

/**
 * Excepción lanzada cuando la contraseña no cumple con los requisitos de seguridad.
 */
public class ContraseniaException extends Exception {

    public ContraseniaException(String mensaje) {
        super(mensaje);
    }
}
