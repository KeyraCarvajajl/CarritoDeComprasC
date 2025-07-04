package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalView extends JFrame {

    private MensajeInternacionalizacionHandler mensajeHandler;

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

    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemBuscarUsuario;

    private JMenuItem menuItemIdiomaEspanol;
    private JMenuItem menuItemIdiomaIngles;
    private JMenuItem menuItemIdiomaFrances;

    private JMenuItem menuItemCerrarSesion;

    private JDesktopPane jDesktopPane;
    private JLabel lblUsuario;

    private ProductoAnadirView ventanaProductoAnadir;
    private ProductoEliminarView ventanaProductoEliminar;
    private ProductoModificarView ventanaProductoModificar;
    private ProductoListaView ventanaProductoLista;

    private CarritoAnadirView ventanaCarritoAnadir;
    private CarritoDetalleView ventanaCarritoDetalle;
    private CarritoEliminarView ventanaCarritoEliminar;
    private CarritoModificarView ventanaCarritoModificar;
    private CarritoListaView ventanaCarritoLista;

    private UsuarioEliminarView ventanaUsuarioEliminar;
    private UsuarioModificarView ventanaUsuarioModificar;
    private UsuarioListaView ventanaUsuarioLista;
    private UsuarioListaView ventanaUsuarioListar;
    private RegistrarseView ventanaRegistrarse;
    private LoginView ventanaLogin;

    private CuestionarioView ventanaRecuperarContrasenia;
    private CambiarContraseniaView ventanaCambiarContrasenia;


    public MenuPrincipalView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        initComponents();
    }

    private void initComponents() {
        jDesktopPane = new MiJDesktopPane();
        menuBar = new JMenuBar();
        menuProducto = new JMenu(mensajeHandler.get("menu.producto"));
        menuCarrito = new JMenu(mensajeHandler.get("menu.carrito"));
        menuUsuario = new JMenu(mensajeHandler.get("menu.usuario"));
        menuIdioma = new JMenu(mensajeHandler.get("menu.idiomas"));
        menuCerrarSesion = new JMenu(mensajeHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto = new JMenuItem(mensajeHandler.get("menu.producto.crear"));
        menuItemEliminarProducto = new JMenuItem(mensajeHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto = new JMenuItem(mensajeHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(mensajeHandler.get("menu.producto.buscar"));

        menuItemModificarUsuario = new JMenuItem(mensajeHandler.get("menu.usuario.modificar"));
        menuItemBuscarUsuario = new JMenuItem(mensajeHandler.get("menu.usuario.buscar"));
        menuItemEliminarUsuario = new JMenuItem(mensajeHandler.get("menu.usuario.eliminar"));

        menuItemAnadirCarrito = new JMenuItem(mensajeHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito = new JMenuItem(mensajeHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito = new JMenuItem(mensajeHandler.get("menu.carrito.eliminar"));

        menuItemIdiomaEspanol = new JMenuItem(mensajeHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles = new JMenuItem(mensajeHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances = new JMenuItem(mensajeHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion = new JMenuItem(mensajeHandler.get("menu.salir.cerrar"));

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuIdioma);
        menuBar.add(menuUsuario);
        menuBar.add(menuCerrarSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemModificarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemAnadirCarrito);
        menuCarrito.add(menuItemBuscarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemBuscarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuIdioma.add(menuItemIdiomaEspanol);
        menuIdioma.add(menuItemIdiomaIngles);
        menuIdioma.add(menuItemIdiomaFrances);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(mensajeHandler.get("app.titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        MiJDesktopPane dibujo = new MiJDesktopPane();
        dibujo.setPreferredSize(new Dimension(400, 300));
        add(dibujo, BorderLayout.CENTER);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(lblUsuario, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        registrarEventos();
        deshabilitarMenusAdministrador();
        actualizarTextos(mensajeHandler);
    }

    private void registrarEventos() {

        menuItemAnadirCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoAnadirView vista = new CarritoAnadirView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemBuscarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoListaView vista = new CarritoListaView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemEliminarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoEliminarView vista = new CarritoEliminarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        // 👉 Agrega al menú los ítems faltantes:
        menuItemModificarCarrito = new JMenuItem("Modificar carrito");
        menuCarrito.add(menuItemModificarCarrito);
        menuItemModificarCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoModificarView vista = new CarritoModificarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        JMenuItem menuItemDetalleCarrito = new JMenuItem("Detalle carrito");
        menuCarrito.add(menuItemDetalleCarrito);
        menuItemDetalleCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarritoDetalleView vista = new CarritoDetalleView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemCrearProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoAnadirView vista = new ProductoAnadirView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoEliminarView vista = new ProductoEliminarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemModificarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoModificarView vista = new ProductoModificarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemBuscarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoListaView vista = new ProductoListaView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioEliminarView vista = new UsuarioEliminarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModificarView vista = new UsuarioModificarView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemBuscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioListaView vista = new UsuarioListaView(mensajeHandler);
                jDesktopPane.add(vista);
                vista.setVisible(true);
            }
        });

        menuItemIdiomaEspanol.addActionListener(e -> cambiarIdioma("es", "EC"));
        menuItemIdiomaIngles.addActionListener(e -> cambiarIdioma("en", "US"));
        menuItemIdiomaFrances.addActionListener(e -> cambiarIdioma("fr", "FR"));

    }


    public void mostrarUsuario(String nombre) {
        lblUsuario.setText("Usuario: " + nombre);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void deshabilitarMenusAdministrador() {
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemModificarProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
    }

    public void cambiarIdioma(String lenguaje, String pais) {
        mensajeHandler.setLenguaje(lenguaje, pais);
        setTitle(mensajeHandler.get("app.titulo"));

        menuProducto.setText(mensajeHandler.get("menu.producto"));
        menuCarrito.setText(mensajeHandler.get("menu.carrito"));
        menuUsuario.setText(mensajeHandler.get("menu.usuario"));
        menuIdioma.setText(mensajeHandler.get("menu.idiomas"));
        menuCerrarSesion.setText(mensajeHandler.get("menu.cerrarSesion"));

        menuItemCrearProducto.setText(mensajeHandler.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajeHandler.get("menu.producto.eliminar"));
        menuItemModificarProducto.setText(mensajeHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeHandler.get("menu.producto.buscar"));

        menuItemAnadirCarrito.setText(mensajeHandler.get("menu.carrito.añadir"));
        menuItemBuscarCarrito.setText(mensajeHandler.get("menu.carrito.buscar"));
        menuItemEliminarCarrito.setText(mensajeHandler.get("menu.carrito.eliminar"));
        menuItemModificarCarrito.setText(mensajeHandler.get("menu.carrito.modificar"));

        menuItemEliminarUsuario.setText(mensajeHandler.get("menu.usuario.eliminar"));
        menuItemModificarUsuario.setText(mensajeHandler.get("menu.usuario.modificar"));
        menuItemBuscarUsuario.setText(mensajeHandler.get("menu.usuario.buscar"));

        menuItemIdiomaEspanol.setText(mensajeHandler.get("menu.idioma.es"));
        menuItemIdiomaIngles.setText(mensajeHandler.get("menu.idioma.en"));
        menuItemIdiomaFrances.setText(mensajeHandler.get("menu.idioma.fr"));

        menuItemCerrarSesion.setText(mensajeHandler.get("menu.salir.cerrar"));

        lblUsuario.setText(mensajeHandler.get("menu.usuario.etiqueta") + ":");

        // PRODUCTOS
        if (ventanaProductoAnadir != null && ventanaProductoAnadir.isVisible()) {
            ventanaProductoAnadir.actualizarTextos(mensajeHandler);
        }
        if (ventanaProductoEliminar != null && ventanaProductoEliminar.isVisible()) {
            ventanaProductoEliminar.actualizarTextos(mensajeHandler);
        }
        if (ventanaProductoModificar != null && ventanaProductoModificar.isVisible()) {
            ventanaProductoModificar.actualizarTextos(mensajeHandler);
        }
        if (ventanaProductoLista != null && ventanaProductoLista.isVisible()) {
            ventanaProductoLista.actualizarTextos(mensajeHandler);
        }

        // CARRITOS
        if (ventanaCarritoAnadir != null && ventanaCarritoAnadir.isVisible()) {
            ventanaCarritoAnadir.actualizarTextos(mensajeHandler);
        }
        if (ventanaCarritoEliminar != null && ventanaCarritoEliminar.isVisible()) {
            ventanaCarritoEliminar.actualizarTextos(mensajeHandler);
        }
        if (ventanaCarritoModificar != null && ventanaCarritoModificar.isVisible()) {
            ventanaCarritoModificar.actualizarTextos(mensajeHandler);
        }
        if (ventanaCarritoLista != null && ventanaCarritoLista.isVisible()) {
            ventanaCarritoLista.actualizarTextos(mensajeHandler);
        }
        if (ventanaCarritoDetalle != null && ventanaCarritoDetalle.isVisible()) {
            ventanaCarritoDetalle.actualizarTextos(mensajeHandler);
        }

        // USUARIOS
        if (ventanaUsuarioEliminar != null && ventanaUsuarioEliminar.isVisible()) {
            ventanaUsuarioEliminar.actualizarTextos(mensajeHandler);
        }
        if (ventanaUsuarioModificar != null && ventanaUsuarioModificar.isVisible()) {
            ventanaUsuarioModificar.actualizarTextos(mensajeHandler);
        }
        if (ventanaUsuarioListar != null && ventanaUsuarioListar.isVisible()) {
            ventanaUsuarioListar.actualizarTextos(mensajeHandler);
        }
        if (ventanaRegistrarse != null && ventanaRegistrarse.isVisible()) {
            ventanaRegistrarse.actualizarTextos(mensajeHandler);
        }
        if (ventanaLogin != null && ventanaLogin.isVisible()) {
            ventanaLogin.actualizarTextos(mensajeHandler);
        }

        // RECUPERACIÓN DE CONTRASEÑA
        if (ventanaRecuperarContrasenia != null && ventanaRecuperarContrasenia.isVisible()) {
            ventanaRecuperarContrasenia.actualizarTextos(mensajeHandler);
        }
        if (ventanaCambiarContrasenia != null && ventanaCambiarContrasenia.isVisible()) {
            ventanaCambiarContrasenia.actualizarTextos(mensajeHandler);
        }
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        // Menús principales
        menuProducto.setText(mensajeHandler.get("menu.producto"));
        menuCarrito.setText(mensajeHandler.get("menu.carrito"));
        menuUsuario.setText(mensajeHandler.get("menu.usuario"));
        menuIdioma.setText(mensajeHandler.get("menu.idioma"));
        menuCerrarSesion.setText(mensajeHandler.get("menu.cerrar"));

        // Submenús Producto
        menuItemCrearProducto.setText(mensajeHandler.get("producto.crear"));
        menuItemEliminarProducto.setText(mensajeHandler.get("producto.eliminar"));
        menuItemModificarProducto.setText(mensajeHandler.get("producto.modificar"));
        menuItemBuscarProducto.setText(mensajeHandler.get("producto.buscar"));

        // Submenús Carrito
        menuItemAnadirCarrito.setText(mensajeHandler.get("carrito.anadir"));
        menuItemBuscarCarrito.setText(mensajeHandler.get("carrito.buscar"));
        menuItemEliminarCarrito.setText(mensajeHandler.get("carrito.eliminar"));
        menuItemModificarCarrito.setText(mensajeHandler.get("carrito.modificar"));

        // Submenús Usuario
        menuItemEliminarUsuario.setText(mensajeHandler.get("usuario.eliminar"));
        menuItemModificarUsuario.setText(mensajeHandler.get("usuario.modificar"));
        menuItemBuscarUsuario.setText(mensajeHandler.get("usuario.buscar"));

        // Idiomas
        menuItemIdiomaEspanol.setText(mensajeHandler.get("idioma.espanol"));
        menuItemIdiomaIngles.setText(mensajeHandler.get("idioma.ingles"));
        menuItemIdiomaFrances.setText(mensajeHandler.get("idioma.frances"));

        // Otros
        menuItemCerrarSesion.setText(mensajeHandler.get("menu.cerrar"));
        lblUsuario.setText(mensajeHandler.get("menu.usuario"));
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }
    public JMenuItem getMenuItemModificarCarrito() { return menuItemModificarCarrito; }
    public void setMenuItemModificarCarrito(JMenuItem menuItemModificarCarrito) { this.menuItemModificarCarrito = menuItemModificarCarrito; }
    public JMenuItem getMenuItemEliminarUsuario() { return menuItemEliminarUsuario; }
    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) { this.menuItemEliminarUsuario = menuItemEliminarUsuario; }
    public JMenuItem getMenuItemModificarUsuario() { return menuItemModificarUsuario; }
    public void setMenuItemModificarUsuario(JMenuItem menuItemModificarUsuario) { this.menuItemModificarUsuario = menuItemModificarUsuario; }
    public JMenuItem getMenuItemBuscarUsuario() { return menuItemBuscarUsuario; }
    public void setMenuItemBuscarUsuario(JMenuItem menuItemBuscarUsuario) { this.menuItemBuscarUsuario = menuItemBuscarUsuario; }
    public JMenuBar getBarraMenu() { return menuBar; }
    public void setMenuBar(JMenuBar menuBar) { this.menuBar = menuBar; }
    public JMenu getMenuProducto() { return menuProducto; }
    public void setMenuProducto(JMenu menuProducto) { this.menuProducto = menuProducto; }
    public JMenuItem getMenuItemCrearProducto() { return menuItemCrearProducto; }
    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) { this.menuItemCrearProducto = menuItemCrearProducto; }
    public JMenu getMenuCarrito() { return menuCarrito; }
    public void setMenuCarrito(JMenu menuCarrito) { this.menuCarrito = menuCarrito; }
    public JMenuItem getMenuItemEliminarProducto() { return menuItemEliminarProducto; }
    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) { this.menuItemEliminarProducto = menuItemEliminarProducto; }
    public JMenuItem getMenuItemModificarProducto() { return menuItemModificarProducto; }
    public void setMenuItemModificarProducto(JMenuItem menuItemModificarProducto) { this.menuItemModificarProducto = menuItemModificarProducto; }
    public JMenuItem getMenuItemBuscarProducto() { return menuItemBuscarProducto; }
    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) { this.menuItemBuscarProducto = menuItemBuscarProducto; }
    public JMenuItem getMenuItemAñadirCarrito() { return menuItemAnadirCarrito; }
    public void setMenuItemAñadirCarrito(JMenuItem menuItemAñadirCarrito) { this.menuItemAnadirCarrito = menuItemAñadirCarrito; }
    public JDesktopPane getjDesktopPane() { return jDesktopPane; }
    public void setjDesktopPane(JDesktopPane jDesktopPane) { this.jDesktopPane = jDesktopPane; }
    public JMenuItem getMenuItemEliminarCarrito() { return menuItemEliminarCarrito; }
    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) { this.menuItemEliminarCarrito = menuItemEliminarCarrito; }
    public JMenuItem getMenuItemBuscarCarrito() { return menuItemBuscarCarrito; }
    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) { this.menuItemBuscarCarrito = menuItemBuscarCarrito; }
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() { return mensajeHandler; }
    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeHandler) { this.mensajeHandler = MenuPrincipalView.this.mensajeHandler; }
    public JMenu getMenuIdioma() { return menuIdioma; }
    public void setMenuIdioma(JMenu menuIdioma) { this.menuIdioma = menuIdioma; }
    public JMenu getMenuCerrarSesion() { return menuCerrarSesion; }
    public void setMenuCerrarSesion(JMenu menuCerrarSesion) { this.menuCerrarSesion = menuCerrarSesion; }
    public JMenuItem getMenuItemIdiomaEspanol() { return menuItemIdiomaEspanol; }
    public void setMenuItemIdiomaEspanol(JMenuItem menuItemIdiomaEspanol) { this.menuItemIdiomaEspanol = menuItemIdiomaEspanol; }
    public JMenuItem getMenuItemIdiomaIngles() { return menuItemIdiomaIngles; }
    public void setMenuItemIdiomaIngles(JMenuItem menuItemIdiomaIngles) { this.menuItemIdiomaIngles = menuItemIdiomaIngles; }
    public JMenuItem getMenuItemIdiomaFrances() { return menuItemIdiomaFrances; }
    public void setMenuItemIdiomaFrances(JMenuItem menuItemIdiomaFrances) { this.menuItemIdiomaFrances = menuItemIdiomaFrances; }
    public JMenuItem getMenuItemCerrarSesion() { return menuItemCerrarSesion; }
    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) { this.menuItemCerrarSesion = menuItemCerrarSesion; }
    public JLabel getLblUsuario() { return lblUsuario; }
    public void setLblUsuario(JLabel lblUsuario) { this.lblUsuario = lblUsuario; }
}
