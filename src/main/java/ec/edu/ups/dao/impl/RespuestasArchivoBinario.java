package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RespuestasArchivoBinario implements RespuestaDAO {

    private static final String ARCHIVO = "respuestas.bin";
    private List<Respuesta> respuestas;

    public RespuestasArchivoBinario() {
        respuestas = cargarDesdeArchivo();
    }

    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        guardarEnArchivo();
    }

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

    @Override
    public List<Respuesta> listarTodos() {
        return new ArrayList<>(respuestas);
    }

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

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(respuestas);
        } catch (IOException e) {
            System.err.println("Error al guardar respuestas: " + e.getMessage());
        }
    }

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
