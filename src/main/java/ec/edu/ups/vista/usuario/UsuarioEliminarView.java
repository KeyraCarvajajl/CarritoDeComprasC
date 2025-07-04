package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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
        setSize(300, 190);
        cambiarIdioma(mensajeHandler);
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("usuario.eliminar.titulo"));

        lblNombre.setText(mensajeHandler.get("usuario.eliminar.nombre"));
        lblContrasenia.setText(mensajeHandler.get("usuario.eliminar.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("usuario.eliminar.confirmar"));

        btnEliminarUsuario.setText(mensajeHandler.get("usuario.eliminar.eliminar"));
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

