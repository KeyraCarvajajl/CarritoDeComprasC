package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.CarritoDAOArchivoBinario;
import ec.edu.ups.modelo.Carrito;

import java.util.List;

/**
 * Clase de utilidad para leer y mostrar por consola todos los carritos almacenados
 * en el archivo binario {@code carritos.bin}.
 * <p>
 * Esta clase se usa como herramienta de prueba para verificar el correcto funcionamiento
 * del almacenamiento en archivos binarios dentro del sistema de carrito de compras.
 * </p>
 *
 * <p>Debe ejecutarse desde el método {@code main}, no está integrada a la interfaz gráfica.</p>
 *
 * @author Keyra
 * @version 1.0
 */
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.impl.CarritoDAOArchivoBinario;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;

import java.util.Date;

public class LeerCarritosBin {


    public static void main(String[] args) {
        CarritoDAO dao = new CarritoDAOArchivoBinario();

        // Crear un carrito y agregar productos
        Carrito carrito = new Carrito(101, new Date());
        carrito.agregarProducto(new Producto(1, "Leche", 1.25), 3);
        carrito.agregarProducto(new Producto(2, "Pan", 0.50), 5);

        // Guardar el carrito
        dao.crear(carrito);

        // Leer carritos
        System.out.println("📦 Carritos guardados:");
        dao.listarTodos().forEach(System.out::println);
    }
}
