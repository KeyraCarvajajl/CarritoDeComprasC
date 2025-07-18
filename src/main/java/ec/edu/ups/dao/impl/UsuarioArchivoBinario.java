package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioArchivoBinario implements UsuarioDAO {

    private static final String ARCHIVO = "usuarios.bin";
    private List<Usuario> usuarios;

    public UsuarioArchivoBinario() {
        usuarios = cargarDesdeArchivo();
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
        guardarEnArchivo();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(int codigo) {
        usuarios.removeIf(u -> u.getCodigo() == codigo);
        guardarEnArchivo();
    }

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

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

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

    @Override
    public List<Usuario> obtenerTodos() {
        return listarTodos();
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

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
