package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.PreguntasController;
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
        PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
        MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es","EC");


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                LoginView loginView = new LoginView(mensajeHandler);
                RegistrarseView registrarseView = new RegistrarseView(mensajeHandler);
                CambiarContraseniaView cambiarContraseniaView = new CambiarContraseniaView(mensajeHandler);
                CuestionarioView cuestionarioView = new CuestionarioView(mensajeHandler, usuarioDAO);

                UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(mensajeHandler);
                UsuarioListaView usuarioListaView = new UsuarioListaView(mensajeHandler);
                UsuarioModificarView usuarioModificarView = new UsuarioModificarView(mensajeHandler);

                UsuarioController usuarioController = new UsuarioController(
                        usuarioDAO, loginView, registrarseView,
                        usuarioListaView, usuarioModificarView,
                        usuarioEliminarView, mensajeHandler
                );

                PreguntasDAO preguntasDAO = new PreguntasDAOMemoria();
                PreguntasController preguntasController = new PreguntasController(
                        cuestionarioView,
                        cambiarContraseniaView,
                        preguntasDAO,
                        mensajeHandler,
                        null
                );

                loginView.setVisible(true);

                loginView.getBtnRegistrarse().addActionListener(e -> {
                    JFrame frame = new JFrame(mensajeHandler.get("login.registrarse"));
                    frame.setContentPane(registrarseView.getContentPane()); // usa la instancia conectada al controlador
                    frame.setSize(500, 800);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                });


                loginView.getBtnOlvidarContrasenia().addActionListener(e -> {
                    CuestionarioView recuperarView = new CuestionarioView(mensajeHandler, usuarioDAO);
                    JFrame frame = new JFrame(mensajeHandler.get("login.olvidaste"));
                    frame.setContentPane(recuperarView.getContentPane());
                    frame.setSize(600, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(true);
                });


                usuarioController.setPreguntasDependencias(cuestionarioView, cambiarContraseniaView, preguntasDAO, mensajeHandler);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAutenticado != null) {

                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO carritoDAO = new CarritoDAOMemoria();
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
                            usuarioController.setUsuarioModificarView(usuarioModificarView);
                            usuarioController.configurarEventosModificar();

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
                            usuarioController.setUsuarioEliminarView(usuarioEliminarView);
                            usuarioController.setUsuarioModificarView(usuarioModificarView);

                            principalView.setVisible(true);
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



                            principalView.getMenuItemIdiomaEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.actualizarTextos("es", "EC");
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
                                        usuarioController.actualizarTablaUsuarios(); // Mostrar todos los usuarios
                                        usuarioController.configurarEventosLista(); // Configura los botones Buscar y Cerrar
                                        principalView.getjDesktopPane().add(usuarioListaView);
                                        usuarioListaView.setVisible(true);
                                    } else {
                                        usuarioListaView.toFront();
                                    }
                                }
                            });



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

                            principalView.getMenuItemListaUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!usuarioListaView.isVisible()) {
                                        usuarioController.actualizarTablaUsuarios(); // ← nombre correcto del método
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
                                    principalView.actualizarTextos("en", "US");

                                    productoAnadirView.actualizarTextos(mensajeHandler);
                                    productoEliminarView.actualizarTextos(mensajeHandler);
                                    productoListaView.actualizarTextos(mensajeHandler);
                                    productoModificarView.actualizarTextos(mensajeHandler);

                                    carritoAnadirView.actualizarTextos(mensajeHandler);
                                    carritoDetalleView.actualizarTextos(mensajeHandler);
                                    carritoEliminarView.actualizarTextos(mensajeHandler);
                                    carritoListaView.actualizarTextos(mensajeHandler);
                                    carritoModificarView.actualizarTextos(mensajeHandler);

                                    cambiarContraseniaView.actualizarTextos(mensajeHandler);
                                    cuestionarioView.actualizarTextos(mensajeHandler);

                                    loginView.actualizarTextos(mensajeHandler);
                                    registrarseView.actualizarTextos(mensajeHandler);
                                    usuarioEliminarView.actualizarTextos(mensajeHandler);
                                    usuarioListaView.actualizarTextos(mensajeHandler);
                                    usuarioModificarView.actualizarTextos(mensajeHandler);
                                }
                            });

                            principalView.getMenuItemIdiomaEspanol().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.actualizarTextos("es", "EC");

                                    productoAnadirView.actualizarTextos(mensajeHandler);
                                    productoEliminarView.actualizarTextos(mensajeHandler);
                                    productoListaView.actualizarTextos(mensajeHandler);
                                    productoModificarView.actualizarTextos(mensajeHandler);

                                    carritoAnadirView.actualizarTextos(mensajeHandler);
                                    carritoDetalleView.actualizarTextos(mensajeHandler);
                                    carritoEliminarView.actualizarTextos(mensajeHandler);
                                    carritoListaView.actualizarTextos(mensajeHandler);
                                    carritoModificarView.actualizarTextos(mensajeHandler);

                                    cambiarContraseniaView.actualizarTextos(mensajeHandler);
                                    cuestionarioView.actualizarTextos(mensajeHandler);

                                    loginView.actualizarTextos(mensajeHandler);
                                    registrarseView.actualizarTextos(mensajeHandler);
                                    usuarioEliminarView.actualizarTextos(mensajeHandler);
                                    usuarioListaView.actualizarTextos(mensajeHandler);
                                    usuarioModificarView.actualizarTextos(mensajeHandler);
                                }
                            });

                            principalView.getMenuItemIdiomaFrances().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    principalView.actualizarTextos("fr", "FR");

                                    productoAnadirView.actualizarTextos(mensajeHandler);
                                    productoEliminarView.actualizarTextos(mensajeHandler);
                                    productoListaView.actualizarTextos(mensajeHandler);
                                    productoModificarView.actualizarTextos(mensajeHandler);

                                    carritoAnadirView.actualizarTextos(mensajeHandler);
                                    carritoDetalleView.actualizarTextos(mensajeHandler);
                                    carritoEliminarView.actualizarTextos(mensajeHandler);
                                    carritoListaView.actualizarTextos(mensajeHandler);
                                    carritoModificarView.actualizarTextos(mensajeHandler);

                                    cambiarContraseniaView.actualizarTextos(mensajeHandler);
                                    cuestionarioView.actualizarTextos(mensajeHandler);

                                    loginView.actualizarTextos(mensajeHandler);
                                    registrarseView.actualizarTextos(mensajeHandler);
                                    usuarioEliminarView.actualizarTextos(mensajeHandler);
                                    usuarioListaView.actualizarTextos(mensajeHandler);
                                    usuarioModificarView.actualizarTextos(mensajeHandler);
                                }
                            });

                        }
                    }
                });
            }
        });
    }
}