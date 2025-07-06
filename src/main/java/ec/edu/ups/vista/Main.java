package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.PreguntasDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.PreguntasDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
        MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es","EC");


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                LoginView loginView = new LoginView(mensajeHandler);
                RegistrarseView registrarseView = new RegistrarseView(mensajeHandler);
                PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
                CuestionarioView cuestionarioView = new CuestionarioView(mensajeHandler, usuarioDAO);
                CambiarContraseniaView cuestionarioRecuView = new CambiarContraseniaView(mensajeHandler);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, registrarseView,mensajeHandler);
                loginView.setVisible(true);
                usuarioController.setPreguntasDependencias(cuestionarioView, cuestionarioRecuView, preguntasDAO, mensajeHandler);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAutenticado != null) {

                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO = new CarritoDAOMemoria();
                            PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
                            MenuPrincipalView principalView = new MenuPrincipalView(mensajeHandler);

                            ProductoAnadirView productoAnadirView = new ProductoAnadirView(mensajeHandler);
                            ProductoListaView productoListaView = new ProductoListaView(mensajeHandler);
                            ProductoModificarView productoModificarView = new ProductoModificarView(mensajeHandler);
                            ProductoEliminarView productoEliminarView = new ProductoEliminarView(mensajeHandler);

                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(mensajeHandler);
                            CarritoListaView carritoListaView = new CarritoListaView(mensajeHandler);
                            CarritoEliminarView carritoEliminarView = new CarritoEliminarView(mensajeHandler);
                            CarritoModificarView carritoModificarView = new CarritoModificarView(mensajeHandler);
                            CarritoDetalleView carritoDetalleView = new CarritoDetalleView(mensajeHandler);

                            UsuarioListaView usuarioListaView = new UsuarioListaView(mensajeHandler);
                            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mensajeHandler);
                            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mensajeHandler);

                            ProductoController productoController = new ProductoController(
                                    productoAnadirView,
                                    productoListaView,
                                    productoModificarView,
                                    productoEliminarView,
                                    carritoAnadirView,
                                    productoDAO
                            );
                            CarritoController carritoController = new CarritoController(
                                    carritoDAO,
                                    productoDAO,
                                    carritoAnadirView,
                                    carritoListaView,
                                    carritoEliminarView,
                                    carritoModificarView,
                                    carritoDetalleView,
                                    mensajeHandler);

                            productoController.setProductoAnadirView(productoAnadirView);
                            productoController.setProductoListaView(productoListaView);
                            productoController.setProductoModificarView(productoModificarView);
                            productoController.setProductoEliminarView(productoEliminarView);
                            productoController.setCarritoAnadirView(carritoAnadirView);
                            usuarioController.setUsuarioListarView(usuarioListaView);
                            usuarioController.setMenuPrincipalView(principalView);

                            // CREAR PRODUCTO
                            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoAnadirView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoAnadirView);
                                        productoAnadirView.setVisible(true);
                                    } else {
                                        productoAnadirView.toFront();
                                    }
                                }
                            });

                        // LISTAR PRODUCTOS
                            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoListaView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoListaView);
                                        productoListaView.setVisible(true);
                                    } else {
                                        productoListaView.toFront();
                                    }
                                }
                            });

// MODIFICAR PRODUCTO
                            principalView.getMenuItemModificarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoModificarView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoModificarView);
                                        productoModificarView.setVisible(true);
                                    } else {
                                        productoModificarView.toFront();
                                    }
                                }
                            });

// ELIMINAR PRODUCTO
                            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!productoEliminarView.isVisible()) {
                                        principalView.getjDesktopPane().add(productoEliminarView);
                                        productoEliminarView.setVisible(true);
                                    } else {
                                        productoEliminarView.toFront();
                                    }
                                }
                            });

                            // AÑADIR CARRITO
                            principalView.getMenuItemAnadirCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoAnadirView.isVisible()) {
                                        principalView.getjDesktopPane().add(carritoAnadirView);
                                        carritoAnadirView.setVisible(true);
                                    } else {
                                    carritoAnadirView.toFront();
                                    }
                                }
                            });

                            // BUSCAR CARRITO
                            principalView.getMenuItemListaCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoListaView.isVisible()) {
                                        principalView.getjDesktopPane().add(carritoListaView);
                                        carritoListaView.setVisible(true);
                                    } else {
                                        carritoListaView.toFront();
                                    }
                                }
                            });

                            // ELIMINAR CARRITO
                            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoEliminarView.isVisible()) {
                                        principalView.getjDesktopPane().add(carritoEliminarView);
                                        carritoEliminarView.setVisible(true);
                                    } else {
                                        carritoEliminarView.toFront();
                                    }
                                }
                            });

                            // MODIFICAR CARRITO
                            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoModificarView.isVisible()) {
                                        principalView.getjDesktopPane().add(carritoModificarView);
                                        carritoModificarView.setVisible(true);
                                    } else {
                                        carritoModificarView.toFront();
                                    }
                                }
                            });

                            // DETALLE CARRITO
                            principalView.getMenuItemDetalleCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoDetalleView.isVisible()) {
                                        principalView.getjDesktopPane().add(carritoDetalleView);
                                        carritoDetalleView.setVisible(true);
                                    } else {
                                        carritoDetalleView.toFront();
                                    }
                                }
                            });


                            principalView.getMenuItemIdiomaEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("es", "EC");
                                }
                            });

                            // MODIFICAR USUARIO
                            principalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioModificarView.isVisible()) {
                                        principalView.getjDesktopPane().add(usuarioModificarView);
                                        usuarioModificarView.setVisible(true);
                                    } else {
                                        usuarioModificarView.toFront();
                                    }
                                }
                            });

// ELIMINAR USUARIO
                            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioEliminarView.isVisible()) {
                                        principalView.getjDesktopPane().add(usuarioEliminarView);
                                        usuarioEliminarView.setVisible(true);
                                    } else {
                                        usuarioEliminarView.toFront();
                                    }
                                }
                            });

                            principalView.getMenuItemListaUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioListaView.isVisible()) {
                                        principalView.getjDesktopPane().add(usuarioListaView);
                                        usuarioListaView.setVisible(true);
                                    } else {
                                        usuarioListaView.toFront();
                                    }
                                }
                            });


                            principalView.getMenuItemIdiomaIngles().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("en", "US");
                                }
                            });
                            principalView.getMenuItemIdiomaFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.cambiarIdioma("fr", "FR");
                                }
                            });
                            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    usuarioController.cerrarSesion();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}