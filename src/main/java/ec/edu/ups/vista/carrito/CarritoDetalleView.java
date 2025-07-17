package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * Clase que representa la vista de detalle de un carrito de compras.
 * Esta interfaz permite buscar un carrito por su ID y visualizar los productos asociados,
 * mostrando su subtotal, IVA y total. Incluye soporte para internacionalización e íconos personalizados.
 *
 * Funcionalidades principales:
 * - Búsqueda de carrito por ID
 * - Visualización de los productos del carrito en una tabla
 * - Cálculo y presentación del subtotal, IVA y total
 * - Compatibilidad con cambio de idioma mediante {@link MensajeInternacionalizacionHandler}
 * - Inclusión de íconos en botones para una mejor experiencia de usuario
 *
 * Esta clase extiende {@link JInternalFrame} para integrarse en una interfaz MDI.
 *
 * @author [Tu nombre]
 */
public class CarritoDetalleView extends JInternalFrame {

    /**
     * Campo de texto para ingresar el ID del carrito cuyos detalles se desean visualizar.
     */
    private JTextField txtIdDet;

    /**
     * Botón para ejecutar la búsqueda del carrito según el ID ingresado.
     */
    private JButton btnBuscarDetalle;

    /**
     * Tabla donde se mostrarán los productos pertenecientes al carrito seleccionado.
     */
    private JTable tblDetCarrito;

    /**
     * Campo de texto que muestra el subtotal del carrito.
     */
    private JTextField txtSubTotal;

    /**
     * Campo de texto que muestra el valor del IVA aplicado al carrito.
     */
    private JTextField txtIVA;

    /**
     * Campo de texto que muestra el total a pagar por el carrito.
     */
    private JTextField txtTotal;

    /**
     * Botón que permite cerrar la ventana de detalles del carrito.
     */
    private JButton btnAceptarDetalle;

    /**
     * Panel principal que contiene todos los componentes de la interfaz.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta que indica que se están visualizando los detalles del carrito.
     */
    private JLabel lblDetallesCarrito;

    /**
     * Etiqueta asociada al campo de texto para ingresar el ID del carrito.
     */
    private JLabel lblID;

    /**
     * Etiqueta que identifica el campo de subtotal.
     */
    private JLabel lblSubTotal;

    /**
     * Etiqueta que identifica el campo de IVA.
     */
    private JLabel lblIVA;

    /**
     * Etiqueta que identifica el campo de total.
     */
    private JLabel lblTotal;

    /**
     * Modelo de tabla que contiene los datos de los productos visualizados en la tabla.
     */
    private DefaultTableModel modelo;

