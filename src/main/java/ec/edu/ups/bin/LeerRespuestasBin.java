package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.RespuestasDAOArchivoBinario;
import ec.edu.ups.modelo.Respuesta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public static void main(String[] args) {
        String archivoBinario = "bin/respuestas.bin";

        List<String> preguntas = new ArrayList<>();
        List<String> respuestas = new ArrayList<>();


        // Guardar en binario
        RespuestasDAOArchivoBinario dao = new RespuestasDAOArchivoBinario(archivoBinario);
        for (int i = 0; i < preguntas.size() && i < respuestas.size(); i++) {
            Respuesta respuesta = new Respuesta("base", preguntas.get(i), respuestas.get(i));
            dao.guardarRespuesta(respuesta);
        }

        // Imprimir en consola
        System.out.println("=== Respuestas registradas ===");
        for (int i = 0; i < preguntas.size() && i < respuestas.size(); i++) {
            System.out.println("Pregunta: " + preguntas.get(i) + " | Respuesta: " + respuestas.get(i));
        }
    }
}

