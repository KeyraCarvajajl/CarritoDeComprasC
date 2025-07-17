package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link UsuarioDAO} que utiliza un archivo de texto plano
 * para almacenar y recuperar datos de usuarios.
 *
 * <p>Este DAO permite realizar operaciones CRUD básicas sobre usuarios,
 * con almacenamiento persistente en un archivo llamado {@code usuarios.txt}.</p>
 *
 * <p>La primera línea del archivo contiene los encabezados de los campos:
 * cedula, nombre, correo, teléfono, fechaNacimiento, contraseña, rol.</p>
 *
 * @author Keyra
 */
public class UsuarioDAOArchivoTexto implements UsuarioDAO {

    /**
     * Ruta del archivo de texto donde se almacenan los datos de los usuarios.
     * El archivo {@code usuarios.txt} se crea o se utiliza dentro del mismo
     * directorio del proyecto.
     */
    private final String path = "usuarios.txt";

    /**
     * Constructor que inicializa el archivo si no existe y escribe los encabezados.
     */
    public UsuarioDAOArchivoTexto() {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("cedula,nombre,correo,telefono,fechaNacimiento,contrasena,rol");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Autentica a un usuario verificando su nombre de usuario y contraseña.
     *
     * @param username    Nombre de usuario (cédula).
     * @param contrasenia Contraseña a validar.
     * @return Usuario autenticado o {@code null} si no coincide.
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario u = buscarPorUsername(username);
        if (u != null && u.getContrasenia().equals(contrasenia)) {
            return u;
        }
        return null;
    }

    /**
     * Crea un nuevo usuario y lo añade al archivo.
     *
     * @param usuario Usuario a guardar.
     */
    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(convertirUsuarioALinea(usuario));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca un usuario por su nombre de usuario (cédula).
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o {@code null}.
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            reader.readLine(); // saltar cabecera
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 7) continue;
                if (datos[0].equals(username)) {
                    return construirUsuarioDesdeDatos(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Elimina un usuario por su código.
     * No implementado en esta versión ya que se trabaja con cédula.
     *
     * @param codigo Código de usuario.
     */
    @Override
    public void eliminar(int codigo) {
        // No se usa por código
    }

    /**
     * Elimina un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar.
     */
    public void eliminarPorUsername(String username) {
        List<Usuario> usuarios = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("cedula,nombre,correo,telefono,fechaNacimiento,contrasena,rol");
            writer.newLine();
            for (Usuario u : usuarios) {
                if (!u.getUsername().equals(username)) {
                    writer.write(convertirUsuarioALinea(u));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza la información de un usuario ya existente.
     *
     * @param usuario Usuario con los datos actualizados.
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("cedula,nombre,correo,telefono,fechaNacimiento,contrasena,rol");
            writer.newLine();
            for (Usuario u : usuarios) {
                if (u.getUsername().equals(usuario.getUsername())) {
                    u = usuario; // actualizar
                }
                writer.write(convertirUsuarioALinea(u));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lista todos los usuarios registrados.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            reader.readLine(); // saltar cabecera
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 7) continue;
                usuarios.add(construirUsuarioDesdeDatos(datos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Lista todos los usuarios con rol ADMINISTRADOR.
     *
     * @return Lista de administradores.
     */
    @Override
    public List<Usuario> listarAdministradores() {
        List<Usuario> admins = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol() == Rol.ADMINISTRADOR) {
                admins.add(u);
            }
        }
        return admins;
    }

    /**
     * Lista los usuarios según su rol.
     *
     * @param rol Rol a filtrar.
     * @return Lista de usuarios con el rol indicado.
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }

    /**
     * Devuelve todos los usuarios (alias de {@link #listarTodos()}).
     *
     * @return Lista completa de usuarios.
     */
    @Override
    public List<Usuario> obtenerTodos() {
        return listarTodos();
    }

    // 🔧 MÉTODOS AUXILIARES

    /**
     * Convierte un objeto Usuario en una línea de texto CSV.
     *
     * @param u Usuario a convertir.
     * @return Línea de texto con los atributos separados por coma.
     */
    private String convertirUsuarioALinea(Usuario u) {
        return String.join(",",
                u.getUsername(),
                u.getNombre(),
                u.getCorreo(),
                u.getTelefono(),
                u.getFechaNacimiento().toString(),
                u.getContrasenia(),
                u.getRol().toString()
        );
    }

    /**
     * Construye un objeto Usuario desde un arreglo de Strings.
     *
     * @param datos Arreglo con los datos del usuario.
     * @return Objeto Usuario creado.
     */
    private Usuario construirUsuarioDesdeDatos(String[] datos) {
        Usuario u = new Usuario();
        u.setUsername(datos[0]);
        u.setNombre(datos[1]);
        u.setCorreo(datos[2]);
        u.setTelefono(datos[3]);
        u.setFechaNacimiento(LocalDate.parse(datos[4]));
        u.setContrasenia(datos[5]);
        u.setRol(Rol.valueOf(datos[6]));
        return u;
    }
}
