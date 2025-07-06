package ec.edu.ups.vista.producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductoModificarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnModificar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblModificarProducto;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public ProductoModificarView(MensajeInternacionalizacionHandler mensajeHandler) {
        super(mensajeHandler.get("ventana.producto.modificar"), true, true, false, true);
        this.mensajeHandler = mensajeHandler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(550, 550);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        cambiarIdioma(mensajeHandler);
        imagenIcon();
    }

    private void imagenIcon() {
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
        setTitle(mensajeHandler.get("producto.modificar.titulo"));

        lblModificarProducto.setText(mensajeHandler.get("producto.modificar.titulo"));
        lblCodigo.setText(mensajeHandler.get("producto.modificar.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.modificar.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.modificar.precio"));

        btnBuscar.setText(mensajeHandler.get("producto.modificar.buscar"));
        btnEliminar.setText(mensajeHandler.get("producto.modificar.eliminar"));
        btnModificar.setText(mensajeHandler.get("producto.modificar.modificar"));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblModificarProducto.setText(ProductoModificarView.this.mensajeHandler.get("producto.modificar.titulo"));
        lblCodigo.setText(ProductoModificarView.this.mensajeHandler.get("producto.codigo"));
        lblNombre.setText(ProductoModificarView.this.mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(ProductoModificarView.this.mensajeHandler.get("producto.precio"));

        btnBuscar.setText(ProductoModificarView.this.mensajeHandler.get("producto.buscar"));
        btnEliminar.setText(ProductoModificarView.this.mensajeHandler.get("producto.eliminar"));
        btnModificar.setText(ProductoModificarView.this.mensajeHandler.get("producto.modificar"));

        setTitle(ProductoModificarView.this.mensajeHandler.get("producto.modificar.titulo"));
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

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }



    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        cambiarIdioma(mensajeHandler);
    }
}