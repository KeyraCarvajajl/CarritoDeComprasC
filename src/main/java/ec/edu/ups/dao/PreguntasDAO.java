package ec.edu.ups.dao;

import ec.edu.ups.modelo.Preguntas;
import java.util.List;

/**
 * Interfaz DAO para gestionar preguntas de seguridad asociadas a los usuarios.
 *
 * <p>Esta interfaz permite guardar preguntas personalizadas por usuario, buscarlas por nombre de usuario
 * y obtener todas las preguntas base definidas por el sistema para el registro o recuperación de contraseñas.
 *
 * <p>Forma parte del módulo de seguridad del sistema de carrito de compras.
 * Las implementaciones pueden variar según el tipo de almacenamiento (memoria, archivo, base de datos).
 *
 * @author Keyra
 */

public interface PreguntasDAO {

    /**
     * Guarda una pregunta de seguridad personalizada para un usuario.
     *
     * @param pregunta la pregunta a guardar.
     */

    void guardar(Preguntas pregunta);

    /**
     * Busca las preguntas de seguridad asociadas a un usuario específico.
     *
     * @param username el nombre de usuario.
     * @return una lista de preguntas asociadas al usuario.
     */

    List<Preguntas> buscarPorUsername(String username);

    /**
     * Obtiene todas las preguntas base del sistema que pueden ser seleccionadas durante el registro.
     *
     * @return una lista con las preguntas predefinidas del sistema.
     */

    List<Preguntas> obtenerTodas();
}
