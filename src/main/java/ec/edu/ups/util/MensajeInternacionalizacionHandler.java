package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase utilitaria encargada de manejar la carga de mensajes internacionalizados
 * mediante archivos de propiedades (.properties) según el idioma y país especificados.
 * Se utiliza para soportar múltiples idiomas en la interfaz de usuario.
 *
 * Los archivos de recursos deben llamarse "mensajes_{idioma}_{PAIS}.properties"
 * y ubicarse en el paquete de recursos correspondiente.
 *
 * Ejemplo de uso:
 * <pre>
 *     MensajeInternacionalizacionHandler handler = new MensajeInternacionalizacionHandler("es", "EC");
 *     String texto = handler.get("usuario.nombre");
 * </pre>
 *
 * @author Keyra
 */
public class MensajeInternacionalizacionHandler {

    /**
     * Recurso que contiene los mensajes internacionalizados cargados desde
     * el archivo .properties correspondiente al idioma y país seleccionados.
     */
    private ResourceBundle bundle;

    /**
     * Representa la configuración regional actual (idioma y país),
     * utilizada para seleccionar el archivo de mensajes adecuado.
     */
    private Locale locale;

    /**
     * Crea un manejador de mensajes internacionalizados para el idioma y país dados.
     *
     * @param lenguaje Código del idioma (por ejemplo, "es" para español, "en" para inglés).
     * @param pais     Código del país (por ejemplo, "EC" para Ecuador, "US" para Estados Unidos).
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el mensaje correspondiente a una clave, según el archivo de propiedades cargado.
     *
     * @param key Clave del mensaje a obtener.
     * @return Valor del mensaje en el idioma actual o la clave si no se encuentra.
     */
    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            System.err.println("No se encontró la clave: " + key);
            return key;  // Devuelve la clave en caso de error (se puede cambiar para mostrar un mensaje predeterminado)
        }
    }

    /**
     * Cambia el idioma y país actual, recargando el archivo de recursos correspondiente.
     *
     * @param lenguaje Código del idioma.
     * @param pais     Código del país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    /**
     * Obtiene el {@link Locale} actual configurado en el manejador.
     *
     * @return Objeto {@link Locale} actual.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Establece un nuevo {@link Locale} directamente.
     *
     * @param locale Objeto {@link Locale} a establecer.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
