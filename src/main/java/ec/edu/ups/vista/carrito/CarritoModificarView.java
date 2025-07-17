package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * Vista interna del sistema encargada de modificar un carrito de compras existente.
 * Permite buscar un carrito por código, visualizar los productos contenidos,
 * y modificar únicamente las cantidades de cada producto en la tabla.
 * <p>
 * Esta vista extiende {@link JInternalFrame} y está diseñada para funcionar dentro de una interfaz MDI.
 * Además, cuenta con soporte para internacionalización dinámica utilizando {@link MensajeInternacionalizacionHandler}.
 * <p>
 * Los botones e íconos están personalizados para mejorar la experiencia del usuario.
 *
 * <p><b>Características:</b></p>
 * <ul>
 *   <li>Búsqueda de carritos por código</li>
 *   <li>Visualización y edición parcial de los productos (solo la columna cantidad es editable)</li>
 *   <li>Actualización de idioma dinámica</li>
 *   <li>Soporte para íconos en los botones</li>
 * </ul>
 *
 * @author Keyra
 */
public class CarritoModificarView extends JInternalFrame {
    /**
     * Campo de texto donde se ingresa el código del carrito a modificar.
     */
    private JTextField txtCodigo;

    /**
     * Campo de texto que muestra o permite modificar la fecha del carrito.
     */
    private JTextField txtFecha;

    /**
     * Botón para buscar un carrito por su código.
     */
    private JButton btnBuscar;

    /**
     * Tabla que muestra los productos del carrito. Solo se puede modificar la cantidad.
     */
    private JTable tblView;

    /**
     * Botón para aplicar los cambios en las cantidades de productos del carrito.
     */
    private JButton btnModificar;

    /**
     * Etiqueta asociada al campo de código del carrito.
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta asociada al campo de fecha del carrito.
     */
    private JLabel lblFecha;

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     */
    private JPanel panelPrincipal;

    /**
     * Modelo de tabla que define las columnas y filas para los productos del carrito.
     * Solo la columna de cantidad es editable.
     */
    private DefaultTableModel modelo;

    /**
     * Manejador de internacionalización utilizado para actualizar los textos visibles
     * de la interfaz en diferentes idiomas.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor que inicializa la ventana de modificación de carritos.
     * Configura los componentes gráficos principales de la vista, incluyendo:
     * el diseño, los botones, etiquetas, tabla de productos y la internacionalización dinámica.
     * <p>
     * También define un modelo personalizado para la tabla, donde solo la columna "Cantidad" es editable,
     * permitiendo modificar únicamente la cantidad de productos en el carrito.
     * <p>
     * Además, se asignan íconos a los botones y se establecen los textos iniciales según el idioma actual.
     *
     * @param mensajeHandler Manejador de mensajes para aplicar la internacionalización a los componentes de la interfaz.
     */
    public CarritoModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Modificar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(650, 700);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        /**
         * Modelo de tabla personalizado: solo columna 3 ("Cantidad") editable
         */
        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        tblView.setModel(modelo);

