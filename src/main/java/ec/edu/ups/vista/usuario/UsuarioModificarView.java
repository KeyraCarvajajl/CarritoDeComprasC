package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase UsuarioModificarView
 * ---------------------------
 * Ventana interna del sistema que permite modificar los datos de un usuario existente.
 * Esta interfaz forma parte del módulo de gestión de usuarios y se encarga de capturar
 * los nuevos datos del usuario como nombre, correo electrónico, fecha de nacimiento,
 * teléfono y contraseña, permitiendo su actualización.
 *
 * <p>La clase utiliza componentes de Swing y es compatible con el sistema de
 * internacionalización dinámica mediante {@link MensajeInternacionalizacionHandler}.</p>
 *
 * Funcionalidades principales:
 * - Buscar usuario por nombre o código.
 * - Mostrar datos actuales del usuario.
 * - Permitir la modificación de datos personales.
 * - Validar coincidencia de contraseñas antes de guardar.
 *
 * @author Keyra
 * @version 1.0
 * @since 2025-07-17
 */

public class UsuarioModificarView extends JInternalFrame {

    /**
     * Campo de texto para ingresar el nombre o identificador del usuario a buscar.
     */
    private JTextField txtBuscarUsuario;

    /**
     * Panel principal que contiene todos los componentes gráficos de la ventana.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta que indica al usuario que debe ingresar un nombre o identificador para buscar.
     */
    private JLabel lblUsuarioBuscar;

    /**
     * Botón para ejecutar la acción de búsqueda de usuario.
     */
    private JButton btnBuscar;

    /**
     * Etiqueta que indica el campo del nombre del usuario.
     */
    private JLabel lblNombre;

    /**
     * Campo de texto para visualizar o editar el nombre del usuario.
     */
    private JTextField txtNombre;

    /**
     * Campo de texto formateado para la fecha de nacimiento del usuario.
     */
    private JFormattedTextField txtFecha;

    /**
     * Campo de texto para visualizar o editar el correo electrónico del usuario.
     */
    private JTextField txtCorreo;

    /**
     * Etiqueta que indica el campo del correo electrónico.
     */
    private JLabel lblCorreo;

    /**
     * Etiqueta que indica el campo del número telefónico.
     */
    private JLabel lblTelefono;

    /**
     * Campo de texto para visualizar o editar el número de teléfono del usuario.
     */
    private JTextField txtTelefono;

    /**
     * Botón para confirmar la modificación de los datos del usuario.
     */
    private JButton btnModificarUsuario;

    /**
     * Botón para cancelar la operación y cerrar o limpiar la ventana.
     */
    private JButton btnCancelar;

    /**
     * Etiqueta que indica el campo de fecha de nacimiento.
     */
    private JLabel lblFecha;

