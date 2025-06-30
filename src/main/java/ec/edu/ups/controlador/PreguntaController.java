package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

public class PreguntaController {

    private PreguntaDAO preguntaDAO;

    public PreguntaController(PreguntaDAO preguntaDAO) {
        this.preguntaDAO = preguntaDAO;
    }

    public void registrarPregunta(String username, String pregunta, String respuesta) {
        Pregunta p = new Pregunta(username, pregunta, respuesta);
        preguntaDAO.guardarPregunta(p);
    }

    public boolean validarRespuesta(String username, String respuestaIngresada) {
        Pregunta p = preguntaDAO.buscarPorUsername(username);
        return p != null && p.getRespuesta().equalsIgnoreCase(respuestaIngresada);
    }

    public String obtenerPregunta(String username) {
        Pregunta p = preguntaDAO.buscarPorUsername(username);
        return (p != null) ? p.getPregunta() : null;
    }
}
