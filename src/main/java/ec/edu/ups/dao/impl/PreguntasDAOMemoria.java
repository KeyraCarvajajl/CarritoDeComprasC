package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.ArrayList;
import java.util.List;

public class PreguntasDAOMemoria implements PreguntasDAO {

    private final List<Preguntas> lista = new ArrayList<>();

    @Override
    public void guardar(Preguntas pregunta) {
        lista.add(pregunta);
    }

    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        List<Preguntas> resultado = new ArrayList<>();
        for (Preguntas p : lista) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public List<Preguntas> obtenerTodas() {
        return new ArrayList<>(lista);
    }
}
