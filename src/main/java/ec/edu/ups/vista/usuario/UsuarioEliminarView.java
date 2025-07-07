package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class UsuarioEliminarView extends JInternalFrame {
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JButton btnEliminarUsuario;
    private JLabel lblNombre;
    private JLabel lblContrasenia;
    private JLabel lblConfirmarContrasenia;
    private JPanel panelPrincipal;
    private MensajeInternacionalizacionHandler mensajeHandler;

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


    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        lblContrasenia.setText(mensajeHandler.get("usuario.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("usuario.confirmar"));
        btnEliminarUsuario.setText(mensajeHandler.get("usuario.boton.eliminar"));
        setTitle(mensajeHandler.get("usuario.eliminar.titulo"));
    }


    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JPasswordField getTxtContraseña() {
        return txtContrasenia;
    }

    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    public JButton getBtnEliminarUsuario() {
        return btnEliminarUsuario;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

