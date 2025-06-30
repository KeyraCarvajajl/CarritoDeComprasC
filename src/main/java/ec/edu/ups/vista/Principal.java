package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.CarritoAnadirView;
import ec.edu.ups.vista.carrito.CarritoEliminarView;
import ec.edu.ups.vista.carrito.CarritoListaView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuIdioma;
    private JMenu menuSalir;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemAñadirCarrito;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemEliminarCarrito;

    private JMenuItem menuItemIdiomaEspanol;
    private JMenuItem menuItemIdiomaIngles;
    private JMenuItem menuItemIdiomaFrances;

    private JMenuItem menuItemSalir;
    private JMenuItem menuItemCerrarSesion;


    private MiJDesktopPane jDesktopPane;
    private JLabel lblUsuario;

    private ProductoAnadirView productoAnadirView;
    private CarritoAnadirView carritoAnadirView;
    private CarritoEliminarView carritoEliminarView;
    private CarritoListaView carritoListaView;
    private ProductoEliminarView productoEliminarView;
    private ProductoModificarView productoModificarView;
    private ProductoListaView productoListaView;


    public Principal() {
        mensajeInternacionalizacionHandler = new MensajeInternacionalizacionHandler("es","EC");
        initComponents();
    }

    public JMenuBar getBarraMenu() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void mostrarUsuario(String nombre) {
        lblUsuario.setText("Usuario: " + nombre);
    }


    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public void setMenuItemActualizarProducto(JMenuItem menuItemActualizarProducto) {
        this.menuItemActualizarProducto = menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemAñadirCarrito() {
        return menuItemAñadirCarrito;
    }

    public void setMenuItemAñadirCarrito(JMenuItem menuItemAñadirCarrito) {
        this.menuItemAñadirCarrito = menuItemAñadirCarrito;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(MiJDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
    }

    public JMenu getMenuSalir() {
        return menuSalir;
    }

    public void setMenuSalir(JMenu menuSalir) {
        this.menuSalir = menuSalir;
    }

    public JMenuItem getMenuItemIdiomaEspanol() {
        return menuItemIdiomaEspanol;
    }

    public void setMenuItemIdiomaEspanol(JMenuItem menuItemIdiomaEspanol) {
        this.menuItemIdiomaEspanol = menuItemIdiomaEspanol;
    }

    public JMenuItem getMenuItemIdiomaIngles() {
        return menuItemIdiomaIngles;
    }

    public void setMenuItemIdiomaIngles(JMenuItem menuItemIdiomaIngles) {
        this.menuItemIdiomaIngles = menuItemIdiomaIngles;
    }

    public JMenuItem getMenuItemSalir() {
        return menuItemSalir;
    }

    public void setMenuItemSalir(JMenuItem menuItemSalir) {
        this.menuItemSalir = menuItemSalir;
    }

    public JMenuItem getMenuItemIdiomaFrances() {
        return menuItemIdiomaFrances;
    }

    public void setMenuItemIdiomaFrances(JMenuItem menuItemIdiomaFrances) {
        this.menuItemIdiomaFrances = menuItemIdiomaFrances;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }

    private void initComponents() {
        jDesktopPane = new MiJDesktopPane();
        setContentPane(jDesktopPane); // o usa add(jDesktopPane) si estás usando Layouts
        menuBar = new JMenuBar();


        menuProducto = new JMenu(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito = new JMenu(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuIdioma = new JMenu(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuSalir = new JMenu(mensajeInternacionalizacionHandler.get("menu.salir"));


        menuItemCrearProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.crear"));

        menuItemEliminarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemActualizarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));
        menuItemAñadirCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));

        menuItemIdiomaEspanol = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemSalir = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.salir.salir"));
        menuItemCerrarSesion = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuIdioma);
        menuBar.add(menuSalir);
        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuCarrito.add(menuItemAñadirCarrito);
        menuCarrito.add(menuItemBuscarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuIdioma.add(menuItemIdiomaEspanol);
        menuIdioma.add(menuItemIdiomaIngles);
        menuIdioma.add(menuItemIdiomaFrances);
        menuSalir.add(menuItemSalir);
        menuSalir.add(menuItemCerrarSesion);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblUsuario, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        menuItemSalir.addActionListener(e -> System.exit(0));
        menuItemCerrarSesion.addActionListener(e -> {
            dispose();
            LoginView loginView = new LoginView(mensajeInternacionalizacionHandler);
            loginView.cambiarIdioma();
            loginView.setVisible(true);
        });

    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemActualizarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
    }

    public void cambiarIdioma(String  lenguaje, String pais) {
        mensajeInternacionalizacionHandler.setLenguaje(lenguaje, pais);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));

        menuProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuSalir.setText(mensajeInternacionalizacionHandler.get("menu.salir"));

        menuItemCrearProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemActualizarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuItemAñadirCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));

        menuItemIdiomaEspanol.setText(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles.setText(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances.setText(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemSalir.setText(mensajeInternacionalizacionHandler.get("menu.salir.salir"));
        menuItemCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));

        jDesktopPane.setMensajeHandler(mensajeInternacionalizacionHandler);


        if (carritoAnadirView != null && !carritoAnadirView.isClosed()) {
            carritoAnadirView.setMensajeHandler(mensajeInternacionalizacionHandler);
            carritoAnadirView.cambiarIdioma();
        }

        if (carritoEliminarView != null && carritoEliminarView.isVisible()) {
            carritoEliminarView.setMensajeHandler(mensajeInternacionalizacionHandler);
            carritoEliminarView.cambiarIdioma();
        }


        for (JInternalFrame frame : jDesktopPane.getAllFrames()) {
            if (frame instanceof ProductoAnadirView pav) {
                pav.setMensajeHandler(mensajeInternacionalizacionHandler);
                pav.cambiarIdioma();
            }
        }

        if (carritoListaView != null) carritoListaView.setMensajeHandler(mensajeInternacionalizacionHandler);
        if (carritoListaView != null) carritoListaView.cambiarIdioma();

        if (productoAnadirView != null) productoAnadirView.setMensajeHandler(mensajeInternacionalizacionHandler);
        if (carritoAnadirView != null) carritoAnadirView.setMensajeHandler(mensajeInternacionalizacionHandler);
        if (carritoEliminarView != null) carritoEliminarView.setMensajeHandler(mensajeInternacionalizacionHandler);
        if (carritoListaView != null) carritoListaView.setMensajeHandler(mensajeInternacionalizacionHandler);

        if (productoEliminarView != null && productoEliminarView.isVisible()) {
            productoEliminarView.setMensajeHandler(mensajeInternacionalizacionHandler);
            productoEliminarView.cambiarIdioma();
        }

        if (productoModificarView != null && productoModificarView.isVisible()) {
            productoModificarView.setMensajeHandler(mensajeInternacionalizacionHandler);
            productoModificarView.cambiarIdioma();
        }

        if (productoListaView != null && productoListaView.isVisible()) {
            productoListaView.setMensajeHandler(mensajeInternacionalizacionHandler);
            productoListaView.cambiarIdioma();
        }


    }

    public void setCarritoAnadirView(CarritoAnadirView carritoAnadirView) {
        this.carritoAnadirView = carritoAnadirView;
    }

    public void setCarritoEliminarView(CarritoEliminarView carritoEliminarView) {
        this.carritoEliminarView = carritoEliminarView;
    }

    public void setCarritoListaView(CarritoListaView carritoListaView) {
        this.carritoListaView = carritoListaView;
    }

    public void setProductoAnadirView(ProductoAnadirView productoAnadirView) {
        this.productoAnadirView = productoAnadirView;
    }

    public void setProductoEliminarView(ProductoEliminarView productoEliminarView) {
        this.productoEliminarView = productoEliminarView;
    }

    public void setProductoModificarView(ProductoModificarView productoModificarView) {
        this.productoModificarView = productoModificarView;
    }

    public void setProductoListaView(ProductoListaView productoListaView) {
        this.productoListaView = productoListaView;
    }
}