    /**
     * Manejador de mensajes para la internacionalización dinámica de los textos en pantalla.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la ventana interna UsuarioModificarView.
     * Inicializa los componentes de la interfaz gráfica, establece el título internacionalizado
     * y configura los textos e iconos visuales mediante el manejador de internacionalización.
     *
     * @param mensajeHandler Objeto utilizado para obtener los textos en diferentes idiomas.
     */
    public UsuarioModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
        actualizarTextos(mensajeHandler);
        limpiarCampos();
        imagenIcon();
    }

    /**
     * Actualiza los textos de todos los componentes visibles de la interfaz gráfica
     * utilizando las claves definidas en el manejador de internacionalización.
     *
     * @param mensajeHandler Objeto que gestiona la carga de textos internacionalizados.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuarioBuscar.setText(mensajeHandler.get("usuario.buscar.usuario"));
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        lblFecha.setText(mensajeHandler.get("usuario.fecha"));
        lblCorreo.setText(mensajeHandler.get("usuario.correo"));
        lblTelefono.setText(mensajeHandler.get("usuario.telefono"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnModificarUsuario.setText(mensajeHandler.get("boton.modificar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
    }

    /**
     * Carga y asigna los iconos a los botones de la interfaz.
     * Redimensiona las imágenes para que se ajusten correctamente al diseño.
     * Si ocurre un error al cargar alguno de los iconos, se imprime un mensaje en consola.
     */
    private void imagenIcon() {
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

        URL btGuardar = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (btGuardar != null) {
            ImageIcon iconBtnGuardar = new ImageIcon(btGuardar);
            Image imgGuardar = iconBtnGuardar.getImage();  // Convierte ImageIcon a Image
            Image newImgGuardar = imgGuardar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnGuardar = new ImageIcon(newImgGuardar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnModificarUsuario.setIcon(iconBtnGuardar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
        }

        URL btCancelar = LoginView.class.getClassLoader().getResource("imagenes/cancelar.png");
        if (btCancelar != null) {
            ImageIcon iconBtnCancelar = new ImageIcon(btCancelar);
            Image imgCancelar = iconBtnCancelar.getImage();  // Convierte ImageIcon a Image
            Image newImgCancelar = imgCancelar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnCancelar = new ImageIcon(newImgCancelar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnCancelar.setIcon(iconBtnCancelar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Cancelar");
        }

    }

    /**
     * Obtiene el campo de texto para ingresar el nombre de usuario a buscar.
     * @return JTextField del nombre de usuario.
     */
    public JTextField getTxtBuscarUsuario() {
        return txtBuscarUsuario;
    }

    /**
     * Obtiene el panel principal de la ventana.
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Obtiene la etiqueta del campo de búsqueda de usuario.
     * @return JLabel con el texto del campo de búsqueda.
     */
    public JLabel getLblUsuarioBuscar() {
        return lblUsuarioBuscar;
    }

    /**
     * Obtiene el botón para buscar usuario.
     * @return JButton de búsqueda.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Obtiene la etiqueta del campo nombre.
     * @return JLabel con el texto del nombre.
     */
    public JLabel getLblNombre() {
        return lblNombre;
    }

    /**
     * Obtiene el campo de texto del nombre.
     * @return JTextField del nombre.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Obtiene el campo de texto para la fecha de nacimiento.
     * @return JFormattedTextField para la fecha.
     */
    public JFormattedTextField getTxtFecha() {
        return txtFecha;
    }

    /**
     * Obtiene el campo de texto del correo electrónico.
     * @return JTextField del correo.
     */
    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    /**
     * Obtiene la etiqueta del correo electrónico.
     * @return JLabel con el texto del correo.
     */
    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    /**
     * Obtiene la etiqueta del teléfono.
     * @return JLabel del teléfono.
     */
    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    /**
     * Obtiene el campo de texto del teléfono.
     * @return JTextField del teléfono.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * Obtiene el botón para modificar el usuario.
     * @return JButton de modificación.
     */
    public JButton getBtnModificarUsuario() {
        return btnModificarUsuario;
    }

    /**
     * Obtiene el botón para cancelar la operación.
     * @return JButton de cancelar.
     */
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * Obtiene la etiqueta de la fecha de nacimiento.
     * @return JLabel de la fecha.
     */
    public JLabel getLblFecha() {
        return lblFecha;
    }

    /**
     * Obtiene el manejador de internacionalización.
     * @return Objeto MensajeInternacionalizacionHandler.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    /**
     * Establece el campo de texto del usuario a buscar.
     * @param txtBuscarUsuario JTextField del usuario.
     */
    public void setTxtBuscarUsuario(JTextField txtBuscarUsuario) {
        this.txtBuscarUsuario = txtBuscarUsuario;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal JPanel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece la etiqueta del campo de búsqueda de usuario.
     * @param lblUsuarioBuscar JLabel de búsqueda.
     */
    public void setLblUsuarioBuscar(JLabel lblUsuarioBuscar) {
        this.lblUsuarioBuscar = lblUsuarioBuscar;
    }

    /**
     * Establece el botón para buscar usuario.
     * @param btnBuscar JButton de búsqueda.
     */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /**
     * Establece la etiqueta del nombre.
     * @param lblNombre JLabel del nombre.
     */
    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    /**
     * Establece el campo de texto del nombre.
     * @param txtNombre JTextField del nombre.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de fecha de nacimiento.
     * @param txtFecha JFormattedTextField de la fecha.
     */
    public void setTxtFecha(JFormattedTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    /**
     * Establece el campo de correo electrónico.
     * @param txtCorreo JTextField del correo.
     */
    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    /**
     * Establece la etiqueta del correo electrónico.
     * @param lblCorreo JLabel del correo.
     */
    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    /**
     * Establece la etiqueta del teléfono.
     * @param lblTelefono JLabel del teléfono.
     */
    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    /**
     * Establece el campo de texto del teléfono.
     * @param txtTelefono JTextField del teléfono.
     */
    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    /**
     * Establece el botón de modificar usuario.
     * @param btnModificarUsuario JButton de modificación.
     */
    public void setBtnModificarUsuario(JButton btnModificarUsuario) {
        this.btnModificarUsuario = btnModificarUsuario;
    }

    /**
     * Establece el botón de cancelar.
     * @param btnCancelar JButton de cancelar.
     */
    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    /**
     * Establece la etiqueta de la fecha de nacimiento.
     * @param lblFecha JLabel de la fecha.
     */
    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    /**
     * Establece el manejador de internacionalización.
     * @param mensajeHandler Objeto de tipo MensajeInternacionalizacionHandler.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    /**
     * Muestra un mensaje en una ventana emergente.
     * @param mensaje El mensaje a mostrar al usuario.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos de entrada del formulario de modificación.
     */
    public void limpiarCampos() {
        txtBuscarUsuario.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

}