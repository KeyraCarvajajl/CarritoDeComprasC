package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link RespuestaDAO} que utiliza archivos de texto
 * para almacenar respuestas de seguridad de los usuarios.
 * <p>
 * Cada línea del archivo {@code respuestas.txt} tiene el formato:
 * {@code username|pregunta|respuesta}.
 * </p>
 * <p>
 * Esta clase permite registrar respuestas, consultarlas por usuario, validarlas
 * y listar todas las respuestas almacenadas.
 * </p>
 *
 * @author Keyra
 * @version 1.0
 */
public class RespuestaDAOArchivoTexto implements RespuestaDAO {

    /** Ruta del archivo de texto que almacena las respuestas. */
    private static final String ARCHIVO = "data/respuestas.txt";

    /**
     * Guarda una respuesta de seguridad en el archivo de texto.
     *
     * @param respuesta Objeto {@link Respuesta} que contiene username, pregunta y respuesta.
     */
    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(respuesta.getUsername() + "|" + respuesta.getPregunta() + "|" + respuesta.getRespuesta());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar la respuesta: " + e.getMessage());
        }
    }

    /**
     * Devuelve todas las respuestas asociadas a un usuario específico.
     *
     * @param username Nombre de usuario.
     * @return Lista de respuestas registradas para ese usuario.
     */
    @Override
    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        List<Respuesta> respuestas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3 && partes[0].equalsIgnoreCase(username)) {
                    respuestas.add(new Respuesta(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer respuestas: " + e.getMessage());
        }
        return respuestas;
    }

    /**
     * Valida si al menos dos respuestas del usuario coinciden con las almacenadas.
     *
     * @param username Nombre de usuario.
     * @param respuestasUsuario Respuestas ingresadas por el usuario.
     * @return {@code true} si se encuentran al menos dos coincidencias, {@code false} si no.
     */
    @Override
    public boolean validarRespuestas(String username, List<Respuesta> respuestasUsuario) {
        List<Respuesta> respuestasGuardadas = obtenerRespuestasPorUsuario(username);
        int coincidencias = 0;

        for (Respuesta respuestaIngresada : respuestasUsuario) {
            for (Respuesta guardada : respuestasGuardadas) {
                if (respuestaIngresada.getPregunta().equalsIgnoreCase(guardada.getPregunta()) &&
                        respuestaIngresada.getRespuesta().equalsIgnoreCase(guardada.getRespuesta())) {
                    coincidencias++;
                }
            }
        }

        return coincidencias >= 2; // Se puede ajustar el umbral si deseas
    }

    /**
     * Lista todas las respuestas registradas en el archivo.
     *
     * @return Lista completa de objetos {@link Respuesta}.
     */
    @Override
    public List<Respuesta> listarTodos() {
        List<Respuesta> respuestas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    respuestas.add(new Respuesta(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer respuestas: " + e.getMessage());
        }
        return respuestas;
    }
}
