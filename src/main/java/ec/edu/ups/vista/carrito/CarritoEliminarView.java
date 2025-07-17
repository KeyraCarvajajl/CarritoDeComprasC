package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * Clase {@code CarritoEliminarView} representa la vista gráfica para eliminar carritos de compras.
 * Incluye campos para ingresar el código, visualizar productos del carrito, y botones para buscar,
 * eliminar y vaciar el carrito. Implementa soporte para internacionalización e íconos gráficos.
 *
 * Esta clase extiende {@link JInternalFrame} para integrarse con una interfaz MDI.
 *
 * @author Keyra
 */
public class CarritoEliminarView extends JInternalFrame {

    /**
     * Campo de texto donde se ingresa el código del carrito que se desea eliminar.
     */
    private JTextField txtCodigo;

    /**
     * Botón que permite buscar el carrito correspondiente al código ingresado.
     */
    private JButton btnBuscar;

    /**
     * Tabla que muestra los productos contenidos en el carrito seleccionado.
     */
    private JTable tblLProductos;

    /**
     * Botón que elimina el carrito actualmente cargado en la vista.
     */
    private JButton btnEliminar;

    /**
     * Botón que vacía los productos del carrito sin eliminarlo completamente.
     */
    private JButton btnVaciar;

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta para el campo de código del carrito.
     */
    private JLabel lblCodigo;

    /**
     * Campo de texto que muestra la fecha de creación o modificación del carrito.
     */
    private JTextField txtFecha;

    /**
     * Etiqueta para el campo de fecha del carrito.
     */
    private JLabel lblFecha;

    /**
     * Manejador de mensajes para internacionalización dinámica de textos.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Modelo de tabla que define las columnas y estructura de datos de {@code tblLProductos}.
     */
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la vista de eliminación de carritos.
     * Configura el diseño del formulario, los íconos de los botones,
     * las columnas de la tabla y aplica la internacionalización de textos.
     *
     * @param mensajeHandler El manejador de mensajes para la internacionalización dinámica.
     */
    public CarritoEliminarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(600, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblLProductos.setModel(modelo);
        this.mensajeHandler = mensajeHandler;

        actualizarTextos(mensajeHandler);
        iconoImagen();
    }

    /**
     * Carga y asigna íconos redimensionados a los botones de la vista
     * (Eliminar, Vaciar, Buscar). Las imágenes se obtienen desde el
     * recurso `imagenes/` del proyecto y se ajustan a un tamaño de 30x30 píxeles.
     * <p>
     * En caso de no encontrarse el recurso gráfico correspondiente, se muestra
     * un mensaje de error en la consola.
     */
    private void iconoImagen() {
        /**
         * Redimensionar icono "Eliminar"
         */
        URL btEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (btEliminar != null) {
            ImageIcon iconBtnEliminar = new ImageIcon(btEliminar);
            Image imgEliminar = iconBtnEliminar.getImage();  // Convierte ImageIcon a Image
            Image newImgEliminar = imgEliminar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnEliminar = new ImageIcon(newImgEliminar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnEliminar.setIcon(iconBtnEliminar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Eliminar");
        }

        /**
         * Redimensionar icono "Vaciar"
         */
        URL btVaciar = LoginView.class.getClassLoader().getResource("imagenes/vaciar.png");
        if (btVaciar != null) {
            ImageIcon iconBtnVaciar = new ImageIcon(btVaciar);
            Image imgVaciar = iconBtnVaciar.getImage();  // Convierte ImageIcon a Image
            Image newImgVaciar = imgVaciar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnVaciar = new ImageIcon(newImgVaciar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnVaciar.setIcon(iconBtnVaciar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Vaciar");
        }

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

    }

    /**
     * Actualiza todos los textos visibles en la vista utilizando el manejador
     * de internacionalización proporcionado. Este método se encarga de
     * traducir dinámicamente las etiquetas, botones y el título de la ventana
     * según el idioma seleccionado por el usuario.
     *
     * @param mensajeHandler Objeto que gestiona los textos internacionalizados
     *                       de la interfaz gráfica.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(mensajeHandler.get("carrito.codigo"));
        lblFecha.setText(mensajeHandler.get("carrito.fecha"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeHandler.get("boton.eliminar"));
        btnVaciar.setText(mensajeHandler.get("boton.vaciar"));

        setTitle(mensajeHandler.get("carrito.eliminar.titulo"));
    }


    /**
     * Obtiene el campo de texto para el código del carrito.
     * @return Campo de texto del código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Establece el campo de texto del código del carrito.
     * @param txtCodigo Campo de texto que representa el código.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Obtiene el panel principal de la vista.
     * @return Panel principal de tipo JPanel.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal Panel principal a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Obtiene el botón para vaciar el carrito.
     * @return Botón "Vaciar".
     */
    public JButton getBtnVaciar() {
        return btnVaciar;
    }

    /**
     * Establece el botón para vaciar el carrito.
     * @param btnVaciar Botón "Vaciar" a asignar.
     */
    public void setBtnVaciar(JButton btnVaciar) {
        this.btnVaciar = btnVaciar;
    }

    /**
     * Obtiene el botón para eliminar el carrito.
     * @return Botón "Eliminar".
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Establece el botón para eliminar el carrito.
     * @param btnEliminar Botón "Eliminar" a asignar.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * Obtiene la tabla que muestra los productos del carrito.
     * @return Tabla de productos.
     */
    public JTable getTblLProductos() {
        return tblLProductos;
    }

    /**
     * Establece la tabla que muestra los productos del carrito.
     * @param tblLProductos Tabla de productos a asignar.
     */
    public void setTblLProductos(JTable tblLProductos) {
        this.tblLProductos = tblLProductos;
    }

    /**
     * Obtiene el botón para buscar un carrito.
     * @return Botón "Buscar".
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el botón para buscar un carrito.
     * @param btnBuscar Botón "Buscar" a asignar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Obtiene el campo de texto para la fecha del carrito.
     * @return Campo de texto de fecha.
     */
    public JTextField getTxtFecha() {
        return txtFecha;
    }

    /**
     * Establece el campo de texto para la fecha del carrito.
     * @param txtFecha Campo de texto de fecha a asignar.
     */
    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    /**
     * Obtiene el manejador de internacionalización usado en la vista.
     * @return Manejador de mensajes internacionalizados.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    /**
     * Establece el manejador de internacionalización de la vista.
     * @param mensajeHandler Objeto de tipo {@code MensajeInternacionalizacionHandler}.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }


    public void mostrarMensaje(String s) {
    }

    public void limpiarCampos() {
    }
}
