package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Clase que representa un producto disponible para ser añadido al carrito de compras.
 * Contiene atributos como código, nombre y precio del producto.
 *
 * Esta clase es utilizada para gestionar el inventario y operaciones relacionadas
 * con los productos en el sistema.
 *
 * @author Keyra
 */
public class Producto implements Serializable {
    /**
     * Código único del producto.
     */
    private int codigo;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Precio del producto.
     */
    private double precio;

    /**
     * Constructor principal que permite inicializar un producto con todos sus atributos.
     *
     * @param codigo Código único del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo Código único del producto.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre Nombre a asignar al producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio Precio a asignar al producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Retorna el código del producto.
     *
     * @return Código del producto.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el precio del producto.
     *
     * @return Precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación en cadena del producto con su nombre y precio.
     *
     * @return Cadena con el formato "nombre - $precio".
     */
    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

}