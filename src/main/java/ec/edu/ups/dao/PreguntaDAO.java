package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.List;

public interface PreguntaDAO {

    void guardarPregunta(Pregunta pregunta);

    List<Pregunta> buscarPorUsername(String username);
}
