package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;

    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public String get(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            System.err.println("No se encontró la clave: " + key);
            return key;  // Devuelve la clave en caso de error (se puede cambiar para mostrar un mensaje predeterminado)
        }
    }

    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
