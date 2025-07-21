package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * La clase {@code ProductoModificarView} representa una ventana interna (JInternalFrame)
 * dentro del sistema de carrito de compras que permite modificar, buscar o eliminar productos
 * existentes. La ventana forma parte del módulo de gestión de productos.
 * <p>
 * Incluye campos de entrada para el código, nombre y precio del producto, botones para
 * realizar las acciones correspondientes, y soporte para internacionalización mediante
 * la clase {@link MensajeInternacionalizacionHandler}.
 * </p>
 *
 * <p>
 * Esta clase se integra en la interfaz MDI (Multiple Document Interface) y respeta el patrón
 * MVC, actuando como la vista para las operaciones de modificación de productos.
 * </p>
 *
 * @author Keyra
 */

public class ProductoModificarView extends JInternalFrame {

    /**
     * Panel principal que contiene todos los componentes visuales de la ventana.
     */
    private JPanel panelPrincipal;

    /**
     * Campo de texto para ingresar o mostrar el código del producto.
     */
    private JTextField txtCodigo;

    /**
     * Campo de texto para ingresar o mostrar el nombre del producto.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para ingresar o mostrar el precio del producto.
     */
    private JTextField txtPrecio;

    /**
     * Botón que permite buscar un producto por su código.
     */
    private JButton btnBuscar;

    /**
     * Botón que permite eliminar un producto del sistema.
     */
    private JButton btnEliminar;

    /**
     * Botón que permite modificar los datos de un producto.
     */
    private JButton btnModificar;

    /**
     * Etiqueta que muestra el texto correspondiente al campo de código del producto.
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta que muestra el texto correspondiente al campo de nombre del producto.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta que muestra el texto correspondiente al campo de precio del producto.
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta que indica el título o encabezado de la vista de modificación de productos.
     */
    private JLabel lblModificarProducto;

    /**
     * Manejador de internacionalización utilizado para actualizar los textos visibles
     * según el idioma seleccionado.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase ProductoModificarView.
     * Inicializa los componentes de la ventana para modificar productos,
     * configura la internacionalización, el diseño y los íconos.
     *
     * @param mensajeHandler Manejador de internacionalización que proporciona
     *                       los textos traducidos según el idioma seleccionado.
     */
    public ProductoModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.producto.modificar"), true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Carga y asigna íconos redimensionados a los botones de la ventana.
     * Establece íconos para los botones Modificar, Eliminar y Buscar.
     * En caso de error al cargar un ícono, se muestra un mensaje en consola.
     */
    private void imagenIcon() {
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
     * Actualiza los textos visibles en la interfaz gráfica utilizando
     * las claves definidas en el archivo de internacionalización.
     *
     * @param mensajeHandler Manejador de internacionalización para obtener
     *                       los textos traducidos según el idioma seleccionado.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblModificarProducto.setText(mensajeHandler.get("producto.modificar.titulo"));
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.precio"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeHandler.get("boton.eliminar"));
        btnModificar.setText(mensajeHandler.get("boton.modificar"));

        setTitle(mensajeHandler.get("producto.modificar.titulo"));
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
     * Obtiene el botón para modificar un producto.
     * @return JButton para modificar.
     */
    public JButton getBtnModificar() {
        return btnModificar;
    }

    /**
     * Obtiene el botón para eliminar un producto.
     * @return JButton para eliminar.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Obtiene el panel principal de la vista.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Establece el campo de texto del código del producto.
     * @param txtCodigo JTextField del código.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Establece el campo de texto del nombre del producto.
     * @param txtNombre JTextField del nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de texto del precio del producto.
     * @param txtPrecio JTextField del precio.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Establece el botón para buscar un producto.
     * @param btnBuscar JButton de búsqueda.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece el botón para modificar un producto.
     * @param btnModificar JButton para modificar.
     */
    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    /**
     * Establece el botón para eliminar un producto.
     * @param btnEliminar JButton para eliminar.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal JPanel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el manejador de mensajes para internacionalización
     * y actualiza los textos en la vista.
     * @param mensajeHandler Manejador de internacionalización.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        actualizarTextos(mensajeHandler);
    }

    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje El mensaje que se desea mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia los campos de texto de la vista.
     * Deja vacíos los campos de código, nombre y precio.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

}