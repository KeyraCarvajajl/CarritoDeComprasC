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
import javax.swing.JMenuBar;

/**
 * Clase que representa la ventana principal del sistema de compras.
 * Implementa una interfaz MDI utilizando {@link javax.swing.JDesktopPane} para
 * contener y gestionar múltiples ventanas internas como productos, carritos y usuarios.
 *
 * Esta clase contiene un menú con las siguientes funcionalidades:
 * - Gestión de productos (añadir, eliminar, listar, modificar)
 * - Gestión de carritos de compras
 * - Gestión de usuarios (registro, eliminación, modificación, listado)
 * - Opciones de idioma dinámico (español, inglés, francés)
 * - Cierre de sesión
 *
 * Además, incluye soporte para internacionalización dinámica mediante un
 * {@link MensajeInternacionalizacionHandler} que actualiza todos los textos del menú.
 *
 * @author Keyra
 */
public class MenuPrincipalView extends JFrame {

    /** Manejador para los textos internacionalizados del sistema. */
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /** Barra de menú principal que contiene todos los menús de la aplicación. */
    private JMenuBar menuBar;

    /** Menú para gestionar operaciones relacionadas con productos. */
    private JMenu menuProducto;

    /** Menú para gestionar operaciones del carrito de compras. */
    private JMenu menuCarrito;

    /** Menú para gestionar operaciones relacionadas con los usuarios. */
    private JMenu menuUsuario;

    /** Menú para seleccionar el idioma de la aplicación. */
    private JMenu menuIdioma;

    /** Menú para cerrar sesión del usuario. */
    private JMenu menuCerrarSesion;

// ----------------------------- Ítems del menú Producto -----------------------------

    /** Opción de menú para crear un nuevo producto. */
    private JMenuItem menuItemCrearProducto;

    /** Opción de menú para eliminar un producto existente. */
    private JMenuItem menuItemEliminarProducto;

    /** Opción de menú para modificar los datos de un producto. */
    private JMenuItem menuItemModificarProducto;

    /** Opción de menú para buscar y listar productos registrados. */
    private JMenuItem menuItemBuscarProducto;

// ----------------------------- Ítems del menú Carrito -----------------------------

    /** Opción de menú para añadir un nuevo carrito. */
    private JMenuItem menuItemAnadirCarrito;

    /** Opción de menú para buscar un carrito existente. */
    private JMenuItem menuItemBuscarCarrito;

    /** Opción de menú para eliminar un carrito existente. */
    private JMenuItem menuItemEliminarCarrito;

    /** Opción de menú para modificar un carrito registrado. */
    private JMenuItem menuItemModificarCarrito;

    /** Opción de menú para listar todos los carritos registrados. */
    private JMenuItem menuItemListaCarrito;

    /** Opción de menú para mostrar los detalles de un carrito. */
    private JMenuItem menuItemDetalleCarrito;

// ----------------------------- Ítems del menú Usuario -----------------------------

    /** Opción de menú para eliminar un usuario existente. */
    private JMenuItem menuItemEliminarUsuario;

    /** Opción de menú para modificar los datos de un usuario. */
    private JMenuItem menuItemModificarUsuario;

    /** Opción de menú para listar los usuarios registrados. */
    private JMenuItem menuItemListaUsuario;

// ----------------------------- Ítems del menú Idioma -----------------------------

    /** Opción de menú para cambiar el idioma a español. */
    private JMenuItem menuItemIdiomaEspanol;

    /** Opción de menú para cambiar el idioma a inglés. */
    private JMenuItem menuItemIdiomaIngles;

    /** Opción de menú para cambiar el idioma a francés. */
    private JMenuItem menuItemIdiomaFrances;

// ----------------------------- Opción de cierre de sesión -----------------------------

    /** Opción de menú para cerrar sesión y volver a la pantalla de login. */
    private JMenuItem menuItemCerrarSesion;

// ----------------------------- Escritorio principal -----------------------------

    /** Panel personalizado que actúa como escritorio MDI para contener ventanas internas. */
    private MiJDesktopPane jDesktopPane;

