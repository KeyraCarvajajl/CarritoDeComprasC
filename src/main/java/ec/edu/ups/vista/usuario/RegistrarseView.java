package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class RegistrarseView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombreCompleto;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JPasswordField txtConfirmarContrasenia;
    private JFormattedTextField jFormatedFecha;
    private JTextField txtCorreoElectrónico;
    private JTextField txtTeléfono;

    private JComboBox cbxPregunta1;
    private JTextField txtPregunta1;
    private JComboBox cbxPregunta2;
    private JTextField txtPregunta2;
    private JComboBox cbxPregunta3;
    private JTextField txtPregunta3;

    private JButton btnRegistrarse;
    private JButton btnCancelar;

    private JLabel lblNombre;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblConfirmarContrasenia;
    private JLabel lblFechaDeNacimiento;
    private JLabel lblCorreoElectrónico;
    private JLabel lblTeléfono;
    private JLabel lblRegistrar;
    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JLabel lblPregunta3;
    private JLabel lblPreuntasSeguridad;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public RegistrarseView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setTitle(mensajeHandler.get("usuario.view.registrar.titulo"));
        cambiarIdioma(mensajeHandler);
        cargarPreguntas();
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("usuario.registro.titulo"));

        lblRegistrar.setText(mensajeHandler.get("usuario.registro.titulo"));
        lblNombre.setText(mensajeHandler.get("usuario.registro.nombre"));
        lblUsuario.setText(mensajeHandler.get("usuario.registro.usuario"));
        lblContrasenia.setText(mensajeHandler.get("usuario.registro.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("usuario.registro.confirmar"));
        lblFechaDeNacimiento.setText(mensajeHandler.get("usuario.registro.fecha"));
        lblCorreoElectrónico.setText(mensajeHandler.get("usuario.registro.correo"));
        lblTeléfono.setText(mensajeHandler.get("usuario.registro.telefono"));

        lblPreuntasSeguridad.setText(mensajeHandler.get("usuario.registro.preguntas"));
        lblPregunta1.setText(mensajeHandler.get("usuario.registro.pregunta1"));
        lblPregunta2.setText(mensajeHandler.get("usuario.registro.pregunta2"));
        lblPregunta3.setText(mensajeHandler.get("usuario.registro.pregunta3"));

        btnRegistrarse.setText(mensajeHandler.get("usuario.registro.registrarse"));
        btnCancelar.setText(mensajeHandler.get("usuario.registro.cancelar"));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblRegistrar.setText(RegistrarseView.this.mensajeHandler.get("usuario.view.registrar.titulo"));
        lblNombre.setText(RegistrarseView.this.mensajeHandler.get("usuario.nombre"));
        lblUsuario.setText(RegistrarseView.this.mensajeHandler.get("usuario.usuario"));
        lblContrasenia.setText(RegistrarseView.this.mensajeHandler.get("usuario.contrasenia"));
        lblConfirmarContrasenia.setText(RegistrarseView.this.mensajeHandler.get("usuario.confirmar"));
        lblFechaDeNacimiento.setText(RegistrarseView.this.mensajeHandler.get("usuario.fecha"));
        lblCorreoElectrónico.setText(RegistrarseView.this.mensajeHandler.get("usuario.correo"));
        lblTeléfono.setText(RegistrarseView.this.mensajeHandler.get("usuario.telefono"));
        lblPreuntasSeguridad.setText(RegistrarseView.this.mensajeHandler.get("usuario.preguntas"));
        lblPregunta1.setText(RegistrarseView.this.mensajeHandler.get("usuario.pregunta1"));
        lblPregunta2.setText(RegistrarseView.this.mensajeHandler.get("usuario.pregunta2"));
        lblPregunta3.setText(RegistrarseView.this.mensajeHandler.get("usuario.pregunta3"));

        btnRegistrarse.setText(RegistrarseView.this.mensajeHandler.get("usuario.registrarse"));
        btnCancelar.setText(RegistrarseView.this.mensajeHandler.get("usuario.cancelar"));

        setTitle(RegistrarseView.this.mensajeHandler.get("usuario.view.registrar.titulo"));
    }



    // Getters
    public JTextField getTxtNombreCompleto() { return txtNombreCompleto; }

    public JTextField getTxtUsuario() { return txtUsuario; }

    public JPasswordField getTxtContrasenia() { return txtContrasenia; }

    public JPasswordField getTxtConfirmarContrasenia() { return txtConfirmarContrasenia; }

    public JFormattedTextField getTxtFechaNacimiento() { return jFormatedFecha; }

    public JTextField getTxtCorreo() { return txtCorreoElectrónico; }

    public JTextField getTxtTelefono() { return txtTeléfono; }

    public JComboBox getCbxPregunta1() { return cbxPregunta1; }

    public JComboBox getCbxPregunta2() { return cbxPregunta2; }

    public JComboBox getCbxPregunta3() { return cbxPregunta3; }

    public JTextField getTxtPregunta1() { return txtPregunta1; }

    public JTextField getTxtPregunta2() { return txtPregunta2; }

    public JTextField getTxtPregunta3() { return txtPregunta3; }

    public JButton getBtnRegistrarse() { return btnRegistrarse; }

    public JButton getBtnCancelar() { return btnCancelar; }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombreCompleto.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
        txtConfirmarContrasenia.setText("");
        jFormatedFecha.setText("");
        txtCorreoElectrónico.setText("");
        txtTeléfono.setText("");
        txtPregunta1.setText("");
        txtPregunta2.setText("");
        txtPregunta3.setText("");
    }

    public void cargarPreguntas() {
        cbxPregunta1.removeAllItems();
        cbxPregunta2.removeAllItems();
        cbxPregunta3.removeAllItems();

        String idioma = mensajeHandler.getLocale().getLanguage();

        if (idioma.equals("es")) {
            cbxPregunta1.addItem("¿Color favorito?");
            cbxPregunta1.addItem("¿Nombre de tu mascota?");
            cbxPregunta1.addItem("¿Ciudad natal?");

            cbxPregunta2.addItem("¿Color favorito?");
            cbxPregunta2.addItem("¿Nombre de tu mascota?");
            cbxPregunta2.addItem("¿Ciudad natal?");

            cbxPregunta3.addItem("¿Color favorito?");
            cbxPregunta3.addItem("¿Nombre de tu mascota?");
            cbxPregunta3.addItem("¿Ciudad natal?");
        } else if (idioma.equals("en")) {
            cbxPregunta1.addItem("Favorite color?");
            cbxPregunta1.addItem("Pet's name?");
            cbxPregunta1.addItem("Hometown?");

            cbxPregunta2.addItem("Favorite color?");
            cbxPregunta2.addItem("Pet's name?");
            cbxPregunta2.addItem("Hometown?");

            cbxPregunta3.addItem("Favorite color?");
            cbxPregunta3.addItem("Pet's name?");
            cbxPregunta3.addItem("Hometown?");
        } else if (idioma.equals("fr")) {

            cbxPregunta1.addItem("Couleur préférée ?");
            cbxPregunta1.addItem("Nom de votre animal de compagnie ?");
            cbxPregunta1.addItem("Ville natale ?");

            cbxPregunta2.addItem("Couleur préférée ?");
            cbxPregunta2.addItem("Nom de votre animal de compagnie ?");
            cbxPregunta2.addItem("Ville natale ?");

            cbxPregunta3.addItem("Couleur préférée ?");
            cbxPregunta3.addItem("Nom de votre animal de compagnie ?");
            cbxPregunta3.addItem("Ville natale ?");
        }
    }
}
