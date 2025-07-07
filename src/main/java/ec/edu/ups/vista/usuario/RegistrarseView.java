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

        actualizarTextos(mensajeHandler);
        cargarPreguntas();
        imagenIcon();
    }

    private void imagenIcon() {
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

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(mensajeHandler.get("registro.nombre"));
        lblUsuario.setText(mensajeHandler.get("registro.usuario"));
        lblContrasenia.setText(mensajeHandler.get("registro.contrasenia"));
        lblConfirmarContrasenia.setText(mensajeHandler.get("registro.confirmar"));
        lblFechaDeNacimiento.setText(mensajeHandler.get("registro.fecha"));
        lblCorreoElectrónico.setText(mensajeHandler.get("registro.correo"));
        lblTeléfono.setText(mensajeHandler.get("registro.telefono"));
        lblRegistrar.setText(mensajeHandler.get("registro.titulo"));

        lblPreuntasSeguridad.setText(mensajeHandler.get("registro.seguridad"));
        lblPregunta1.setText(mensajeHandler.get("registro.pregunta1"));
        lblPregunta2.setText(mensajeHandler.get("registro.pregunta2"));
        lblPregunta3.setText(mensajeHandler.get("registro.pregunta3"));

        btnRegistro.setText(mensajeHandler.get("boton.registrar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));

        setTitle(mensajeHandler.get("registro.titulo"));
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
