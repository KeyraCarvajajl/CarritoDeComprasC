package ec.edu.ups.vista.carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class CarritoListaView extends JInternalFrame {

    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnListar;
    private JTable tblPCarrito;
    private JPanel panelPrincipal;
    private JLabel lblCarrito;
    private JTextField txtTotalCarrito;
    private JLabel lblTotalCarrito;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public CarritoListaView(MensajeInternacionalizacionHandler mensajeHandler) {
        super("Listar Carrito", true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        // Crear panel principal y configurarlo
        panelPrincipal = new JPanel(null);
        panelPrincipal.setBackground(new Color(250,222,212)); // Fondo rosado claro

        // Inicializar componentes
        lblCarrito = new JLabel("Código:");
        txtCodigo = new JTextField();
        btnBuscar = new JButton("Buscar");
        btnListar = new JButton("Listar");
        lblTotalCarrito = new JLabel("Total:");
        txtTotalCarrito = new JTextField();

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Subtotal"}, 0);
        tblPCarrito = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tblPCarrito);

        // Establecer posiciones
        lblCarrito.setBounds(30, 20, 100, 25);
        txtCodigo.setBounds(130, 20, 150, 25);
        btnBuscar.setBounds(300, 20, 100, 30);
        btnListar.setBounds(410, 20, 100, 30);
        scrollPane.setBounds(30, 70, 480, 300);
        lblTotalCarrito.setBounds(30, 390, 100, 25);
        txtTotalCarrito.setBounds(130, 390, 100, 25);

        // Agregar componentes al panel
        panelPrincipal.add(lblCarrito);
        panelPrincipal.add(txtCodigo);
        panelPrincipal.add(btnBuscar);
        panelPrincipal.add(btnListar);
        panelPrincipal.add(scrollPane);
        panelPrincipal.add(lblTotalCarrito);
        panelPrincipal.add(txtTotalCarrito);

        // Configurar ventana
        setContentPane(panelPrincipal);
        setSize(600, 600);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        imagenIcono();
        actualizarTextos(mensajeHandler);
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCarrito.setText(mensajeHandler.get("carrito.lista.titulo"));
        lblTotalCarrito.setText(mensajeHandler.get("carrito.total"));

        btnBuscar.setText(mensajeHandler.get("boton.buscar"));
        btnListar.setText(mensajeHandler.get("boton.listar"));

        setTitle(mensajeHandler.get("carrito.lista.titulo"));

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
            Image newImgBuscar = imgBuscar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnBuscar = new ImageIcon(newImgBuscar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnBuscar.setIcon(iconBtnBuscar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Buscar");
        }

// Redimensionar icono "Listar"
        URL btListar = LoginView.class.getClassLoader().getResource("imagenes/listar.png");
        if (btListar != null) {
            ImageIcon iconBtnListar = new ImageIcon(btListar);
            Image imgListar = iconBtnListar.getImage();  // Convierte ImageIcon a Image
            Image newImgListar = imgListar.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnListar = new ImageIcon(newImgListar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnListar.setIcon(iconBtnListar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Listar");
        }

    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        // Cambiar el título de la ventana
        setTitle(mensajeHandler.get("carrito.detalle.titulo"));

        // Actualiza las etiquetas
        lblCarrito.setText(mensajeHandler.get("carrito.titulo"));
        lblTotalCarrito.setText(mensajeHandler.get("carrito.total"));

        // Actualiza los botones
        btnBuscar.setText(mensajeHandler.get("carrito.buscar"));
        btnListar.setText(mensajeHandler.get("carrito.listar"));

        // Actualiza los tooltips si es necesario
        txtCodigo.setToolTipText(mensajeHandler.get("carrito.codigo.tooltip"));
        txtTotalCarrito.setToolTipText(mensajeHandler.get("carrito.total.tooltip"));
        txtTotalCarrito.setToolTipText(mensajeHandler.get("carrito.total.pagar"));
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

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public JTextField getTxtTotal() {
        return txtTotalCarrito;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotalCarrito = txtTotal;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarCarrito() {
    }

    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }
}
