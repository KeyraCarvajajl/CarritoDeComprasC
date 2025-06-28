package ec.edu.ups.vista;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoListaView  extends JInternalFrame{
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblPCarrito;
    private JPanel panelPrincipal;
    private JTextField txtTotal;
    DefaultTableModel modelo;

    public CarritoListaView(){
        super("Listado de Carritos", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblPCarrito.setModel(modelo);
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

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void cargarCarrito(){

    }
}
