package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.CarritoEliminarView;
import ec.edu.ups.vista.CarritoListaView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private CarritoListaView carritoListarView;
    private CarritoEliminarView carritoEliminarView;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO, ProductoDAO productoDAO, CarritoAnadirView carritoAnadirView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carrito = new Carrito();
        configurarEventosEnVistas();
    }

    public void setCarritoListaView(CarritoListaView carritoListaView) {
        this.carritoListarView = carritoListaView;
        configurarEventosEnLista();
    }

    public void setCarritoEliminarView(CarritoEliminarView carritoEliminarView) {
        this.carritoEliminarView = carritoEliminarView;
        configurarEventosEnEliminar();
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    private void configurarEventosEnEliminar() {
        carritoEliminarView.getBtnEliminar().addActionListener(e -> elimarCarrito());
        carritoEliminarView.getBtnBuscar().addActionListener(e -> buscarPorCodigoParaEliminar());
    }

    private void configurarEventosEnLista() {
        carritoListarView.getBtnBuscar().addActionListener(e -> buscarPorCodigo());
        carritoListarView.getBtnListar().addActionListener(e -> listarCarrito());
    }

    private void configurarEventosEnVistas() {
        carritoAnadirView.getBtnAñadir().addActionListener(e -> anadirProducto());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getBtnLimpiar().addActionListener(e -> limpiarCampos());
    }

    private void elimarCarrito() {
        String codigoTexto = carritoEliminarView.getTxtCodigo().getText();
        if (!codigoTexto.matches("\\d+")) {
            carritoEliminarView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
        if (carrito == null) {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que desea eliminar el carrito con código " + codigo + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            carritoDAO.eliminar(codigo);
            carritoEliminarView.mostrarMensaje("Carrito eliminado correctamente.");
            carritoEliminarView.limpiarCampos();
        } else {
            carritoEliminarView.mostrarMensaje("Eliminación cancelada.");
        }
    }

    private void buscarPorCodigoParaEliminar() {
        String codigoTexto = carritoEliminarView.getTxtCodigo().getText();
        if (!codigoTexto.matches("\\d+")) {
            carritoEliminarView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        DefaultTableModel modelo = (DefaultTableModel) carritoEliminarView.getTblLProductos().getModel();
        modelo.setRowCount(0);

        if (carrito == null) {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado.");
        } else {
            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }
        }
    }

    private void buscarPorCodigo() {
        String codigoTexto = carritoListarView.getTxtCodigo().getText();
        if (!codigoTexto.matches("\\d+")) {
            carritoListarView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);

        DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
        modelo.setRowCount(0);

        if (carrito == null) {
            carritoListarView.mostrarMensaje("Carrito no encontrado");
        } else {
            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }
            carritoListarView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotal()));
        }
    }

    private void limpiarCampos() {
        carrito.vaciarCarrito();
        carritoAnadirView.mostrarMensaje("Carrito Limpiado");
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        carritoAnadirView.limpiarCampos();
        carritoAnadirView.getCbxCantidad().setSelectedIndex(0);
    }

    private void guardarCarrito() {
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito creado correctamente");
        System.out.println(carritoDAO.listarTodos());
    }

    private void anadirProducto() {
        String codigoTexto = carritoAnadirView.getTxtCodigo().getText();
        if (!codigoTexto.matches("\\d+")) {
            carritoAnadirView.mostrarMensaje("Ingrese un código válido.");
            return;
        }

        int codigo = Integer.parseInt(codigoTexto);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.mostrarMensaje("Producto no encontrado.");
            return;
        }

        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto, cantidad);
        cargarProductos();
        mostrarTotales();
    }

    private void cargarProductos() {
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad()
            });
        }
    }

    private void listarCarrito() {
        List<Carrito> carritos = carritoDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
        modelo.setRowCount(0);
        carritoListarView.getTxtTotal().setText(""); // limpiar total una sola vez

        for (Carrito c : carritos) {
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }
        }
    }

    private void mostrarTotales() {
        carritoAnadirView.getTxtSubTotal().setText(String.format("%.2f", carrito.calcularSubtotal()));
        carritoAnadirView.getTxtIva().setText(String.format("%.2f", carrito.calcularIVA()));
        carritoAnadirView.getTxtTotal().setText(String.format("%.2f", carrito.calcularTotal()));
    }
}
