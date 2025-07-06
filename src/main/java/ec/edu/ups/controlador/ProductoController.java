package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.carrito.CarritoAnadirView;
import ec.edu.ups.vista.carrito.CarritoEliminarView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;

import javax.swing.*;
import java.util.List;

public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoListaView productoListaView;
    private ProductoModificarView productoModificarView;
    private ProductoEliminarView productoEliminarView;
    private CarritoAnadirView carritoAnadirView;
    private CarritoEliminarView carritoEliminarView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoModificarView productoModificarView,
                              ProductoEliminarView productoEliminarView,
                              CarritoAnadirView carritoAnadirView,
                              ProductoDAO productoDAO) {
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoModificarView = productoModificarView;
        this.productoEliminarView = productoEliminarView;
        this.carritoAnadirView = carritoAnadirView;
        this.productoDAO = productoDAO;
    }

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.configurarAnadirEventos();
    }

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
        this.configurarListaEventos();
    }

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
        this.configurarModificarEventos();
    }

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
        this.configurarEliminarEventos();
    }

    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {
        this.carritoAnadirView = carritoAnadirView;
    }

    public void setCarritoEliminarView(CarritoEliminarView carritoEliminarView) {
        this.carritoEliminarView = carritoEliminarView;
    }

    private void configurarEliminarEventos() {
        productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoParaEliminar());
        productoEliminarView.getBtnEliminar().addActionListener(e -> eliminarProducto());
    }

    private void configurarModificarEventos() {
        productoModificarView.getBtnModificar().addActionListener(e -> modificarProducto());
        productoModificarView.getBtnBuscar().addActionListener(e -> buscarProductoParaModificar());
    }

    private void configurarAnadirEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());
    }

    private void configurarListaEventos() {
        if (productoListaView.getBtnListar() != null) {
            productoListaView.getBtnListar().addActionListener(e -> listarProductos());
        }
    }

    private void buscarProductoParaEliminar() {
        int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoEliminarView.getTxtNombre().setText(producto.getNombre());
            productoEliminarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoEliminarView.mostrarMensaje("Producto no encontrado");
            productoEliminarView.limpiarCampos();
        }
    }

    private void eliminarProducto() {
        int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Está seguro que desea eliminar este producto?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            int codigo = Integer.parseInt(productoEliminarView.getTxtCodigo().getText());
            productoDAO.eliminar(codigo);
            productoEliminarView.mostrarMensaje("Producto eliminado correctamente");
            productoEliminarView.limpiarCampos();
        } else {
            productoEliminarView.mostrarMensaje("Eliminación cancelada");
        }
    }

    private void buscarProductoParaModificar() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto != null) {
            productoModificarView.getTxtNombre().setText(producto.getNombre());
            productoModificarView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        } else {
            productoModificarView.mostrarMensaje("Producto no encontrado");
            productoModificarView.limpiarCampos();
        }
    }

    private void modificarProducto() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        String nombre = productoModificarView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(producto);
        productoModificarView.mostrarMensaje("Producto modificado correctamente");
        productoModificarView.limpiarCampos();
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
}

