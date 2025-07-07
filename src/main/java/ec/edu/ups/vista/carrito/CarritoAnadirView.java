package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        this.mensajeHandler = mensajeHandler;
        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        actualizarTextos(mensajeHandler);
        limpiarCampos();
        iconoImagen();
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.anadir.titulo")); // Título ventana
        lblCodigo.setText(mensajes.get("producto.codigo"));
        lblNombre.setText(mensajes.get("producto.nombre"));
        lblPrecio.setText(mensajes.get("producto.precio"));
        lblCantidad.setText(mensajes.get("producto.cantidad"));
        lblSubTotal.setText(mensajes.get("carrito.subtotal"));
        lblIva.setText(mensajes.get("carrito.iva"));
        lblTotal.setText(mensajes.get("carrito.total"));

        btnBuscar.setText(mensajes.get("boton.buscar"));
        btnAnadir.setText(mensajes.get("boton.anadir"));
        btnGuardar.setText(mensajes.get("boton.guardar"));
        btnLimpiar.setText(mensajes.get("boton.limpiar"));

        // Puedes actualizar también los headers de la tabla si es necesario
        modelo.setColumnIdentifiers(new String[]{
                mensajes.get("producto.codigo"),
                mensajes.get("producto.nombre"),
                mensajes.get("producto.precio"),
                mensajes.get("producto.cantidad"),
                mensajes.get("carrito.subtotal")
        });
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

    private void iconoImagen() {
        // Redimensionar icono "Buscar"
        URL btBuscar = getClass().getClassLoader().getResource("imagenes/buscar.png");
        if (btBuscar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btBuscar);
            Image imgBuscar = iconBtnAceptar.getImage();
            Image newImgBuscar = imgBuscar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgBuscar);
            btnBuscar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

        // Redimensionar icono "Añadir"
        URL btAnadir = getClass().getClassLoader().getResource("imagenes/anadir.png");
        if (btAnadir != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btAnadir);
            Image imgAnadir = iconBtnAceptar.getImage();
            Image newImgAnadir = imgAnadir.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgAnadir);
            btnAnadir.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Añadir");
        }

        // Redimensionar icono "Guardar"
        URL btGuardar = getClass().getClassLoader().getResource("imagenes/guardar.png");
        if (btGuardar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btGuardar);
            Image imgGuardar = iconBtnAceptar.getImage();
            Image newImgGuardar = imgGuardar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgGuardar);
            btnGuardar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Guardar");
        }

        // Redimensionar icono "Limpiar"
        URL btLimpiar = getClass().getClassLoader().getResource("imagenes/limpiar.png");
        if (btLimpiar != null) {
            ImageIcon iconBtnAceptar = new ImageIcon(btLimpiar);
            Image imgLimpiar = iconBtnAceptar.getImage();
            Image newImgLimpiar = imgLimpiar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Ajustar el tamaño
            iconBtnAceptar = new ImageIcon(newImgLimpiar);
            btnLimpiar.setIcon(iconBtnAceptar);
        } else {
            System.err.println("Error: No se ha cargado el icono de Limpiar");
        }
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