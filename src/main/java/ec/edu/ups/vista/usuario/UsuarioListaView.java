package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

/**
 * Clase UsuarioListaView
 * ----------------------
 * Esta clase representa la ventana interna (JInternalFrame) encargada de listar a los usuarios registrados
 * en el sistema. Permite visualizar los datos en una tabla, realizar búsquedas filtradas por nombre,
 * correo, rol o código, y admite soporte para internacionalización.
 *
 * Funcionalidades principales:
 * - Carga dinámica de usuarios en una JTable.
 * - Búsqueda de usuarios por distintos criterios.
 * - Soporte de internacionalización para cambiar el idioma de la interfaz.
 *
 * Esta vista forma parte del módulo de gestión de usuarios del sistema de carrito de compras.
 *
 * @author Keyra
 */
public class UsuarioListaView extends JInternalFrame {

    /**
     * Tabla que muestra la lista de usuarios registrados en el sistema.
     */
    private JTable tablaUsuarios;

    /**
     * ComboBox para seleccionar el tipo de filtro (nombre, correo, rol, código) al realizar la búsqueda de usuarios.
     */
    private JComboBox<String> cbxFiltro;

    /**
     * Botón para listar todos los usuarios en la tabla.
     */
    private JButton btnListar;

    /**
     * Botón para cerrar la ventana actual.
     */
    private JButton btnCerrar;

    /**
     * Campo de texto donde se ingresa el valor a buscar (nombre, correo, etc.) según el filtro seleccionado.
     */
    private JTextField txtNombre;

    /**
     * Etiqueta que indica el campo de búsqueda.
     */
    private JLabel lblNombre;

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     */
    private JPanel panelPrincipal;

    /**
     * Botón para ejecutar la búsqueda del usuario según el filtro y el texto ingresado.
     */
    private JButton btnBuscar;

    /**
     * Modelo de tabla que maneja la estructura de filas y columnas de la JTable de usuarios.
     */
    private DefaultTableModel modelo;

    /**
     * Manejador de internacionalización para cambiar dinámicamente los textos según el idioma seleccionado.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase UsuarioListaView.
     * Inicializa los componentes de la ventana, establece propiedades de la ventana interna,
     * configura el contenido principal, ajusta tamaño y permite la internacionalización de textos.
     *
     * @param mensajeHandler El manejador de internacionalización para mostrar los textos en el idioma seleccionado.
     */
    public UsuarioListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super();
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        cbxFiltro.addItem("Nombre");
        cbxFiltro.addItem("Correo");
        cbxFiltro.addItem("Rol");
        cbxFiltro.addItem("Código");

        modelo = new DefaultTableModel();
        tablaUsuarios.setModel(modelo);

        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Actualiza todos los textos visibles de la interfaz gráfica según el idioma
     * proporcionado por el {@link MensajeInternacionalizacionHandler}.
     *
     * @param mensajeHandler El manejador que contiene las traducciones para los distintos idiomas.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        btnListar.setText(mensajeHandler.get("boton.listar"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnCerrar.setText(mensajeHandler.get("boton.cerrar"));
        setTitle(mensajeHandler.get("usuario.lista.titulo"));
    }

    /**
     * Carga y establece los íconos en los botones de la ventana, redimensionándolos
     * para que se ajusten correctamente al diseño de la interfaz.
     * Los íconos deben estar ubicados en el paquete de recursos "imagenes".
     */
    private void imagenIcon() {
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

        URL btCerrar = LoginView.class.getClassLoader().getResource("imagenes/cerrar.png");
        if (btCerrar != null) {
            ImageIcon iconBtnCerrar = new ImageIcon(btCerrar);
            Image imgCerrar = iconBtnCerrar.getImage();  // Convierte ImageIcon a Image
            Image newImgCerrar = imgCerrar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnCerrar = new ImageIcon(newImgCerrar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnCerrar.setIcon(iconBtnCerrar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Cerrar");
        }

    }

    /**
     * Obtiene la tabla de usuarios.
     * @return La tabla que muestra los usuarios.
     */
    public JTable getTblUsuarios() {
        return tablaUsuarios;
    }

    /**
     * Obtiene el comboBox para seleccionar el filtro de búsqueda.
     * @return ComboBox con los tipos de filtro.
     */
    public JComboBox<String> getCbxFiltro() {
        return cbxFiltro;
    }

    /**
     * Obtiene el botón de listar (mal nombrado como btnBuscar).
     * @return Botón para listar usuarios.
     */
    public JButton getBtnBuscar() {
        return btnListar;
    }

    /**
     * Obtiene el botón para cerrar la ventana.
     * @return Botón de cerrar.
     */
    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    /**
     * Obtiene el campo de texto para el nombre a buscar.
     * @return Campo de texto del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Obtiene el modelo de la tabla de usuarios.
     * @return Modelo de tabla.
     */
    public DefaultTableModel getTableModel() {
        return modelo;
    }

    /**
     * Obtiene el modelo de la tabla.
     * @return El modelo usado por la tabla de usuarios.
     */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /**
     * Obtiene la tabla completa de usuarios.
     * @return Componente JTable que muestra los datos.
     */
    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    /**
     * Obtiene la etiqueta del nombre.
     * @return JLabel del campo nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Obtiene el panel principal del formulario.
     * @return Panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene el manejador de internacionalización actual.
     * @return Instancia de MensajeInternacionalizacionHandler.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    /**
     * Establece el componente JTable que muestra los usuarios.
     * @param tablaUsuarios La tabla de usuarios.
     */
    public void setTablaUsuarios(JTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    /**
     * Establece el comboBox para los filtros.
     * @param cbxFiltro El comboBox de filtros.
     */
    public void setCbxFiltro(JComboBox<String> cbxFiltro) {
        this.cbxFiltro = cbxFiltro;
    }

    /**
     * Establece el botón para buscar (internamente btnListar).
     * @param btnBuscar Botón de búsqueda.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnListar = btnBuscar;
    }

    /**
     * Establece el botón de cerrar.
     * @param btnCerrar Botón de cerrar.
     */
    public void setBtnCerrar(JButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }

    /**
     * Establece el campo de texto del nombre.
     * @param txtNombre Campo de texto de nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece la etiqueta para el nombre.
     * @param lblNombre Etiqueta correspondiente al nombre.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Establece el panel principal.
     * @param panelPrincipal El panel principal del formulario.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el modelo de la tabla.
     * @param modelo Modelo de tabla.
     */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Establece el manejador de internacionalización y actualiza los textos.
     * @param mensajeHandler El manejador de mensajes.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Muestra un mensaje emergente en pantalla.
     * @param mensaje El mensaje a mostrar en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

}
