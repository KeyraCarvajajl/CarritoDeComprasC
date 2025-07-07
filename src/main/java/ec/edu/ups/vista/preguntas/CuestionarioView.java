package ec.edu.ups.vista.preguntas;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
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

        imagenIcon();
        actualizarTextos(mensajeHandler);

        btnValidar.addActionListener(e -> validarRespuesta());
        btnCancelar.addActionListener(e -> dispose());

        txtUsuario.addActionListener(e -> cargarPreguntasAleatorias(txtUsuario.getText().trim()));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuario.setText(mensajeHandler.get("recuperar.usuario"));
        lblPreguntas.setText(mensajeHandler.get("recuperar.pregunta"));
        lblRespuesta.setText(mensajeHandler.get("recuperar.respuesta"));

        btnValidar.setText(mensajeHandler.get("boton.validar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));

        setTitle(mensajeHandler.get("recuperar.titulo"));
    }


    private void imagenIcon(){
        URL btValidar = LoginView.class.getClassLoader().getResource("imagenes/validar.png");
        if (btValidar != null) {
            ImageIcon iconBtnValidar = new ImageIcon(btValidar);
            Image imgValidar = iconBtnValidar.getImage();  // Convierte ImageIcon a Image
            Image newImgValidar = imgValidar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnValidar = new ImageIcon(newImgValidar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnValidar.setIcon(iconBtnValidar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Validar");
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

    public JComboBox<String> getCbxPreguntas() {
        return cbxPreguntas;
    }

}
