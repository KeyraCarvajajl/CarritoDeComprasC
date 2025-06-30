package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.util.Locale;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelSecundario;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JComboBox comboBox1;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblIdioma;
    private JButton btnOlvidarContrasenia;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public LoginView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("ventana.login"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Llenar comboBox con idiomas
        comboBox1.addItem("Español");
        comboBox1.addItem("Ingles");
        comboBox1.addItem("Francés");

        // Listener para cambio de idioma
        comboBox1.addActionListener(e -> {
            String seleccion = (String) comboBox1.getSelectedItem();
            if (seleccion != null) {
                switch (seleccion) {
                    case "Español":
                        mensajeHandler.setLocale(new Locale("es", "EC"));
                        break;
                    case "Ingles":
                        mensajeHandler.setLocale(new Locale("en", "US"));
                        break;
                    case "Francés":
                        mensajeHandler.setLocale(new Locale("fr", "FR"));
                        break;
                }
                cambiarIdioma(); // Actualiza los textos en pantalla
            }
        });

        cambiarIdioma();
    }


    public JComboBox getComboBox1() {return comboBox1;}

    public JComboBox setComboBox1(JComboBox comboBox1) {this.comboBox1 = comboBox1; return comboBox1;}

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelSecundario() {
        return panelSecundario;
    }

    public void setPanelSecundario(JPanel panelSecundario) {
        this.panelSecundario = panelSecundario;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JPasswordField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JButton getBtnOlvidarContrasenia() {
        return btnOlvidarContrasenia;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.login"));
        lblUsuario.setText(mensajeHandler.get("etiqueta.usuario"));
        lblContrasenia.setText(mensajeHandler.get("etiqueta.contrasena"));
        lblIdioma.setText(mensajeHandler.get("etiqueta.idioma"));
        btnIniciarSesion.setText(mensajeHandler.get("boton.ingresar"));
        btnRegistrarse.setText(mensajeHandler.get("boton.registrarse"));
        btnOlvidarContrasenia.setText(mensajeHandler.get("boton.olvidar"));
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        cambiarIdioma();
    }

}