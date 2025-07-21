package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Locale;
/**
 * Clase {@code LoginView} que representa la ventana de inicio de sesión del sistema.
 *
 * <p>Permite al usuario ingresar su nombre de usuario (cédula) y contraseña para acceder al sistema.
 * También proporciona acceso a las funcionalidades de recuperación de contraseña y registro de nuevos usuarios.</p>
 *
 * <p>Esta clase implementa una interfaz gráfica utilizando Swing y permite el cambio de idioma dinámico
 *
 * <p>Componentes destacados:</p>
 * <ul>
 *   <li>Campo de texto para el nombre de usuario</li>
 *   <li>Campo de contraseña</li>
 *   <li>Botones para iniciar sesión, registrarse y recuperar contraseña</li>
 *   <li>Selector de idioma (Español, Inglés, Francés)</li>
 * </ul>
 *
 * <p>Forma parte del paquete {@code ec.edu.ups.vista.usuario} y sigue el patrón MVC.</p>
 *
 * @author Keyra
 */

public class LoginView extends JFrame {

    /** Panel principal que contiene todos los componentes de la ventana de login. */
    private JPanel panelPrincipal;

    /** Panel secundario utilizado para agrupar visualmente los campos de ingreso. */
    private JPanel panelSecundario;

    /** Campo de texto donde el usuario debe ingresar su nombre de usuario (cédula). */
    private JTextField txtUsername;

    /** Campo de contraseña donde el usuario debe ingresar su clave secreta. */
    private JPasswordField txtContrasenia;

    /** Botón para iniciar sesión en el sistema. */
    private JButton btnIniciarSesion;

    /** Botón que redirige a la ventana de registro de nuevos usuarios. */
    private JButton btnRegistrarse;

    /** ComboBox que permite al usuario seleccionar el idioma de la interfaz (Español, Inglés, Francés). */
    private JComboBox comboBox1;

    /** Etiqueta que acompaña al campo de nombre de usuario. */
    private JLabel lblUsuario;

    /** Etiqueta que acompaña al campo de contraseña. */
    private JLabel lblContrasenia;

    /** Etiqueta que acompaña al selector de idioma. */
    private JLabel lblIdioma;

    /** Botón que abre la ventana de recuperación de contraseña. */
    private JButton btnOlvidarContrasenia;

