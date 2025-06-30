package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

public interface PreguntaDAO {
    void guardarPregunta(Pregunta pregunta);
    Pregunta buscarPorUsername(String username);
}
