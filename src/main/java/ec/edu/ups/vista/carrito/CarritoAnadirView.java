package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * La clase {@code CarritoAnadirView} representa la ventana interna del sistema
 * que permite al usuario añadir productos a un carrito de compras.
 * <p>
 * Esta interfaz gráfica está construida con Swing y permite realizar
 * las siguientes operaciones:
 * <ul>
 *     <li>Buscar un producto por su código</li>
 *     <li>Visualizar nombre, precio y subtotal</li>
 *     <li>Seleccionar una cantidad para agregar</li>
 *     <li>Añadir productos al carrito</li>
 *     <li>Ver los productos añadidos en una tabla</li>
 *     <li>Calcular el subtotal, IVA y total</li>
 *     <li>Guardar el carrito</li>
 *     <li>Limpiar los campos del formulario</li>
 * </ul>
 * Además, soporta internacionalización mediante la clase {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}.
 *
 * <p><b>Componentes principales:</b></p>
 * <ul>
 *     <li>{@code JTextField} para ingresar código, nombre, precio, subtotal, IVA y total</li>
 *     <li>{@code JButton} para buscar, añadir, guardar y limpiar</li>
 *     <li>{@code JTable} para mostrar los productos añadidos</li>
 *     <li>{@code JComboBox} para seleccionar la cantidad</li>
 *     <li>{@code JLabel} para mostrar etiquetas internacionalizadas</li>
 * </ul>
 *
 * <p>Esta clase hereda de {@code JInternalFrame}, permitiendo integrarse
 * dentro de una interfaz MDI con {@code JDesktopPane}.</p>
 *
 * @author Keyra
 */

public class CarritoAnadirView extends JInternalFrame {

    /** Panel principal que contiene todos los componentes de la interfaz. */
    private JPanel panelPrincipal;

    /** Modelo de tabla que gestiona los datos de la tabla de productos. */
    private DefaultTableModel tableModel;

    /** Campo de texto para ingresar el código del producto. */
    private JTextField txtCodigo;

    /** Campo de texto para mostrar el nombre del producto. */
    private JTextField txtNombre;

    /** Campo de texto para mostrar el precio del producto. */
    private JTextField txtPrecio;

    /** Botón para buscar un producto según el código ingresado. */
    private JButton btnBuscar;

    /** Botón para añadir un producto al carrito. */
    private JButton btnAnadir;

    /** Tabla para mostrar los productos añadidos al carrito. */
    private JTable tblProductos;

    /** Campo de texto para mostrar el subtotal de los productos añadidos. */
    private JTextField txtSubTotal;

    /** Campo de texto para mostrar el valor del IVA calculado. */
    private JTextField txtIVA;

    /** Campo de texto para mostrar el total a pagar. */
    private JTextField txtTotal;

    /** ComboBox que permite seleccionar la cantidad de producto a añadir. */
    private JComboBox<String> cbxCantidad;

    /** Botón para guardar el carrito completo. */
    private JButton btnGuardar;

    /** Botón para limpiar todos los campos del formulario. */
    private JButton btnLimpiar;

    /** Etiqueta para el campo de código. */
    private JLabel lblCodigo;

    /** Etiqueta para el campo de nombre. */
    private JLabel lblNombre;

    /** Etiqueta para el campo de precio. */
    private JLabel lblPrecio;

    /** Etiqueta para el campo de cantidad. */
    private JLabel lblCantidad;

    /** Etiqueta para el campo de subtotal. */
    private JLabel lblSubTotal;

    /** Etiqueta para el campo de IVA. */
    private JLabel lblIva;

    /** Etiqueta para el campo de total. */
    private JLabel lblTotal;

    /** Modelo de tabla utilizado para mostrar productos en el carrito. */
    private DefaultTableModel modelo;

