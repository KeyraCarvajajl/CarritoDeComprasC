package ec.edu.ups.vista.preguntas;

import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.modelo.Preguntas;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CuestionarioView extends JFrame {

    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JTextField txtUsuario;

    private JButton btnValidar;
    private JButton btnCancelar;
    private JComboBox<String> cbxPreguntas;
    private JTextField txtRespuesta;
    private JLabel lblPreguntas;
    private JLabel lblRespuesta;

    private final MensajeInternacionalizacionHandler mensajeHandler;
    private final PreguntasDAO preguntaDAO;

    public CuestionarioView(MensajeInternacionalizacionHandler mensajeHandler, PreguntasDAO preguntaDAO) {
        this.mensajeHandler = mensajeHandler;
        this.preguntaDAO = preguntaDAO;

        setTitle(mensajeHandler.get("recuperar.titulo"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridLayout(5, 2, 10, 10));
        lblUsuario = new JLabel(mensajeHandler.get("recuperar.usuario"));
        txtUsuario = new JTextField();

        lblPreguntas = new JLabel(mensajeHandler.get("recuperar.pregunta"));
        cbxPreguntas = new JComboBox<>();

        lblRespuesta = new JLabel(mensajeHandler.get("recuperar.respuesta"));
        txtRespuesta = new JTextField();

        btnValidar = new JButton(mensajeHandler.get("recuperar.validar"));
        btnCancelar = new JButton(mensajeHandler.get("recuperar.cancelar"));

        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(txtUsuario);

        panelPrincipal.add(lblPreguntas);
        panelPrincipal.add(cbxPreguntas);

        panelPrincipal.add(lblRespuesta);
        panelPrincipal.add(txtRespuesta);

        panelPrincipal.add(btnValidar);
        panelPrincipal.add(btnCancelar);

        setContentPane(panelPrincipal);

        actualizarPreguntas(); // Cargar preguntas iniciales
    }

    public void actualizarPreguntas() {
        cbxPreguntas.removeAllItems();
        List<Preguntas> preguntas = preguntaDAO.obtenerTodas();

        for (Preguntas pregunta : preguntas) {
            cbxPreguntas.addItem(pregunta.getPregunta());
        }
    }



    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Getters para el controlador
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public JButton getBtnValidar() {
        return btnValidar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JComboBox<String> getCbxPreguntas() {
        return cbxPreguntas;
    }
}
