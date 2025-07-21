package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.ProductoDAOArchivoBinario;
import ec.edu.ups.modelo.Producto;
import java.io.File;
import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todos los productos almacenados
 * en el archivo binario {@code productos.bin}.
 *
 * Esta clase permite verificar el correcto guardado y lectura de los objetos {@link Producto}
 * desde el sistema de almacenamiento binario del proyecto.
 *
 * @author Keyra
 * @version 1.1
 */
public class LeerProductosBin {

    public static void main(String[] args) {
        String rutaArchivo = "data/productos.bin";
        ProductoDAOArchivoBinario dao = new ProductoDAOArchivoBinario(rutaArchivo);

        // Si el archivo no existe o está vacío, agregar productos por defecto
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || dao.listarTodos().isEmpty()) {
            dao.crear(new Producto(1, "Manzanas", 0.30));
            dao.crear(new Producto(2, "Pan", 0.15));
            dao.crear(new Producto(3, "Leche", 1.20));
            dao.crear(new Producto(4, "Arroz", 0.50));
            dao.crear(new Producto(5, "Huevos", 1.80));
        }

        // Imprimir productos almacenados
        List<Producto> productos = dao.listarTodos();
        System.out.println("=== Contenido del archivo productos.bin ===");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}
