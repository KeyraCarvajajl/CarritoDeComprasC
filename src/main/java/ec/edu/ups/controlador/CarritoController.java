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

/**
 * Controlador que gestiona las operaciones relacionadas con carritos de compras.
 * Actúa como intermediario entre las vistas del módulo "Carrito" y los DAOs correspondientes.
 *
 * <p>Responsabilidades:</p>
 * <ul>
 *   <li>Agregar productos al carrito</li>
 *   <li>Guardar, eliminar y modificar carritos</li>
 *   <li>Mostrar detalles y listar carritos</li>
 *   <li>Actualizar dinámicamente la vista con los datos</li>
 * </ul>
 *
 * <p>Aplica principios del patrón MVC y gestiona la internacionalización y el formateo
 * de moneda y fechas según la configuración de idioma seleccionada.</p>
 *
 * @author Keyra
 */

public class CarritoController {

    /**
     * DAO para acceder a los datos de carritos.
     */

    private final CarritoDAO carritoDAO;

    /**
     * DAO para acceder a los datos de productos.
     */

    private final ProductoDAO productoDAO;

    /**
     * Vista para añadir un nuevo carrito.
     */

    private final CarritoAnadirView carritoAnadirView;

    /**
     * Vista para listar carritos existentes.
     */

    private final CarritoListaView carritoListaView;

    /**
     * Vista para eliminar un carrito existente.
     */

    private final CarritoEliminarView carritoEliminarView;

    /**
     * Vista para ver el detalle de un carrito específico.
     */

    private final CarritoDetalleView carritoDetalleView;

    /**
     * Vista para modificar un carrito existente.
     */

    private final CarritoModificarView carritoModificarView;

    /**
     * Manejador para internacionalización y cambio de idioma.
     */

    private final MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Carrito actual en uso o edición.
     */

    private Carrito carrito;

    /**
     * Constructor principal que inicializa controladores, DAOs, vistas y configura eventos.
     */

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

    /**
     * Configura los eventos de todos los botones de las vistas de carrito.
     */

