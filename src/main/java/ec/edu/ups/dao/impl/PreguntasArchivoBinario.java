package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.List;

public class PreguntasArchivoBinario implements PreguntasDAO {
    @Override
    public void guardar(Preguntas pregunta) {

    }

    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        return List.of();
    }

    @Override
    public List<Preguntas> obtenerTodas() {
        return List.of();
    }
}
