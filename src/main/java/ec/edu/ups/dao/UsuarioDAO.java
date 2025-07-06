package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import java.util.List;

public interface UsuarioDAO {

    Usuario autenticar(String username, String contrasenia);

    void crear(Usuario usuario);

    Usuario buscarPorUsername(String username);

    void eliminar(int codigo); // En lugar de int

    void actualizar(Usuario usuario);

    List<Usuario> listarTodos();

    List<Usuario> listarAdministradores();

    List<Usuario> listarPorRol(Rol rol);

    List<Usuario> obtenerTodos();

}
