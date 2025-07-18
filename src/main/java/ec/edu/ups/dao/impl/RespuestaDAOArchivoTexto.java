package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gestionar respuestas de seguridad usando archivo de texto.
 * Formato de línea: username|pregunta|respuesta
 */
public class RespuestaDAOArchivoTexto implements RespuestaDAO {

    private static final String ARCHIVO = "data/respuestas.txt";

    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(respuesta.getUsername() + "|" + respuesta.getPregunta() + "|" + respuesta.getRespuesta());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar la respuesta: " + e.getMessage());
        }
    }

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
}
