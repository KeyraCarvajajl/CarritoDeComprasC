package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * Vista interna para listar los productos de un carrito de compras.
 * Muestra los productos en una tabla, permite buscar por código de carrito y listar todos.
 * También permite ver el total acumulado y soporta internacionalización dinámica.
 *
 * Forma parte del módulo visual del sistema de carrito de compras.
 *
 * @author Keyra
 */
public class CarritoListaView extends JInternalFrame {

    /**
     * Campo de texto para ingresar el código del carrito a buscar.
     */
    private JTextField txtCodigo;

    /**
     * Botón para ejecutar la búsqueda del carrito según el código ingresado.
     */
    private JButton btnBuscar;

    /**
     * Botón para listar todos los carritos disponibles en el sistema.
     */
    private JButton btnListar;

    /**
     * Tabla que muestra los productos contenidos en el carrito seleccionado.
     */
    private JTable tblPCarrito;

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta que indica el campo de código del carrito.
     */
    private JLabel lblCarrito;

    /**
     * Campo de texto que muestra el total calculado del carrito.
     */
    private JTextField txtTotalCarrito;

    /**
     * Etiqueta que indica el campo del total del carrito.
     */
    private JLabel lblTotalCarrito;

    /**
     * Modelo de la tabla que define las columnas y estructura de datos para la tabla de productos.
     */
    private DefaultTableModel modelo;

