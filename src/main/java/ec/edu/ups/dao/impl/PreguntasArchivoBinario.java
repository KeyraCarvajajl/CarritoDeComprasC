package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz {@link PreguntasDAO} que utiliza archivos binarios
 * para almacenar y recuperar preguntas de seguridad.
 *
 * @author Keyra
 */
public class PreguntasArchivoBinario implements PreguntasDAO {

    private String archivoRuta;
    private List<Preguntas> preguntas;

    /**
     * Constructor que recibe la ruta del archivo y carga los datos.
     * @param rutaArchivo Ruta del archivo binario.
     */
    public PreguntasArchivoBinario(String rutaArchivo) {
        this.archivoRuta = rutaArchivo;
        new File("bin").mkdirs(); // crea carpeta si no existe
        this.preguntas = cargarDesdeArchivo();
    }

    /**
     * Guarda una nueva pregunta en el archivo binario.
     *
     * @param pregunta Objeto {@link Preguntas} a guardar.
     */
    @Override
    public void guardar(Preguntas pregunta) {
        preguntas.add(pregunta);
        guardarEnArchivo();
    }

    /**
     * Busca todas las preguntas asociadas a un nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Lista de preguntas relacionadas.
     */
    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        return preguntas.stream()
                .filter(p -> p.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    /**
     * Devuelve la lista completa de preguntas disponibles.
     *
     * @return Lista de todas las preguntas.
     */
    @Override
    public List<Preguntas> obtenerTodas() {
        return new ArrayList<>(preguntas);
    }

    /**
     * Guarda la lista de preguntas en el archivo binario.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoRuta))) {
            oos.writeObject(preguntas);
        } catch (IOException e) {
            System.err.println("Error al guardar preguntas: " + e.getMessage());
        }
    }

    /**
     * Carga las preguntas desde el archivo binario.
     *
     * @return Lista de preguntas cargadas o vacía si no existe el archivo.
     */
    private List<Preguntas> cargarDesdeArchivo() {
        File archivo = new File(archivoRuta);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoRuta))) {
            return (List<Preguntas>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar preguntas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
