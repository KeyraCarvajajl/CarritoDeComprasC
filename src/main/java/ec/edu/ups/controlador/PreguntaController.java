package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.List;

public class PreguntaController {

    private final PreguntaDAO preguntaDAO;

    public PreguntaController(PreguntaDAO preguntaDAO) {
        this.preguntaDAO = preguntaDAO;
    }

    public void registrarPreguntas(List<Pregunta> preguntas) {
        for (Pregunta p : preguntas) {
            preguntaDAO.guardarPregunta(p);
        }
    }

    public boolean validarRespuestas(String username, List<Pregunta> respuestasUsuario) {
        List<Pregunta> preguntasGuardadas = preguntaDAO.buscarPorUsername(username);

        if (preguntasGuardadas == null || preguntasGuardadas.size() != respuestasUsuario.size()) {
            return false;
        }

        for (int i = 0; i < preguntasGuardadas.size(); i++) {
            String respuestaCorrecta = preguntasGuardadas.get(i).getRespuesta();
            String respuestaIngresada = respuestasUsuario.get(i).getRespuesta();

            if (!respuestaCorrecta.equalsIgnoreCase(respuestaIngresada)) {
                return false;
            }
        }

        return true;
    }

    public List<Pregunta> obtenerPreguntas(String username) {
        return preguntaDAO.buscarPorUsername(username);
    }
}
