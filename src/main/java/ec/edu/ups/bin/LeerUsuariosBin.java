package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.UsuarioArchivoBinario;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todos los usuarios almacenados
 * en el archivo binario {@code usuarios.bin}.
 * <p>
 * Esta clase permite verificar que los objetos {@link Usuario} se estén guardando y recuperando
 * correctamente desde el archivo binario, como parte del sistema de persistencia configurable.
 * </p>
 *
 * <p>Se utiliza exclusivamente como herramienta de prueba, fuera del entorno gráfico.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class LeerUsuariosBin {

    /**
     * Método principal que carga los usuarios desde el archivo binario y los muestra en consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        UsuarioArchivoBinario dao = new UsuarioArchivoBinario();
        List<Usuario> usuarios = dao.listarTodos();

        System.out.println("=== Contenido del archivo usuarios.bin ===");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}
