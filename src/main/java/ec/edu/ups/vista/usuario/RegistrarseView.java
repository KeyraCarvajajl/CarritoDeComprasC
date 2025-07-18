package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase {@code RegistrarseView} representa la interfaz gráfica para el registro de nuevos usuarios
 * en el sistema. Permite al usuario ingresar sus datos personales, seleccionar preguntas de seguridad
 * y proporcionar respuestas, necesarias para la recuperación de contraseña.
 *
 * <p>Esta clase extiende {@code JInternalFrame} y se integra dentro del contenedor MDI del sistema.</p>
 *
 * <p>Incluye soporte para internacionalización dinámica mediante la clase
 * {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}, así como iconos personalizados
 * para una mejor experiencia visual.</p>
 *
 * <p><b>Funcionalidades principales:</b></p>
 * <ul>
 *   <li>Registro de usuario con campos como nombre, correo, teléfono, cédula y contraseña.</li>
 *   <li>Selección de preguntas de seguridad y respuestas.</li>
 *   <li>Internacionalización dinámica y soporte para cambio de idioma.</li>
 *   <li>Iconos personalizados en los botones.</li>
 * </ul>
 *
 * @author Keyra
 */

public class RegistrarseView extends JInternalFrame {

    /**
     * Panel principal que contiene todos los elementos de la vista de registro.
     */
    private JPanel panelPrincipal;

    /**
     * Campo de texto para ingresar el nombre completo del usuario.
     */
    private JTextField txtNombreCompleto;

    /**
     * Campo de texto para ingresar el nombre de usuario (usualmente cédula).
     */
    private JTextField txtUsuario;

    /**
     * Campo de contraseña para ingresar la contraseña del usuario.
     */
    private JPasswordField txtContrasenia;

    /**
     * Campo de contraseña para confirmar la contraseña ingresada.
     */
    private JPasswordField txtConfirmarContrasenia;

    /**
     * Campo de texto formateado para ingresar la fecha de nacimiento del usuario.
     */
    private JFormattedTextField jFormatedFecha;

    /**
     * Campo de texto para ingresar el correo electrónico del usuario.
     */
    private JTextField txtCorreoElectronico;

    /**
     * Campo de texto para ingresar el número de teléfono del usuario.
     */
    private JTextField txtTelefono;

    /**
     * ComboBox para seleccionar la primera pregunta de seguridad.
     */
    private JComboBox cbxPregunta1;

    /**
     * Campo de texto para ingresar la respuesta a la primera pregunta de seguridad.
     */
    private JTextField txtPregunta1;

    /**
     * ComboBox para seleccionar la segunda pregunta de seguridad.
     */
    private JComboBox cbxPregunta2;

    /**
     * Campo de texto para ingresar la respuesta a la segunda pregunta de seguridad.
     */
    private JTextField txtPregunta2;

    /**
     * ComboBox para seleccionar la tercera pregunta de seguridad.
     */
    private JComboBox cbxPregunta3;

    /**
     * Campo de texto para ingresar la respuesta a la tercera pregunta de seguridad.
     */
    private JTextField txtPregunta3;

    /**
     * Botón para registrar al usuario.
     */
    private JButton btnRegistro;

    /**
     * Botón para cancelar el registro y cerrar la ventana.
     */
    private JButton btnCancelar;


    /**
     * Etiqueta para el campo de nombre completo.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta para el campo de nombre de usuario.
     */
    private JLabel lblUsuario;

    /**
     * Etiqueta para el campo de contraseña.
     */
    private JLabel lblContrasenia;

    /**
     * Etiqueta para el campo de confirmar contraseña.
     */
    private JLabel lblConfirmarContrasenia;

    /**
     * Etiqueta para el campo de fecha de nacimiento.
     */
    private JLabel lblFechaDeNacimiento;

    /**
     * Etiqueta para el campo de correo electrónico.
     */
    private JLabel lblCorreoElectronico;

    /**
     * Etiqueta para el campo de teléfono.
     */
    private JLabel lblTelefono;

