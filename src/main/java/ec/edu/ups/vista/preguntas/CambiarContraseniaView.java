package ec.edu.ups.vista.preguntas;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class CambiarContraseniaView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JLabel lblNuevaContrasenia;
    private JLabel lblConfirmarContrasenia;
    private JPasswordField txtNuevaContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JButton btnGuardar;
    private JButton btnCancelar;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public CambiarContraseniaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setTitle(mensajeHandler.get("ventana.cambiar.contrasenia"));
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setSize(400, 250);
        setLocation(100, 100);

        initComponents();
        cambiarIdioma(mensajeHandler);
        imagenIcon();
    }

    private void imagenIcon() {
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

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("ventana.cambiar.contrasenia"));
        lblNuevaContrasenia.setText(mensajeHandler.get("label.nueva.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("label.confirmar.contrasenia"));
        btnGuardar.setText(mensajeHandler.get("boton.guardar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }

    // Getters para el controlador
    public JPasswordField getTxtNuevaContrasenia() {
        return txtNuevaContrasenia;
    }

    public JPasswordField getTxtConfirmarContrasenia() {
        return txtConfirmarContrasenia;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
}
