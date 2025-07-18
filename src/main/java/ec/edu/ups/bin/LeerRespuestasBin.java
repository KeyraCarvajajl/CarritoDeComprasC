package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.RespuestasArchivoBinario;
import ec.edu.ups.modelo.Respuesta;

import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todas las respuestas almacenadas
 * en el archivo binario {@code respuestas.bin}.
 * <p>
 * Esta clase permite verificar que las respuestas a las preguntas de seguridad
 * se estén almacenando correctamente en el archivo binario del sistema.
 * </p>
 *
 * <p>Se ejecuta únicamente como herramienta de prueba, fuera de la interfaz gráfica.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class LeerRespuestasBin {

    /**
     * Método principal que carga las respuestas desde el archivo binario y las muestra en consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        RespuestasArchivoBinario dao = new RespuestasArchivoBinario();
        List<Respuesta> respuestas = dao.listarTodos();

        System.out.println("=== Contenido del archivo respuestas.bin ===");
        for (Respuesta r : respuestas) {
            System.out.println(r);
        }
    }
}
