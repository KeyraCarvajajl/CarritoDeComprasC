package ec.edu.ups.modelo;

/**
 * Clase que representa un ítem dentro del carrito de compras.
 * Cada ítem contiene un producto y la cantidad correspondiente.
 *
 * Proporciona métodos para acceder al producto, la cantidad y
 * calcular el subtotal del ítem.
 *
 * @author Keyra
 */
public class ItemCarrito {

    /**
     * Producto asociado al ítem.
     */
    private Producto producto;

    /**
     * Cantidad del producto agregada al carrito.
     */
    private int cantidad;

    public ItemCarrito() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param producto Producto a agregar.
     * @param cantidad Cantidad del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Asigna el producto al ítem.
     *
     * @param producto Producto que se desea asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Asigna la cantidad de unidades del producto.
     *
     * @param cantidad Número de unidades.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el producto asignado al ítem.
     *
     * @return Producto del ítem.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Devuelve la cantidad de unidades del producto.
     *
     * @return Cantidad del ítem.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del ítem como precio * cantidad.
     *
     * @return Subtotal del ítem.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Devuelve una representación textual del ítem,
     * incluyendo el producto, cantidad y subtotal.
     *
     * @return Cadena descriptiva del ítem.
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}