    /**
     * Etiqueta principal con el título de la vista de registro.
     */
    private JLabel lblRegistrar;

    /**
     * Etiqueta para la primera pregunta de seguridad.
     */
    private JLabel lblPregunta1;

    /**
     * Etiqueta para la segunda pregunta de seguridad.
     */
    private JLabel lblPregunta2;

    /**
     * Etiqueta para la tercera pregunta de seguridad.
     */
    private JLabel lblPregunta3;

    /**
     * Etiqueta para el bloque de preguntas de seguridad.
     */
    private JLabel lblPreuntasSeguridad;

    /**
     * Manejador de internacionalización que permite cambiar dinámicamente los textos de la vista.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la clase RegistrarseView.
     *
     * Crea una nueva ventana interna (JInternalFrame) para registrar usuarios en el sistema.
     * Establece el panel principal, la configuración de internacionalización, el tamaño y
     * los iconos de los botones. Inicializa además todos los componentes gráficos necesarios
     * para el proceso de registro, incluyendo campos de texto, etiquetas, preguntas de seguridad,
     * botones y paneles.
     *
     * @param mensajeHandler El manejador de internacionalización que proporciona los textos
     *        traducidos según el idioma seleccionado.
     */
    public RegistrarseView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setTitle(mensajeHandler.get("usuario.view.registrar.titulo"));

