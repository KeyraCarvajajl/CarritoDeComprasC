package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoEliminarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblLProductos;
    private JButton btnEliminar;
    private JButton btnVaciar;
    private JPanel panelPrincipal;
    private JLabel lblCodigo;
    private MensajeInternacionalizacionHandler mensajeHandler;


    public CarritoEliminarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.carrito.eliminar"), true, true, false, true);
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
                mensajeHandler.get("tabla.columna.subtotal") // o "total" si usas otra clave
        };
        modelo.setColumnIdentifiers(columnas);
        tblLProductos.setModel(modelo);

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

    public JButton getBtnVaciar() {
        return btnVaciar;
    }

    public void setBtnVaciar(JButton btnVaciar) {
        this.btnVaciar = btnVaciar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public JTable getTblLProductos() {
        return tblLProductos;
    }

    public void setTblLProductos(JTable tblLProductos) {
        this.tblLProductos = tblLProductos;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
    }

    public void cambiarIdioma() {
        setTitle(mensajeHandler.get("ventana.carrito.eliminar"));

        lblCodigo.setText(mensajeHandler.get("etiqueta.codigo"));
        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeHandler.get("boton.eliminar"));
        btnVaciar.setText(mensajeHandler.get("boton.vaciar"));

        String[] columnas = {
                mensajeHandler.get("tabla.columna.codigo"),
                mensajeHandler.get("tabla.columna.nombre"),
                mensajeHandler.get("tabla.columna.precio"),
                mensajeHandler.get("tabla.columna.cantidad"),
                mensajeHandler.get("tabla.columna.subtotal") // O "total" si esa es tu clave
        };

        if (tblLProductos.getColumnCount() == columnas.length) {
            for (int i = 0; i < columnas.length; i++) {
                tblLProductos.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
            }
            tblLProductos.getTableHeader().repaint();
        }
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

}
