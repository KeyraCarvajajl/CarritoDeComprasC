package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.*;

/**
 * Clase que representa un carrito de compras. Cada carrito contiene una lista de
 * productos seleccionados con su respectiva cantidad, fecha de creación, y métodos
 * para calcular subtotal, IVA y total a pagar.
 *
 * El carrito aplica un IVA del 15% y asigna un código autoincremental a cada instancia.
 *
 * @author Keyra
 */
public class Carrito implements Serializable {

    /**
     * Porcentaje de IVA aplicado al subtotal (15%).
     */
    private final double IVA = 0.15;

    /**
     * Contador estático para generar códigos únicos para cada carrito.
     */
    private static int contador = 1;

    /**
     * Código único del carrito.
     */
    private int codigo;

    /**
     * Fecha de creación del carrito.
     */
    private Date fechaCreacion;

    /**
     * Lista de ítems contenidos en el carrito.
     */
    private List<ItemCarrito> items;

    /**
     * Constructor. Inicializa el carrito con un código único, la fecha actual y una lista vacía de ítems.
     */
    public Carrito() {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new Date();
    }

    /**
     * Constructor con parámetros para reconstrucción desde archivo.
     *
     * @param codigo Código del carrito.
     * @param fecha Fecha de creación.
     */
    public Carrito(int codigo, Date fecha) {
        this.codigo = codigo;
        this.fechaCreacion = fecha;
        this.items = new ArrayList<>();
    }


    /**
     * Retorna el código del carrito.
     *
     * @return código numérico único del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Asigna un nuevo código al carrito.
     *
     * @param codigo nuevo valor para el código.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la fecha de creación del carrito.
     *
     * @return objeto Date que representa la fecha.
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del carrito.
     *
     * @param fechaCreacion nueva fecha de creación.
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto al carrito junto con la cantidad especificada.
     *
     * @param producto producto a agregar.
     * @param cantidad número de unidades del producto.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito usando su código.
     *
     * @param codigoProducto código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Elimina todos los productos del carrito.
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Devuelve la lista de ítems contenidos en el carrito.
     *
     * @return lista de objetos ItemCarrito.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     *
     * @return {@code true} si no contiene ítems, {@code false} en caso contrario.
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el subtotal del carrito sumando el precio por cantidad de cada ítem.
     *
     * @return valor numérico del subtotal.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /**
     * Calcula el IVA del carrito a partir del subtotal.
     *
     * @return monto del IVA (15% del subtotal).
     */
    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    /**
     * Calcula el total del carrito sumando el subtotal más el IVA.
     *
     * @return total a pagar.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Devuelve una representación en texto del carrito con detalles de fecha, subtotal, IVA y total.
     *
     * @return string descriptivo del carrito.
     */
    @Override
    public String toString() {
        return String.format("Carrito #%d | Fecha: %s | Items: %d | Subtotal: $%.2f | IVA: $%.2f | Total: $%.2f",
                codigo,
                fechaCreacion.toString(),
                items.size(),
                calcularSubtotal(),
                calcularIVA(),
                calcularTotal());
    }


}