        this.mensajeHandler = mensajeHandler;
        imagenIcono();
        actualizarTextos(mensajeHandler);
    }

    /**
     * Actualiza los textos visibles de los componentes de la interfaz gráfica,
     * como etiquetas, botones y el título de la ventana, utilizando los recursos
     * proporcionados por el manejador de internacionalización.
     * <p>
     * También actualiza los encabezados de la tabla con las claves correspondientes
     * al idioma seleccionado.
     *
     * @param mensajeHandler Manejador de mensajes internacionalizados que proporciona los textos traducidos.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(mensajeHandler.get("carrito.codigo"));
        lblFecha.setText(mensajeHandler.get("carrito.fecha"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnModificar.setText(mensajeHandler.get("boton.modificar"));

        setTitle(mensajeHandler.get("carrito.modificar.titulo"));

        modelo.setColumnIdentifiers(new String[]{
                mensajeHandler.get("producto.codigo"),
                mensajeHandler.get("producto.nombre"),
                mensajeHandler.get("producto.precio"),
                mensajeHandler.get("producto.cantidad"),
                mensajeHandler.get("carrito.subtotal")
        });
    }

    /**
     * Carga y asigna íconos redimensionados a los botones "Buscar" y "Modificar"
     * desde los recursos de la aplicación.
     * <p>
     * Las imágenes se escalan a 30x30 píxeles para adaptarse al diseño de la interfaz.
     * Si no se encuentran los recursos gráficos, se muestra un mensaje de error en consola.
     */
    private void imagenIcono() {
        /**
         * Redimensionar icono "Buscar"
         */
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnBuscar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnBuscar.getImage();  // Convierte ImageIcon a Image
            Image newImgBuscar = imgBuscar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscar.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        /**
         * Redimensionar icono "Modificar"
         */
        URL btModificar = LoginView.class.getClassLoader().getResource("imagenes/modificar.png");
        if (btModificar != null) {
            ImageIcon iconBtnModificar = new ImageIcon(btModificar);
            Image imgModificar = iconBtnModificar.getImage();  // Convierte ImageIcon a Image
            Image newImgModificar = imgModificar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnModificar = new ImageIcon(newImgModificar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnModificar.setIcon(iconBtnModificar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Modificar");
        }

    }

    /**
     * Cambia dinámicamente el idioma de todos los textos visibles en la vista,
     * utilizando los recursos provistos por el {@code mensajeHandler}.
     * <p>
     * Actualiza el título de la ventana, las etiquetas, los botones y los tooltips
     * asociados a los campos de texto para reflejar el idioma seleccionado.
     *
     * @param mensajeHandler Manejador de internacionalización que contiene los textos traducidos.
     */
    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        /**
         * Cambiar el título de la ventana
         */
        setTitle(mensajeHandler.get("producto.detalle.titulo"));

        /**
         * Cambiar las etiquetas de los campos
         */
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblFecha.setText(mensajeHandler.get("producto.fecha"));

        /**
         * Cambiar los botones
         */
        btnBuscar.setText(mensajeHandler.get("producto.buscar"));
        btnModificar.setText(mensajeHandler.get("producto.modificar"));

        /**
         * Actualizar tooltips si es necesario
         */
        txtCodigo.setToolTipText(mensajeHandler.get("producto.codigo.tooltip"));
        txtFecha.setToolTipText(mensajeHandler.get("producto.fecha.tooltip"));
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje informativo.
     * Este método se utiliza para notificar al usuario sobre eventos como errores,
     * confirmaciones o advertencias dentro de la vista de modificación de carritos.
     *
     * @param mensaje Texto que se mostrará en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //----------------- Getters
    /**
     * Obtiene el campo de texto del código del carrito.
     * @return Campo de texto para el código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Obtiene el campo de texto de la fecha del carrito.
     * @return Campo de texto para la fecha.
     */
    public JTextField getTxtFecha() {
        return txtFecha;
    }

    /**
     * Obtiene el botón para buscar el carrito.
     * @return Botón "Buscar".
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Obtiene la tabla que muestra los productos del carrito.
     * @return Tabla de productos.
     */
    public JTable getTblView() {
        return tblView;
    }

    /**
     * Obtiene el botón para modificar la cantidad de productos del carrito.
     * @return Botón "Modificar".
     */
    public JButton getBtnModificar() {
        return btnModificar;
    }

    /**
     * Obtiene la etiqueta asociada al campo de código.
     * @return Etiqueta del código.
     */
    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    /**
     * Obtiene la etiqueta asociada al campo de fecha.
     * @return Etiqueta de la fecha.
     */
    public JLabel getLblFecha() {
        return lblFecha;
    }

    /**
     * Obtiene el panel principal que contiene todos los elementos gráficos.
     * @return Panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el modelo de la tabla que contiene los productos del carrito.
     * @return Modelo de la tabla.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene el manejador de internacionalización para los textos de la vista.
     * @return Manejador de mensajes internacionalizados.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    //--------------------- Setters
    /**
     * Establece el campo de texto para el código del carrito.
     * @param txtCodigo Campo de texto a asignar.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Establece el campo de texto para la fecha del carrito.
     * @param txtFecha Campo de texto a asignar.
     */
    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    /**
     * Establece el botón para buscar un carrito por código.
     * @param btnBuscar Botón a asignar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece la tabla que muestra los productos del carrito.
     * @param tblView Tabla a asignar.
     */
    public void setTblView(JTable tblView) {
        this.tblView = tblView;
    }

    /**
     * Establece el botón para modificar el carrito.
     * @param btnModificar Botón a asignar.
     */
    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    /**
     * Establece la etiqueta asociada al campo de código.
     * @param lblCodigo Etiqueta a asignar.
     */
    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    /**
     * Establece la etiqueta asociada al campo de fecha.
     * @param lblFecha Etiqueta a asignar.
     */
    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal Panel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el modelo de datos de la tabla.
     * @param modelo Modelo de tabla a asignar.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Establece el manejador de internacionalización de la vista.
     * @param mensajeHandler Manejador a asignar.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

}
