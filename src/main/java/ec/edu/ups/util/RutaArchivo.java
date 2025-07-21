package ec.edu.ups.util;

/**
 * Clase para gestionar la ruta base de almacenamiento de archivos.
 * Esta ruta puede ser configurada dinámicamente por el usuario.
 */
public class RutaArchivo {
    private static String rutaBase = "data";

    public static String getRutaBase() {
        return rutaBase;
    }

    public static void setRutaBase(String rutaBase) {
        RutaArchivo.rutaBase = rutaBase;
    }
}
