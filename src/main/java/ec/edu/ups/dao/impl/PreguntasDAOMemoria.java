package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria del DAO para preguntas de seguridad.
 *
 * <p>Esta clase permite guardar y consultar preguntas asociadas a usuarios,
 * almacenando todos los datos en una lista en memoria. Es útil para pruebas y
 * almacenamiento temporal, ya que no hay persistencia en archivos ni bases de datos.</p>
 *
 * <p>Permite:
 * <ul>
 *   <li>Guardar nuevas preguntas.</li>
 *   <li>Buscar preguntas por nombre de usuario.</li>
 *   <li>Obtener todas las preguntas almacenadas en el sistema.</li>
 * </ul>
 *
 * @author Keyra
 */

public class PreguntasDAOMemoria implements PreguntasDAO {

    /** Lista en memoria de todas las preguntas almacenadas. */
    private final List<Preguntas> lista = new ArrayList<>();

    /**
     * Guarda una nueva pregunta en la lista.
     *
     * @param pregunta la pregunta a almacenar.
     */
    @Override
    public void guardar(Preguntas pregunta) {
        lista.add(pregunta);
    }

    /**
     * Busca todas las preguntas asociadas a un nombre de usuario.
     *
     * @param username el nombre de usuario a buscar.
     * @return una lista de preguntas asociadas al usuario.
     */
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

    /**
     * Devuelve todas las preguntas almacenadas en el sistema.
     *
     * @return lista de preguntas.
     */
    @Override
    public List<Preguntas> obtenerTodas() {
        return new ArrayList<>(lista);
    }
}
