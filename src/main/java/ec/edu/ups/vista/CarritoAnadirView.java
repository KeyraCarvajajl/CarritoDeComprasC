package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private DefaultTableModel tableModel;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnAñadir;
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
    private MensajeInternacionalizacionHandler mensajeHandler;


    public CarritoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.carrito.anadir"), true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setSize(500, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("tabla.columna.codigo"),
                mensajeHandler.get("tabla.columna.nombre"),
                mensajeHandler.get("tabla.columna.precio"),
                mensajeHandler.get("tabla.columna.cantidad"),
                mensajeHandler.get("tabla.columna.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        cambiarIdioma();
    }

    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
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

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public void setBtnAñadir(JButton btnAñadir) {
        this.btnAñadir = btnAñadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblCarrito) {
        this.tblProductos = tblCarrito;
    }

    public JTextField getTxtSubTotal() {
        return txtSubTotal;
    }

    public void setTxtSubTotal(JTextField txtSubTotal) {
        this.txtSubTotal = txtSubTotal;
    }

    public JTextField getTxtIva() {
        return txtIVA;
    }

    public void setTxtIva(JTextField txtIVA) {
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

    public void limpiarCampos(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtSubTotal.setText("");
        txtIVA.setText("");
        txtTotal.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.carrito.anadir"));

        lblCodigo.setText(mensajeHandler.get("etiqueta.codigo"));
        lblNombre.setText(mensajeHandler.get("etiqueta.nombre"));
        lblPrecio.setText(mensajeHandler.get("etiqueta.precio"));
        lblCantidad.setText(mensajeHandler.get("tabla.columna.cantidad"));
        lblSubTotal.setText(mensajeHandler.get("etiqueta.subtotal"));
        lblIva.setText(mensajeHandler.get("etiqueta.iva"));
        lblTotal.setText(mensajeHandler.get("etiqueta.total"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnAñadir.setText(mensajeHandler.get("boton.anadir"));
        btnGuardar.setText(mensajeHandler.get("boton.guardar"));
        btnLimpiar.setText(mensajeHandler.get("boton.limpiar"));

        String[] columnas = {
                mensajeHandler.get("tabla.columna.codigo"),
                mensajeHandler.get("tabla.columna.nombre"),
                mensajeHandler.get("tabla.columna.precio"),
                mensajeHandler.get("tabla.columna.cantidad"),
                mensajeHandler.get("tabla.columna.subtotal")
        };

        if (tblProductos.getColumnCount() == columnas.length) {
            for (int i = 0; i < columnas.length; i++) {
                tblProductos.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
            }
            tblProductos.getTableHeader().repaint();
        }
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

}