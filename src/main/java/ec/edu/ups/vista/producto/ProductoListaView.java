package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * Clase que representa la vista para listar productos dentro del sistema.
 *
 * Esta ventana interna (`JInternalFrame`) permite visualizar todos los productos registrados
 * en una tabla con soporte para internacionalización y visualización mejorada.
 * La clase sigue el patrón MVC, actuando como la vista en la arquitectura del sistema.
 *
 * Funcionalidades:
 * - Mostrar lista de productos en una tabla.
 * - Cargar íconos personalizados en botones.
 * - Cambiar el idioma de forma dinámica con soporte para archivos `.properties`.
 *
 * @author Keyra
 * @version 1.0
 * @since 2025-07-17
 */
public class ProductoListaView extends JInternalFrame {

    /**
     * Campo de texto para ingresar el nombre del producto a buscar.
     */
    private JTextField txtBuscar;

    /**
     * Tabla donde se muestran los productos listados.
     */
    private JTable tblProductos;

    /**
     * Panel principal que contiene todos los componentes visuales de la vista.
     */
    private JPanel panelPrincipal;

    /**
     * Botón que permite listar todos los productos registrados.
     */
    private JButton btnListar;

    /**
     * Etiqueta que indica el campo de búsqueda por nombre.
     */
    private JLabel lblNombre;

    /**
     * Botón que ejecuta la acción de búsqueda de un producto por nombre.
     */
    private JButton btnBuscar;

    /**
     * Modelo de tabla utilizado para manejar dinámicamente los datos de la tabla de productos.
     */
    private DefaultTableModel modelo;

    /**
     * Manejador de internacionalización que permite cambiar los textos de la interfaz
     * de forma dinámica según el idioma seleccionado.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase ProductoListaView.
     * Inicializa la interfaz gráfica para listar productos, configurando el panel principal,
     * los componentes visuales y la internacionalización dinámica.
     *
     * @param mensajeHandler Objeto encargado de manejar los mensajes internacionalizados.
     */
    public ProductoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.producto.lista"), true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object [] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Carga y asigna iconos redimensionados a los botones de la vista, específicamente a los botones
     * de "Buscar" y "Listar". Los iconos se obtienen del paquete de recursos del proyecto.
     * Si el recurso no se encuentra, se imprime un mensaje de error en la consola.
     */
    private void imagenIcon() {
        /**
         * Redimensionar icono "Listar"
         */
        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon iconBtnListar = new ImageIcon(btListar);
            Image imgListar = iconBtnListar.getImage();  // Convierte ImageIcon a Image
            Image newImgListar = imgListar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnListar = new ImageIcon(newImgListar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnListar.setIcon(iconBtnListar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
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
     * Actualiza los textos visibles de la interfaz utilizando el manejador de internacionalización.
     * Este método es útil para cambiar dinámicamente el idioma de los componentes como etiquetas,
     * botones y título de la ventana.
     *
     * @param mensajeHandler Objeto que proporciona los textos traducidos según el idioma actual.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("producto.nombre"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnListar.setText(mensajeHandler.get("boton.listar"));

        setTitle(mensajeHandler.get("producto.lista.titulo"));
    }

    /**
     * Retorna la etiqueta del nombre del producto.
     * @return JLabel con el nombre del producto.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Retorna el botón para buscar productos.
     * @return JButton para la acción de búsqueda.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Retorna el manejador de internacionalización.
     * @return MensajeInternacionalizacionHandler usado por la vista.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    /**
     * Retorna el campo de texto utilizado para ingresar el término de búsqueda.
     * @return JTextField del buscador.
     */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    /**
     * Retorna la tabla que muestra los productos.
     * @return JTable con los productos listados.
     */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /**
     * Retorna el panel principal de la vista.
     * @return JPanel de la vista.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Retorna el botón para listar productos.
     * @return JButton para la acción de listar.
     */
    public JButton getBtnListar() {
        return btnListar;
    }

    /**
     * Retorna el modelo de tabla utilizado en la vista.
     * @return DefaultTableModel de la tabla de productos.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Establece la etiqueta del nombre del producto.
     * @param lblNombre JLabel a asignar.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Establece el botón para buscar productos.
     * @param btnBuscar JButton para búsqueda.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece el campo de texto del buscador.
     * @param txtBuscar JTextField a asignar.
     */
    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    /**
     * Establece la tabla donde se mostrarán los productos.
     * @param tblProductos JTable a asignar.
     */
    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal JPanel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el botón de listar productos.
     * @param btnListar JButton de listar.
     */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /**
     * Establece el modelo de la tabla.
     * @param modelo Modelo DefaultTableModel para la tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Asigna el manejador de internacionalización y actualiza los textos.
     * @param mensajeHandler Handler de mensajes traducidos.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        actualizarTextos(mensajeHandler);
    }

    /**
     * Carga una lista de productos en la tabla.
     * Limpia previamente el modelo de tabla antes de insertar los nuevos registros.
     *
     * @param listaProductos Lista de objetos Producto a mostrar.
     */
    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0); // Limpiar tabla
        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }
    }
}
