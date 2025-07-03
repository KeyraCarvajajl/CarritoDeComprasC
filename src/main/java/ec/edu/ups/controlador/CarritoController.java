package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoListaView carritoListarView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoDetalleView carritoDetalleView;
    private final CarritoModificarView carritoModificarView;
    private final MensajeInternacionalizacionHandler mensajeHandler;
    private final Carrito carrito;



    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             CarritoListaView carritoListarView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoModificarView carritoModificarView,
                             CarritoDetalleView carritoDetalleView,
                             MensajeInternacionalizacionHandler mensajeI) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListarView = carritoListarView;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoDetalleView = carritoDetalleView;
        this.mensajeHandler = mensajeI;
        this.carrito = new Carrito();
        configurarEventosEnVistas();
        configurarEventosEnDetalles();

    }
    private void configurarEventosEnDetalles(){
        carritoDetalleView.getBtnBuscarDetalle().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        carritoDetalleView.getBtnAceptarDetalle().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carritoDetalleView.dispose();
            }
        });

    }

    private void configurarEventosEnVistas() {
        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });
        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });
        carritoAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });
        carritoEliminarView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elimarCarrito();
            }
        });
        carritoEliminarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigoParaEliminar();
            }
        });
        carritoEliminarView.getBtnVaciar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarCarrio();
            }
        });
        carritoListarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorCodigo();
            }
        });
        carritoListarView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCarrito();
            }
        });

    }

    private void buscarDetalleCarrito(){

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
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro que desea eliminar el carrito con código " + codigo + "?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );
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
        int codigo = Integer.parseInt(carritoListarView.getTxtCodigo().getText());
        Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
        if (carrito == null) {
            carritoListarView.mostrarMensaje("Carrito no encontrado");
            DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
            modelo.setRowCount(0);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
            modelo.setRowCount(0);
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
    private void vaciarCarrio(){
        carrito.vaciarCarrito();
        carritoEliminarView.mostrarMensaje("Carrito Vaciado");
        carritoEliminarView.limpiarCampos();
    }
    private void limpiarCampos() {
        carritoAnadirView.mostrarMensaje("Carrito Limpiado");
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        carritoAnadirView.limpiarCampos();
        carritoAnadirView.getCbxCantidad().setSelectedIndex(0);
    }
    private void guardarCarrito() {
        carrito.setCodigo(generarCodigo());
        System.out.println("Guardando carrito con código: " + carrito.getCodigo());
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito creado correctamente");
        System.out.println(carritoDAO.listarTodos());
    }
    private int generarCodigo() {
        return carritoDAO.listarTodos().size() + 1;
    }
    private void anadirProducto() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        int cantidad =  Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto, cantidad);
        cargarProductos();
        mostrarTotales();
    }

    private void cargarProductos(){
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{ item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad() });
        }
    }
    private void listarCarrito(){
        List<Carrito> carritos = carritoDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) carritoListarView.getTblPCarrito().getModel();
        modelo.setRowCount(0);

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
            carritoListarView.getTxtTotal().setText("");
        }
    }
    private void mostrarTotales(){
        Locale locale = carritoAnadirView.getMensajeHandler().getLocale();
        String subtotal = FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale);
        String iva = String.valueOf(carrito.calcularIVA());
        String total = String.valueOf(carrito.calcularTotal());

        System.out.println(FormateadorUtils.formatearFecha(carrito.getFechaCreacion().getTime(), locale));

        carritoAnadirView.getTxtSubTotal().setText(subtotal);
        carritoAnadirView.getTxtIVA().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }
    private void buscarProducto(){
        String codigoTexto = carritoAnadirView.getTxtCodigo().getText();

        if (!codigoTexto.matches("\\d+")) {
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
            return;
        }
        int codigo = Integer.parseInt(codigoTexto);
        Producto producto = productoDAO.buscarPorCodigo(codigo);

        if (producto == null) {
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.format("%.2f", producto.getPrecio()));
        }
    }
}
