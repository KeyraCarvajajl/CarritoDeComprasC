package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
        cambiarIdioma(mensajeHandler);
        limpiarCampos();
        imagenIcon();
    }

    private void imagenIcon() {
        // Redimensionar icono "Buscar"
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

// Redimensionar icono "Guardar"
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

// Redimensionar icono "Cancelar"
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