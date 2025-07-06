package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoEliminarView extends JInternalFrame {
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTable tblLProductos;
    private JButton btnEliminar;
    private JButton btnVaciar;
    private JPanel panelPrincipal;
    private JLabel lblCodigo;
    private JTextField txtFecha;
    private JLabel lblFecha;
    private MensajeInternacionalizacionHandler mensajeHandler;
    private DefaultTableModel modelo;

    public CarritoEliminarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Nombre", "Precio", "Cantidad", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblLProductos.setModel(modelo);
        this.mensajeHandler = mensajeHandler;


        cambiarIdioma(mensajeHandler);
        iconoImagen();
    }

    private void iconoImagen() {
        // Redimensionar icono "Eliminar"
        URL btEliminar = LoginView.class.getClassLoader().getResource("imagenes/eliminar.png");
        if (btEliminar != null) {
            ImageIcon iconBtnEliminar = new ImageIcon(btEliminar);
            Image imgEliminar = iconBtnEliminar.getImage();  // Convierte ImageIcon a Image
            Image newImgEliminar = imgEliminar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnEliminar = new ImageIcon(newImgEliminar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnEliminar.setIcon(iconBtnEliminar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Eliminar");
        }

// Redimensionar icono "Vaciar"
        URL btVaciar = LoginView.class.getClassLoader().getResource("imagenes/vaciar.png");
        if (btVaciar != null) {
            ImageIcon iconBtnVaciar = new ImageIcon(btVaciar);
            Image imgVaciar = iconBtnVaciar.getImage();  // Convierte ImageIcon a Image
            Image newImgVaciar = imgVaciar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnVaciar = new ImageIcon(newImgVaciar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnVaciar.setIcon(iconBtnVaciar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Vaciar");
        }

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

    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        // Actualiza el título de la ventana
        setTitle(mensajeHandler.get("producto.lista.titulo"));

        // Actualiza las etiquetas
        lblCodigo.setText(mensajeHandler.get("producto.codigo"));
        lblFecha.setText(mensajeHandler.get("producto.fecha"));

        // Actualiza los botones
        btnBuscar.setText(mensajeHandler.get("producto.buscar"));
        btnEliminar.setText(mensajeHandler.get("producto.eliminar"));
        btnVaciar.setText(mensajeHandler.get("producto.vaciar"));

        // Actualiza los tooltips si es necesario
        txtCodigo.setToolTipText(mensajeHandler.get("producto.codigo.tooltip"));
        txtFecha.setToolTipText(mensajeHandler.get("producto.fecha.tooltip"));
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
}
