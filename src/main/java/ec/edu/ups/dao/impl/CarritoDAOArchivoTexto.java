package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Implementación de la interfaz {@link CarritoDAO} que utiliza un archivo de texto
 * para almacenar información de los carritos y sus productos.
 * <p>
 * El archivo se encuentra en la ruta {@code data/carritos.txt}, donde cada línea
 * representa un ítem de un carrito con el formato:
 * {@code codigoCarrito;fechaCreacion;codigoProducto;cantidad}.
 * </p>
 * <p>
 * Esta implementación permite persistencia legible para humanos y puede ser utilizada
 * cuando se desee un almacenamiento más simple y portable que los archivos binarios.
 * </p>
 *
 * @author Keyra
 * @version 1.0
 */
public class CarritoDAOArchivoTexto implements CarritoDAO {

    /** Ruta del archivo donde se almacenan los carritos. */
    private static final String ARCHIVO = "data/carritos.txt";

    /** Formato de fecha utilizado en el archivo de texto. */
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    /** DAO para recuperar productos al reconstruir carritos desde el archivo. */
    private ProductoDAO productoDAO;

    /**
     * Constructor que recibe una implementación de {@link ProductoDAO},
     * necesaria para reconstruir los productos al leer los carritos desde el archivo.
     *
     * @param productoDAO Implementación de {@code ProductoDAO}
     */
    public CarritoDAOArchivoTexto(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    /**
     * Guarda los ítems del carrito en el archivo de texto.
     *
     * @param carrito Carrito a guardar.
     */
    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            for (ItemCarrito item : carrito.obtenerItems()) {
                Producto producto = item.getProducto();
                int cantidad = item.getCantidad();
                writer.write(carrito.getCodigo() + ";" +
                        new java.sql.Date(carrito.getFechaCreacion().getTime()) + ";" +
                        producto.getCodigo() + ";" +
                        cantidad);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar carrito: " + e.getMessage());
        }
    }

    /**
     * Busca un carrito por su código.
     *
     * @param codigo Código del carrito.
     * @return El carrito correspondiente o {@code null} si no se encuentra.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        List<Carrito> carritos = listarTodos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca un carrito por su código y fecha de creación.
     *
     * @param codigo Código del carrito.
     * @param fecha Fecha de creación.
     * @return El carrito correspondiente o {@code null} si no se encuentra.
     */
    @Override
    public Carrito buscarPorCodigoYFecha(int codigo, Date fecha) {
        List<Carrito> carritos = listarTodos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo && c.getFechaCreacion().equals(fecha)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Actualiza la información de un carrito sobrescribiendo su contenido en el archivo.
     *
     * @param carrito Carrito con la información actualizada.
     */
    @Override
    public void actualizar(Carrito carrito) {
        eliminar(carrito.getCodigo());
        crear(carrito);
    }

    /**
     * Elimina un carrito del archivo de texto.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Carrito c : carritos) {
                if (c.getCodigo() != codigo) {
                    for (ItemCarrito item : c.obtenerItems()) {
                        writer.write(c.getCodigo() + ";" + formato.format(c.getFechaCreacion()) + ";" +
                                item.getProducto().getCodigo() + ";" + item.getCantidad());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar carrito: " + e.getMessage());
        }
    }

    /**
     * Lista todos los carritos almacenados en el archivo de texto.
     * Reconstruye los objetos {@code Carrito} y sus productos.
     *
     * @return Lista de carritos leídos desde el archivo.
     */
    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            Map<Integer, Carrito> mapaCarritos = new HashMap<>();

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    int codigo = Integer.parseInt(partes[0]);
                    Date fecha = formatoFecha.parse(partes[1]);
                    int codProducto = Integer.parseInt(partes[2]);
                    int cantidad = Integer.parseInt(partes[3]);

                    Carrito carrito = mapaCarritos.get(codigo);
                    if (carrito == null) {
                        carrito = new Carrito(codigo, fecha);
                        mapaCarritos.put(codigo, carrito);
                    }

                    Producto producto = productoDAO.buscarPorCodigo(codProducto);
                    if (producto != null) {
                        carrito.agregarProducto(producto, cantidad);
                    } else {
                        System.out.println("⚠ Producto no encontrado: código " + codProducto);
                    }
                }
            }

            carritos.addAll(mapaCarritos.values());

        } catch (IOException | ParseException e) {
            System.err.println("Error al leer carritos: " + e.getMessage());
        }

        return carritos;
    }

    /**
     * Guarda los ítems del carrito como líneas independientes en el archivo.
     *
     * @param carrito Carrito a guardar.
     */
    @Override
    public void guardar(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            for (ItemCarrito item : carrito.obtenerItems()) {
                String linea = String.format("%d;%d;%d",
                        carrito.getCodigo(),
                        item.getProducto().getCodigo(),
                        item.getCantidad());

                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar carrito: " + e.getMessage());
        }
    }
}
