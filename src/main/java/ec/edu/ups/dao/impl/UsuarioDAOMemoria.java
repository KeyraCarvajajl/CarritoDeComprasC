package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {

    private final List<Usuario> usuarios;

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

    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equalsIgnoreCase(username)) {
                iterator.remove();
                break;
            }
        }
    }

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

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios); // para evitar modificar la lista original
    }

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
}
