package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase {@code ProductoEliminarView} representa una ventana interna (JInternalFrame)
 * que permite al usuario eliminar un producto del sistema ingresando su código.
 *
 * Esta clase forma parte del módulo de gestión de productos del sistema de carrito de compras.
 * Utiliza componentes Swing para la interfaz gráfica, implementa internacionalización dinámica
 * mediante {@code MensajeInternacionalizacionHandler} y aplica iconos personalizados a los botones.
 *
 * <p><strong>Características:</strong></p>
 * <ul>
 *     <li>Permite buscar un producto por su código.</li>
 *     <li>Confirma visualmente la eliminación del producto.</li>
 *     <li>Admite cambio dinámico de idioma.</li>
 *     <li>Estilo visual coherente con el resto del sistema.</li>
 * </ul>
 *
 * @author Keyra
 */
public class ProductoEliminarView extends JInternalFrame {
    /**
     * Panel principal que contiene todos los componentes de la interfaz gráfica.
     */
    private JPanel panelPrincipal;

    /**
     * Campo de texto para ingresar el código del producto que se desea eliminar.
     */
    private JTextField txtCodigo;

    /**
     * Campo de texto que muestra el nombre del producto encontrado.
     * Es de solo lectura y se llena automáticamente tras la búsqueda.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto que muestra el precio del producto encontrado.
     * Es de solo lectura y se llena automáticamente tras la búsqueda.
     */
    private JTextField txtPrecio;

    /**
     * Botón que permite eliminar el producto del sistema.
     */
    private JButton btnEliminar;

    /**
     * Botón que ejecuta la búsqueda del producto en base al código ingresado.
     */
    private JButton btnBuscar;

    /**
     * Etiqueta descriptiva para el campo de código del producto.
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta descriptiva para el campo de nombre del producto.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta descriptiva para el campo de precio del producto.
     */
    private JLabel lblPrecio;

    /**
     * Etiqueta principal que identifica la funcionalidad de la ventana
     * como "Eliminar Producto".
     */
    private JLabel lblEliminarProducto;

    /**
     * Manejador de internacionalización utilizado para traducir dinámicamente los textos de la interfaz.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase ProductoEliminarView.
     * Inicializa los componentes de la interfaz para eliminar un producto,
     * configura la internacionalización y los iconos de los botones.
     *
     * @param mensajeHandler Manejador de internacionalización para cargar los textos
     *                       en el idioma seleccionado.
     */
    public ProductoEliminarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.producto.eliminar"), true, true, false, true);
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
     * Actualiza los textos visibles de los componentes de la interfaz
     * utilizando las claves definidas en el archivo de propiedades
     * del manejador de internacionalización.
     *
     * @param mensajeHandler Manejador de mensajes que contiene los textos traducidos.
     */

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.precio"));
        lblEliminarProducto.setText(mensajeHandler.get("producto.eliminar.titulo"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeHandler.get("boton.eliminar"));

        setTitle(mensajeHandler.get("producto.eliminar.titulo"));
    }

    /**
     * Carga los íconos de los botones desde los recursos y los asigna a los
     * botones correspondientes, redimensionándolos para que se ajusten
     * visualmente al diseño de la interfaz.
     * En caso de error al cargar el recurso, se imprime un mensaje de advertencia.
     */
    private void imagenIcon() {
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

    }

    /**
     * Cambia dinámicamente el idioma de todos los componentes de texto
     * visibles en la ventana, utilizando el manejador de internacionalización
     * provisto. Este método permite actualizar la interfaz cuando el usuario
     * selecciona un nuevo idioma desde el menú principal.
     *
     * @param mensajeHandler Manejador de mensajes internacionalizados que contiene los textos en el idioma seleccionado.
     */
    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("producto.eliminar.titulo"));

        lblEliminarProducto.setText(mensajeHandler.get("producto.eliminar.titulo"));
        lblCodigo.setText(mensajeHandler.get("producto.eliminar.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.eliminar.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.eliminar.precio"));

        btnBuscar.setText(mensajeHandler.get("producto.eliminar.buscar"));
        btnEliminar.setText(mensajeHandler.get("producto.eliminar.eliminar"));
    }

    /**
     * Retorna el panel principal de la ventana.
     *
     * @return panel principal de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Retorna el campo de texto donde se ingresa el código del producto.
     *
     * @return campo de texto del código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Retorna el campo de texto donde se muestra o ingresa el nombre del producto.
     *
     * @return campo de texto del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Retorna el campo de texto donde se muestra o ingresa el precio del producto.
     *
     * @return campo de texto del precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Retorna el botón para eliminar el producto.
     *
     * @return botón de eliminar.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Retorna el botón para buscar un producto por su código.
     *
     * @return botón de buscar.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Establece el panel principal de la ventana.
     *
     * @param panelPrincipal el panel principal a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el campo de texto para el código del producto.
     *
     * @param txtCodigo campo de texto para el código.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Establece el campo de texto para el nombre del producto.
     *
     * @param txtNombre campo de texto para el nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de texto para el precio del producto.
     *
     * @param txtPrecio campo de texto para el precio.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Establece el botón para eliminar el producto.
     *
     * @param btnEliminar botón de eliminar.
     */
    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    /**
     * Establece el botón para buscar un producto.
     *
     * @param btnBuscar botón de buscar.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece el manejador de mensajes para la internacionalización.
     *
     * @param mensajeHandler manejador de mensajes.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Muestra un mensaje en pantalla mediante un cuadro de diálogo.
     *
     * @param mensaje el texto del mensaje que se desea mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia los campos de texto de código, nombre y precio del producto.
     * Este método es útil luego de eliminar o buscar un producto.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }
}