    /**
     * Manejador de mensajes utilizado para la internacionalización dinámica de los textos de la interfaz.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;


    /**
     * Constructor de la vista CarritoListaView.
     * Inicializa todos los componentes gráficos, establece el diseño,
     * configura la tabla de productos e íconos, y aplica la traducción según el idioma actual.
     *
     * @param mensajeHandler Manejador de mensajes para la internacionalización dinámica.
     */
    public CarritoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Listar Carrito", true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        /**
         * // Crear panel principal y configurarlo
         */
        panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(250,222,212)); // Fondo rosado claro

        /**
         * Inicializar componentes
         */
        lblCarrito = new JLabel("Código:");
        txtCodigo = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnListar = new JButton("Listar");
        lblTotalCarrito = new JLabel("Total:");
        txtTotalCarrito = new JTextField();

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Subtotal"}, 0);
        tblPCarrito = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tblPCarrito);

        /**
         *  Establecer posiciones
         */
        lblCarrito.setBounds(30, 20, 100, 25);
        txtCodigo.setBounds(130, 20, 150, 25);
        btnBuscar.setBounds(300, 20, 100, 30);
        btnListar.setBounds(410, 20, 100, 30);
        scrollPane.setBounds(30, 70, 480, 300);
        lblTotalCarrito.setBounds(30, 390, 100, 25);
        txtTotalCarrito.setBounds(130, 390, 100, 25);

        /**
         * Agregar componentes al panel
         */
        panelPrincipal.add(lblCarrito);
        panelPrincipal.add(txtCodigo);
        panelPrincipal.add(btnBuscar);
        panelPrincipal.add(btnListar);
        panelPrincipal.add(scrollPane);
        panelPrincipal.add(lblTotalCarrito);
        panelPrincipal.add(txtTotalCarrito);

        /**
         * Configurar ventana
         */
        setContentPane(panelPrincipal);
        setSize(600, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        imagenIcono();
        actualizarTextos(mensajeHandler);
    }

    /**
     * Actualiza todos los textos visibles de la ventana, incluidos títulos, etiquetas,
     * botones y encabezados de tabla, según el idioma seleccionado.
     *
     * @param mensajeHandler Manejador de internacionalización con los textos traducidos.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCarrito.setText(mensajeHandler.get("carrito.lista.titulo"));
        lblTotalCarrito.setText(mensajeHandler.get("carrito.total"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnListar.setText(mensajeHandler.get("boton.listar"));

        setTitle(mensajeHandler.get("carrito.lista.titulo"));

        modelo.setColumnIdentifiers(new String[]{
                mensajeHandler.get("producto.codigo"),
                mensajeHandler.get("producto.nombre"),
                mensajeHandler.get("producto.precio"),
                mensajeHandler.get("producto.cantidad"),
                mensajeHandler.get("carrito.subtotal")
        });
    }


    /**
     * Carga los íconos para los botones "Buscar" y "Listar" desde la carpeta de recursos del proyecto.
     * Los íconos se redimensionan a 20x20 píxeles para mantener consistencia visual con la interfaz.
     * <p>
     * Si no se encuentra el recurso gráfico, se imprime un mensaje de error en consola.
     */
    private void imagenIcono() {
        /**
         * Redimensionar icono "Buscar"
         */
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnBuscar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnBuscar.getImage();  // Convierte ImageIcon a Image
            Image newImgBuscar = imgBuscar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscar.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        /**
         * Redimensionar icono "Listar"
         */
        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon iconBtnListar = new ImageIcon(btListar);
            Image imgListar = iconBtnListar.getImage();  // Convierte ImageIcon a Image
            Image newImgListar = imgListar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnListar = new ImageIcon(newImgListar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnListar.setIcon(iconBtnListar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
        }

    }

    /**
     * Actualiza dinámicamente los textos de los componentes de la interfaz gráfica
     * cuando el usuario cambia el idioma del sistema.
     * <p>
     * Modifica etiquetas, botones y tooltips utilizando claves del archivo de propiedades.
     *
     * @param mensajeHandler Manejador que proporciona los textos traducidos según el idioma actual.
     */
    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        /**
         * Cambiar el título de la ventana
         */
        setTitle(mensajeHandler.get("carrito.detalle.titulo"));

        /**
         * Actualiza las etiquetas
         */
        lblCarrito.setText(mensajeHandler.get("carrito.titulo"));
        lblTotalCarrito.setText(mensajeHandler.get("carrito.total"));

        /**
         * Actualiza los botones
         */
        btnBuscar.setText(mensajeHandler.get("carrito.buscar"));
        btnListar.setText(mensajeHandler.get("carrito.listar"));

        /**
         * Actualiza los tooltips si es necesario
         */
        txtCodigo.setToolTipText(mensajeHandler.get("carrito.codigo.tooltip"));
        txtTotalCarrito.setToolTipText(mensajeHandler.get("carrito.total.tooltip"));
        txtTotalCarrito.setToolTipText(mensajeHandler.get("carrito.total.pagar"));
    }


    // ------------------ txtCodigo ------------------

    /**
     * Retorna el campo de texto donde se ingresa el código del carrito.
     * @return JTextField del código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Establece el campo de texto para el código del carrito.
     * @param txtCodigo Campo de texto a asignar.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    // ------------------ panelPrincipal ------------------

    /**
     * Retorna el panel principal de la vista.
     * @return JPanel que contiene todos los componentes.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal Panel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    // ------------------ tblPCarrito ------------------

    /**
     * Retorna la tabla donde se listan los productos del carrito.
     * @return JTable de productos.
     */
    public JTable getTblPCarrito() {
        return tblPCarrito;
    }

    /**
     * Establece la tabla que muestra los productos del carrito.
     * @param tblPCarrito Tabla a asignar.
     */
    public void setTblPCarrito(JTable tblPCarrito) {
        this.tblPCarrito = tblPCarrito;
    }

    // ------------------ btnListar ------------------

    /**
     * Retorna el botón que permite listar todos los carritos.
     * @return JButton Listar.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Establece el botón para listar los carritos.
     * @param btnListar Botón a asignar.
     */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    // ------------------ btnBuscar ------------------

    /**
     * Retorna el botón que permite buscar un carrito por código.
     * @return JButton Buscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón para buscar un carrito.
     * @param btnBuscar Botón a asignar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    // ------------------ modelo ------------------

    /**
     * Retorna el modelo de datos de la tabla de productos.
     * @return DefaultTableModel utilizado por la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo de datos para la tabla de productos.
     * @param modelo Modelo a asignar.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    // ------------------ txtTotalCarrito ------------------

    /**
     * Retorna el campo de texto que muestra el total del carrito.
     * @return JTextField del total del carrito.
     */
    public JTextField getTxtTotal() {
        return txtTotalCarrito;
    }

    /**
     * Establece el campo de texto que muestra el total del carrito.
     * @param txtTotal Campo de texto a asignar.
     */
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotalCarrito = txtTotal;
    }

    // ------------------ mensajeHandler ------------------

    /**
     * Establece el manejador de internacionalización.
     * @param mensajeHandler Manejador a asignar.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    // ------------------ Métodos auxiliares ------------------

    /**
     * Muestra un cuadro de diálogo con el mensaje especificado.
     * @param mensaje Texto a mostrar en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Método reservado para cargar la información de un carrito.
     * Se debe implementar en una versión futura del sistema.
     */
}
