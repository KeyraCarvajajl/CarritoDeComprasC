package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.PreguntasArchivoBinario;
import ec.edu.ups.modelo.Preguntas;

import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todas las preguntas almacenadas
 * en el archivo binario {@code preguntas.bin}.
 * <p>
 * Esta clase permite verificar que las preguntas de seguridad se hayan guardado correctamente
 * en el sistema de archivo binario utilizado en la aplicación.
 * </p>
 *
 * <p>Se ejecuta únicamente desde la consola, fuera del flujo de la interfaz gráfica.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class LeerPreguntasBin {

    /**
     * Método principal que carga las preguntas desde el archivo binario y las muestra en consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        PreguntasArchivoBinario dao = new PreguntasArchivoBinario();
        List<Preguntas> preguntas = dao.obtenerTodas();

        System.out.println("=== Contenido del archivo preguntas.bin ===");
        for (Preguntas p : preguntas) {
            System.out.println(p);
        }
    }
}
