package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsuarioListaView extends JFrame {

    private JTable tblUsuarios;
    private JComboBox<String> cbxFiltro;
    private JButton btnBuscar;
    private JButton btnCerrar;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JPanel panelPrincipal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public UsuarioListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super();
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana

        modelo = new DefaultTableModel();
        tblUsuarios.setModel(modelo);

        cambiarIdioma(mensajeHandler);
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("usuario.lista.titulo"));

        lblNombre.setText(mensajeHandler.get("usuario.lista.nombre"));

        btnBuscar.setText(mensajeHandler.get("usuario.lista.buscar"));
        btnCerrar.setText(mensajeHandler.get("usuario.lista.cerrar"));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblNombre.setText(UsuarioListaView.this.mensajeHandler.get("usuario.nombre"));
        btnBuscar.setText(UsuarioListaView.this.mensajeHandler.get("usuario.buscar"));
        btnCerrar.setText(UsuarioListaView.this.mensajeHandler.get("usuario.cerrar"));

        setTitle(UsuarioListaView.this.mensajeHandler.get("usuario.lista.titulo"));
    }


    // Getters
    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    public JComboBox<String> getCbxFiltro() {
        return cbxFiltro;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnCerrar() {
        return btnCerrar;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public DefaultTableModel getTableModel() {
        return modelo;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }


    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
