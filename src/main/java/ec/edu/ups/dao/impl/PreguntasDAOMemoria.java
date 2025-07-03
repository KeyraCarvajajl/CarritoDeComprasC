package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDAOMemoria implements PreguntasDAO {

    private final List<Preguntas> preguntas;

    public PreguntasDAOMemoria() {
        preguntas = new ArrayList<>();

        // Preguntas base del sistema (sin username)
        preguntas.add(new Preguntas("Juan123","¿Cuál es el nombre de tu primer mascota?"));
        preguntas.add(new Preguntas("María123","¿Cuál es tu comida favorita?"));
        preguntas.add(new Preguntas("Julio123","¿Cuál es el segundo nombre de tu padre?"));
        preguntas.add(new Preguntas("Bryan123","¿En qué ciudad naciste?"));
        preguntas.add(new Preguntas("Diana123","¿Cuál fue tu primer trabajo?"));
        preguntas.add(new Preguntas("Eli123","¿Cómo se llamaba tu mejor amigo de la infancia?"));
        preguntas.add(new Preguntas("Pablo123","¿Cuál es tu película favorita?"));
        preguntas.add(new Preguntas("Sebas123","¿Qué marca fue tu primer celular?"));
        preguntas.add(new Preguntas("Erick123","¿Cómo se llama tu canción favorita?"));
        preguntas.add(new Preguntas("Erika123","¿Cuál es tu equipo de fútbol favorito?"));
    }

    @Override
    public void guardar(Preguntas pregunta) {
        preguntas.add(pregunta);
    }

    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        List<Preguntas> resultado = new ArrayList<>();
        for (Preguntas p : preguntas) {
            if (p.getUsername() != null && p.getUsername().equalsIgnoreCase(username)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Método para obtener preguntas base del sistema
    public List<Preguntas> obtenerTodas() {
        List<Preguntas> base = new ArrayList<>();
        for (Preguntas p : preguntas) {
            if (p.getUsername() == null) {
                base.add(p);
            }
        }
        return base;
    }
}
