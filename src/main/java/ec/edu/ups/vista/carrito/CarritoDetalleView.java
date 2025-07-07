package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoDetalleView extends JInternalFrame {
    private JTextField txtIdDet;
    private JButton btnBuscarDetalle;
    private JTable tblDetCarrito;
    private JTextField txtSubTotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JButton btnAceptarDetalle;
    private JPanel panelPrincipal;
    private JLabel lblDetallesCarrito;
    private JLabel lblID;
    private JLabel lblSubTotal;
    private JLabel lblIVA;
    private JLabel lblTotal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoDetalleView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Detalle del Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblDetCarrito.setModel(modelo);
        this.mensajeHandler = mensajeHandler;
        actualizarTextos(mensajeHandler);
        iconoImagen();
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.detalle.titulo"));
        lblID.setText(mensajes.get("carrito.detalle.id"));
        lblSubTotal.setText(mensajes.get("carrito.subtotal"));
        lblIVA.setText(mensajes.get("carrito.iva"));
        lblTotal.setText(mensajes.get("carrito.total"));
        lblDetallesCarrito.setText(mensajes.get("carrito.detalle.etiqueta"));
        btnBuscarDetalle.setText(mensajes.get("boton.buscar"));
        btnAceptarDetalle.setText(mensajes.get("boton.aceptar"));
    }


    private void iconoImagen() {
        // Redimensionar icono "Buscar"
        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnBuscar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnBuscar.getImage();  // Convierte ImageIcon a Image
            Image newImgBuscar = imgBuscar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscarDetalle.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Detalle");
        }

// Redimensionar icono "Aceptar"
        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            Image imgAceptar = iconBtnAceptar.getImage();  // Convierte ImageIcon a Image
            Image newImgAceptar = imgAceptar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnAceptar = new ImageIcon(newImgAceptar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnAceptarDetalle.setIcon(iconBtnAceptar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar Detalle");
        }

    }

    public JTextField getTxtIdDet() {
        return txtIdDet;
    }

    public void setTxtIdDet(JTextField txtIdDet) {
        this.txtIdDet = txtIdDet;
    }

    public JButton getBtnBuscarDetalle() {
        return btnBuscarDetalle;
    }

    public void setBtnBuscarDetalle(JButton btnBuscarDetalle) {
        this.btnBuscarDetalle = btnBuscarDetalle;
    }

    public JTable getTblDetCarrito() {
        return tblDetCarrito;
    }

    public void setTblDetCarrito(JTable tblDetCarrito) {
        this.tblDetCarrito = tblDetCarrito;
    }

    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    public JTextField getTxtIVA() {
        return txtIVA;
    }

    public void setTxtIVA(JTextField txtIVA) {
        this.txtIVA = txtIVA;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public JButton getBtnAceptarDetalle() {
        return btnAceptarDetalle;
    }

    public void setBtnAceptarDetalle(JButton btnAceptarDetalle) {
        this.btnAceptarDetalle = btnAceptarDetalle;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JLabel getLblDetallesCarrito() {
        return lblDetallesCarrito;
    }

    public void setLblDetallesCarrito(JLabel lblDetallesCarrito) {
        this.lblDetallesCarrito = lblDetallesCarrito;
    }

    public JLabel getLblID() {
        return lblID;
    }

    public void setLblID(JLabel lblID) {
        this.lblID = lblID;
    }

    public JLabel getLblSubTotal() {
        return lblSubTotal;
    }

    public void setLblSubTotal(JLabel lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    public JLabel getLblIVA() {
        return lblIVA;
    }

    public void setLblIVA(JLabel lblIVA) {
        this.lblIVA = lblIVA;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setLblTotal(JLabel lblTotal) {
        this.lblTotal = lblTotal;
    }

    public DefaultTableModel getModelo() {
        return modelo;
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
