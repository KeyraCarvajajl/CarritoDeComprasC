package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Clase utilitaria que proporciona métodos para formatear fechas y cantidades monetarias
 * de acuerdo con una configuración regional (Locale) específica.
 * También permite comparar si dos fechas representan el mismo día.
 *
 * @author Keyra
 */
public class FormateadorUtils {

    /**
     * Formatea una cantidad numérica como valor monetario de acuerdo con el Locale proporcionado.
     *
     * @param cantidad La cantidad numérica a formatear.
     * @param locale   El {@link Locale} que define el formato regional de moneda (por ejemplo, Locale.US).
     * @return Una cadena que representa la cantidad en formato monetario local (ej. "$1,000.00").
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha en una cadena legible utilizando el formato regional especificado.
     *
     * @param fecha  La fecha a formatear.
     * @param locale El {@link Locale} que define el formato regional de fecha.
     * @return Una cadena con la fecha formateada (ej. "17-jul-2025").
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }

    /**
     * Compara si dos fechas corresponden al mismo día según el formato regional por defecto.
     *
     * @param f1 Primera fecha.
     * @param f2 Segunda fecha.
     * @return {@code true} si ambas fechas representan el mismo día, {@code false} en caso contrario.
     */
    public static boolean sonFechasIguales(Date f1, Date f2) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return formato.format(f1).equals(formato.format(f2));
    }
}