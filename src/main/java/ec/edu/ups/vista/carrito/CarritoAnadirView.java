package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private DefaultTableModel tableModel;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubTotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JComboBox<String> cbxCantidad;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSubTotal;
    private JLabel lblIva;
    private JLabel lblTotal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Carrito de Compras", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        this.mensajeHandler = mensajeHandler;
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        cambiarIdioma(mensajeHandler);
        limpiarCampos();
        actualizarTextos(mensajeHandler);
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("carrito.anadir.titulo"));

        lblCodigo.setText(mensajeHandler.get("carrito.anadir.codigo"));
        lblNombre.setText(mensajeHandler.get("carrito.anadir.nombre"));
        lblPrecio.setText(mensajeHandler.get("carrito.anadir.precio"));
        lblCantidad.setText(mensajeHandler.get("carrito.anadir.cantidad"));
        lblSubTotal.setText(mensajeHandler.get("carrito.anadir.subtotal"));
        lblIva.setText(mensajeHandler.get("carrito.anadir.iva"));
        lblTotal.setText(mensajeHandler.get("carrito.anadir.total"));

        btnBuscar.setText(mensajeHandler.get("carrito.anadir.buscar"));
        btnAnadir.setText(mensajeHandler.get("carrito.anadir.anadir"));
        btnGuardar.setText(mensajeHandler.get("carrito.anadir.guardar"));
        btnLimpiar.setText(mensajeHandler.get("carrito.anadir.limpiar"));
    }


    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSubTotal.setText("");
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.codigo"));
        lblNombre.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.nombre"));
        lblPrecio.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.precio"));
        lblCantidad.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.cantidad"));
        lblSubTotal.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.subtotal"));
        lblIva.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.iva"));
        lblTotal.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.total"));

        btnBuscar.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.buscar"));
        btnAnadir.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.anadir"));
        btnGuardar.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.guardar"));
        btnLimpiar.setText(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.limpiar"));

        setTitle(CarritoAnadirView.this.mensajeHandler.get("carrito.anadir.titulo"));
    }


    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public void setBtnAnadir(JButton btnAñadir) {
        this.btnAnadir = btnAñadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
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

    public JComboBox<String> getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox<String> cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void setLblNombre(JLabel lblNombre) {
        this.lblNombre = lblNombre;
    }

    public JLabel getLblPrecio() {
        return lblPrecio;
    }

    public void setLblPrecio(JLabel lblPrecio) {
        this.lblPrecio = lblPrecio;
    }

    public JLabel getLblCantidad() {
        return lblCantidad;
    }

    public void setLblCantidad(JLabel lblCantidad) {
        this.lblCantidad = lblCantidad;
    }

    public JLabel getLblSubTotal() {
        return lblSubTotal;
    }

    public void setLblSubTotal(JLabel lblSubTotal) {
        this.lblSubTotal = lblSubTotal;
    }

    public JLabel getLblIva() {
        return lblIva;
    }

    public void setLblIva(JLabel lblIva) {
        this.lblIva = lblIva;
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