package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class RespuestaDAOMemoria implements RespuestaDAO {

    private List<Respuesta> respuestas = new ArrayList<>();

    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    @Override
    public List<Respuesta> obtenerRespuestasPorUsuario(String username) {
        List<Respuesta> result = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsername().equals(username)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public boolean validarRespuestas(String username, List<Respuesta> respuestasUsuario) {
        List<Respuesta> almacenadas = obtenerRespuestasPorUsuario(username);
        int coincidencias = 0;

        for (Respuesta rUsuario : respuestasUsuario) {
            for (Respuesta rAlmacenada : almacenadas) {
                if (rUsuario.getPregunta().equals(rAlmacenada.getPregunta())
                        && rUsuario.getRespuesta().equalsIgnoreCase(rAlmacenada.getRespuesta())) {
                    coincidencias++;
                }
            }
        }

        return coincidencias == respuestasUsuario.size();
    }
}
