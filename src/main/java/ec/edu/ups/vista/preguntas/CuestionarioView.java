package ec.edu.ups.vista.preguntas;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CuestionarioView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblPreguntas;
    private JComboBox<String> cbxPreguntas;
    private JLabel lblRespuesta;
    private JTextField txtRespuesta;
    private JButton btnValidar;
    private JButton btnCancelar;

    private final MensajeInternacionalizacionHandler mensajeHandler;
    private final UsuarioDAO usuarioDAO;
    private Usuario usuarioActual;

    public CuestionarioView(MensajeInternacionalizacionHandler mensajeHandler, UsuarioDAO usuarioDAO) {
        this.mensajeHandler = mensajeHandler;
        this.usuarioDAO = usuarioDAO;

        setContentPane(panelPrincipal);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);

        actualizarTextos(mensajeHandler);

        btnValidar.addActionListener(e -> validarRespuesta());
        btnCancelar.addActionListener(e -> dispose());

        txtUsuario.addActionListener(e -> cargarPreguntasAleatorias(txtUsuario.getText().trim()));
    }

    public void cambiarIdioma() {
        lblUsuario.setText(mensajeHandler.get("label.usuario"));
        lblPreguntas.setText(mensajeHandler.get("label.preguntas"));
        lblRespuesta.setText(mensajeHandler.get("label.respuesta"));
        btnValidar.setText(mensajeHandler.get("boton.validar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));
    }


    private void cargarPreguntasAleatorias(String username) {
        usuarioActual = usuarioDAO.buscarPorUsername(username);
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.usuario.noencontrado"));
            return;
        }

        List<String> preguntas = new ArrayList<>();
        preguntas.add(usuarioActual.getPregunta1());
        preguntas.add(usuarioActual.getPregunta2());
        preguntas.add(usuarioActual.getPregunta3());
        Collections.shuffle(preguntas);

        cbxPreguntas.removeAllItems();
        for (String pregunta : preguntas) {
            cbxPreguntas.addItem(pregunta);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }


    private void validarRespuesta() {
        String preguntaSeleccionada = (String) cbxPreguntas.getSelectedItem();
        String respuestaIngresada = txtRespuesta.getText().trim();

        if (preguntaSeleccionada == null || respuestaIngresada.isEmpty()) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.completar"));
            return;
        }

        boolean esCorrecta = false;
        if (preguntaSeleccionada.equals(usuarioActual.getPregunta1()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta1())) {
            esCorrecta = true;
        } else if (preguntaSeleccionada.equals(usuarioActual.getPregunta2()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta2())) {
            esCorrecta = true;
        } else if (preguntaSeleccionada.equals(usuarioActual.getPregunta3()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta3())) {
            esCorrecta = true;
        }

        if (esCorrecta) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.correcto"));
            // Aquí puedes abrir la ventana CambiarContraseniaView
        } else {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.incorrecto"));
        }
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(CuestionarioView.this.mensajeHandler.get("recuperar.titulo"));
        lblUsuario.setText(CuestionarioView.this.mensajeHandler.get("recuperar.usuario"));
        lblPreguntas.setText(CuestionarioView.this.mensajeHandler.get("recuperar.pregunta"));
        lblRespuesta.setText(CuestionarioView.this.mensajeHandler.get("recuperar.respuesta"));
        btnValidar.setText(CuestionarioView.this.mensajeHandler.get("recuperar.validar"));
        btnCancelar.setText(CuestionarioView.this.mensajeHandler.get("recuperar.cancelar"));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JLabel getLblPreguntas() {
        return lblPreguntas;
    }

    public void setLblPreguntas(JLabel lblPreguntas) {
        this.lblPreguntas = lblPreguntas;
    }

    public JComboBox<String> getCbxPreguntas() {
        return cbxPreguntas;
    }

    public void setCbxPreguntas(JComboBox<String> cbxPreguntas) {
        this.cbxPreguntas = cbxPreguntas;
    }

    public JLabel getLblRespuesta() {
        return lblRespuesta;
    }

    public void setLblRespuesta(JLabel lblRespuesta) {
        this.lblRespuesta = lblRespuesta;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public void setTxtRespuesta(JTextField txtRespuesta) {
        this.txtRespuesta = txtRespuesta;
    }

    public JButton getBtnValidar() {
        return btnValidar;
    }

    public void setBtnValidar(JButton btnValidar) {
        this.btnValidar = btnValidar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}
