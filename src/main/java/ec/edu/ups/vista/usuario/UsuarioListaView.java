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
    private JButton btnListar;
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

        cambiarIdioma();
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.usuario.listar"));

        lblNombre.setText(mensajeHandler.get("etiqueta.buscar.por"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnCerrar.setText(mensajeHandler.get("boton.cerrar"));

        cbxFiltro.removeAllItems();
        cbxFiltro.addItem(mensajeHandler.get("usuario.nombre"));
        cbxFiltro.addItem(mensajeHandler.get("usuario.username"));
        cbxFiltro.addItem(mensajeHandler.get("usuario.rol"));

        modelo.setColumnIdentifiers(new String[]{
                mensajeHandler.get("usuario.nombre"),
                mensajeHandler.get("usuario.username"),
                mensajeHandler.get("usuario.rol")
        });
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

    public JButton getBtnListar() {
        return btnListar;
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
