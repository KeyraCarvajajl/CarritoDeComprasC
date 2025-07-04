package ec.edu.ups.vista.producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private MensajeInternacionalizacionHandler mensajeHandler;

    public ProductoAnadirView(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        setContentPane(panelPrincipal);
        setTitle(mensajeHandler.get("producto.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        cambiarIdioma(mensajeHandler);
    }

    public void cambiarIdioma(MensajeInternacionalizacionHandler mensajeHandler) {
        setTitle(mensajeHandler.get("producto.anadir.titulo"));

        lblCodigo.setText(mensajeHandler.get("producto.anadir.codigo"));
        lblNombre.setText(mensajeHandler.get("producto.anadir.nombre"));
        lblPrecio.setText(mensajeHandler.get("producto.anadir.precio"));

        btnAceptar.setText(mensajeHandler.get("producto.anadir.aceptar"));
        btnLimpiar.setText(mensajeHandler.get("producto.anadir.limpiar"));
    }

    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblCodigo.setText(ProductoAnadirView.this.mensajeHandler.get("producto.codigo"));
        lblNombre.setText(ProductoAnadirView.this.mensajeHandler.get("producto.nombre"));
        lblPrecio.setText(ProductoAnadirView.this.mensajeHandler.get("producto.precio"));

        btnAceptar.setText(ProductoAnadirView.this.mensajeHandler.get("producto.aceptar"));
        btnLimpiar.setText(ProductoAnadirView.this.mensajeHandler.get("producto.limpiar"));

        setTitle(ProductoAnadirView.this.mensajeHandler.get("producto.anadir.titulo"));
    }



    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }


    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

}