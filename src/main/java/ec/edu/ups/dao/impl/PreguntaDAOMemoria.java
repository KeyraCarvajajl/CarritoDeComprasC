package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {

    private final List<Pregunta> preguntas;

    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<>();
    }

    @Override
    public void guardarPregunta(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    @Override
    public List<Pregunta> buscarPorUsername(String username) {
        List<Pregunta> resultado = new ArrayList<>();
        for (Pregunta p : preguntas) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