    /** Manejador de internacionalización que permite actualizar los textos de la interfaz según el idioma seleccionado. */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Crea una nueva instancia de la ventana de inicio de sesión (LoginView).
     * Establece el título, el contenido del panel principal y configura
     * el comportamiento de cierre. También aplica internacionalización dinámica
     * a los textos visibles y carga los íconos de los botones.
     *
     * @param mensajeHandler El manejador de internacionalización utilizado para traducir los textos
     *                       según el idioma seleccionado por el usuario.
     */
    public LoginView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("ventana.login"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        comboBox1.addItem("Español");
        comboBox1.addItem("Ingles");
        comboBox1.addItem("Francés");

        comboBox1.addActionListener(e -> {
            String seleccion = (String) comboBox1.getSelectedItem();
            if (seleccion != null) {
                switch (seleccion) {
                    case "Español":
                        mensajeHandler.setLocale(Locale.forLanguageTag("es-ES"));
                        break;
                    case "Ingles":
                        mensajeHandler.setLocale(Locale.forLanguageTag("en-US"));
                        break;
                    case "Francés":
                        mensajeHandler.setLocale(Locale.forLanguageTag("fr-FR"));
                        break;
                }
                actualizarTextos(mensajeHandler); // Actualiza los textos en pantalla
            }
        });

        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Asigna íconos personalizados a los botones de la ventana de inicio de sesión.
     * Los íconos se redimensionan para mantener una presentación estética uniforme.
     * Los recursos deben estar en la carpeta "imagenes" dentro del classpath.
     *
     * Botones personalizados:
     * - Iniciar sesión: iniciar.png
     * - Registrarse: registrar.png
     * - Recuperar contraseña: pregunta.png
     */
    private void imagenIcon() {
        URL btIniciarSesion = LoginView.class.getClassLoader().getResource("imagenes/iniciarSesión.png");
        if (btIniciarSesion != null) {
            ImageIcon iconBtnIniciarSesion = new ImageIcon(btIniciarSesion);
            Image imgIniciarSesion = iconBtnIniciarSesion.getImage();  // Convierte ImageIcon a Image
            Image newImgIniciarSesion = imgIniciarSesion.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnIniciarSesion = new ImageIcon(newImgIniciarSesion);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnIniciarSesion.setIcon(iconBtnIniciarSesion);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Iniciar Sesión");
        }

        URL btRegistro = LoginView.class.getClassLoader().getResource("imagenes/registro.png");
        if (btRegistro != null) {
            ImageIcon iconBtnRegistro = new ImageIcon(btRegistro);
            Image imgRegistro = iconBtnRegistro.getImage();  // Convierte ImageIcon a Image
            Image newImgRegistro = imgRegistro.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnRegistro = new ImageIcon(newImgRegistro);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnRegistrarse.setIcon(iconBtnRegistro);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Registro");
        }

        URL btOlvidasteContrasena = LoginView.class.getClassLoader().getResource("imagenes/olvidarContraseña.png");
        if (btOlvidasteContrasena != null) {
            ImageIcon iconBtnOlvidasteContrasena = new ImageIcon(btOlvidasteContrasena);
            Image imgOlvidasteContrasena = iconBtnOlvidasteContrasena.getImage();  // Convierte ImageIcon a Image
            Image newImgOlvidasteContrasena = imgOlvidasteContrasena.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnOlvidasteContrasena = new ImageIcon(newImgOlvidasteContrasena);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnOlvidarContrasenia.setIcon(iconBtnOlvidasteContrasena);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Olvidaste Contraseña");
        }

    }

    /**
     * Actualiza todos los textos visibles de la interfaz según el idioma actual.
     * Utiliza claves definidas en los archivos .properties manejados por
     * MensajeInternacionalizacionHandler.
     *
     * @param mensajeHandler Manejador de mensajes para obtener los textos localizados.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuario.setText(mensajeHandler.get("login.usuario"));
        lblContrasenia.setText(mensajeHandler.get("login.contrasenia"));
        lblIdioma.setText(mensajeHandler.get("login.idioma"));

        btnIniciarSesion.setText(mensajeHandler.get("login.iniciar"));
        btnRegistrarse.setText(mensajeHandler.get("login.registrarse"));
        btnOlvidarContrasenia.setText(mensajeHandler.get("login.olvidaste"));

        setTitle(mensajeHandler.get("login.titulo"));
    }

    /**
     * Retorna el JComboBox que permite seleccionar el idioma.
     *
     * @return JComboBox para selección de idioma.
     */
    public JComboBox getComboBox1() {
        return comboBox1;
    }

    /**
     * Retorna el panel principal de la vista de login.
     *
     * @return JPanel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Retorna el panel secundario, que contiene los campos del formulario.
     *
     * @return JPanel secundario.
     */
    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    /**
     * Retorna el campo de texto donde se ingresa el nombre de usuario.
     *
     * @return JTextField del nombre de usuario.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * Retorna el campo de contraseña.
     *
     * @return JPasswordField para la contraseña.
     */
    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    /**
     * Retorna el botón para iniciar sesión.
     *
     * @return JButton "Iniciar sesión".
     */
    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    /**
     * Retorna el botón para registrarse.
     *
     * @return JButton "Registrarse".
     */
    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    /**
     * Retorna el botón para recuperar la contraseña.
     *
     * @return JButton "¿Olvidaste tu contraseña?".
     */
    public JButton getBtnOlvidarContrasenia() {
        return btnOlvidarContrasenia;
    }

    /**
     * Establece el panel principal de la vista de login.
     *
     * @param panelPrincipal Nuevo JPanel principal.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece el panel secundario de la vista.
     *
     * @param panelSecundario Nuevo JPanel secundario.
     */
    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    /**
     * Establece el campo de texto del nombre de usuario.
     *
     * @param txtUsername JTextField con el nombre de usuario.
     */
    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    /**
     * Establece el campo de contraseña.
     *
     * @param txtContrasenia JPasswordField con la contraseña.
     */
    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    /**
     * Establece el botón para iniciar sesión.
     *
     * @param btnIniciarSesion JButton "Iniciar sesión".
     */
    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    /**
     * Establece el botón para registrarse.
     *
     * @param btnRegistrarse JButton "Registrarse".
     */
    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    /**
     * Muestra un mensaje emergente al usuario mediante un cuadro de diálogo.
     *
     * @param mensaje Mensaje que se desea mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Establece el manejador de mensajes para la internacionalización
     * y actualiza todos los textos visibles en la vista.
     *
     * @param mensajeHandler Objeto encargado de gestionar los textos traducidos.
     */
    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        actualizarTextos(mensajeHandler);
    }

}