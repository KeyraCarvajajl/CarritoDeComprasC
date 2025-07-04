package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;

import java.util.List;

public interface RespuestaDAO {

    void guardarRespuesta(Respuesta respuesta);

    List<Respuesta>obtenerRespuestasPorUsuario(String username);

    boolean validarRespuestas(String username, List<Respuesta> respuestas);
}
