package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de Preguntas usando archivo de texto.
 * Guarda cada pregunta como una línea: username|pregunta
 */
public class PreguntasDAOArchivoTexto implements PreguntasDAO {

    private static final String ARCHIVO = "data/preguntas.txt";

    @Override
    public void guardar(Preguntas pregunta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(pregunta.getUsername() + "|" + pregunta.getPregunta());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar pregunta: " + e.getMessage());
        }
    }

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
