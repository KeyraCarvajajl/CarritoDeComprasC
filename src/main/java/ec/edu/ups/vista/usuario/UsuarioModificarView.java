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
        cambiarIdioma(mensajeHandler);
        limpiarCampos();
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("usuario.modificar.titulo"));

        lblUsuarioBuscar.setText(mensajeHandler.get("usuario.modificar.buscar.usuario"));
        lblNombre.setText(mensajeHandler.get("usuario.modificar.nombre"));
        lblCorreo.setText(mensajeHandler.get("usuario.modificar.correo"));
        lblTelefono.setText(mensajeHandler.get("usuario.modificar.telefono"));

        btnBuscar.setText(mensajeHandler.get("usuario.modificar.buscar"));
        btnGuardar.setText(mensajeHandler.get("usuario.modificar.guardar"));
        btnCancelar.setText(mensajeHandler.get("usuario.modificar.cancelar"));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuarioBuscar.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.usuario"));
        lblNombre.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.nombre"));
        lblCorreo.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.correo"));
        lblTelefono.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.telefono"));

        btnBuscar.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.buscar"));
        btnGuardar.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.guardar"));
        btnCancelar.setText(UsuarioModificarView.this.mensajeHandler.get("usuario.cancelar"));

        setTitle(UsuarioModificarView.this.mensajeHandler.get("usuario.modificar.titulo"));
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