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
    private JButton btnModificarUsuario;
    private JButton btnCancelar;
    private JLabel lblFecha;

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
        actualizarTextos(mensajeHandler);
        limpiarCampos();
        imagenIcon();
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuarioBuscar.setText(mensajeHandler.get("usuario.buscar.usuario"));
        lblNombre.setText(mensajeHandler.get("usuario.nombre"));
        lblFecha.setText(mensajeHandler.get("usuario.fecha"));
        lblCorreo.setText(mensajeHandler.get("usuario.correo"));
        lblTelefono.setText(mensajeHandler.get("usuario.telefono"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnModificarUsuario.setText(mensajeHandler.get("boton.modificar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
        setTitle(mensajeHandler.get("usuario.view.modificar.titulo"));
    }


    private void imagenIcon() {
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

        URL btGuardar = LoginView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (btGuardar != null) {
            ImageIcon iconBtnGuardar = new ImageIcon(btGuardar);
            Image imgGuardar = iconBtnGuardar.getImage();  // Convierte ImageIcon a Image
            Image newImgGuardar = imgGuardar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnGuardar = new ImageIcon(newImgGuardar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnModificarUsuario.setIcon(iconBtnGuardar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
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

    public JTextField getTxtBuscarUsuario() {
        return txtBuscarUsuario;
    }

    public void setTxtBuscarUsuario(JTextField txtBuscarUsuario) {
        this.txtBuscarUsuario = txtBuscarUsuario;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getLblUsuarioBuscar() {
        return lblUsuarioBuscar;
    }

    public void setLblUsuarioBuscar(JLabel lblUsuarioBuscar) {
        this.lblUsuarioBuscar = lblUsuarioBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JFormattedTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(JFormattedTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public void setTxtCorreo(JTextField txtCorreo) {
        this.txtCorreo = txtCorreo;
    }

    public JLabel getLblCorreo() {
        return lblCorreo;
    }

    public void setLblCorreo(JLabel lblCorreo) {
        this.lblCorreo = lblCorreo;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public void setLblTelefono(JLabel lblTelefono) {
        this.lblTelefono = lblTelefono;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JButton getBtnModificarUsuario() {
        return btnModificarUsuario;
    }

    public void setBtnModificarUsuario(JButton btnModificarUsuario) {
        this.btnModificarUsuario = btnModificarUsuario;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }


}