package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.excepciones.*;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de UsuarioDAO que guarda los usuarios en un archivo de texto plano.
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {

    private static final String ARCHIVO = "data/usuarios.txt";

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(formatearUsuario(usuario));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(int codigo) {
        List<Usuario> usuarios = listarTodos();
        usuarios.removeIf(u -> u.getCodigo() == codigo);
        sobrescribirArchivo(usuarios);
    }

    @Override
    public void actualizar(Usuario usuarioActualizado) {
        List<Usuario> usuarios = listarTodos();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCodigo() == usuarioActualizado.getCodigo()) {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }
        sobrescribirArchivo(usuarios);
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 9) {
                    try {
                        Usuario u = new Usuario();
                        u.setUsername(partes[0]);
                        u.setContrasenia(partes[1]);
                        u.setRol(Rol.valueOf(partes[2]));
                        u.setCodigo(Integer.parseInt(partes[3]));
                        u.setNombre(partes[4]);
                        u.setNombreCompleto(partes[5]);
                        u.setFechaNacimiento(LocalDate.parse(partes[6]));
                        u.setCorreo(partes[7]);
                        u.setTelefono(partes[8]);

                        usuarios.add(u);
                    } catch (CedulaException | CorreoException | ContraseniaException |
                             CamposException | FechaException e) {
                        System.err.println("Error en validación de datos del usuario: " + e.getMessage());
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
        }

        return usuarios;
    }


    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol() == Rol.ADMINISTRADOR) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : listarTodos()) {
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

    private String formatearUsuario(Usuario u) {
        return u.getUsername() + "|" + u.getContrasenia() + "|" + u.getRol() + "|" +
                u.getCodigo() + "|" + u.getNombre() + "|" + u.getNombreCompleto() + "|" +
                u.getFechaNacimiento() + "|" + u.getCorreo() + "|" + u.getTelefono();
    }

    private void sobrescribirArchivo(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios) {
                writer.write(formatearUsuario(u));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir archivo de usuarios: " + e.getMessage());
        }
    }
}
