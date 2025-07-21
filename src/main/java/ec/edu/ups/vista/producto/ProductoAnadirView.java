package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

/**
 * Ventana interna de la aplicación que permite añadir nuevos productos al sistema.
 * <p>
 * El usuario puede ingresar los datos del producto, como código, nombre, precio y cantidad,
 * y guardarlos a través de una interfaz gráfica amigable.
 * <p>
 * Esta clase forma parte del módulo de gestión de productos y extiende {@link javax.swing.JInternalFrame}
 * para integrarse con la interfaz MDI de la aplicación.
 * También incluye soporte para internacionalización dinámica mediante {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}
 * y personalización visual con íconos.
 *
 * <p><b>Características:</b></p>
 * <ul>
 *   <li>Ingreso de información detallada de productos</li>
 *   <li>Validaciones visuales y estructura ordenada del formulario</li>
 *   <li>Internacionalización dinámica</li>
 *   <li>Íconos personalizados para los botones</li>
 * </ul>
 *
 * @author Keyra
 */

public class ProductoAnadirView extends JInternalFrame {

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     * Sirve como contenedor del formulario de ingreso de productos.
     */
    private JPanel panelPrincipal;

    /**
     * Campo de texto donde se ingresa el precio del producto.
     */
    private JTextField txtPrecio;

    /**
     * Campo de texto donde se ingresa el nombre del producto.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto donde se ingresa el código único del producto.
     */
    private JTextField txtCodigo;

    /**
     * Botón para aceptar y guardar el nuevo producto ingresado.
     */
    private JButton btnAceptar;

    /**
     * Botón para limpiar todos los campos del formulario.
     */
    private JButton btnLimpiar;

    /**
     * Etiqueta que indica el campo del código del producto.
     */
    private JLabel lblCodigo;

    /**
     * Etiqueta que indica el campo del nombre del producto.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta que indica el campo del precio del producto.
     */
    private JLabel lblPrecio;

    /**
     * Manejador de internacionalización que permite cambiar los textos de la interfaz
     * según el idioma seleccionado.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor que inicializa la ventana de añadir producto.
     * <p>
     * Configura la interfaz gráfica, establece las propiedades del {@code JInternalFrame},
     * aplica los textos internacionalizados a los componentes, y carga los íconos personalizados
     * para los botones "Aceptar" y "Limpiar".
     *
     * @param mensajeHandler Manejador de internacionalización que proporciona los textos traducidos
     *                       a los diferentes idiomas soportados por el sistema.
     */
    public ProductoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setTitle("Datos del producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        imagenIcon();
        actualizarTextos(mensajeHandler);
    }

    /**
     * Carga y asigna íconos personalizados a los botones "Aceptar" y "Limpiar".
     * <p>
     * Las imágenes se obtienen desde la carpeta de recursos del proyecto
     * y se redimensionan a 30x30 píxeles para mantener la coherencia visual en la interfaz.
     * <p>
     * Si alguno de los íconos no se encuentra, se muestra un mensaje de error en la consola.
     */
    private void imagenIcon() {
        /**
         * Redimensionar icono "Aceptar"
         */
        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            Image imgAceptar = iconBtnAceptar.getImage();  // Convierte ImageIcon a Image
            Image newImgAceptar = imgAceptar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnAceptar = new ImageIcon(newImgAceptar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnAceptar.setIcon(iconBtnAceptar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar");
        }

        /**
         * Redimensionar icono "Limpiar"
         */
        URL btLimpiar = LoginView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (btLimpiar != null) {
            ImageIcon iconBtnLimpiar = new ImageIcon(btLimpiar);
            Image imgLimpiar = iconBtnLimpiar.getImage();  // Convierte ImageIcon a Image
            Image newImgLimpiar = imgLimpiar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnLimpiar = new ImageIcon(newImgLimpiar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnLimpiar.setIcon(iconBtnLimpiar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Limpiar");
        }
    }

    /**
     * Actualiza dinámicamente los textos visibles de la ventana utilizando
     * el manejador de internacionalización proporcionado.
     * <p>
     * Se modifican las etiquetas, los textos de los botones y el título de la ventana
     * en función del idioma seleccionado.
     *
     * @param mensajeHandler Manejador que proporciona los textos traducidos desde archivos de propiedades.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.precio"));

        btnAceptar.setText(mensajeHandler.get("boton.aceptar"));
        btnLimpiar.setText(mensajeHandler.get("boton.limpiar"));

        setTitle(mensajeHandler.get("producto.anadir.titulo")); // Solo si usas JInternalFrame o JFrame
    }


    //--------------- Getters
    /**
     * Retorna el panel principal de la vista.
     * @return Panel principal que contiene los componentes de la interfaz.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Retorna el campo de texto para el precio del producto.
     * @return Campo de texto para ingresar el precio.
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * Retorna el campo de texto para el nombre del producto.
     * @return Campo de texto para ingresar el nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Retorna el campo de texto para el código del producto.
     * @return Campo de texto para ingresar el código.
     */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /**
     * Retorna el botón para aceptar y guardar el producto.
     * @return Botón "Aceptar".
     */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /**
     * Retorna el botón para limpiar los campos del formulario.
     * @return Botón "Limpiar".
     */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    //------------------ Setters
    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal Panel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el campo de texto para el precio del producto.
     * @param txtPrecio Campo de texto a asignar.
     */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /**
     * Establece el campo de texto para el nombre del producto.
     * @param txtNombre Campo de texto a asignar.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de texto para el código del producto.
     * @param txtCodigo Campo de texto a asignar.
     */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /**
     * Establece el botón para aceptar el registro del producto.
     * @param btnAceptar Botón a asignar.
     */
    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    /**
     * Establece el botón para limpiar los campos del formulario.
     * @param btnLimpiar Botón a asignar.
     */
    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /**
     * Establece el manejador de internacionalización para la vista.
     * @param mensajeHandler Manejador de mensajes a asignar.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje informativo o de advertencia.
     * @param mensaje Mensaje que se desea mostrar al usuario.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario: código, nombre y precio.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra por consola todos los productos de una lista (modo prueba o depuración).
     * @param productos Lista de productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}