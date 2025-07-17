package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase UsuarioEliminarView.
 * <p>
 * Esta clase representa la interfaz gráfica para eliminar usuarios dentro del sistema
 * de carrito de compras. Permite buscar un usuario por su nombre de usuario (username),
 * visualizar sus datos y proceder con su eliminación si así se desea.
 * </p>
 *
 * <p>
 * Incluye soporte para internacionalización dinámica mediante la clase
 * {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}, así como personalización de íconos.
 * </p>
 *
 * <p>
 * Componentes principales:
 * <ul>
 *   <li>Campo de texto para ingresar el nombre de usuario</li>
 *   <li>Botón de búsqueda</li>
 *   <li>Botón para eliminar al usuario</li>
 *   <li>Etiquetas descriptivas y título</li>
 * </ul>
 * </p>
 *
 * @author Keyra
 */

public class UsuarioEliminarView extends JInternalFrame {
    // Atributos

    /**
     * Campo de texto donde se ingresa el nombre del usuario a eliminar.
     */
    private JTextField txtNombre;

    /**
     * Campo de contraseña que solicita la clave del usuario para confirmar la identidad.
     */
    private JPasswordField txtContrasenia;

    /**
     * Campo de contraseña para confirmar la clave ingresada.
     */
    private JPasswordField txtConfirmarContrasenia;

    /**
     * Botón que al presionarse elimina el usuario si los datos ingresados son correctos.
     */
    private JButton btnEliminarUsuario;

    /**
     * Etiqueta descriptiva para el campo de nombre de usuario.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta descriptiva para el campo de contraseña.
     */
    private JLabel lblContrasenia;

    /**
     * Etiqueta descriptiva para el campo de confirmación de contraseña.
     */
    private JLabel lblConfirmarContrasenia;

    /**
     * Panel principal que contiene todos los componentes de la interfaz de eliminación de usuarios.
     */
    private JPanel panelPrincipal;

    /**
     * Manejador de internacionalización para cambiar dinámicamente los textos de la interfaz.
     */
    private MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Constructor de la ventana interna UsuarioEliminarView.
     * Inicializa la interfaz gráfica para eliminar un usuario del sistema.
     * Configura el panel principal, establece propiedades de la ventana
     * y aplica la internacionalización e íconos gráficos correspondientes.
     *
     * @parammensajeHandler Manejador de internacionalización para los textos visibles.
     */
    public UsuarioEliminarView(MensajeInternacionalizacionHandler mensajeI) {
        super("Eliminar Usuarios", true, true, false, true);
        this.mensajeHandler = mensajeI;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        actualizarTextos(mensajeHandler);
        imagenIcon();
    }

    /**
     * Establece íconos personalizados a los botones de la interfaz. En este caso,
     * se aplica un ícono de "eliminar" redimensionado para el botón de eliminar usuario.
     * Si no se encuentra el recurso de imagen, se imprime un mensaje de error.
     */
    private void imagenIcon() {
        URL btEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (btEliminar != null) {
            ImageIcon iconBtnEliminar = new ImageIcon(btEliminar);
            Image imgEliminar = iconBtnEliminar.getImage();  // Convierte ImageIcon a Image
            Image newImgEliminar = imgEliminar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnEliminar = new ImageIcon(newImgEliminar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnEliminarUsuario.setIcon(iconBtnEliminar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Eliminar");
        }

    }

    /**
     * Actualiza todos los textos visibles de la interfaz con base en el idioma actual
     * seleccionado en el sistema. Utiliza las claves definidas en los archivos .properties
     * a través del manejador de internacionalización {@link MensajeInternacionalizacionHandler}.
     *
     * @param mensajeHandler Manejador de internacionalización que contiene los textos traducidos.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        lblContrasenia.setText(mensajeHandler.get("usuario.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("usuario.confirmar"));
        btnEliminarUsuario.setText(mensajeHandler.get("usuario.boton.eliminar"));
        setTitle(mensajeHandler.get("usuario.eliminar.titulo"));
    }

    /**
     * Devuelve el campo de texto que contiene el nombre del usuario a eliminar.
     *
     * @return JTextField correspondiente al nombre de usuario.
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * Devuelve el campo de contraseña ingresada para validar la eliminación.
     *
     * @return JPasswordField correspondiente a la contraseña del usuario.
     */
    public JPasswordField getTxtContraseña() {
        return txtContrasenia;
    }

    /**
     * Devuelve el campo de texto donde se confirma la contraseña para validar la eliminación.
     *
     * @return JPasswordField correspondiente a la confirmación de contraseña.
     */
    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    /**
     * Devuelve el botón que permite ejecutar la acción de eliminar un usuario.
     *
     * @return JButton para eliminar usuario.
     */
    public JButton getBtnEliminarUsuario() {
        return btnEliminarUsuario;
    }

    /**
     * Muestra un cuadro de diálogo con el mensaje proporcionado.
     * Útil para notificar al usuario sobre errores, confirmaciones o advertencias.
     *
     * @param mensaje Texto que se desea mostrar en el cuadro de diálogo.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Establece el campo de texto para ingresar el nombre del usuario.
     *
     * @param txtNombre JTextField con el nombre del usuario.
     */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /**
     * Establece el campo de texto para ingresar la contraseña del usuario.
     *
     * @param txtContrasenia JPasswordField con la contraseña del usuario.
     */
    public void setTxtContraseña(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    /**
     * Establece el campo de texto para confirmar la contraseña del usuario.
     *
     * @param txtConfirmarContrasenia JPasswordField con la confirmación de la contraseña.
     */
    public void setTxtConfirmarContrasenia(JPasswordField txtConfirmarContrasenia) {
        this.txtConfirmarContrasenia = txtConfirmarContrasenia;
    }

    /**
     * Establece el botón que ejecuta la acción de eliminar al usuario.
     *
     * @param btnEliminarUsuario JButton para eliminar usuario.
     */
    public void setBtnEliminarUsuario(JButton btnEliminarUsuario) {
        this.btnEliminarUsuario = btnEliminarUsuario;
    }

}

