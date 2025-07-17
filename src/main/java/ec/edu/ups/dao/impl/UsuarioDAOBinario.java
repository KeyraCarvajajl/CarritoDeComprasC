package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del DAO de usuarios que utiliza archivos binarios para persistencia.
 * Almacena todos los objetos {@link Usuario} en un archivo binario llamado {@code usuarios.dat}.
 * Esta clase permite crear, buscar, modificar, eliminar y autenticar usuarios.
 *
 * <p>El archivo se serializa/deserializa completamente en cada operación de escritura.</p>
 *
 * @author Keyra
 */
public class UsuarioDAOBinario implements UsuarioDAO {

    /**
     * Ruta del archivo binario donde se guardan los objetos {@link Usuario}.
     */
    private String ruta = "usuarios.dat";

    /**
     * Constructor que inicializa la ruta del archivo binario.
     */
    public UsuarioDAOBinario() {
        this.ruta = ruta;
    }

    /**
     * Crea un nuevo usuario y lo añade al archivo binario.
     *
     * @param usuario el objeto {@code Usuario} a guardar.
     */
    @Override
    public void crear(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        usuarios.add(usuario);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(usuarios);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    /**
     * Autentica un usuario según su nombre de usuario y contraseña.
     *
     * @param username    el nombre de usuario.
     * @param contrasenia la contraseña.
     * @return el {@code Usuario} autenticado o {@code null} si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : listarTodos()) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario a buscar.
     * @return el {@code Usuario} encontrado o {@code null} si no existe.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : listarTodos()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su código numérico.
     *
     * @param codigo el código del usuario a eliminar.
     */

    @Override
    public void eliminar(int codigo) {
        List<Usuario> usuarios = listarTodos();
        boolean encontrado = false;

        for (Usuario u : usuarios) {
            if (u.getCodigo() == codigo) {
                usuarios.remove(u);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
                oos.writeObject(usuarios);
                oos.close();
            } catch (IOException e) {
                System.out.println("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param usuario el objeto {@code Usuario} con los nuevos datos.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
                break;
            }
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(usuarios);
            oos.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    /**
     * Lista todos los usuarios almacenados en el archivo binario.
     *
     * @return una lista de {@code Usuario}.
     */
    @Override
    public List<Usuario> listarTodos() {
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            List<Usuario> lista = (List<Usuario>) ois.readObject();
            ois.close();
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer archivo binario: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Lista todos los usuarios que tienen rol de ADMINISTRADOR.
     *
     * @return una lista de administradores.
     */
    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> lista = listarTodos();
        List<Usuario> admins = new ArrayList<>();

        for (Usuario u : lista) {
            if (u.getRol() == Rol.ADMINISTRADOR) {
                admins.add(u);
            }
        }

        return admins;
    }

    /**
     * Lista los usuarios filtrados por su rol.
     *
     * @param rol el rol que se desea filtrar.
     * @return una lista de usuarios con el rol especificado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> lista = listarTodos();
        List<Usuario> resultado = new ArrayList<>();

        for (Usuario u : lista) {
            if (u.getRol() == rol) {
                resultado.add(u);
            }
        }

        return resultado;
    }

    /**
     * Devuelve todos los usuarios almacenados. Equivalente a {@link #listarTodos()}.
     *
     * @return lista de todos los usuarios.
     */
    @Override
    public List<Usuario> obtenerTodos() {
        return listarTodos();
    }
}
