package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link RespuestaDAO} que almacena respuestas
 * de seguridad utilizando un archivo binario.
 * <p>
 * Las respuestas se serializan en el archivo {@code respuestas.bin} mediante
 * {@code ObjectOutputStream}. Esta clase permite guardar, validar, listar
 * y recuperar respuestas asociadas a un usuario.
 * </p>
 *
 * <p>Se considera válida la recuperación si al menos 3 respuestas coinciden.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class RespuestasArchivoBinario implements RespuestaDAO {

    /** Ruta del archivo binario donde se almacenan las respuestas. */
    private static final String ARCHIVO = "respuestas.bin";

    /** Lista en memoria que contiene todas las respuestas cargadas. */
    private List<Respuesta> respuestas;

    /**
     * Constructor que carga las respuestas desde el archivo binario al iniciar.
     */
    public RespuestasArchivoBinario() {
        respuestas = cargarDesdeArchivo();
    }

    /**
     * Guarda una respuesta de seguridad en la lista y en el archivo binario.
     *
     * @param respuesta Objeto {@link Respuesta} a guardar.
     */
    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        guardarEnArchivo();
    }

    /**
     * Obtiene todas las respuestas asociadas a un usuario.
     *
     * @param username Nombre de usuario.
     * @return Lista de respuestas correspondientes a ese usuario.
     */
    @Override
    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        List<Respuesta> resultado = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsername().equalsIgnoreCase(username)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    /**
     * Devuelve una lista con todas las respuestas almacenadas.
     *
     * @return Lista de objetos {@link Respuesta}.
     */
    @Override
    public List<Respuesta> listarTodos() {
        return new ArrayList<>(respuestas);
    }

    /**
     * Valida si al menos tres respuestas coinciden con las almacenadas.
     *
     * @param username Nombre del usuario.
     * @param respuestasIngresadas Lista de respuestas ingresadas por el usuario.
     * @return {@code true} si hay al menos 3 coincidencias válidas, {@code false} si no.
     */
    @Override
    public boolean validarRespuestas(String username, List<Respuesta> respuestasIngresadas) {
        List<Respuesta> almacenadas = obtenerRespuestasPorUsuario(username);
        int correctas = 0;

        for (Respuesta ingresada : respuestasIngresadas) {
            for (Respuesta guardada : almacenadas) {
                if (ingresada.getPregunta().equalsIgnoreCase(guardada.getPregunta()) &&
                        ingresada.getRespuesta().equalsIgnoreCase(guardada.getRespuesta())) {
                    correctas++;
                }
            }
        }

        return correctas >= 3;
    }

    /**
     * Guarda todas las respuestas en el archivo binario.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(respuestas);
        } catch (IOException e) {
            System.err.println("Error al guardar respuestas: " + e.getMessage());
        }
    }

    /**
     * Carga las respuestas desde el archivo binario.
     *
     * @return Lista de respuestas, o vacía si el archivo no existe o ocurre un error.
     */
    private List<Respuesta> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Respuesta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar respuestas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
