package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import javax.swing.JMenuBar;

public class MenuPrincipalView extends JFrame {

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
    private JMenu menuCerrarSesion;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemModificarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemAnadirCarrito;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemModificarCarrito;
    private JMenuItem menuItemListaCarrito;
    private JMenuItem menuItemDetalleCarrito;

    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemListaUsuario;

    private JMenuItem menuItemIdiomaEspanol;
    private JMenuItem menuItemIdiomaIngles;
    private JMenuItem menuItemIdiomaFrances;

    private JMenuItem menuItemCerrarSesion;

    private MiJDesktopPane jDesktopPane;
    private JLabel lblUsuario;

    private ProductoAnadirView productoAnadirView;
    private ProductoEliminarView productoEliminarView;
    private ProductoModificarView productoModificarView;
    private ProductoListaView productoListaView;

    private CarritoDetalleView carritoDetalleView;
    private CarritoEliminarView carritoEliminarView;
    private CarritoModificarView carritoModificarView;
    private CarritoListaView carritoListaView;
    private CarritoAnadirView carritoAnadirView;

    private CambiarContraseniaView cambiarContraseniaView;
    private CuestionarioView cuestionarioView;

    public MenuPrincipalView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeInternacionalizacionHandler = mensajeHandler;
        initComponents();
        agregarListeners();
    }

    private void initComponents() {
        jDesktopPane = new MiJDesktopPane();

        menuBar = new JMenuBar();
        mensajeInternacionalizacionHandler.get("menu.producto");
        menuProducto = new JMenu(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito = new JMenu(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuUsuario = new JMenu(mensajeInternacionalizacionHandler.get("menu.usuario"));
        menuIdioma = new JMenu(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuCerrarSesion = new JMenu(mensajeInternacionalizacionHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuItemModificarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.usuario.modificar"));
        menuItemListaUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("Listar Usuarios"));
        menuItemEliminarUsuario = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.usuario.eliminar"));

        menuItemAnadirCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemEliminarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));
        menuItemModificarCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.actualizar"));
        menuItemListaCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.carrito.listar"));
        menuItemDetalleCarrito = new JMenuItem(mensajeInternacionalizacionHandler.get("Detalle Carrito"));

        menuItemIdiomaEspanol = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion = new JMenuItem(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemModificarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemAnadirCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemModificarCarrito);
        menuCarrito.add(menuItemListaCarrito);
        menuCarrito.add(menuItemDetalleCarrito);

        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemListaUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuIdioma.add(menuItemIdiomaEspanol);
        menuIdioma.add(menuItemIdiomaIngles);
        menuIdioma.add(menuItemIdiomaFrances);

        menuCerrarSesion.add(menuItemCerrarSesion);

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuBar.add(menuCerrarSesion);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        MiJDesktopPane dibujo = new MiJDesktopPane();
        dibujo.setPreferredSize(new Dimension(400, 300));
        add(dibujo, BorderLayout.CENTER);
    }

    private void agregarListeners() {
        menuItemCerrarSesion.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    mensajeInternacionalizacionHandler.get("mensaje.confirmarCerrarSesion"),
                    mensajeInternacionalizacionHandler.get("titulo.confirmar"),
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                this.dispose();
                LoginView login = new LoginView(mensajeInternacionalizacionHandler);
                login.setVisible(true);
            }
        });

        menuItemIdiomaEspanol.addActionListener(e -> {
            actualizarTextos("es", "EC");

            if (carritoAnadirView != null) {
                carritoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoDetalleView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoListaView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoModificarView != null) {
                carritoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoEliminarView != null) {
                carritoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cambiarContraseniaView != null) {
                cambiarContraseniaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cuestionarioView != null) {
                cuestionarioView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoAnadirView != null) {
                productoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoEliminarView != null) {
                productoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoModificarView != null) {
                productoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoListaView != null) {
                productoListaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }
        });

        menuItemIdiomaIngles.addActionListener(e -> {
            actualizarTextos("en", "US");

            if (carritoAnadirView != null) {
                carritoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoDetalleView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoListaView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoModificarView != null) {
                carritoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoEliminarView != null) {
                carritoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cambiarContraseniaView != null) {
                cambiarContraseniaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cuestionarioView != null) {
                cuestionarioView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoAnadirView != null) {
                productoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoEliminarView != null) {
                productoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoModificarView != null) {
                productoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoListaView != null) {
                productoListaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }
        });

        menuItemIdiomaFrances.addActionListener(e -> {
            actualizarTextos("fr", "FR");

            if (carritoAnadirView != null) {
                carritoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoDetalleView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoListaView != null) {
                carritoDetalleView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoModificarView != null) {
                carritoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (carritoEliminarView != null) {
                carritoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cambiarContraseniaView != null) {
                cambiarContraseniaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (cuestionarioView != null) {
                cuestionarioView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoAnadirView != null) {
                productoAnadirView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoEliminarView != null) {
                productoEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoModificarView != null) {
                productoModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (productoListaView != null) {
                productoListaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }
        });

    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
    }

    public void actualizarTextos(String lenguaje, String pais){
        mensajeInternacionalizacionHandler.setLenguaje(lenguaje, pais);
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));

        menuProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario"));
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        menuCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuItemAnadirCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.añadir"));
        menuItemListaCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));
        menuItemModificarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.actualizar"));
        menuItemDetalleCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.detalle"));

        menuItemModificarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.modificar"));
        menuItemEliminarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.eliminar"));
        menuItemListaUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.cargar"));

        menuItemIdiomaEspanol.setText(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles.setText(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances.setText(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));
    }

    public void deshabilitarMenusAdministrador(){
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
        getMenuItemModificarProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemAnadirCarrito().setEnabled(false);
        getMenuItemListaCarrito().setEnabled(false);
    }

    public MenuBar getMenuBar() {
        return null;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenuProducto() {
        return menuProducto;
    }

    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
    }

    public JMenu getMenuCerrarSesion() {
        return menuCerrarSesion;
    }

    public void setMenuCerrarSesion(JMenu menuCerrarSesion) {
        this.menuCerrarSesion = menuCerrarSesion;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemModificarProducto() {
        return menuItemModificarProducto;
    }

    public void setMenuItemModificarProducto(JMenuItem menuItemModificarProducto) {
        this.menuItemModificarProducto = menuItemModificarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemAnadirCarrito() {
        return menuItemAnadirCarrito;
    }

    public void setMenuItemAnadirCarrito(JMenuItem menuItemAnadirCarrito) {
        this.menuItemAnadirCarrito = menuItemAnadirCarrito;
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    public void setMenuItemModificarCarrito(JMenuItem menuItemModificarCarrito) {
        this.menuItemModificarCarrito = menuItemModificarCarrito;
    }

    public JMenuItem getMenuItemListaCarrito() {
        return menuItemListaCarrito;
    }

    public void setMenuItemListaCarrito(JMenuItem menuItemListaCarrito) {
        this.menuItemListaCarrito = menuItemListaCarrito;
    }

    public JMenuItem getMenuItemDetalleCarrito() {
        return menuItemDetalleCarrito;
    }

    public void setMenuItemDetalleCarrito(JMenuItem menuItemDetalleCarrito) {
        this.menuItemDetalleCarrito = menuItemDetalleCarrito;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) {
        this.menuItemEliminarUsuario = menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    public void setMenuItemModificarUsuario(JMenuItem menuItemModificarUsuario) {
        this.menuItemModificarUsuario = menuItemModificarUsuario;
    }

    public JMenuItem getMenuItemListaUsuario() {
        return menuItemListaUsuario;
    }

    public void setMenuItemListaUsuario(JMenuItem menuItemListaUsuario) {
        this.menuItemListaUsuario = menuItemListaUsuario;
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

    public MiJDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(MiJDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JDesktopPane getDesktopPane() {
        return jDesktopPane;  // o el nombre real que   tu JDesktopPane
    }

}