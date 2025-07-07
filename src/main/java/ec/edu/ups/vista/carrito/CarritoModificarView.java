package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoModificarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JButton btnBuscar;
    private JTable tblView;
    private JButton btnModificar;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JPanel panelPrincipal;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Modificar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(650, 700);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        // Modelo de tabla personalizado: solo columna 3 ("Cantidad") editable
        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo se puede editar la cantidad
            }
        };
        tblView.setModel(modelo);

        this.mensajeHandler = mensajeHandler;
        imagenIcono();
        actualizarTextos(mensajeHandler);
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(mensajeHandler.get("carrito.codigo"));
        lblFecha.setText(mensajeHandler.get("carrito.fecha"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnModificar.setText(mensajeHandler.get("boton.modificar"));

        setTitle(mensajeHandler.get("carrito.modificar.titulo"));

        modelo.setColumnIdentifiers(new String[]{
                mensajeHandler.get("producto.codigo"),
                mensajeHandler.get("producto.nombre"),
                mensajeHandler.get("producto.precio"),
                mensajeHandler.get("producto.cantidad"),
                mensajeHandler.get("carrito.subtotal")
        });
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

// Redimensionar icono "Modificar"
        URL btModificar = LoginView.class.getClassLoader().getResource("imagenes/modificar.png");
        if (btModificar != null) {
            ImageIcon iconBtnModificar = new ImageIcon(btModificar);
            Image imgModificar = iconBtnModificar.getImage();  // Convierte ImageIcon a Image
            Image newImgModificar = imgModificar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnModificar = new ImageIcon(newImgModificar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnModificar.setIcon(iconBtnModificar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Modificar");
        }

    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        // Cambiar el título de la ventana
        setTitle(mensajeHandler.get("producto.detalle.titulo"));

        // Cambiar las etiquetas de los campos
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblFecha.setText(mensajeHandler.get("producto.fecha"));

        // Cambiar los botones
        btnBuscar.setText(mensajeHandler.get("producto.buscar"));
        btnModificar.setText(mensajeHandler.get("producto.modificar"));

        // Actualizar tooltips si es necesario
        txtCodigo.setToolTipText(mensajeHandler.get("producto.codigo.tooltip"));
        txtFecha.setToolTipText(mensajeHandler.get("producto.fecha.tooltip"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }





    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(JTextField txtFecha) {
        this.txtFecha = txtFecha;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblView() {
        return tblView;
    }

    public void setTblView(JTable tblView) {
        this.tblView = tblView;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JLabel getLblCodigo() {
        return lblCodigo;
    }

    public void setLblCodigo(JLabel lblCodigo) {
        this.lblCodigo = lblCodigo;
    }

    public JLabel getLblFecha() {
        return lblFecha;
    }

    public void setLblFecha(JLabel lblFecha) {
        this.lblFecha = lblFecha;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
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
