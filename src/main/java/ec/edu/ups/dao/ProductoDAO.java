package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Interfaz DAO para gestionar operaciones CRUD sobre productos en el sistema de carrito de compras.
 *
 * <p>Define los métodos necesarios para crear, buscar, actualizar, eliminar y listar productos.
 * Las implementaciones de esta interfaz pueden variar en el mecanismo de persistencia (memoria, archivo o base de datos).
 *
 * <p>Esta interfaz sigue los principios del patrón DAO para separar la lógica de persistencia de la lógica de negocio.
 *
 * @author Keyra
 */

public interface ProductoDAO {

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto el producto a ser registrado.
     */

    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     *
     * @param codigo el código del producto a buscar.
     * @return el producto encontrado, o {@code null} si no existe.
     */

    Producto buscarPorCodigo(int codigo);

    /**
     * Busca productos cuyo nombre contenga el texto especificado.
     *
     * @param nombre el texto a buscar dentro del nombre del producto.
     * @return una lista de productos que coinciden parcialmente con el nombre.
     */

    List <Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un producto existente.
     *
     * @param producto el producto con la información actualizada.
     */

    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema por su código.
     *
     * @param codigo el código del producto a eliminar.
     */

    void eliminar(int codigo);

    /**
     * Modifica un producto existente. Similar a {@code actualizar}, pero puede diferenciarse según implementación.
     *
     * @param producto el producto con los cambios a aplicar.
     * @return {@code true} si la modificación fue exitosa, {@code false} en caso contrario.
     */

    boolean modificar(Producto producto);

    /**
     * Lista todos los productos registrados en el sistema.
     *
     * @return una lista de todos los productos disponibles.
     */

    List<Producto> listarTodos();

}
