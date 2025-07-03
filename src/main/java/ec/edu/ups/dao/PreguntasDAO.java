package ec.edu.ups.dao;

import ec.edu.ups.modelo.Preguntas;

import java.util.List;

public interface PreguntasDAO {

    void guardar(Preguntas pregunta);

    List<Preguntas> buscarPorUsername(String username);

    List<Preguntas> obtenerTodas(); // para mostrar las preguntas base del sistema
}
