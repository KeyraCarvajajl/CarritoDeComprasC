package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import java.util.List;

/**
 * Interfaz DAO para la entidad {@link Usuario}.
 * <p>
 * Define las operaciones básicas de acceso a datos para manejar usuarios dentro del sistema
 * de carrito de compras.
 *
 * <p>Las implementaciones de esta interfaz pueden almacenar los datos en memoria, archivos de texto,
 * archivos binarios o bases de datos, según la configuración elegida al inicio del sistema.
 *
 * <p>Métodos comunes:
 * <ul>
 *   <li>{@link #crear(Usuario)}: Guarda un nuevo usuario.</li>
 *   <li>{@link #buscarPorUsername(String)}: Busca un usuario por su nombre de usuario.</li>
 *   <li>{@link #autenticar(String, String)}: Verifica credenciales de acceso.</li>
 *   <li>{@link #eliminar(int)}: Elimina un usuario por su código.</li>
 *   <li>{@link #listarTodos()}: Devuelve todos los usuarios almacenados.</li>
 * </ul>
 *
 * @author Keyra
 */

public interface UsuarioDAO {

    /**
     * Verifica si las credenciales ingresadas son válidas.
     *
     * @param username    nombre de usuario.
     * @param contrasenia contraseña correspondiente al usuario.
     * @return el objeto Usuario autenticado, o {@code null} si las credenciales no coinciden.
     */

    Usuario autenticar(String username, String contrasenia);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario el objeto Usuario que se desea guardar.
     */

    void crear(Usuario usuario);

    /**
     * Busca un usuario en el sistema a partir del nombre de usuario (username).
     *
     * @param username el nombre de usuario a buscar.
     * @return el objeto Usuario encontrado o {@code null} si no existe.
     */

    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario identificado por su código único.
     *
     * @param codigo el código identificador del usuario a eliminar.
     */

    void eliminar(int codigo); // En lugar de int

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario el objeto Usuario con los datos actualizados.
     */

    void actualizar(Usuario usuario);

    /**
     * Retorna una lista de todos los usuarios registrados en el sistema.
     * <p>
     * Este método puede ser redundante con {@link #obtenerTodos()} dependiendo de la implementación,
     * pero se mantiene por compatibilidad o separación lógica en la interfaz.
     *
     * @return una lista de objetos Usuario.
     */

    List<Usuario> listarTodos();

    /**
     * Devuelve una lista de usuarios cuyo rol es {@code ADMINISTRADOR}.
     * <p>
     * Útil para mostrar o gestionar solo los usuarios con permisos de administración.
     *
     * @return lista de usuarios administradores.
     */

    List<Usuario> listarAdministradores();

    /**
     * Devuelve una lista de usuarios que tengan el rol especificado.
     *
     * @param rol el rol que se desea filtrar (por ejemplo, {@code USUARIO} o {@code ADMINISTRADOR}).
     * @return lista de usuarios que cumplen con el rol indicado.
     */

    List<Usuario> listarPorRol(Rol rol);

    /**
     * Devuelve todos los usuarios registrados en el sistema.
     * <p>
     * Similar a {@link #listarTodos()}, puede usarse en contextos más amplios
     * como filtros dinámicos, exportaciones, etc.
     *
     * @return lista completa de usuarios.
     */

    List<Usuario> obtenerTodos();

}
