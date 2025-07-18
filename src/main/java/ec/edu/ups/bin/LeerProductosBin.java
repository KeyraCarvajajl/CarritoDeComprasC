package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.ProductoDAOArchivoBinario;
import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todos los productos almacenados
 * en el archivo binario {@code productos.bin}.
 * <p>
 * Esta clase permite verificar el correcto guardado y lectura de los objetos {@link Producto}
 * desde el sistema de almacenamiento binario del proyecto.
 * </p>
 *
 * <p>Se utiliza como herramienta de prueba fuera del entorno gráfico de la aplicación.</p>
 *
 * @author Keyra
 * @version 1.0
 */
public class LeerProductosBin {

    /**
     * Método principal que carga los productos desde el archivo binario y los muestra en consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        ProductoDAOArchivoBinario dao = new ProductoDAOArchivoBinario();
        List<Producto> productos = dao.listarTodos();

        System.out.println("=== Contenido del archivo productos.bin ===");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}
