package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.Date;
import java.util.List;

/**
 * Interfaz DAO para gestionar los carritos de compras en el sistema.
 *
 * <p>Define las operaciones necesarias para crear, buscar, actualizar, eliminar y listar carritos.
 * Las implementaciones pueden variar según el tipo de almacenamiento utilizado
 * (en memoria, archivo de texto, archivo binario, base de datos, etc.).
 *
 * <p>Un carrito representa una colección de productos seleccionados por un usuario en una fecha específica.
 * Se utiliza como parte del flujo de compras del sistema.
 *
 * @author Keyra
 */

public interface CarritoDAO {

    /**
     * Crea un nuevo carrito en el sistema.
     *
     * @param carrito el carrito a crear.
     */

    void crear(Carrito carrito);

    /**
     * Busca un carrito por su código único.
     *
     * @param codigo el código del carrito.
     * @return el carrito encontrado, o {@code null} si no existe.
     */

    Carrito buscarPorCodigo(int codigo);

    /**
     * Busca un carrito por su código y fecha de creación.
     *
     * @param codigo el código del carrito.
     * @param fecha la fecha de creación del carrito.
     * @return el carrito encontrado, o {@code null} si no existe.
     */

    Carrito buscarPorCodigoYFecha(int codigo, Date fecha);

    /**
     * Actualiza la información de un carrito existente.
     *
     * @param carrito el carrito con la información actualizada.
     */

    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito del sistema según su código.
     *
     * @param codigo el código del carrito a eliminar.
     */

    void eliminar(int codigo);

    /**
     * Lista todos los carritos registrados en el sistema.
     *
     * @return una lista con todos los carritos.
     */

    List<Carrito> listarTodos();
}
