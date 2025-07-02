package ec.edu.ups.modelo;

import java.text.DecimalFormat;
import java.util.*;

public class Carrito {

    private final double IVA = 0.12;

    private static int contador = 1;

    private int codigo;
    private Date fechaCreacion;
    private List<ItemCarrito> items;

    public Carrito() {
        this.codigo = contador++;
        this.items = new ArrayList<>();
        this.fechaCreacion = new Date();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    public double calcularIVA() {
        return calcularSubtotal() * IVA;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        double subtotal = calcularSubtotal();
        double iva = subtotal * IVA;
        double total = subtotal + iva;

        return String.format("Carrito #%d | Fecha: %s | Items: %d | Subtotal: $%s | IVA: $%s | Total: $%s",
                codigo,
                fechaCreacion.toString(),
                items.size(),
                df.format(subtotal),
                df.format(iva),
                df.format(total));
    }
}
