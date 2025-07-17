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

/**
 * Controlador de productos dentro del sistema de carrito de compras.
 * <p>
 * Esta clase se encarga de coordinar la lógica entre las vistas relacionadas con
 * los productos y el acceso a los datos a través del DAO. Administra las operaciones
 * de añadir, listar, modificar y eliminar productos, así como la interacción con
 * la vista de añadir productos a carritos.
 * </p>
 * <p>
 * Se sigue el patrón MVC (Modelo-Vista-Controlador), separando la lógica de negocio
 * de la presentación para mejorar la mantenibilidad y escalabilidad del sistema.
 * </p>
 *
 * @author Keyra
 */

public class ProductoController {

    /**
     * Vista para añadir nuevos productos al sistema.
     */

    private ProductoAnadirView productoAnadirView;

    /**
     * Vista para listar todos los productos registrados.
     */

    private ProductoListaView productoListaView;

    /**
     * Vista para modificar los datos de un producto existente.
     */

    private ProductoModificarView productoModificarView;

    /**
     * Vista para eliminar un producto del sistema.
     */

    private ProductoEliminarView productoEliminarView;

    /**
     * Vista que permite añadir productos a un carrito de compras.
     * Se utiliza para buscar productos existentes y agregarlos con una cantidad.
     */

    private CarritoAnadirView carritoAnadirView;

    /**
     * Vista que permite eliminar productos de un carrito.
     */

    private CarritoEliminarView carritoEliminarView;

    /**
     * Objeto de acceso a datos para productos.
     * Proporciona métodos CRUD sobre la colección de productos.
     */

    private final ProductoDAO productoDAO;

    /**
     * Constructor de la clase ProductoController.
     * Inicializa todas las vistas relacionadas con la gestión de productos
     * y el DAO encargado del acceso a datos.
     *
     * @param productoAnadirView Vista para añadir productos.
     * @param productoListaView Vista para listar productos.
     * @param productoModificarView Vista para modificar productos.
     * @param productoEliminarView Vista para eliminar productos.
     * @param carritoAnadirView Vista para añadir productos a un carrito.
     * @param productoDAO DAO que gestiona los datos de productos.
     */

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

    /**
     * Establece la vista de añadir producto y configura sus eventos.
     *
     * @param productoAnadirView la vista de añadir producto
     */

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
        this.configurarAnadirEventos();
    }

    /**
     * Establece la vista de listar productos y configura sus eventos.
     *
     * @param productoListaView la vista de listar productos
     */

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
        this.configurarListaEventos();
    }

    /**
     * Establece la vista de modificar productos y configura sus eventos.
     *
     * @param productoModificarView la vista de modificar productos
     */

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
        this.configurarModificarEventos();
    }

    /**
     * Establece la vista de eliminar productos y configura sus eventos.
     *
     * @param productoEliminarView la vista de eliminar productos
     */

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
        this.configurarEliminarEventos();
    }

    /**
     * Establece la vista de añadir producto desde el carrito.
     *
     * @param carritoAnadirView la vista de añadir producto desde el carrito
     */

    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {
        this.carritoAnadirView = carritoAnadirView;
    }

    /**
     * Establece la vista de eliminar producto desde el carrito.
     *
     * @param carritoEliminarView la vista de eliminar producto desde el carrito
     */

    public void setCarritoEliminarView(CarritoEliminarView carritoEliminarView) {
        this.carritoEliminarView = carritoEliminarView;
    }

    /**
     * Configura los eventos para la vista de eliminar productos.
     */

    private void configurarEliminarEventos() {
        productoEliminarView.getBtnBuscar().addActionListener(e -> buscarProductoParaEliminar());
        productoEliminarView.getBtnEliminar().addActionListener(e -> eliminarProducto());
    }

    /**
     * Configura los eventos para la vista de modificar productos.
     */

    private void configurarModificarEventos() {
        productoModificarView.getBtnModificar().addActionListener(e -> modificarProducto());
        productoModificarView.getBtnBuscar().addActionListener(e -> buscarProductoParaModificar());
    }

    /**
     * Configura los eventos para la vista de añadir productos.
     */

    private void configurarAnadirEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(e -> guardarProducto());
    }

    /**
     * Configura los eventos para la vista de listar productos.
     */

    private void configurarListaEventos() {
        if (productoListaView.getBtnListar() != null) {
            productoListaView.getBtnListar().addActionListener(e -> listarProductos());
        }
    }

    /**
     * Busca un producto por su código para mostrarlo en la vista de eliminación.
     */

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

    /**
     * Elimina un producto del sistema después de una confirmación del usuario.
     */

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

    /**
     * Busca un producto por su código para modificarlo en la vista correspondiente.
     */

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

    /**
     * Modifica los datos de un producto y actualiza el DAO.
     */

    private void modificarProducto() {
        int codigo = Integer.parseInt(productoModificarView.getTxtCodigo().getText());
        String nombre = productoModificarView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoModificarView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.actualizar(producto);
        productoModificarView.mostrarMensaje("Producto modificado correctamente");
        productoModificarView.limpiarCampos();
    }

    /**
     * Guarda un nuevo producto en el sistema y actualiza la vista.
     */

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    /**
     * Lista todos los productos y los muestra en la vista correspondiente.
     */

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }
}

