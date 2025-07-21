package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link UsuarioDAO} que gestiona usuarios utilizando
 * persistencia en archivo binario.
 * <p>
 * Los datos de los usuarios se almacenan en {@code usuarios.bin} usando serialización
 * con {@code ObjectOutputStream}. Esta clase permite operaciones completas de
 * autenticación, registro, edición, eliminación y filtrado por rol.
 * </p>
 *
 * <p>Esta implementación garantiza la persistencia y recuperación automática
 * de datos al reiniciar el sistema.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class UsuarioArchivoBinario implements UsuarioDAO {

    /** Ruta del archivo binario donde se almacenan los usuarios. */
    private static final String ARCHIVO = "bin/usuarios.bin";

    /** Lista interna de usuarios cargada desde el archivo. */
    private List<Usuario> usuarios;

    /**
     * Constructor que inicializa la lista de usuarios cargándolos desde el archivo binario.
     */
    public UsuarioArchivoBinario() {
        usuarios = cargarDesdeArchivo();
    }

    /**
     * Autentica un usuario comparando su username y contraseña.
     *
     * @param username Nombre de usuario.
     * @param contrasenia Contraseña del usuario.
     * @return Usuario autenticado o {@code null} si no se encuentra coincidencia.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Registra un nuevo usuario y lo guarda en el archivo.
     *
     * @param usuario Usuario a registrar.
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
        guardarEnArchivo();
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o {@code null} si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su código.
     *
     * @param codigo Código del usuario a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        usuarios.removeIf(u -> u.getCodigo() == codigo);
        guardarEnArchivo();
    }

    /**
     * Actualiza los datos de un usuario existente identificado por su código.
     *
     * @param usuarioActualizado Usuario con los datos nuevos.
     */
    @Override
    public void actualizar(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodigo() == usuarioActualizado.getCodigo()) {
                usuarios.set(i, usuarioActualizado);
                guardarEnArchivo();
                return;
            }
        }
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * @return Lista de todos los usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Lista solo los usuarios que tienen rol ADMINISTRADOR.
     *
     * @return Lista de usuarios administradores.
     */
    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> admins = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol() == Rol.ADMINISTRADOR) {
                admins.add(u);
            }
        }
        return admins;
    }

    /**
     * Lista los usuarios filtrados por rol (USUARIO o ADMINISTRADOR).
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con ese rol.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol() == rol) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    /**
     * Devuelve todos los usuarios registrados (alias de {@link #listarTodos()}).
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> obtenerTodos() {
        return listarTodos();
    }

    /**
     * Guarda la lista completa de usuarios en el archivo binario.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de usuarios desde el archivo binario.
     *
     * @return Lista de usuarios cargados o vacía si no existe el archivo o hay error.
     */
    private List<Usuario> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
