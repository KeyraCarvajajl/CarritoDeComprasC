package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.HashMap;
import java.util.Map;

public class PreguntaDAOMemoria implements PreguntaDAO {

    private Map<String, Pregunta> preguntas = new HashMap<>();

    @Override
    public void guardarPregunta(Pregunta pregunta) {
        preguntas.put(pregunta.getUsername(), pregunta);
    }

    @Override
    public Pregunta buscarPorUsername(String username) {
        return preguntas.get(username);
    }
}
