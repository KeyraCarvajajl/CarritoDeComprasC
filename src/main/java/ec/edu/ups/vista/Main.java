package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Usuario;

import javax.swing.*;
import java.awt.event.*;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        java.awt.EventQueue.invokeLater(() -> {

            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            LoginView loginView = new LoginView();
            loginView.setVisible(true);

            UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView);

            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                    if (usuarioAutenticado != null) {

                        ProductoDAO productoDAO = new ProductoDAOMemoria();
                        CarritoDAO carritoDAO = new CarritoDAOMemoria();

                        Principal principalView = new Principal();

                        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                        ProductoListaView productoListaView = new ProductoListaView();
                        ProductoModificarView productoModificarView = new ProductoModificarView();
                        ProductoEliminarView productoEliminarView = new ProductoEliminarView();
                        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                        CarritoListaView carritoListarView = new CarritoListaView();
                        CarritoEliminarView carritoEliminarView = new CarritoEliminarView();

                        ProductoController productoController = new ProductoController(
                                productoAnadirView,
                                productoListaView,
                                productoModificarView,
                                productoEliminarView,
                                carritoAnadirView,
                                productoDAO
                        );
                        CarritoController carritoController = new CarritoController(carritoDAO, productoDAO, carritoAnadirView);
                        productoController.setProductoAnadirView(productoAnadirView);
                        productoController.setProductoListaView(productoListaView);
                        productoController.setProductoModificarView(productoModificarView);
                        productoController.setProductoEliminarView(productoEliminarView);
                        productoController.setCarritoAnadirView(carritoAnadirView);
                        carritoController.setCarritoListaView(carritoListarView);
                        carritoController.setCarritoEliminarView(carritoEliminarView);

                        principalView.getMenuItemBuscarCarrito().addActionListener(e1 -> {
                            if (!carritoListarView.isVisible()) {
                                principalView.getjDesktopPane().add(carritoListarView);
                                carritoListarView.setVisible(true);
                            } else {
                                carritoListarView.toFront();
                            }
                        });

                        principalView.getMenuItemEliminarCarrito().addActionListener(e1 -> {
                            if (!carritoEliminarView.isVisible()) {
                                principalView.getjDesktopPane().add(carritoEliminarView);
                                carritoEliminarView.setVisible(true);
                            } else {
                                carritoEliminarView.toFront();
                            }
                        });

                        principalView.getMenuItemCrearProducto().addActionListener(e1 -> {
                            if (!productoAnadirView.isVisible()) {
                                principalView.getjDesktopPane().add(productoAnadirView);
                                productoAnadirView.setVisible(true);
                            } else {
                                productoAnadirView.toFront();
                            }
                        });

                        principalView.getMenuItemBuscarProducto().addActionListener(e1 -> {
                            if (!productoListaView.isVisible()) {
                                principalView.getjDesktopPane().add(productoListaView);
                                productoListaView.setVisible(true);
                            } else {
                                productoListaView.toFront();
                            }
                        });

                        principalView.getMenuItemActualizarProducto().addActionListener(e1 -> {
                            if (!productoModificarView.isVisible()) {
                                principalView.getjDesktopPane().add(productoModificarView);
                                productoModificarView.setVisible(true);
                            } else {
                                productoModificarView.toFront();
                            }
                        });

                        principalView.getMenuItemEliminarProducto().addActionListener(e1 -> {
                            if (!productoEliminarView.isVisible()) {
                                principalView.getjDesktopPane().add(productoEliminarView);
                                productoEliminarView.setVisible(true);
                            } else {
                                productoEliminarView.toFront();
                            }
                        });

                        principalView.getMenuItemAñadirCarrito().addActionListener(e1 -> {
                            if (!carritoAnadirView.isVisible()) {
                                principalView.getjDesktopPane().add(carritoAnadirView);
                                carritoAnadirView.setVisible(true);
                            } else {
                                carritoAnadirView.toFront();
                            }
                        });

                        principalView.getMenuItemIdiomaEspanol().addActionListener(e1 -> principalView.cambiarIdioma("es", "EC"));
                        principalView.getMenuItemIdiomaIngles().addActionListener(e1 -> principalView.cambiarIdioma("en", "US"));
                        principalView.getMenuItemIdiomaFrances().addActionListener(e1 -> principalView.cambiarIdioma("fr", "FR"));

                        principalView.setVisible(true);
                    }
                }
            });
        });
    }
}