        actualizarTextos(mensajeHandler);
        cargarPreguntas();
        imagenIcon();
    }

    /**
     * Establece los íconos personalizados en los botones de la interfaz de registro.
     *
     * Carga y redimensiona las imágenes de los recursos para los botones
     * de registro y cancelación. Si las imágenes no se encuentran, se imprime
     * un mensaje de error por consola.
     *
     * Las imágenes deben estar ubicadas en el paquete de recursos, típicamente
     * en la ruta: src/main/resources/imagenes.
     */
    private void imagenIcon() {
        URL btRegistro = LoginView.class.getClassLoader().getResource("imagenes/registro.png");
        if (btRegistro != null) {
            ImageIcon iconBtnRegistro = new ImageIcon(btRegistro);
            Image imgRegistro = iconBtnRegistro.getImage();  // Convierte ImageIcon a Image
            Image newImgRegistro = imgRegistro.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnRegistro = new ImageIcon(newImgRegistro);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnRegistro.setIcon(iconBtnRegistro);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Registro");
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
     * Actualiza todos los textos visibles de la ventana con base en el idioma seleccionado.
     *
     * Utiliza el manejador de internacionalización para establecer el texto correspondiente
     * a cada etiqueta, botón y el título de la ventana. Este método permite cambiar dinámicamente
     * el idioma de la interfaz sin necesidad de reiniciar la aplicación.
     *
     * @param mensajeHandler El manejador de internacionalización utilizado para obtener
     *        los textos traducidos.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("registro.nombre"));
        lblUsuario.setText(mensajeHandler.get("registro.usuario"));
        lblContrasenia.setText(mensajeHandler.get("registro.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("registro.confirmar"));
        lblFechaDeNacimiento.setText(mensajeHandler.get("registro.fecha"));
        lblCorreoElectronico.setText(mensajeHandler.get("registro.correo"));
        lblTelefono.setText(mensajeHandler.get("registro.telefono"));
        lblRegistrar.setText(mensajeHandler.get("registro.titulo"));

        lblPreuntasSeguridad.setText(mensajeHandler.get("registro.seguridad"));
        lblPregunta1.setText(mensajeHandler.get("registro.pregunta1"));
        lblPregunta2.setText(mensajeHandler.get("registro.pregunta2"));
        lblPregunta3.setText(mensajeHandler.get("registro.pregunta3"));

        btnRegistro.setText(mensajeHandler.get("boton.registrar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));

        setTitle(mensajeHandler.get("registro.titulo"));
    }

    /**
     * Devuelve el campo de texto para el nombre completo.
     * @return JTextField del nombre completo.
     */
    public JTextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    /**
     * Devuelve el campo de texto del nombre de usuario.
     * @return JTextField del nombre de usuario.
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * Devuelve el campo de contraseña.
     * @return JPasswordField de la contraseña.
     */
    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    /**
     * Devuelve el campo para confirmar la contraseña.
     * @return JPasswordField de confirmación.
     */
    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    /**
     * Devuelve el campo de texto con formato para la fecha de nacimiento.
     * @return JFormattedTextField para fecha.
     */
    public JFormattedTextField getTxtFechaNacimiento() {
        return jFormatedFecha;
    }

    /**
     * Devuelve el campo de texto del correo electrónico.
     * @return JTextField del correo electrónico.
     */
    public JTextField getTxtCorreo() {
        return txtCorreoElectronico;
    }

    /**
     * Devuelve el campo de texto del número de teléfono.
     * @return JTextField del teléfono.
     */
    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    /**
     * Devuelve el comboBox para la primera pregunta de seguridad.
     * @return JComboBox de pregunta 1.
     */
    public JComboBox getCbxPregunta1() {
        return cbxPregunta1;
    }

    /**
     * Devuelve el comboBox para la segunda pregunta de seguridad.
     * @return JComboBox de pregunta 2.
     */
    public JComboBox getCbxPregunta2() {
        return cbxPregunta2;
    }

    /**
     * Devuelve el comboBox para la tercera pregunta de seguridad.
     * @return JComboBox de pregunta 3.
     */
    public JComboBox getCbxPregunta3() {
        return cbxPregunta3;
    }

    /**
     * Devuelve el campo de texto para la respuesta a la primera pregunta.
     * @return JTextField de respuesta 1.
     */
    public JTextField getTxtPregunta1() {
        return txtPregunta1;
    }

    /**
     * Devuelve el campo de texto para la respuesta a la segunda pregunta.
     * @return JTextField de respuesta 2.
     */
    public JTextField getTxtPregunta2() {
        return txtPregunta2;
    }

    /**
     * Devuelve el campo de texto para la respuesta a la tercera pregunta.
     * @return JTextField de respuesta 3.
     */
    public JTextField getTxtPregunta3() {
        return txtPregunta3;
    }

    /**
     * Devuelve el botón de registro.
     * @return JButton de registro.
     */
    public JButton getBtnRegistro() {
        return btnRegistro;
    }

    /**
     * Devuelve el botón de cancelar.
     * @return JButton de cancelar.
     */
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * Muestra un mensaje emergente en la ventana actual.
     *
     * @param mensaje Texto del mensaje que se desea mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos de entrada del formulario de registro.
     *
     * Restablece el contenido de los campos de texto, contraseñas,
     * fecha, correo, teléfono y respuestas de seguridad.
     */
    public void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
        jFormatedFecha.setText("");
        txtCorreoElectronico.setText("");
        txtTelefono.setText("");
        txtPregunta1.setText("");
        txtPregunta2.setText("");
        txtPregunta3.setText("");
    }

    /**
     * Carga las preguntas de seguridad predeterminadas en los tres JComboBox.
     *
     * Este método agrega una lista de preguntas comunes de seguridad a los
     * comboBox `cbxPregunta1`, `cbxPregunta2` y `cbxPregunta3` respectivamente.
     */
    private void cargarPreguntas() {
        String[] preguntas = {
                "¿Cuál es tu color favorito?",
                "¿Cuál es el nombre de tu primera mascota?",
                "¿Cuál es tu comida favorita?",
                "¿Cuál es tu canción favorita?",
                "¿En qué ciudad naciste?",
                "¿Cuál es tu película favorita?",
                "¿Cuál es el nombre de tu mejor amigo de la infancia?",
                "¿Cuál es tu deporte favorito?",
                "¿Qué país te gustaría visitar?",
                "¿Cómo se llama tu profesor favorito?"
        };

        for (String p : preguntas) {
            cbxPregunta1.addItem(p);
            cbxPregunta2.addItem(p);
            cbxPregunta3.addItem(p);
        }
    }
}