    /**
     * Manejador de mensajes internacionalizados, usado para actualizar los textos
     * según el idioma seleccionado por el usuario.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la ventana CarritoDetalleView.
     * Inicializa la interfaz gráfica con sus componentes visuales, configura el modelo de la tabla
     * y aplica la internacionalización e íconos personalizados a los botones.
     *
     * @param mensajeHandler Manejador de internacionalización que permite actualizar los textos
     *                       de la interfaz según el idioma seleccionado.
     */
    public CarritoDetalleView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Detalle del Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblDetCarrito.setModel(modelo);
        this.mensajeHandler = mensajeHandler;
        actualizarTextos(mensajeHandler);
        iconoImagen();
    }

    /**
     * Actualiza todos los textos visibles de la ventana con base en el idioma
     * definido por el manejador de mensajes de internacionalización.
     *
     * @param mensajes Objeto de tipo MensajeInternacionalizacionHandler que contiene
     *                 los textos traducidos según el idioma seleccionado.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.detalle.titulo"));
        lblID.setText(mensajes.get("carrito.detalle.id"));
        lblSubTotal.setText(mensajes.get("carrito.subtotal"));
        lblIVA.setText(mensajes.get("carrito.iva"));
        lblTotal.setText(mensajes.get("carrito.total"));
        lblDetallesCarrito.setText(mensajes.get("carrito.detalle.etiqueta"));
        btnBuscarDetalle.setText(mensajes.get("boton.buscar"));
        btnAceptarDetalle.setText(mensajes.get("boton.aceptar"));
    }

    /**
     * Carga y redimensiona los íconos gráficos utilizados en los botones de la interfaz.
     * Aplica imágenes personalizadas a los botones de "Buscar" y "Aceptar".
     * Muestra errores en consola si no se encuentran los archivos de imagen.
     */
    private void iconoImagen() {
        /**
         * Redimensionar icono "Buscar"
         */
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnBuscar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnBuscar.getImage();  // Convierte ImageIcon a Image
            Image newImgBuscar = imgBuscar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscarDetalle.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Detalle");
        }

        /**
         * Redimensionar icono "Aceptar"
         */
        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            Image imgAceptar = iconBtnAceptar.getImage();  // Convierte ImageIcon a Image
            Image newImgAceptar = imgAceptar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnAceptar = new ImageIcon(newImgAceptar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnAceptarDetalle.setIcon(iconBtnAceptar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar Detalle");
        }

    }
    /**
     * Devuelve el campo de texto para ingresar el ID del carrito.
     * @return JTextField del ID del carrito.
     */
    public JTextField getTxtIdDet() {
        return txtIdDet;
    }
    /**
     * Establece el campo de texto del ID del carrito.
     * @param txtIdDet JTextField del ID del carrito.
     */
    public void setTxtIdDet(JTextField txtIdDet) {
        this.txtIdDet = txtIdDet;
    }
    /**
     * Devuelve el botón para buscar el detalle del carrito.
     * @return JButton del botón buscar.
     */
    public JButton getBtnBuscarDetalle() {
        return btnBuscarDetalle;
    }
    /**
     * Establece el botón de búsqueda de detalles del carrito.
     * @param btnBuscarDetalle JButton a configurar.
     */
    public void setBtnBuscarDetalle(JButton btnBuscarDetalle) {
        this.btnBuscarDetalle = btnBuscarDetalle;
    }
    /**
     * Devuelve la tabla que contiene los productos del carrito.
     * @return JTable de productos.
     */
    public JTable getTblDetCarrito() {
        return tblDetCarrito;
    }
    /**
     * Establece la tabla de productos del carrito.
     * @param tblDetCarrito JTable de productos.
     */
    public void setTblDetCarrito(JTable tblDetCarrito) {
        this.tblDetCarrito = tblDetCarrito;
    }
    /**
     * Devuelve el campo de texto del subtotal.
     * @return JTextField del subtotal.
     */
    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }
    /**
     * Establece el campo de texto del subtotal.
     * @param txtSubTotal JTextField del subtotal.
     */
    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }
    /**
     * Devuelve el campo de texto del IVA.
     * @return JTextField del IVA.
     */
    public JTextField getTxtIVA() {
        return txtIVA;
    }
    /**
     * Establece el campo de texto del IVA.
     * @param txtIVA JTextField del IVA.
     */
    public void setTxtIVA(JTextField txtIVA) {
        this.txtIVA = txtIVA;
    }
    /**
     * Devuelve el campo de texto del total.
     * @return JTextField del total.
     */
    public JTextField getTxtTotal() {
        return txtTotal;
    }
    /**
     * Establece el campo de texto del total.
     * @param txtTotal JTextField del total.
     */
    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }
    /**
     * Devuelve el botón de aceptar.
     * @return JButton de aceptar.
     */
    public JButton getBtnAceptarDetalle() {
        return btnAceptarDetalle;
    }
    /**
     * Establece el botón de aceptar.
     * @param btnAceptarDetalle JButton a configurar.
     */
    public void setBtnAceptarDetalle(JButton btnAceptarDetalle) {
        this.btnAceptarDetalle = btnAceptarDetalle;
    }
    /**
     * Devuelve el panel principal del formulario.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    /**
     * Establece el panel principal del formulario.
     * @param panelPrincipal JPanel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    /**
     * Devuelve la etiqueta de detalles del carrito.
     * @return JLabel con el título de detalles.
     */
    public JLabel getLblDetallesCarrito() {
        return lblDetallesCarrito;
    }
    /**
     * Establece la etiqueta de detalles del carrito.
     * @param lblDetallesCarrito JLabel a configurar.
     */
    public void setLblDetallesCarrito(JLabel lblDetallesCarrito) {
        this.lblDetallesCarrito = lblDetallesCarrito;
    }
    /**
     * Devuelve la etiqueta del ID.
     * @return JLabel del ID.
     */
    public JLabel getLblID() {
        return lblID;
    }
    /**
     * Establece la etiqueta del ID.
     * @param lblID JLabel a configurar.
     */
    public void setLblID(JLabel lblID) {
        this.lblID = lblID;
    }
    /**
     * Devuelve la etiqueta del subtotal.
     * @return JLabel del subtotal.
     */
    public JLabel getLblSubTotal() {
        return lblSubTotal;
    }
    /**
     * Establece la etiqueta del subtotal.
     * @param lblSubTotal JLabel a configurar.
     */
    public void setLblSubTotal(JLabel lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }
    /**
     * Devuelve la etiqueta del IVA.
     * @return JLabel del IVA.
     */
    public JLabel getLblIVA() {
        return lblIVA;
    }
    /**
     * Establece la etiqueta del IVA.
     * @param lblIVA JLabel a configurar.
     */
    public void setLblIVA(JLabel lblIVA) {
        this.lblIVA = lblIVA;
    }
    /**
     * Devuelve la etiqueta del total.
     * @return JLabel del total.
     */
    public JLabel getLblTotal() {
        return lblTotal;
    }
    /**
     * Establece la etiqueta del total.
     * @param lblTotal JLabel a configurar.
     */
    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }
    /**
     * Devuelve el modelo de la tabla de productos.
     * @return DefaultTableModel de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }
    /**
     * Establece el modelo de la tabla de productos.
     * @param modelo Modelo de la tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    /**
     * Devuelve el manejador de internacionalización.
     * @return MensajeInternacionalizacionHandler en uso.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }
    /**
     * Establece el manejador de internacionalización.
     * @param mensajeHandler Manejador a configurar.
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
