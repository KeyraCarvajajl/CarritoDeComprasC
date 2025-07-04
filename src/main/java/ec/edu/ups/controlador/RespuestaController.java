package ec.edu.ups.controlador;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.List;

public class RespuestaController {

    private RespuestaDAO respuestaDAO;

    public RespuestaController() {
        this.respuestaDAO = respuestaDAO;
    }

    public void guardarRespuesta(Respuesta respuesta) {
        respuestaDAO.guardarRespuesta(respuesta);
    }

    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        return respuestaDAO.obtenerRespuestasPorUsuario(username);
    }

    public boolean validarRespuestas(String username, List<Respuesta> respuestas) {
        return respuestaDAO.validarRespuestas(username, respuestas);
    }
}

