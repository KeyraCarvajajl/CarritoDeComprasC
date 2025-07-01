package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;

public class UsuarioRegistroView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombreCompleto;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JTextField txtCorreoElectronico;
    private JTextField txtTelefono;
    private JFormattedTextField jFormatedFecha;
    private JComboBox<String> cbxPregunta1;
    private JTextField txtPregunta1;
    private JComboBox<String> cbxPregunta2;
    private JTextField txtPregunta2;
    private JComboBox<String> cbxPregunta3;
    private JTextField txtPregunta3;
    private JButton btnRegistrarse;
    private JButton btnCancelar;

    private JLabel lblNombre;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblConfirmarContrasenia;
    private JLabel lblCorreoElectronico;
    private JLabel lblTelefono;
    private JLabel lblFechaNacimiento;
    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JLabel lblPregunta3;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioRegistroView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("usuario.view.registrar.titulo"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        actualizarTextos(mensajeHandler);
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("usuario.view.registrar.titulo"));
        lblNombre.setText(mensajes.get("usuario.nombre"));
        lblUsuario.setText(mensajes.get("usuario.usuario"));
        lblContrasenia.setText(mensajes.get("usuario.contrasena"));
        lblConfirmarContrasenia.setText(mensajes.get("usuario.confirmar.contrasena"));
        lblCorreoElectronico.setText(mensajes.get("usuario.correo"));
        lblTelefono.setText(mensajes.get("usuario.telefono"));
        lblFechaNacimiento.setText(mensajes.get("usuario.fecha.nacimiento"));
        lblPregunta1.setText(mensajes.get("usuario.pregunta1"));
        lblPregunta2.setText(mensajes.get("usuario.pregunta2"));
        lblPregunta3.setText(mensajes.get("usuario.pregunta3"));
        btnRegistrarse.setText(mensajes.get("usuario.registrar"));
        btnCancelar.setText(mensajes.get("boton.cancelar"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
        txtCorreoElectronico.setText("");
        txtTelefono.setText("");
        jFormatedFecha.setText("");
        txtPregunta1.setText("");
        txtPregunta2.setText("");
        txtPregunta3.setText("");
    }

    // Getters
    public JTextField getTxtNombreCompleto() { return txtNombreCompleto; }
    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasenia() { return txtContrasenia; }
    public JPasswordField getTxtConfirmarContrasenia() { return txtConfirmarContrasenia; }
    public JTextField getTxtCorreoElectronico() { return txtCorreoElectronico; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JFormattedTextField getjFormatedFecha() { return jFormatedFecha; }
    public JComboBox<String> getCbxPregunta1() { return cbxPregunta1; }
    public JTextField getTxtPregunta1() { return txtPregunta1; }
    public JComboBox<String> getCbxPregunta2() { return cbxPregunta2; }
    public JTextField getTxtPregunta2() { return txtPregunta2; }
    public JComboBox<String> getCbxPregunta3() { return cbxPregunta3; }
    public JTextField getTxtPregunta3() { return txtPregunta3; }
    public JButton getBtnRegistrarse() { return btnRegistrarse; }
    public JButton getBtnCancelar() { return btnCancelar; }



}
