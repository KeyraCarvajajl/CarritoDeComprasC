package ec.edu.ups.vista.usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class UsuarioListaView extends JInternalFrame {

    private JTable tablaUsuarios;
    private JComboBox<String> cbxFiltro;
    private JButton btnListar;
    private JButton btnCerrar;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JPanel panelPrincipal;
    private JButton btnBuscar;
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

        // Llenar el comboBox de filtro
        cbxFiltro.addItem("Nombre");
        cbxFiltro.addItem("Correo");
        cbxFiltro.addItem("Rol");
        cbxFiltro.addItem("Código");

        modelo = new DefaultTableModel();
        tablaUsuarios.setModel(modelo);

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
            btnListar.setIcon(iconBtnListar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
        }

        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnBuscar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnBuscar.getImage();  // Convierte ImageIcon a Image
            Image newImgBuscar = imgBuscar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscar.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
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
        btnListar.setText(mensajeHandler.get("usuario.lista.buscar"));
        btnCerrar.setText(mensajeHandler.get("usuario.lista.cerrar"));
    }

    // Getters
    public JTable getTblUsuarios() {
        return tablaUsuarios;
    }

    public JComboBox<String> getCbxFiltro() {
        return cbxFiltro;
    }

    public JButton getBtnBuscar() {
        return btnListar;
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

    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void setTablaUsuarios(JTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    public void setCbxFiltro(JComboBox<String> cbxFiltro) {
        this.cbxFiltro = cbxFiltro;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnListar = btnBuscar;
    }

    public void setBtnCerrar(JButton btnCerrar) {
        this.btnCerrar = btnCerrar;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
