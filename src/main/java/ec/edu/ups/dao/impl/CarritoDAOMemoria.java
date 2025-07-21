package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.FormateadorUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO de carritos.
 *
 * <p>Esta clase mantiene los datos en una lista en memoria, por lo que su uso está recomendado
 * para pruebas o almacenamiento temporal. No se realiza persistencia en archivos o bases de datos.
 *
 * <p>Utiliza un contador interno para asignar códigos únicos a los carritos creados.
 * Permite realizar búsquedas, actualizaciones, eliminaciones y listados.
 *
 * @author Keyra
 */

public class CarritoDAOMemoria implements CarritoDAO {

    private static final String ARCHIVO = "carritos.txt";
    /** Lista de carritos almacenados en memoria. */
    private List<Carrito> carritos;

    /** Contador incremental para asignar códigos únicos a los carritos. */
    private int codigoCarrito = 1;

    /**
     * Constructor que inicializa la lista de carritos vacía.
     */
    public CarritoDAOMemoria() {
        this.carritos = new ArrayList<>();
    }

    /**
     * Crea un nuevo carrito, asignándole un código único y agregándolo a la lista.
     *
     * @param carrito el carrito a agregar.
     */

    @Override
    public void crear(Carrito carrito) {
        carrito.setCodigo(codigoCarrito++);
        carritos.add(carrito);
    }

    /**
     * Busca un carrito por su código.
     *
     * @param codigo el código del carrito a buscar.
     * @return el carrito si se encuentra, o {@code null} si no existe.
     */

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca un carrito por su código y fecha de creación.
     *
     * @param codigo el código del carrito.
     * @param fecha la fecha de creación del carrito.
     * @return el carrito si se encuentra, o {@code null} si no coincide.
     */

    @Override
    public Carrito buscarPorCodigoYFecha(int codigo, Date fecha) {
        for (Carrito carrito : carritos) {
            if (carrito.getCodigo() == codigo &&
                    FormateadorUtils.sonFechasIguales(carrito.getFechaCreacion(), fecha)) {
                return carrito;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente con nuevos datos.
     *
     * @param carrito el carrito actualizado.
     */

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
    }

    /**
     * Elimina un carrito de la lista según su código.
     *
     * @param codigo el código del carrito a eliminar.
     */

    @Override
    public void eliminar(int codigo) {
        Iterator<Carrito> iterator = carritos.iterator();
        while (iterator.hasNext()) {
            Carrito carrito = iterator.next();
            if (carrito.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Devuelve la lista de todos los carritos almacenados en memoria.
     *
     * @return lista de carritos.
     */

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    @Override
    public void guardar(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            for (ItemCarrito item : carrito.obtenerItems()) {
                String linea = String.format("%d;%d;%d",
                        carrito.getCodigo(),
                        item.getProducto().getCodigo(),
                        item.getCantidad());
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar carrito: " + e.getMessage());
        }
    }

}