    /** Manejador de internacionalización utilizado para traducir los textos en la interfaz. */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase {@code CarritoAnadirView}.
     * <p>
     * Inicializa la interfaz gráfica del formulario para añadir productos al carrito,
     * configurando componentes como la tabla, botones, campos de texto y etiquetas.
     * También aplica internacionalización y carga iconos.
     *
     * @param mensajeHandler Manejador de internacionalización que permite traducir los textos
     *                       de la interfaz gráfica al idioma seleccionado.
     */
    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Carrito de Compras", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        this.mensajeHandler = mensajeHandler;
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        actualizarTextos(mensajeHandler);
        limpiarCampos();
        iconoImagen();
    }

    /**
     * Actualiza los textos visibles de la interfaz según el idioma actual definido
     * en el {@code MensajeInternacionalizacionHandler}.
     * <p>
     * Cambia el título de la ventana, los textos de las etiquetas, botones y
     * encabezados de la tabla usando claves del archivo de propiedades de internacionalización.
     *
     * @param mensajes Manejador de mensajes internacionalizados con el idioma y país actual.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.anadir.titulo")); // Título ventana
        lblCodigo.setText(mensajes.get("producto.codigo"));
        lblNombre.setText(mensajes.get("producto.nombre"));
        lblPrecio.setText(mensajes.get("producto.precio"));
        lblCantidad.setText(mensajes.get("producto.cantidad"));
        lblSubTotal.setText(mensajes.get("carrito.subtotal"));
        lblIva.setText(mensajes.get("carrito.iva"));
        lblTotal.setText(mensajes.get("carrito.total"));

        btnBuscar.setText(mensajes.get("boton.buscar"));
        btnAnadir.setText(mensajes.get("boton.anadir"));
        btnGuardar.setText(mensajes.get("boton.guardar"));
        btnLimpiar.setText(mensajes.get("boton.limpiar"));

        modelo.setColumnIdentifiers(new String[]{
                mensajes.get("producto.codigo"),
                mensajes.get("producto.nombre"),
                mensajes.get("producto.precio"),
                mensajes.get("producto.cantidad"),
                mensajes.get("carrito.subtotal")
        });
    }

    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSubTotal.setText("");
    }

    /**
     * Asigna iconos redimensionados a los botones de la interfaz gráfica.
     * <p>
     * Este método busca imágenes en la carpeta de recursos del proyecto
     * (dentro de la carpeta {@code /imagenes}) y las ajusta al tamaño 20x20 píxeles
     * utilizando {@code Image.SCALE_SMOOTH} para que se vean adecuadamente en los botones.
     * Los iconos asignados corresponden a los botones:
     * <ul>
     *     <li>Buscar ({@code buscar.png})</li>
     *     <li>Añadir ({@code anadir.png})</li>
     *     <li>Guardar ({@code guardar.png})</li>
     *     <li>Limpiar ({@code limpiar.png})</li>
     * </ul>
     * Si no se encuentra algún recurso, se imprime un mensaje de error en consola.
     */
    private void iconoImagen() {
        /**
         * Redimensionar icono "Buscar"
         */
        URL btBuscar = getClass().getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnAceptar.getImage();
            Image newImgBuscar = imgBuscar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgBuscar);
            btnBuscar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        /**
         * Redimensionar icono "Añadir"
         */
        URL btAnadir = getClass().getClassLoader().getResource("imagenes/anadir.png");
        if (btAnadir != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAnadir);
            Image imgAnadir = iconBtnAceptar.getImage();
            Image newImgAnadir = imgAnadir.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgAnadir);
            btnAnadir.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Añadir");
        }

        /**
         * Redimensionar icono "Guardar"
         */
        URL btGuardar = getClass().getClassLoader().getResource("imagenes/guardar.png");
        if (btGuardar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btGuardar);
            Image imgGuardar = iconBtnAceptar.getImage();
            Image newImgGuardar = imgGuardar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgGuardar);
            btnGuardar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
        }

        /**
         * Redimensionar icono "Limpiar"
         */
        URL btLimpiar = getClass().getClassLoader().getResource("imagenes/limpiar.png");
        if (btLimpiar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btLimpiar);
            Image imgLimpiar = iconBtnAceptar.getImage();
            Image newImgLimpiar = imgLimpiar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgLimpiar);
            btnLimpiar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Limpiar");
        }
    }

    //---------------------- Getters

    /**
     * Obtiene el panel principal de la vista.
     * @return El panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el modelo de la tabla de productos.
     * @return El modelo de la tabla.
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Obtiene el campo de texto del código del producto.
     * @return JTextField para el código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Obtiene el campo de texto del nombre del producto.
     * @return JTextField para el nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Obtiene el campo de texto del precio del producto.
     * @return JTextField para el precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Obtiene el botón para buscar un producto.
     * @return JButton para buscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Obtiene el botón para añadir un producto al carrito.
     * @return JButton para añadir.
     */
    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    /**
     * Obtiene la tabla de productos añadidos.
     * @return JTable con los productos.
     */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /**
     * Obtiene el campo de texto para el subtotal del carrito.
     * @return JTextField para el subtotal.
     */
    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    /**
     * Obtiene el campo de texto para el IVA.
     * @return JTextField para el IVA.
     */
    public JTextField getTxtIVA() {
        return txtIVA;
    }

    /**
     * Obtiene el campo de texto para el total del carrito.
     * @return JTextField para el total.
     */
    public JTextField getTxtTotal() {
        return txtTotal;
    }

    /**
     * Obtiene el combo box para seleccionar la cantidad de productos.
     * @return JComboBox con las cantidades.
     */
    public JComboBox<String> getCbxCantidad() {
        return cbxCantidad;
    }

    /**
     * Obtiene el botón para guardar el carrito.
     * @return JButton para guardar.
     */
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Obtiene el botón para limpiar los campos.
     * @return JButton para limpiar.
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /**
     * Obtiene la etiqueta del código del producto.
     * @return JLabel para el código.
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * Obtiene la etiqueta del nombre del producto.
     * @return JLabel para el nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Obtiene la etiqueta del precio del producto.
     * @return JLabel para el precio.
     */
    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    /**
     * Obtiene la etiqueta de la cantidad del producto.
     * @return JLabel para la cantidad.
     */
    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    /**
     * Obtiene la etiqueta del subtotal del carrito.
     * @return JLabel para el subtotal.
     */
    public JLabel getLblSubTotal() {
        return lblSubTotal;
    }

    /**
     * Obtiene la etiqueta del IVA.
     * @return JLabel para el IVA.
     */
    public JLabel getLblIva() {
        return lblIva;
    }

    /**
     * Obtiene la etiqueta del total del carrito.
     * @return JLabel para el total.
     */
    public JLabel getLblTotal() {
        return lblTotal;
    }

    /**
     * Obtiene el modelo de datos de la tabla de productos del carrito.
     * @return DefaultTableModel del carrito.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene el manejador de mensajes para internacionalización.
     * @return Instancia de MensajeInternacionalizacionHandler.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    //---------------------- Setters
    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal El panel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el modelo de la tabla de productos.
     * @param tableModel Modelo de la tabla.
     */
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    /**
     * Establece el campo de texto para el código del producto.
     * @param txtCodigo JTextField del código.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Establece el campo de texto para el nombre del producto.
     * @param txtNombre JTextField del nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de texto para el precio del producto.
     * @param txtPrecio JTextField del precio.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Establece el botón para buscar productos.
     * @param btnBuscar JButton de buscar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece el botón para añadir productos al carrito.
     * @param btnAñadir JButton de añadir.
     */
    public void setBtnAnadir(JButton btnAñadir) {
        this.btnAnadir = btnAñadir;
    }

    /**
     * Establece la tabla de productos añadidos.
     * @param tblProductos JTable de productos.
     */
    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    /**
     * Establece el campo de texto para el subtotal del carrito.
     * @param txtSubTotal JTextField del subtotal.
     */
    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    /**
     * Establece el campo de texto para el IVA.
     * @param txtIVA JTextField del IVA.
     */
    public void setTxtIVA(JTextField txtIVA) {
        this.txtIVA = txtIVA;
    }

    /**
     * Establece el campo de texto para el total del carrito.
     * @param txtTotal JTextField del total.
     */
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    /**
     * Establece el combo box para la cantidad de productos.
     * @param cbxCantidad JComboBox con cantidades.
     */
    public void setCbxCantidad(JComboBox<String> cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    /**
     * Establece el botón para guardar el carrito.
     * @param btnGuardar JButton de guardar.
     */
    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    /**
     * Establece el botón para limpiar los campos.
     * @param btnLimpiar JButton de limpiar.
     */
    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /**
     * Establece la etiqueta del código del producto.
     * @param lblCodigo JLabel del código.
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * Establece la etiqueta del nombre del producto.
     * @param lblNombre JLabel del nombre.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Establece la etiqueta del precio del producto.
     * @param lblPrecio JLabel del precio.
     */
    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    /**
     * Establece la etiqueta de la cantidad del producto.
     * @param lblCantidad JLabel de la cantidad.
     */
    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    /**
     * Establece la etiqueta del subtotal del carrito.
     * @param lblSubTotal JLabel del subtotal.
     */
    public void setLblSubTotal(JLabel lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    /**
     * Establece la etiqueta del IVA.
     * @param lblIva JLabel del IVA.
     */
    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
    }

    /**
     * Establece la etiqueta del total del carrito.
     * @param lblTotal JLabel del total.
     */
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    /**
     * Establece el modelo de datos para la tabla de productos del carrito.
     * @param modelo Modelo de la tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Establece el manejador de mensajes para internacionalización.
     * @param mensajeHandler Instancia de MensajeInternacionalizacionHandler.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Muestra un cuadro de diálogo emergente con el mensaje proporcionado.
     *
     * Este método se utiliza para brindar retroalimentación al usuario desde la
     * interfaz gráfica del detalle del carrito. El cuadro de diálogo es modal
     * y se cierra al presionar "Aceptar".
     *
     * @param mensaje El texto que se mostrará en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

}