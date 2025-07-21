package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.UsuarioArchivoBinario;
import ec.edu.ups.excepciones.CedulaException;
import ec.edu.ups.excepciones.ContraseniaException;
import ec.edu.ups.excepciones.CorreoException;
import ec.edu.ups.excepciones.CamposException;
import ec.edu.ups.excepciones.FechaException;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase para insertar y mostrar usuarios desde el archivo binario usuarios.bin
 */
public class LeerUsuariosBin {
    public static void main(String[] args) {
        UsuarioArchivoBinario dao = new UsuarioArchivoBinario();

        if (dao.listarTodos().isEmpty()) {
            try {
                Usuario u1 = new Usuario("0917586173", "Keyra@1", Rol.ADMINISTRADOR);
                u1.setCodigo(1);
                u1.setNombre("Keyra");
                u1.setNombreCompleto("Keyra Pazmiño");
                u1.setFechaNacimiento(LocalDate.of(2000, 5, 21));
                u1.setCorreo("keyra@ups.edu.ec");
                u1.setTelefono("0999999999");
                u1.setPregunta1("¿Cuál es tu color favorito?");
                u1.setRespuesta1("Rojo");
                u1.setPregunta2("¿Cuál es tu comida favorita?");
                u1.setRespuesta2("Pizza");
                u1.setPregunta3("¿Cuál es el segundo nombre de tu madre?");
                u1.setRespuesta3("Yolanda");

                dao.crear(u1);
                System.out.println("Usuario creado correctamente.");
            } catch (CedulaException | ContraseniaException | FechaException | CorreoException | CamposException e) {
                System.err.println("Error al crear usuario: " + e.getMessage());
            }
        }

        // Imprimir los usuarios del archivo
        List<Usuario> usuarios = dao.listarTodos();
        System.out.println("=== Usuarios cargados desde usuarios.bin ===");
        for (Usuario u : usuarios) {
            System.out.println(u);  // Usa el toString()
            System.out.println("Pregunta 1: " + u.getPregunta1() + " | Respuesta: " + u.getRespuesta1());
            System.out.println("Pregunta 2: " + u.getPregunta2() + " | Respuesta: " + u.getRespuesta2());
            System.out.println("Pregunta 3: " + u.getPregunta3() + " | Respuesta: " + u.getRespuesta3());
            System.out.println("--------------------------------------------");
        }
    }
}
