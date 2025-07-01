package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import javax.swing.*;

public class RecuperarContraseniaView extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblPregunta;
    private JComboBox<String> comboPreguntas;
    private JLabel lblRespuesta;
    private JTextField txtRespuesta;
    private JButton btnVerificar;
    private JButton btnCancelar;

    private MensajeInternacionalizacionHandler mensajeHandler;
    private JTextField txtNombreaDeUsuario;
    private JComboBox cbxPreguntas;
    private JLabel lblNombreDeUsuario;
    private JLabel lblPreguntaDeSeguridad;

    public RecuperarContraseniaView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;

        setTitle(mensajeHandler.get("ventana.recuperar"));
        setContentPane(panelPrincipal);
        setSize(500, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.recuperar"));
        lblUsuario.setText(mensajeHandler.get("etiqueta.usuario"));
        lblPregunta.setText(mensajeHandler.get("etiqueta.pregunta"));
        lblRespuesta.setText(mensajeHandler.get("etiqueta.respuesta"));
        btnVerificar.setText(mensajeHandler.get("boton.verificar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }

    // Getters
    public JTextField getTxtUsuario() { return txtUsuario; }

    public JComboBox<String> getComboPreguntas() { return comboPreguntas; }

    public JTextField getTxtRespuesta() { return txtRespuesta; }

    public JButton getBtnVerificar() { return btnVerificar; }

    public JButton getBtnCancelar() { return btnCancelar; }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        cambiarIdioma();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
