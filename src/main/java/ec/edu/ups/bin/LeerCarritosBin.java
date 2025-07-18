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
public class LeerCarritosBin {

    /**
     * Método principal que carga los carritos desde el archivo binario y los muestra en consola.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        CarritoDAOArchivoBinario dao = new CarritoDAOArchivoBinario();
        List<Carrito> carritos = dao.listarTodos();

        System.out.println("=== Contenido del archivo carritos.bin ===");
        for (Carrito c : carritos) {
            System.out.println(c);
        }
    }
}
