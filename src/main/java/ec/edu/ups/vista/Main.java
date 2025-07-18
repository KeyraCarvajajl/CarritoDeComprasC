package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.PreguntasController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.modelo.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.carrito.*;
import ec.edu.ups.vista.preguntas.CambiarContraseniaView;
import ec.edu.ups.vista.preguntas.CuestionarioView;
import ec.edu.ups.vista.producto.ProductoAnadirView;
import ec.edu.ups.vista.producto.ProductoEliminarView;
import ec.edu.ups.vista.producto.ProductoListaView;
import ec.edu.ups.vista.producto.ProductoModificarView;
import ec.edu.ups.vista.usuario.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Clase principal del sistema de Carrito de Compras.
 * Inicializa la aplicación, configura la internacionalización,
 * selecciona el tipo de almacenamiento y muestra la vista de login.
 *
 * Esta clase también es responsable de crear las instancias iniciales
 * de los DAOs, controladores y vistas requeridas para el funcionamiento
 * del sistema.
 *
 * @author Keyra
 */
public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        String[] opciones = {"Memoria", "Archivo de Texto", "Archivo Binario"};
        String seleccion = (String) JOptionPane.showInputDialog(null,
                "Seleccione el tipo de almacenamiento:",
                "Configuración de almacenamiento",
                JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        UsuarioDAO usuarioDAO = new UsuarioDAOArchivoTexto();
        ProductoDAO productoDAO = new ProductoDAOArchivoTexto();
        CarritoDAO carritoDAO = new CarritoDAOArchivoTexto(productoDAO);
        PreguntasDAO preguntasDAO = new PreguntasDAOArchivoTexto();
        RespuestaDAO respuestaDAO = new RespuestaDAOArchivoTexto();
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            System.out.println("Usuario: " + u.getUsername() + " - " + u.getNombre() + " - " + u.getRol());
        }

        MensajeInternacionalizacionHandler mensajeHandler = new MensajeInternacionalizacionHandler("es","EC");

        if (preguntasDAO.obtenerTodas().isEmpty()) {
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es tu color favorito?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es el nombre de tu primer mascota?"));
            preguntasDAO.guardar(new Preguntas("base", "¿En qué ciudad naciste?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es tu comida favorita?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es tu película favorita?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es tu canción favorita?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Qué deporte practicaste en la infancia?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es tu libro favorito?"));
            preguntasDAO.guardar(new Preguntas("base", "¿A qué escuela primaria asististe?"));
            preguntasDAO.guardar(new Preguntas("base", "¿Cuál es el segundo nombre de tu madre?"));
        }

        if (productoDAO.listarTodos().isEmpty()) {
            productoDAO.crear(new Producto(1, "Manzanas", 0.30));
            productoDAO.crear(new Producto(2, "Pan", 0.15));
            productoDAO.crear(new Producto(3, "Leche", 1.20));
            productoDAO.crear(new Producto(4, "Arroz", 0.50));
            productoDAO.crear(new Producto(5, "Huevos", 1.80));
            System.out.println("Productos iniciales cargados.");
        }


        if (carritoDAO.listarTodos().isEmpty()) {
            Producto p1 = new Producto(1, "Camiseta", 10.0);
            Producto p2 = new Producto(2, "Zapatos", 25.0);

            Carrito carrito = new Carrito(100, new Date());
            carrito.agregarProducto(p1, 2); // cantidad 2
            carrito.agregarProducto(p2, 1); // cantidad 1

            carritoDAO.crear(carrito);
            System.out.println("Carrito inicial cargado.");
        }



        if (respuestaDAO.listarTodos().isEmpty()) {
            respuestaDAO.guardarRespuesta(new Respuesta("Keyra2006", "¿Nombre de tu primer mascota?", "Toby"));
            respuestaDAO.guardarRespuesta(new Respuesta("Keyra2006", "¿Nombre de tu escuela?", "Salesiana"));
            System.out.println("Respuestas guardadas exitosamente.");
        }




        EventQueue.invokeLater(new Runnable() {

            /**
             * Método que ejecuta la lógica principal del sistema de carrito de compras.
             *
             * Este método inicializa todas las vistas y controladores necesarios para el funcionamiento
             * de la aplicación. Incluye la configuración de la vista de login, registro, recuperación
             * de contraseña y posterior carga del menú principal si la autenticación es exitosa.
             *
             * Se establecen los listeners para cada opción del menú y para los botones principales,
             * incluyendo internacionalización dinámica, control de visibilidad de ventanas internas
             * y conexión de vistas con sus respectivos controladores.
             *
             * Flujo general del método:
             * <ul>
             *     <li>Inicialización de vistas: login, registro, cuestionario, cambiar contraseña, etc.</li>
             *     <li>Instanciación de controladores: {@code UsuarioController}, {@code PreguntasController}.</li>
             *     <li>Configuración de eventos para botones de login (registrarse, recuperar contraseña).</li>
             *     <li>Al cerrar la ventana de login y si la autenticación fue exitosa:</li>
             *     <ul>
             *         <li>Inicializa vistas de productos, carritos y usuarios.</li>
             *         <li>Configura eventos de cada ítem del menú principal.</li>
             *         <li>Asocia vistas internas al {@code JDesktopPane} de {@code MenuPrincipalView}.</li>
             *         <li>Habilita la internacionalización dinámica para todos los módulos.</li>
             *     </ul>
             * </ul>
             *
             * Este método representa el punto de entrada principal de la aplicación tras la autenticación.
             */
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

                            /**
                             * Acción para mostrar la vista de añadir producto.
                             * Si la ventana no está visible, se añade al JDesktopPane y se muestra.
                             */
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

                            /**
                             * Acción para mostrar la vista de listar productos.
                             */
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

                            /**
                             * Acción para mostrar la vista de modificar productos.
                             */
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

                            /**
                             * Acción para mostrar la vista de eliminar productos.
                             */
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

                            /**
                             * Acción para mostrar la vista de añadir carrito.
                             */
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

                            /**
                             * Acción para mostrar la vista de eliminar carritos.
                             */
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

                            /**
                             * Acción para mostrar la vista de modificar carritos.
                             */
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

                            /**
                             * Acción para mostrar la vista de lista de carritos.
                             */
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

                            /**
                             * Acción para mostrar la vista de detalle de carritos.
                             */
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

                            /**
                             * Acción para mostrar la vista de eliminación de usuario.
                             */
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

                            /**
                             * Acción para mostrar la vista de modificación de usuario.
                             */
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

                            /**
                             * Acción para mostrar la vista de lista de usuarios.
                             * También actualiza y configura la tabla con datos.
                             */
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

                            /**
                             * Cambia el idioma a inglés y actualiza todas las vistas.
                             */
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

                            /**
                             * Cambia el idioma a español y actualiza todas las vistas.
                             */
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

                            /**
                             * Cambia el idioma a francés y actualiza todas las vistas.
                             */
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