package ec.edu.ups.vista;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class RecuperarContraseniaView extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JTextField txtUsuario;

    private JLabel lblPregunta1;
    private JComboBox<String> cbxPregunta1;
    private JTextField txtRespuesta1;

    private JLabel lblPregunta2;
    private JComboBox<String> cbxPregunta2;
    private JTextField txtRespuesta2;

    private JLabel lblPregunta3;
    private JComboBox<String> cbxPregunta3;
    private JTextField txtRespuesta3;

    private JButton btnValidar;
    private JButton btnCancelar;

    private MensajeInternacionalizacionHandler mensajeHandler;

    public RecuperarContraseniaView(MensajeInternacionalizacionHandler mensajeHandler, PreguntaDAO preguntaDAO) {
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        cambiarIdioma();

        cbxPregunta1.addItem("¿Color favorito?");
        cbxPregunta2.addItem("¿Nombre de tu mascota?");
        cbxPregunta3.addItem("¿Ciudad natal?");

    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("recuperar.titulo"));
        lblUsuario.setText(mensajeHandler.get("recuperar.usuario"));
        lblPregunta1.setText(mensajeHandler.get("recuperar.pregunta1"));
        lblPregunta2.setText(mensajeHandler.get("recuperar.pregunta2"));
        lblPregunta3.setText(mensajeHandler.get("recuperar.pregunta3"));
        btnValidar.setText(mensajeHandler.get("recuperar.validar"));
        btnCancelar.setText(mensajeHandler.get("recuperar.cancelar"));

        actualizarPreguntas();
    }

    public void actualizarPreguntas() {
        cbxPregunta1.removeAllItems();
        cbxPregunta2.removeAllItems();
        cbxPregunta3.removeAllItems();

        String idioma = mensajeHandler.getLocale().getLanguage();

        if (idioma.equals("es")) {
            cbxPregunta1.addItem("¿Color favorito?");
            cbxPregunta2.addItem("¿Nombre de tu mascota?");
            cbxPregunta3.addItem("¿Ciudad natal?");
        } else if (idioma.equals("en")) {
            cbxPregunta1.addItem("Favorite color?");
            cbxPregunta2.addItem("Pet's name?");
            cbxPregunta3.addItem("Hometown?");
        } else if (idioma.equals("fr")) {
            cbxPregunta1.addItem("Couleur préférée ?");
            cbxPregunta2.addItem("Nom de votre animal de compagnie ?");
            cbxPregunta3.addItem("Ville natale ?");
        }
    }


    // Getters
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JComboBox<String> getCbxPregunta1() {
        return cbxPregunta1;
    }

    public JComboBox<String> getCbxPregunta2() {
        return cbxPregunta2;
    }

    public JComboBox<String> getCbxPregunta3() {
        return cbxPregunta3;
    }

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public JTextField getTxtRespuesta3() {
        return txtRespuesta3;
    }

    public JButton getBtnValidar() {
        return btnValidar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        cambiarIdioma();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}

