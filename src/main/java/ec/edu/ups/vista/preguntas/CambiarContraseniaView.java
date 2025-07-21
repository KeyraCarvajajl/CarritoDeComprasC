package ec.edu.ups.vista.preguntas;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Ventana interna del sistema que permite a los usuarios cambiar su contraseña.
 * <p>
 * Esta clase proporciona una interfaz gráfica compuesta por campos para ingresar
 * una nueva contraseña y confirmar la misma, junto con botones para guardar los cambios
 * o cancelar la operación.
 * <p>
 * Implementa soporte de internacionalización con {@link MensajeInternacionalizacionHandler},
 * y carga íconos personalizados para los botones "Guardar" y "Cancelar".
 * <p>
 * Forma parte del paquete de preguntas de seguridad y recuperación de credenciales.
 *
 * @author Keyra
 */

public class CambiarContraseniaView extends JInternalFrame {

    /**
     * Panel principal que contiene todos los componentes gráficos de la vista.
     * Utiliza un diseño de tipo GridBagLayout.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta que indica el campo para ingresar la nueva contraseña.
     */
    private JLabel lblNuevaContrasenia;

    /**
     * Etiqueta que indica el campo para confirmar la nueva contraseña.
     */
    private JLabel lblConfirmarContrasenia;

    /**
     * Campo de texto donde el usuario ingresa su nueva contraseña.
     * Está enmascarado para mayor seguridad.
     */
    private JPasswordField txtNuevaContrasenia;

    /**
     * Campo de texto donde el usuario debe confirmar su nueva contraseña.
     * También está enmascarado.
     */
    private JPasswordField txtConfirmarContrasenia;

    /**
     * Botón que permite guardar la nueva contraseña ingresada por el usuario.
     */
    private JButton btnGuardar;

    /**
     * Botón que permite cancelar la operación de cambio de contraseña.
     */
    private JButton btnCancelar;

    /**
     * Manejador de internacionalización para actualizar los textos de la interfaz
     * en diferentes idiomas dinámicamente.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor que inicializa la ventana para cambiar la contraseña del usuario.
     * <p>
     * Configura las propiedades de la ventana (tamaño, posición, cierre),
     * inicializa los componentes gráficos, aplica los textos según el idioma actual
     * y asigna íconos personalizados a los botones.
     *
     * @param mensajeHandler Manejador de mensajes utilizado para la internacionalización dinámica de la interfaz.
     */
    public CambiarContraseniaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setTitle(mensajeHandler.get("ventana.cambiar.contrasenia"));
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setSize(400, 250);
        setLocation(100, 100);

        initComponents();
        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Actualiza los textos visibles de la interfaz gráfica utilizando el manejador
     * de internacionalización. Se aplican los textos traducidos a las etiquetas,
     * botones y al título de la ventana.
     *
     * @param mensajeHandler Manejador que proporciona los textos internacionalizados según el idioma actual.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNuevaContrasenia.setText(mensajeHandler.get("contrasenia.nueva"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("contrasenia.confirmar"));

        btnGuardar.setText(mensajeHandler.get("boton.guardar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));

        setTitle(mensajeHandler.get("contrasenia.cambiar.titulo"));
    }

    /**
     * Carga y asigna íconos personalizados a los botones "Guardar" y "Cancelar".
     * <p>
     * Las imágenes se obtienen desde el directorio de recursos del proyecto
     * y se redimensionan a 30x30 píxeles para mantener una apariencia uniforme.
     * <p>
     * Si los recursos no se encuentran disponibles, se muestra un mensaje de error en la consola.
     */
    private void imagenIcon() {
        /**
         * Redimensionar icono "Guardar"
         */
        URL btGuardar = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (btGuardar != null) {
            ImageIcon iconBtnGuardar = new ImageIcon(btGuardar);
            Image imgGuardar = iconBtnGuardar.getImage();  // Convierte ImageIcon a Image
            Image newImgGuardar = imgGuardar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnGuardar = new ImageIcon(newImgGuardar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnGuardar.setIcon(iconBtnGuardar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
        }

        /**
         * Redimensionar icono "Cancelar"
         */
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

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(235, 243, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        lblNuevaContrasenia = new JLabel();
        txtNuevaContrasenia = new JPasswordField(15);
        lblConfirmarContrasenia = new JLabel();
        txtConfirmarContrasenia = new JPasswordField(15);

        btnGuardar = new JButton();
        btnCancelar = new JButton();

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(lblNuevaContrasenia, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(txtNuevaContrasenia, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panelPrincipal.add(lblConfirmarContrasenia, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panelPrincipal.add(txtConfirmarContrasenia, gbc);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotones, gbc);

        add(panelPrincipal);
    }

    /**
     * Actualiza dinámicamente los textos visibles de la interfaz gráfica
     * cuando el usuario cambia el idioma del sistema.
     * <p>
     * Se actualizan el título de la ventana, las etiquetas y los botones
     * utilizando claves del archivo de propiedades internacionalizado.
     *
     * @param mensajeHandler Manejador que contiene los textos traducidos según el idioma seleccionado.
     */
    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("ventana.cambiar.contrasenia"));
        lblNuevaContrasenia.setText(mensajeHandler.get("label.nueva.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("label.confirmar.contrasenia"));
        btnGuardar.setText(mensajeHandler.get("boton.guardar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }

    // Getters para el controlador
    /**
     * Retorna el campo de texto para ingresar la nueva contraseña.
     * El contenido está enmascarado para proteger la privacidad del usuario.
     *
     * @return Campo de tipo {@code JPasswordField} para la nueva contraseña.
     */
    public JPasswordField getTxtNuevaContrasenia() {
        return txtNuevaContrasenia;
    }

    /**
     * Retorna el campo de texto para confirmar la nueva contraseña ingresada.
     * Este campo también está enmascarado.
     *
     * @return Campo de tipo {@code JPasswordField} para confirmar la contraseña.
     */
    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    /**
     * Retorna el botón que permite guardar la nueva contraseña.
     *
     * @return Botón de tipo {@code JButton} para guardar.
     */
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Retorna el botón que permite cancelar la operación de cambio de contraseña.
     *
     * @return Botón de tipo {@code JButton} para cancelar.
     */
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * Establece el manejador de internacionalización que se usará
     * para actualizar dinámicamente los textos de la interfaz.
     *
     * @param mensajeHandler Manejador de mensajes internacionalizados.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

}