    /** Etiqueta que muestra el nombre o rol del usuario activo. */
    private JLabel lblUsuario;

// ----------------------------- Ventanas internas de Producto -----------------------------

    /** Ventana para añadir productos al sistema. */
    private ProductoAnadirView productoAnadirView;

    /** Ventana para eliminar productos del sistema. */
    private ProductoEliminarView productoEliminarView;

    /** Ventana para modificar productos registrados. */
    private ProductoModificarView productoModificarView;

    /** Ventana para listar todos los productos existentes. */
    private ProductoListaView productoListaView;

// ----------------------------- Ventanas internas de Carrito -----------------------------

    /** Ventana para ver el detalle de productos en un carrito. */
    private CarritoDetalleView carritoDetalleView;

    /** Ventana para eliminar carritos. */
    private CarritoEliminarView carritoEliminarView;

    /** Ventana para modificar carritos registrados. */
    private CarritoModificarView carritoModificarView;

    /** Ventana para listar los carritos registrados. */
    private CarritoListaView carritoListaView;

    /** Ventana para añadir un nuevo carrito. */
    private CarritoAnadirView carritoAnadirView;

// ----------------------------- Ventanas internas de Usuario y Seguridad -----------------------------

    /** Ventana para cambiar la contraseña del usuario. */
    private CambiarContraseniaView cambiarContraseniaView;

    /** Ventana del cuestionario de recuperación de contraseña. */
    private CuestionarioView cuestionarioView;

    /** Ventana de inicio de sesión. */
    private LoginView loginView;

    /** Ventana de registro de nuevos usuarios. */
    private RegistrarseView registrarseView;

    /** Ventana para eliminar un usuario. */
    private UsuarioEliminarView usuarioEliminarView;

    /** Ventana para listar los usuarios registrados. */
    private UsuarioListaView usuarioListaView;

    /** Ventana para modificar los datos de un usuario. */
    private UsuarioModificarView usuarioModificarView;

