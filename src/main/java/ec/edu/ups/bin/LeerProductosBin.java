package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.ProductoDAOArchivoBinario;
import ec.edu.ups.modelo.Producto;

import java.util.List;

public class LeerProductosBin {
    public static void main(String[] args) {
        ProductoDAOArchivoBinario dao = new ProductoDAOArchivoBinario();
        List<Producto> productos = dao.listarTodos();

        System.out.println("=== Contenido del archivo productos.bin ===");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }
}
