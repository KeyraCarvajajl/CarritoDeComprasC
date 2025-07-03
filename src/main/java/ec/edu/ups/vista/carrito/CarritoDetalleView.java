package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        setSize(600, 500);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblDetCarrito.setModel(modelo);
        this.mensajeHandler = mensajeHandler;
        cambiarIdioma();

        URL btBuscar = LoginView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            btnBuscarDetalle.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar Detalle");
        }

        URL btAceptar = LoginView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (btAceptar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAceptar);
            btnAceptarDetalle.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Aceptar Detalle");
        }
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("carrito.detalle.titulo")); // Cambia según la clave de tu título

        lblTotal.setText(mensajeHandler.get("carrito.detalle.total")); // Etiqueta de total

        Object[] columnas = {
                mensajeHandler.get("carrito.detalle.columna.codigo"),
                mensajeHandler.get("carrito.detalle.columna.nombre"),
                mensajeHandler.get("carrito.detalle.columna.precio"),
                mensajeHandler.get("carrito.detalle.columna.cantidad"),
                mensajeHandler.get("carrito.detalle.columna.subtotal")
        };

        modelo.setColumnIdentifiers(columnas); // Este modelo debe ser tu DefaultTableModel
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
}