    private void configurarEventos() {

        /**
         * Eventos para añadir productos al carrito
         */

        carritoAnadirView.getBtnAnadir().addActionListener(e -> anadirProducto());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> guardarCarrito());
        carritoAnadirView.getBtnLimpiar().addActionListener(e -> limpiarCarrito());
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoAnadir());

        /**
         * Eventos para eliminar productos del carrito
         */

        carritoEliminarView.getBtnBuscar().addActionListener(e -> buscarParaEliminar());
        carritoEliminarView.getBtnEliminar().addActionListener(e -> eliminarCarrito());
        carritoEliminarView.getBtnVaciar().addActionListener(e -> vaciarCarrito());

        /**
         * Eventos para Listar productos del carrito
         */

        carritoListaView.getBtnBuscar().addActionListener(e -> buscarParaListar());
        carritoListaView.getBtnListar().addActionListener(e -> listarTodos());

        /**
         * Eventos para modificar productos del carrito
         */

        carritoModificarView.getBtnBuscar().addActionListener(e -> buscarParaModificar());
        carritoModificarView.getBtnModificar().addActionListener(e -> modificarFecha());

        /**
         * Eventos para detallar productos del carrito
         */

        carritoDetalleView.getBtnBuscarDetalle().addActionListener(e -> buscarDetalle());
        carritoDetalleView.getBtnAceptarDetalle().addActionListener(e -> carritoDetalleView.dispose());
    }

    /**
     * Añade un producto al carrito actual según el código y la cantidad seleccionados en la vista.
     * Si no existe un carrito activo, se crea uno nuevo. Luego se busca el producto usando el DAO,
     * y si se encuentra, se agrega al carrito con la cantidad indicada.
     * Finalmente se actualiza la tabla de productos y los totales.
     */

    private void anadirProducto() {
        if (carrito == null) {
            carrito = new Carrito();
        }
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            carrito.agregarProducto(producto, cantidad);
            cargarTablaAnadir();
            mostrarTotales();
        } else {
            carritoAnadirView.mostrarMensaje("Producto no encontrado.");
        }
    }

    /**
     * Llena la tabla de productos en la vista de añadir carrito con los ítems actuales del carrito.
     * Cada fila de la tabla muestra el código, nombre, precio unitario, cantidad y subtotal del producto.
     * El formato de moneda se ajusta según el idioma seleccionado.
     */

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

    /**
     * Calcula y muestra el subtotal, el IVA y el total del carrito en la vista de añadir.
     * Utiliza formateo local para los valores monetarios.
     */

    private void mostrarTotales() {
        Locale locale = mensajeHandler.getLocale();
        carritoAnadirView.getTxtSubTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale));
        carritoAnadirView.getTxtIVA().setText(FormateadorUtils.formatearMoneda(carrito.calcularIVA(), locale));
        carritoAnadirView.getTxtTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale));
    }

    /**
     * Guarda el carrito actual persistiendo sus datos en el DAO.
     * Se genera un nuevo código de carrito, se almacena en la lista de carritos,
     * se muestra un mensaje de éxito y se limpia el formulario para una nueva entrada.
     */

    private void guardarCarrito() {
        carrito.setCodigo(generarCodigo());
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje("Carrito guardado correctamente.");
        limpiarCarrito();
        carrito = new Carrito();
    }

    /**
     * Genera un nuevo código único para el carrito basándose en la cantidad de carritos existentes.
     *
     * @return un entero que representa el nuevo código del carrito.
     */

    private int generarCodigo() {
        return carritoDAO.listarTodos().size() + 1;
    }

    /**
     * Limpia completamente la vista de añadir carrito.
     * Esto incluye: vaciar el carrito, limpiar la tabla, y restablecer los campos de subtotal, IVA y total.
     */

    private void limpiarCarrito() {
        carrito = new Carrito();
        carritoAnadirView.limpiarCampos();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        carritoAnadirView.getTxtSubTotal().setText("");
        carritoAnadirView.getTxtIVA().setText("");
        carritoAnadirView.getTxtTotal().setText("");
    }

    /**
     * Busca un producto por su código ingresado en la vista de añadir y muestra sus datos.
     * Si el producto existe, se visualiza su nombre y precio en los campos correspondientes.
     */

    private void buscarProductoAnadir() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }
    }

    /**
     * Busca un carrito según el código ingresado en la vista de eliminación.
     * Si lo encuentra, carga sus productos en la tabla de eliminación y muestra la fecha de creación formateada.
     * Si no se encuentra, se limpia el campo de fecha y se muestra un mensaje de error.
     */

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
            carritoEliminarView.getTxtFecha().setText("");
        }
    }

    /**
     * Elimina el carrito cuyo código fue ingresado en la vista de eliminación.
     * Muestra un mensaje de confirmación y limpia la vista tras la eliminación.
     */

    private void eliminarCarrito() {
        int codigo = Integer.parseInt(carritoEliminarView.getTxtCodigo().getText());
        carritoDAO.eliminar(codigo);
        carritoEliminarView.mostrarMensaje("Carrito eliminado correctamente.");
        carritoEliminarView.limpiarCampos();
    }

    /**
     * Vacía el carrito activo, eliminando todos sus productos.
     * Muestra un mensaje de confirmación.
     * Solo afecta al carrito actual en memoria, no en el DAO.
     */

    private void vaciarCarrito() {
        carrito.vaciarCarrito();
        carritoEliminarView.mostrarMensaje("Carrito vaciado.");
    }

    /**
     * Busca un carrito por su código ingresado en la vista de listado y muestra sus productos en la tabla.
     * Si el carrito existe, carga los productos y el total calculado.
     * Si no existe, no se muestra nada.
     */

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

    /**
     * Lista todos los carritos disponibles en el sistema junto con sus productos.
     * Muestra el código, nombre, precio, cantidad y subtotal de cada producto en la tabla de la vista de listado.
     * El total general se deja vacío para evitar confusión al mostrar múltiples carritos.
     */

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

    /**
     * Busca un carrito por su código para permitir su modificación.
     * Si lo encuentra, muestra sus productos en la tabla y actualiza la fecha de creación.
     * También se notifica al usuario mediante un mensaje.
     */

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

    /**
     * Modifica únicamente la fecha de creación de un carrito ya existente.
     * La fecha se actualiza al momento actual.
     * Se notifica si la modificación fue exitosa o si no se encontró el carrito.
     */

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

    /**
     * Busca un carrito por su código y muestra un resumen detallado en la vista de detalle.
     * Si se encuentra, se muestran todos los productos del carrito, así como el subtotal, IVA y total.
     * Si no se encuentra, se limpian los campos y se muestra un mensaje de error.
     */

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

    /**
     * Permite modificar las cantidades de productos de un carrito.
     */

    private void modificarProductosEnCarrito() {
        int codigo = Integer.parseInt(carritoModificarView.getTxtCodigo().getText());
        Carrito carritoMod = carritoDAO.buscarPorCodigo(codigo);

        if (carritoMod == null) {
            carritoModificarView.mostrarMensaje("Carrito no encontrado.");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getTblView().getModel();

        carritoMod.vaciarCarrito();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int codProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
            int nuevaCantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());

            Producto producto = productoDAO.buscarPorCodigo(codProducto);
            carritoMod.agregarProducto(producto, nuevaCantidad);
        }
        carritoModificarView.mostrarMensaje("Carrito actualizado correctamente.");
    }
}
