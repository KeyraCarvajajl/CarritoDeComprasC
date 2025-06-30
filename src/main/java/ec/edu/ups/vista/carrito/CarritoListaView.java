package ec.edu.ups.vista.carrito;


import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoListaView  extends JInternalFrame{
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblPCarrito;
    private JPanel panelPrincipal;
    private JLabel lblCarrito;
    private JTextField txtTotal;
    DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.carrito.lista"), true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeHandler.get("tabla.columna.codigo"),
                mensajeHandler.get("tabla.columna.nombre"),
                mensajeHandler.get("tabla.columna.precio"),
                mensajeHandler.get("tabla.columna.cantidad"),
                mensajeHandler.get("tabla.columna.total")
        };
        modelo.setColumnIdentifiers(columnas);
        tblPCarrito.setModel(modelo);

        cambiarIdioma();
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

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.carrito.lista"));

        lblCarrito.setText(mensajeHandler.get("etiqueta.codigo.carrito"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnListar.setText(mensajeHandler.get("boton.listar"));

        String[] columnas = {
                mensajeHandler.get("tabla.columna.codigo"),
                mensajeHandler.get("tabla.columna.nombre"),
                mensajeHandler.get("tabla.columna.precio"),
                mensajeHandler.get("tabla.columna.cantidad"),
                mensajeHandler.get("tabla.columna.total")
        };

        if (tblPCarrito.getColumnCount() == columnas.length) {
            for (int i = 0; i < columnas.length; i++) {
                tblPCarrito.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
            }
            tblPCarrito.getTableHeader().repaint();
        }
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
}
