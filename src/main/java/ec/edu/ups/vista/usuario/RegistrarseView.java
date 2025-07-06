package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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

    private JButton btnRegistro;
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
        imagenIcon();
    }

    private void imagenIcon() {
        // Redimensionar icono "Registro"
        URL btRegistro = LoginView.class.getClassLoader().getResource("imagenes/registro.png");
        if (btRegistro != null) {
            ImageIcon iconBtnRegistro = new ImageIcon(btRegistro);
            Image imgRegistro = iconBtnRegistro.getImage();  // Convierte ImageIcon a Image
            Image newImgRegistro = imgRegistro.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnRegistro = new ImageIcon(newImgRegistro);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnRegistro.setIcon(iconBtnRegistro);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Registro");
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

        btnRegistro.setText(mensajeHandler.get("usuario.registro.registrarse"));
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

        btnRegistro.setText(RegistrarseView.this.mensajeHandler.get("usuario.registrarse"));
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

    public JButton getBtnRegistro() { return btnRegistro; }

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

    private void cargarPreguntas() {
        String[] preguntas = {
                "¿Cuál es tu color favorito?",
                "¿Cuál es el nombre de tu primera mascota?",
                "¿Cuál es tu comida favorita?",
                "¿Cuál es tu canción favorita?",
                "¿En qué ciudad naciste?",
                "¿Cuál es tu película favorita?",
                "¿Cuál es el nombre de tu mejor amigo de la infancia?",
                "¿Cuál es tu deporte favorito?",
                "¿Qué país te gustaría visitar?",
                "¿Cómo se llama tu profesor favorito?"
        };

        for (String p : preguntas) {
            cbxPregunta1.addItem(p);
            cbxPregunta2.addItem(p);
            cbxPregunta3.addItem(p);
        }
    }
}
