package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;

import java.util.List;

/**
 * Implementación de la interfaz {@link PreguntasDAO} que representa el acceso
 * a datos de preguntas de seguridad usando archivos binarios.
 * <p>
 * Esta clase está diseñada para almacenar y recuperar preguntas predefinidas
 * desde un archivo binario. Actualmente se encuentra sin implementación funcional.
 * </p>
 *
 * <p>Debe ser completada con la lógica de lectura/escritura binaria mediante
 * {@code ObjectOutputStream} y {@code ObjectInputStream}.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class PreguntasArchivoBinario implements PreguntasDAO {

    /**
     * Guarda una nueva pregunta en el archivo binario.
     *
     * @param pregunta Objeto {@link Preguntas} a guardar.
     */
    @Override
    public void guardar(Preguntas pregunta) {

    }

    /**
     * Busca todas las preguntas asociadas a un nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Lista de preguntas relacionadas (actualmente vacía).
     */
    @Override
    public List<Preguntas> buscarPorUsername(String username) {
        return List.of();
    }

    /**
     * Devuelve la lista completa de preguntas disponibles.
     *
     * @return Lista de todas las preguntas (actualmente vacía).
     */
    @Override
    public List<Preguntas> obtenerTodas() {
        return List.of();
    }
}
