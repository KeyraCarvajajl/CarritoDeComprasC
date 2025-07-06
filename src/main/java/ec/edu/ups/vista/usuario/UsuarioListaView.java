package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class UsuarioListaView extends JInternalFrame {

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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        tblUsuarios.setModel(modelo);

        cambiarIdioma(mensajeHandler);
        imagenIcon();
    }

    private void imagenIcon() {
        // Redimensionar icono "Listar"
        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon iconBtnListar = new ImageIcon(btListar);
            Image imgListar = iconBtnListar.getImage();  // Convierte ImageIcon a Image
            Image newImgListar = imgListar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnListar = new ImageIcon(newImgListar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscar.setIcon(iconBtnListar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
        }

// Redimensionar icono "Cerrar"
        URL btCerrar = LoginView.class.getClassLoader().getResource("imagenes/cerrar.png");
        if (btCerrar != null) {
            ImageIcon iconBtnCerrar = new ImageIcon(btCerrar);
            Image imgCerrar = iconBtnCerrar.getImage();  // Convierte ImageIcon a Image
            Image newImgCerrar = imgCerrar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnCerrar = new ImageIcon(newImgCerrar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnCerrar.setIcon(iconBtnCerrar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Cerrar");
        }

    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("usuario.lista.titulo"));

        lblNombre.setText(mensajeHandler.get("usuario.lista.nombre"));
        btnBuscar.setText(mensajeHandler.get("usuario.lista.buscar"));
        btnCerrar.setText(mensajeHandler.get("usuario.lista.cerrar"));
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
