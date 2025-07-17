package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementación en memoria del DAO para productos.
 *
 * <p>Esta clase proporciona almacenamiento temporal en una lista de objetos {@link Producto}.
 * Es útil para pruebas o aplicaciones que no requieran persistencia en archivos o bases de datos.</p>
 *
 * <p>Permite:
 * <ul>
 *   <li>Crear nuevos productos.</li>
 *   <li>Buscar productos por código o nombre.</li>
 *   <li>Actualizar y eliminar productos existentes.</li>
 *   <li>Listar todos los productos almacenados.</li>
 * </ul>
 * </p>
 *
 * @author Keyra
 */

public class ProductoDAOMemoria implements ProductoDAO {

    /** Lista de productos almacenados en memoria. */
    private List<Producto> productos;

    /**
     * Constructor que inicializa la lista de productos.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<Producto>();
    }

    /**
     * Agrega un nuevo producto a la lista.
     *
     * @param producto el producto a agregar.
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por su código único.
     *
     * @param codigo el código del producto a buscar.
     * @return el producto encontrado o {@code null} si no existe.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre comience con el texto indicado.
     *
     * @param nombre el texto a buscar al inicio del nombre del producto.
     * @return lista de productos encontrados.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().startsWith(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }

    /**
     * Actualiza la información de un producto existente.
     *
     * @param producto el producto con los nuevos datos.
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
    }

    /**
     * Elimina un producto por su código.
     *
     * @param codigo el código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    /**
     * Este método actualmente no está implementado.
     *
     * @param producto el producto a modificar.
     * @return {@code false}, ya que no se implementa en esta clase.
     */
    @Override
    public boolean modificar(Producto producto) {
        return false;
    }

    /**
     * Retorna una lista con todos los productos almacenados.
     *
     * @return lista de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
