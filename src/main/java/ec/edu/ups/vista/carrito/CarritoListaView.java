package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoListaView extends JInternalFrame {

    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblPCarrito;
    private JPanel panelPrincipal;
    private JLabel lblCarrito;
    private JTextField txtTotalCarrito;
    private JLabel lblTotalCarrito;
    private JTextField txtTotal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("carrito.lista.titulo"), true, true, false, true);

        // Inicialización de los componentes
        if (panelPrincipal == null) panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());  // Configura un layout adecuado (por ejemplo, BorderLayout)

        if (lblCarrito == null) lblCarrito = new JLabel();
        if (txtCodigo == null) txtCodigo = new JTextField();
        if (btnBuscar == null) btnBuscar = new JButton();
        if (btnListar == null) btnListar = new JButton();
        if (txtTotalCarrito == null) txtTotalCarrito = new JTextField();
        if (txtTotal == null) txtTotal = new JTextField();
        if (tblPCarrito == null) tblPCarrito = new JTable();

        // Asignación de modelo a la tabla
        modelo = new DefaultTableModel();
        tblPCarrito.setModel(modelo);
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);

        // Asignar panel y otros componentes
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        this.mensajeHandler = mensajeHandler;

        // Llamar a cambiarIdioma para cargar los textos en el idioma correcto
        cambiarIdioma(mensajeHandler);
        imagenIcono();
    }


    private void imagenIcono() {
        // Redimensionar icono "Buscar"
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

    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        // Cambiar el título de la ventana
        setTitle(mensajeHandler.get("carrito.detalle.titulo"));

        // Actualiza las etiquetas
        lblCarrito.setText(mensajeHandler.get("carrito.titulo"));
        lblTotalCarrito.setText(mensajeHandler.get("carrito.total"));

        // Actualiza los botones
        btnBuscar.setText(mensajeHandler.get("carrito.buscar"));
        btnListar.setText(mensajeHandler.get("carrito.listar"));

        // Actualiza los tooltips si es necesario
        txtCodigo.setToolTipText(mensajeHandler.get("carrito.codigo.tooltip"));
        txtTotalCarrito.setToolTipText(mensajeHandler.get("carrito.total.tooltip"));
        txtTotal.setToolTipText(mensajeHandler.get("carrito.total.pagar"));
    }


    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTable getTblPCarrito() {
        return tblPCarrito;
    }

    public void setTblPCarrito(JTable tblPCarrito) {
        this.tblPCarrito = tblPCarrito;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarCarrito() {
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
}
