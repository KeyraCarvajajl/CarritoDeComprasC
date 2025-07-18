package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link PreguntasDAO} que utiliza un archivo de texto
 * para almacenar preguntas de seguridad relacionadas a los usuarios.
 * <p>
 * Cada línea del archivo {@code preguntas.txt} tiene el formato:
 * {@code username|pregunta}. Esto permite asociar múltiples preguntas a un mismo usuario.
 * </p>
 *
 * <p>La clase permite guardar nuevas preguntas, obtener todas las registradas,
 * o filtrar por nombre de usuario.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class PreguntasDAOArchivoTexto implements PreguntasDAO {

    /** Ruta del archivo donde se almacenan las preguntas. */
    private static final String ARCHIVO = "data/preguntas.txt";

    /**
     * Guarda una nueva pregunta en el archivo de texto.
     * Cada línea representa una pregunta asociada a un usuario.
     *
     * @param pregunta Objeto {@link Preguntas} que contiene el nombre de usuario y la pregunta.
     */
    @Override
    public void guardar(Preguntas pregunta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(pregunta.getUsername() + "|" + pregunta.getPregunta());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar pregunta: " + e.getMessage());
        }
    }

    /**
     * Busca todas las preguntas asociadas a un usuario específico.
     *
     * @param username Nombre de usuario.
     * @return Lista de objetos {@link Preguntas} relacionadas con el usuario.
     */
    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        List<Preguntas> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length == 2 && datos[0].equalsIgnoreCase(username)) {
                    lista.add(new Preguntas(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar preguntas por username: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Obtiene todas las preguntas registradas en el archivo de texto.
     *
     * @return Lista completa de objetos {@link Preguntas}.
     */
    @Override
    public List<Preguntas> obtenerTodas() {
        List<Preguntas> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                if (datos.length == 2) {
                    lista.add(new Preguntas(datos[0], datos[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener todas las preguntas: " + e.getMessage());
        }
        return lista;
    }
}
