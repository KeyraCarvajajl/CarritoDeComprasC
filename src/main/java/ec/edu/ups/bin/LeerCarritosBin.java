package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.CarritoDAOArchivoBinario;
import ec.edu.ups.modelo.Carrito;

import java.util.List;

public class LeerCarritosBin {
    public static void main(String[] args) {
        CarritoDAOArchivoBinario dao = new CarritoDAOArchivoBinario();
        List<Carrito> carritos = dao.listarTodos();

        System.out.println("=== Contenido del archivo carritos.bin ===");
        for (Carrito c : carritos) {
            System.out.println(c);
        }
    }
}
