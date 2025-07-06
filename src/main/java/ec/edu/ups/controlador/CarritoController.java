package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;

import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoListaView carritoListaView;
    private final CarritoEliminarView carritoEliminarView;
    private final CarritoDetalleView carritoDetalleView;
    private final CarritoModificarView carritoModificarView;
    private final MensajeInternacionalizacionHandler mensajeHandler;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             CarritoListaView carritoListaView,
                             CarritoEliminarView carritoEliminarView,
                             CarritoModificarView carritoModificarView,
                             CarritoDetalleView carritoDetalleView,
                             MensajeInternacionalizacionHandler mensajeHandler) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoListaView = carritoListaView;
        this.carritoEliminarView = carritoEliminarView;
        this.carritoModificarView = carritoModificarView;
        this.carritoDetalleView = carritoDetalleView;
        this.mensajeHandler = mensajeHandler;
        configurarEventos();
    }

    private void configurarEventos() {
        // ANADIR
        carritoAnadirView.getBtnAnadir().addActionListener(e -> anadirProducto());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getBtnLimpiar().addActionListener(e -> limpiarCarrito());
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoAnadir());

        // ELIMINAR
        carritoEliminarView.getBtnBuscar().addActionListener(e -> buscarParaEliminar());
        carritoEliminarView.getBtnEliminar().addActionListener(e -> eliminarCarrito());
        carritoEliminarView.getBtnVaciar().addActionListener(e -> vaciarCarrito());

        // LISTAR
        carritoListaView.getBtnBuscar().addActionListener(e -> buscarParaListar());
        carritoListaView.getBtnListar().addActionListener(e -> listarTodos());

        // MODIFICAR
        carritoModificarView.getBtnBuscar().addActionListener(e -> buscarParaModificar());
        carritoModificarView.getBtnModificar().addActionListener(e -> modificarFecha());

        // DETALLE
        carritoDetalleView.getBtnBuscarDetalle().addActionListener(e -> buscarDetalle());
        carritoDetalleView.getBtnAceptarDetalle().addActionListener(e -> carritoDetalleView.dispose());
    }

    private void anadirProducto() {
        if (carrito == null) {
            carrito = new Carrito(); // evita NullPointerException
        }
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            carrito.agregarProducto(producto, cantidad);
            cargarTablaAnadir();
            mostrarTotales();
        } else {
            carritoAnadirView.mostrarMensaje("Producto no encontrado."); // si quieres un mensaje de error
        }
    }


    private void cargarTablaAnadir() {
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        Locale locale = mensajeHandler.getLocale();

        for (ItemCarrito item : carrito.obtenerItems()) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                    item.getCantidad(),
                    FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
            });
        }
    }


    private void mostrarTotales() {
        Locale locale = mensajeHandler.getLocale();
        carritoAnadirView.getTxtSubTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale));
        carritoAnadirView.getTxtIVA().setText(FormateadorUtils.formatearMoneda(carrito.calcularIVA(), locale));
        carritoAnadirView.getTxtTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale));
    }

    private void guardarCarrito() {
        carrito.setCodigo(generarCodigo());
        carritoDAO.crear(carrito);  // guarda el carrito actual
        carritoAnadirView.mostrarMensaje("Carrito guardado correctamente.");
        limpiarCarrito();
        carrito = new Carrito(); // 🔁 REINICIA con un carrito nuevo
    }


    private int generarCodigo() {
        return carritoDAO.listarTodos().size() + 1;
    }

    private void limpiarCarrito() {
        carrito = new Carrito(); // reemplaza el carrito actual con uno vacío
        carritoAnadirView.limpiarCampos(); // limpia campos visuales
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0); // limpia tabla
        carritoAnadirView.getTxtSubTotal().setText("");
        carritoAnadirView.getTxtIVA().setText("");
        carritoAnadirView.getTxtTotal().setText("");
    }


    private void buscarProductoAnadir() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
    }

    private void buscarParaEliminar() {
        int codigo = Integer.parseInt(carritoEliminarView.getTxtCodigo().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);

        if (c != null) {
            DefaultTableModel modelo = (DefaultTableModel) carritoEliminarView.getTblLProductos().getModel();
            modelo.setRowCount(0);
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getProducto().getPrecio() * item.getCantidad()
                });
            }

            String fechaFormateada = FormateadorUtils.formatearFecha(c.getFechaCreacion(), mensajeHandler.getLocale());
            carritoEliminarView.getTxtFecha().setText(fechaFormateada);

        } else {
            carritoEliminarView.mostrarMensaje("Carrito no encontrado.");
            carritoEliminarView.getTxtFecha().setText(""); // Limpia si no se encontró
        }
    }

    private void eliminarCarrito() {
        int codigo = Integer.parseInt(carritoEliminarView.getTxtCodigo().getText());
        carritoDAO.eliminar(codigo);
        carritoEliminarView.mostrarMensaje("Carrito eliminado correctamente.");
        carritoEliminarView.limpiarCampos();
    }

    private void vaciarCarrito() {
        carrito.vaciarCarrito();
        carritoEliminarView.mostrarMensaje("Carrito vaciado.");
    }

    private void buscarParaListar() {
        int codigo = Integer.parseInt(carritoListaView.getTxtCodigo().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);
        DefaultTableModel modelo = (DefaultTableModel) carritoListaView.getTblPCarrito().getModel();
        modelo.setRowCount(0);
        Locale locale = mensajeHandler.getLocale();

        if (c != null) {
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                        item.getCantidad(),
                        FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
                });
            }

            carritoListaView.getTxtTotal().setText(
                    FormateadorUtils.formatearMoneda(c.calcularTotal(), locale)
            );
        }
    }

    private void listarTodos() {
        List<Carrito> carritos = carritoDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) carritoListaView.getTblPCarrito().getModel();
        modelo.setRowCount(0);
        Locale locale = mensajeHandler.getLocale();

        for (Carrito c : carritos) {
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                        item.getCantidad(),
                        FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
                });
            }
        }

        carritoListaView.getTxtTotal().setText("");
    }

    private void buscarParaModificar() {
        int codigo = Integer.parseInt(carritoModificarView.getTxtCodigo().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);

        DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getTblView().getModel();
        modelo.setRowCount(0); // Limpia la tabla

        if (c != null) {
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getSubtotal()
                });
            }

            Date nuevaFecha = new Date();
            c.setFechaCreacion(nuevaFecha);
            carritoModificarView.getTxtFecha().setText(
                    FormateadorUtils.formatearFecha(nuevaFecha, mensajeHandler.getLocale())
            );

            carritoModificarView.mostrarMensaje("Carrito actualizado.");
        } else {
            carritoModificarView.mostrarMensaje("Carrito no encontrado.");
            carritoModificarView.getTxtFecha().setText("");
        }
    }


    private void modificarFecha() {
        int codigo = Integer.parseInt(carritoModificarView.getTxtCodigo().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);

        if (c != null) {
            c.setFechaCreacion(new Date());
            carritoModificarView.mostrarMensaje("Carrito modificado correctamente.");
        } else {
            carritoModificarView.mostrarMensaje("No se pudo modificar. Carrito no encontrado.");
        }
    }

    private void buscarDetalle() {
        int codigo = Integer.parseInt(carritoDetalleView.getTxtIdDet().getText());
        Carrito c = carritoDAO.buscarPorCodigo(codigo);
        DefaultTableModel modelo = (DefaultTableModel) carritoDetalleView.getTblDetCarrito().getModel();
        modelo.setRowCount(0);
        Locale locale = mensajeHandler.getLocale();

        if (c != null) {
            for (ItemCarrito item : c.obtenerItems()) {
                modelo.addRow(new Object[]{
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        FormateadorUtils.formatearMoneda(item.getProducto().getPrecio(), locale),
                        item.getCantidad(),
                        FormateadorUtils.formatearMoneda(item.getSubtotal(), locale)
                });
            }

            carritoDetalleView.getTxtSubTotal().setText(FormateadorUtils.formatearMoneda(c.calcularSubtotal(), locale));
            carritoDetalleView.getTxtIVA().setText(FormateadorUtils.formatearMoneda(c.calcularIVA(), locale));
            carritoDetalleView.getTxtTotal().setText(FormateadorUtils.formatearMoneda(c.calcularTotal(), locale));
        } else {
            carritoDetalleView.mostrarMensaje("Carrito no encontrado.");
            carritoDetalleView.getTxtSubTotal().setText("");
            carritoDetalleView.getTxtIVA().setText("");
            carritoDetalleView.getTxtTotal().setText("");
        }
    }


    private void modificarProductosEnCarrito() {
        int codigo = Integer.parseInt(carritoModificarView.getTxtCodigo().getText());
        Carrito carritoMod = carritoDAO.buscarPorCodigo(codigo);

        if (carritoMod == null) {
            carritoModificarView.mostrarMensaje("Carrito no encontrado.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getTblView().getModel();

        carritoMod.vaciarCarrito(); // primero limpia el carrito

        for (int i = 0; i < modelo.getRowCount(); i++) {
            int codProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
            int nuevaCantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());

            Producto producto = productoDAO.buscarPorCodigo(codProducto);
            carritoMod.agregarProducto(producto, nuevaCantidad); // vuelve a agregar con nueva cantidad
        }

        carritoModificarView.mostrarMensaje("Carrito actualizado correctamente.");
    }

}