    /**
     * Constructor de la clase {@code MenuPrincipalView}.
     *
     * Inicializa la ventana principal del sistema con una interfaz gráfica
     * tipo MDI (Multiple Document Interface). Configura todos los menús,
     * submenús, ítems e iconos, así como las vistas internas del sistema.
     * Además, permite aplicar internacionalización dinámica utilizando
     * el {@code MensajeInternacionalizacionHandler}.
     *
     * Las funcionalidades cubiertas en la interfaz incluyen:
     * <ul>
     *   <li>Gestión de productos: crear, eliminar, modificar y buscar.</li>
     *   <li>Gestión de carritos de compras: añadir, eliminar, modificar, listar y ver detalles.</li>
     *   <li>Gestión de usuarios: eliminar, modificar y listar usuarios.</li>
     *   <li>Soporte para cambiar el idioma de la interfaz (Español, Inglés y Francés).</li>
     *   <li>Opción de cerrar sesión y retornar al login.</li>
     * </ul>
     *
     * También se inicializa el componente {@code MiJDesktopPane}, que actúa como contenedor MDI
     * para mostrar las distintas ventanas internas (productos, usuarios, carritos, etc.).
     */
    public MenuPrincipalView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeInternacionalizacionHandler = mensajeHandler;
        initComponents();
        agregarListeners();
    }

    /**
     * Inicializa todos los componentes gráficos de la ventana principal del sistema.
     *
     * Este método configura la barra de menús principal ({@code JMenuBar}) junto con sus menús
     * y submenús correspondientes. Asocia los íconos representativos a cada opción de menú y
     * define los elementos visuales como el {@code JLabel} del usuario actual y el
     * {@code MiJDesktopPane} para mostrar vistas internas tipo {@code JInternalFrame}.
     *
     * Componentes configurados:
     * <ul>
     *   <li><b>Menú Producto</b>: Crear, Eliminar, Modificar, Listar.</li>
     *   <li><b>Menú Carrito</b>: Añadir, Eliminar, Modificar, Listar, Detalles.</li>
     *   <li><b>Menú Usuario</b>: Eliminar, Modificar, Listar.</li>
     *   <li><b>Menú Idioma</b>: Español, Inglés, Francés.</li>
     *   <li><b>Menú Cerrar Sesión</b>: Opción para cerrar la sesión actual.</li>
     * </ul>
     *
     * Además, se aplica estilo y diseño gráfico a la ventana principal, y se preparan las
     * referencias a las distintas vistas para ser mostradas desde los controladores.
     *
     * Este método debe ser invocado dentro del constructor de la clase
     * {@code MenuPrincipalView} para que todos los componentes queden correctamente
     * configurados y visibles.
     */
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

    /**
     * Asocia los {@code ActionListener} a los distintos elementos del menú principal.
     *
     * Este método permite que cada opción de menú y submenú realice una acción específica
     * al ser seleccionada por el usuario. Las acciones incluyen abrir ventanas internas
     * ({@code JInternalFrame}) como las vistas de productos, carritos y usuarios, cambiar
     * el idioma de la interfaz, o cerrar la sesión activa.
     *
     * Listeners configurados:
     * <ul>
     *   <li><b>Producto</b>: Crear, Eliminar, Modificar, Buscar productos.</li>
     *   <li><b>Carrito</b>: Añadir, Eliminar, Modificar, Listar y Ver detalles de carritos.</li>
     *   <li><b>Usuario</b>: Eliminar, Modificar y Listar usuarios.</li>
     *   <li><b>Idioma</b>: Cambiar idioma a Español, Inglés o Francés (con actualización dinámica de textos).</li>
     *   <li><b>Cerrar sesión</b>: Vuelve a la ventana de inicio de sesión y oculta la vista actual.</li>
     * </ul>
     *
     * Este método debe invocarse luego de {@code initComponents()} en el constructor
     * para asegurar la correcta interacción del usuario con el sistema.
     */
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

            if (loginView != null) {
                loginView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (registrarseView != null) {
                registrarseView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioEliminarView != null) {
                usuarioEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioModificarView != null) {
                usuarioModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioListaView != null) {
                usuarioListaView.actualizarTextos(mensajeInternacionalizacionHandler);
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

            if (loginView != null) {
                loginView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (registrarseView != null) {
                registrarseView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioEliminarView != null) {
                usuarioEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioModificarView != null) {
                usuarioModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioListaView != null) {
                usuarioListaView.actualizarTextos(mensajeInternacionalizacionHandler);
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

            if (loginView != null) {
                loginView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (registrarseView != null) {
                registrarseView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioEliminarView != null) {
                usuarioEliminarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioModificarView != null) {
                usuarioModificarView.actualizarTextos(mensajeInternacionalizacionHandler);
            }

            if (usuarioListaView != null) {
                usuarioListaView.actualizarTextos(mensajeInternacionalizacionHandler);
            }
        });

    }

    /**
     * Retorna el manejador de internacionalización actual.
     *
     * @return El objeto {@code MensajeInternacionalizacionHandler} utilizado para traducir textos en la interfaz.
     */
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacionHandler() {
        return mensajeInternacionalizacionHandler;
    }

    /**
     * Establece un nuevo manejador de internacionalización para la interfaz.
     *
     * @param mensajeInternacionalizacionHandler El nuevo objeto {@code MensajeInternacionalizacionHandler} con el idioma y país deseado.
     */
    public void setMensajeInternacionalizacionHandler(MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler) {
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;
    }

    /**
     * Actualiza todos los textos visibles del menú principal según el idioma seleccionado.
     *
     * Este método modifica dinámicamente los textos de los menús y submenús utilizando el recurso
     * de internacionalización proporcionado. También actualiza el título de la ventana.
     *
     * @param lenguaje Código del idioma (por ejemplo, "es" para español, "en" para inglés, "fr" para francés).
     * @param pais Código del país correspondiente (por ejemplo, "EC", "US", "FR").
     */
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

    /**
     * Desactiva los elementos de menú que no deben estar disponibles para usuarios con rol "ADMINISTRADOR".
     *
     * Este método es útil para ocultar funcionalidades restringidas cuando un administrador
     * inicia sesión, limitando el acceso a opciones relacionadas con productos y carritos.
     */
    public void deshabilitarMenusAdministrador(){
        getMenuItemCrearProducto().setEnabled(false);
        getMenuItemEliminarProducto().setEnabled(false);
        getMenuItemModificarProducto().setEnabled(false);
        getMenuItemBuscarProducto().setEnabled(false);
        getMenuItemAnadirCarrito().setEnabled(false);
        getMenuItemListaCarrito().setEnabled(false);
    }

    /**
     * Devuelve el menú de productos.
     * @return JMenu correspondiente al módulo de productos.
     */
    public JMenu getMenuProducto() {
        return menuProducto;
    }

    /**
     * Establece el menú de productos.
     * @param menuProducto JMenu para gestionar productos.
     */
    public void setMenuProducto(JMenu menuProducto) {
        this.menuProducto = menuProducto;
    }

    /**
     * Devuelve el menú de carritos.
     * @return JMenu correspondiente al módulo de carritos.
     */
    public JMenu getMenuCarrito() {
        return menuCarrito;
    }

    /**
     * Establece el menú de carritos.
     * @param menuCarrito JMenu para gestionar carritos.
     */
    public void setMenuCarrito(JMenu menuCarrito) {
        this.menuCarrito = menuCarrito;
    }

    /**
     * Devuelve el menú de usuarios.
     * @return JMenu correspondiente al módulo de usuarios.
     */
    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    /**
     * Establece el menú de usuarios.
     * @param menuUsuario JMenu para gestionar usuarios.
     */
    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }

    /**
     * Devuelve el menú de idiomas.
     * @return JMenu correspondiente al selector de idiomas.
     */
    public JMenu getMenuIdioma() {
        return menuIdioma;
    }

    /**
     * Establece el menú de idiomas.
     * @param menuIdioma JMenu para selección de idioma de la interfaz.
     */
    public void setMenuIdioma(JMenu menuIdioma) {
        this.menuIdioma = menuIdioma;
    }

    /**
     * Devuelve la barra de menú principal.
     * @return objeto MenuBar (actualmente devuelve null).
     */
    public MenuBar getMenuBar() {
        return null;
    }

    /**
     * Establece la barra de menú principal de la aplicación.
     * @param menuBar JMenuBar principal del sistema.
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    /**
     * Devuelve el menú para cerrar sesión.
     * @return JMenu correspondiente a la opción de cerrar sesión.
     */
    public JMenu getMenuCerrarSesion() {
        return menuCerrarSesion;
    }

    /**
     * Establece el menú para cerrar sesión.
     * @param menuCerrarSesion JMenu asignado para cerrar sesión.
     */
    public void setMenuCerrarSesion(JMenu menuCerrarSesion) {
        this.menuCerrarSesion = menuCerrarSesion;
    }

    /**
     * Devuelve el ítem de menú para crear productos.
     * @return JMenuItem para la opción de crear producto.
     */
    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    /**
     * Establece el ítem de menú para crear productos.
     * @param menuItemCrearProducto JMenuItem asignado para crear producto.
     */
    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    /**
     * Devuelve el ítem de menú para eliminar productos.
     * @return JMenuItem para la opción de eliminar producto.
     */
    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    /**
     * Establece el ítem de menú para eliminar productos.
     * @param menuItemEliminarProducto JMenuItem asignado para eliminar producto.
     */
    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    /**
     * Devuelve el ítem de menú para modificar productos.
     * @return JMenuItem para la opción de modificar producto.
     */
    public JMenuItem getMenuItemModificarProducto() {
        return menuItemModificarProducto;
    }

    /**
     * Establece el ítem de menú para modificar productos.
     * @param menuItemModificarProducto JMenuItem asignado para modificar producto.
     */
    public void setMenuItemModificarProducto(JMenuItem menuItemModificarProducto) {
        this.menuItemModificarProducto = menuItemModificarProducto;
    }

    /**
     * Devuelve el ítem de menú para buscar productos.
     * @return JMenuItem para la opción de buscar producto.
     */
    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    /**
     * Establece el ítem de menú para buscar productos.
     * @param menuItemBuscarProducto JMenuItem asignado para buscar producto.
     */
    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    /**
     * Devuelve el ítem de menú para añadir un nuevo carrito.
     * @return JMenuItem correspondiente a la opción de añadir carrito.
     */
    public JMenuItem getMenuItemAnadirCarrito() {
        return menuItemAnadirCarrito;
    }

    /**
     * Establece el ítem de menú para añadir un nuevo carrito.
     * @param menuItemAnadirCarrito JMenuItem asignado para añadir carrito.
     */
    public void setMenuItemAnadirCarrito(JMenuItem menuItemAnadirCarrito) {
        this.menuItemAnadirCarrito = menuItemAnadirCarrito;
    }

    /**
     * Devuelve el ítem de menú para buscar carritos existentes.
     * @return JMenuItem correspondiente a la opción de buscar carrito.
     */
    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    /**
     * Establece el ítem de menú para buscar carritos existentes.
     * @param menuItemBuscarCarrito JMenuItem asignado para buscar carrito.
     */
    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }

    /**
     * Devuelve el ítem de menú para eliminar un carrito.
     * @return JMenuItem correspondiente a la opción de eliminar carrito.
     */
    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    /**
     * Establece el ítem de menú para eliminar un carrito.
     * @param menuItemEliminarCarrito JMenuItem asignado para eliminar carrito.
     */
    public void setMenuItemEliminarCarrito(JMenuItem menuItemEliminarCarrito) {
        this.menuItemEliminarCarrito = menuItemEliminarCarrito;
    }

    /**
     * Devuelve el ítem de menú para modificar un carrito existente.
     * @return JMenuItem correspondiente a la opción de modificar carrito.
     */
    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }

    /**
     * Establece el ítem de menú para modificar un carrito existente.
     * @param menuItemModificarCarrito JMenuItem asignado para modificar carrito.
     */
    public void setMenuItemModificarCarrito(JMenuItem menuItemModificarCarrito) {
        this.menuItemModificarCarrito = menuItemModificarCarrito;
    }

    /**
     * Devuelve el ítem de menú para listar todos los carritos registrados.
     * @return JMenuItem correspondiente a la opción de listar carritos.
     */
    public JMenuItem getMenuItemListaCarrito() {
        return menuItemListaCarrito;
    }

    /**
     * Establece el ítem de menú para listar todos los carritos registrados.
     * @param menuItemListaCarrito JMenuItem asignado para la opción de listar carritos.
     */
    public void setMenuItemListaCarrito(JMenuItem menuItemListaCarrito) {
        this.menuItemListaCarrito = menuItemListaCarrito;
    }

    /**
     * Devuelve el ítem de menú para ver los detalles de un carrito específico.
     * @return JMenuItem correspondiente a la opción de ver detalles del carrito.
     */
    public JMenuItem getMenuItemDetalleCarrito() {
        return menuItemDetalleCarrito;
    }

    /**
     * Establece el ítem de menú para ver los detalles de un carrito.
     * @param menuItemDetalleCarrito JMenuItem asignado para mostrar detalles del carrito.
     */
    public void setMenuItemDetalleCarrito(JMenuItem menuItemDetalleCarrito) {
        this.menuItemDetalleCarrito = menuItemDetalleCarrito;
    }

    /**
     * Devuelve el ítem de menú para eliminar usuarios del sistema.
     * @return JMenuItem correspondiente a la opción de eliminar usuario.
     */
    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    /**
     * Establece el ítem de menú para eliminar usuarios del sistema.
     * @param menuItemEliminarUsuario JMenuItem asignado para eliminar usuarios.
     */
    public void setMenuItemEliminarUsuario(JMenuItem menuItemEliminarUsuario) {
        this.menuItemEliminarUsuario = menuItemEliminarUsuario;
    }

    /**
     * Devuelve el ítem de menú para modificar la información de un usuario.
     * @return JMenuItem correspondiente a la opción de modificar usuario.
     */
    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    /**
     * Establece el ítem de menú para modificar la información de un usuario.
     * @param menuItemModificarUsuario JMenuItem asignado para modificar usuarios.
     */
    public void setMenuItemModificarUsuario(JMenuItem menuItemModificarUsuario) {
        this.menuItemModificarUsuario = menuItemModificarUsuario;
    }

    /**
     * Devuelve el ítem de menú para listar todos los usuarios registrados.
     * @return JMenuItem correspondiente a la opción de listar usuarios.
     */
    public JMenuItem getMenuItemListaUsuario() {
        return menuItemListaUsuario;
    }

    /**
     * Establece el ítem de menú para listar todos los usuarios registrados.
     * @param menuItemListaUsuario JMenuItem asignado para listar usuarios.
     */
    public void setMenuItemListaUsuario(JMenuItem menuItemListaUsuario) {
        this.menuItemListaUsuario = menuItemListaUsuario;
    }

    /**
     * Devuelve el ítem de menú para seleccionar el idioma Español.
     * @return JMenuItem correspondiente a la opción de idioma Español.
     */
    public JMenuItem getMenuItemIdiomaEspanol() {
        return menuItemIdiomaEspanol;
    }

    /**
     * Establece el ítem de menú para seleccionar el idioma Español.
     * @param menuItemIdiomaEspanol JMenuItem asignado para la opción de cambiar idioma a Español.
     */
    public void setMenuItemIdiomaEspanol(JMenuItem menuItemIdiomaEspanol) {
        this.menuItemIdiomaEspanol = menuItemIdiomaEspanol;
    }

    /**
     * Devuelve el ítem de menú para seleccionar el idioma Inglés.
     * @return JMenuItem correspondiente a la opción de idioma Inglés.
     */
    public JMenuItem getMenuItemIdiomaIngles() {
        return menuItemIdiomaIngles;
    }

    /**
     * Establece el ítem de menú para seleccionar el idioma Inglés.
     * @param menuItemIdiomaIngles JMenuItem asignado para la opción de cambiar idioma a Inglés.
     */
    public void setMenuItemIdiomaIngles(JMenuItem menuItemIdiomaIngles) {
        this.menuItemIdiomaIngles = menuItemIdiomaIngles;
    }

    /**
     * Devuelve el ítem de menú para seleccionar el idioma Francés.
     * @return JMenuItem correspondiente a la opción de idioma Francés.
     */
    public JMenuItem getMenuItemIdiomaFrances() {
        return menuItemIdiomaFrances;
    }

    /**
     * Establece el ítem de menú para seleccionar el idioma Francés.
     * @param menuItemIdiomaFrances JMenuItem asignado para la opción de cambiar idioma a Francés.
     */
    public void setMenuItemIdiomaFrances(JMenuItem menuItemIdiomaFrances) {
        this.menuItemIdiomaFrances = menuItemIdiomaFrances;
    }

    /**
     * Devuelve el ítem de menú para cerrar sesión en el sistema.
     * @return JMenuItem correspondiente a la opción de cerrar sesión.
     */
    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    /**
     * Establece el ítem de menú para cerrar sesión del usuario actual.
     * @param menuItemCerrarSesion JMenuItem asignado para cerrar sesión.
     */
    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    /**
     * Devuelve el componente personalizado JDesktopPane utilizado como contenedor MDI.
     * @return MiJDesktopPane que contiene las ventanas internas de la aplicación.
     */
    public MiJDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    /**
     * Establece el componente JDesktopPane personalizado para contener las ventanas internas.
     * @param jDesktopPane MiJDesktopPane que será utilizado como contenedor MDI.
     */
    public void setjDesktopPane(MiJDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }

    /**
     * Devuelve la etiqueta que muestra el nombre del usuario actualmente conectado.
     * @return JLabel que muestra el nombre del usuario activo.
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * Establece la etiqueta que muestra el nombre del usuario conectado.
     * @param lblUsuario JLabel asignado para mostrar el nombre del usuario activo.
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * Devuelve el contenedor JDesktopPane principal del sistema.
     * @return JDesktopPane utilizado como área de trabajo MDI.
     */
    public JDesktopPane getDesktopPane() {
        return jDesktopPane;
    }

}