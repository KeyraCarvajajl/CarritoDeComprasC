package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;

public class UsuarioModificarView extends JInternalFrame {

    private JTextField txtBuscarUsuario;
    private JPanel panelPrincipal;
    private JLabel lblUsuarioBuscar;
    private JButton btnBuscar;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JFormattedTextField txtFecha;
    private JTextField txtCorreo;
    private JLabel lblCorreo;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
        cambiarIdioma();
        limpiarCampos();
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
        lblUsuarioBuscar.setText(mensajeHandler.get("usuario.usuario"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        lblCorreo.setText(mensajeHandler.get("usuario.correo"));
        lblTelefono.setText(mensajeHandler.get("usuario.telefono"));
        btnGuardar.setText(mensajeHandler.get("boton.guardar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }

    public JTextField getTxtBuscarUsuario() { return txtBuscarUsuario; }

    public JButton getBtnBuscar() { return btnBuscar; }

    public JTextField getTxtNombre() { return txtNombre; }

    public JFormattedTextField getTxtFecha() { return txtFecha; }

    public JTextField getTxtCorreo() { return txtCorreo; }

    public JTextField getTxtTelefono() { return txtTelefono; }

    public JButton getBtnGuardar() { return btnGuardar; }

    public JButton getBtnCancelar() { return btnCancelar; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtBuscarUsuario.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
}