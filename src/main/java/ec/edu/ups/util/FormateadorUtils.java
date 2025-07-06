package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class FormateadorUtils {

    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }

    public static boolean sonFechasIguales(Date f1, Date f2) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        return formato.format(f1).equals(formato.format(f2));
    }


}