package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para usuarios.
 * Almacena los datos de usuarios en una lista local {@code List<Usuario>} sin persistencia en disco.
 * Es útil para pruebas y almacenamiento temporal durante la ejecución.
 * Incluye algunos usuarios predefinidos como admin, keyra y user.
 *
 * @author Keyra
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    /**
     * Lista principal que almacena los usuarios creados en memoria.
     */
    private final List<Usuario> usuarios;

    /**
     * Contador interno para asignar un código único a cada usuario.
     */
    private int contadorCodigo = 1;

    /**
     * Lista secundaria no usada actualmente para retorno en {@code obtenerTodos()}.
     * (⚠️ Puede ser eliminada si no es requerida).
     */
    private List<Usuario> listaUsuarios = new ArrayList<>();

    /**
     * Constructor. Inicializa la lista y agrega tres usuarios de ejemplo: admin, keyra y user.
     */
    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<>();

        Usuario admin = new Usuario("admin", "12345", Rol.ADMINISTRADOR);
        admin.setNombreCompleto("Administrador General");
        admin.setCorreo("admin@correo.com");
        admin.setTelefono("0999999999");

        Usuario keyra = new Usuario("Keyra", "0107909574", Rol.ADMINISTRADOR);
        keyra.setNombreCompleto("Keyra Carvajal");
        keyra.setCorreo("keyra@correo.com");
        keyra.setTelefono("0978705998");

        Usuario user = new Usuario("user", "12345", Rol.USUARIO);
        user.setNombreCompleto("Usuario Común");
        user.setCorreo("user@correo.com");
        user.setTelefono("0977777777");

        crear(admin);
        crear(keyra);
        crear(user);
    }

    /**
     * Autentica un usuario por su nombre y contraseña.
     *
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña.
     * @return Usuario autenticado o {@code null} si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username) &&
                    usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crea un nuevo usuario en la lista de memoria.
     *
     * @param usuario Usuario a agregar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuario.setCodigo(contadorCodigo++);
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Usuario encontrado o {@code null}.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su código (⚠️ lógica incorrecta en condición).
     *
     * @param codigo Código numérico del usuario a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equalsIgnoreCase(String.valueOf(usuario))) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Actualiza los datos de un usuario en la lista.
     *
     * @param usuario Usuario con datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario actual = usuarios.get(i);
            if (actual.getUsername().equalsIgnoreCase(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }
    }

    /**
     * Lista todos los usuarios almacenados en memoria.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios); // para evitar modificar la lista original
    }

    /**
     * Lista todos los usuarios con rol ADMINISTRADOR.
     *
     * @return Lista de administradores.
     */
    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> admins = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getRol() == Rol.ADMINISTRADOR) {
                admins.add(usuario);
            }
        }
        return admins;
    }

    /**
     * Lista usuarios filtrando por un rol específico.
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                resultado.add(usuario);
            }
        }
        return resultado;
    }

    /**
     * Devuelve una copia de {@link #listaUsuarios}, no de la lista principal. ⚠️ Podría causar confusión.
     *
     * @return lista vacía si no se gestiona adecuadamente esta lista.
     */
    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(listaUsuarios);
    }
}
