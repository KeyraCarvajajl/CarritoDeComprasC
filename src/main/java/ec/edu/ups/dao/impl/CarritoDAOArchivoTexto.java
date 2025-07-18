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

public class CarritoDAOArchivoTexto implements CarritoDAO {

    private static final String ARCHIVO = "data/carritos.txt";
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private ProductoDAO productoDAO;

    public CarritoDAOArchivoTexto(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }


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

    @Override
    public void actualizar(Carrito carrito) {
        eliminar(carrito.getCodigo());
        crear(carrito);
    }

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

                    // Buscar o crear carrito
                    Carrito carrito = mapaCarritos.get(codigo);
                    if (carrito == null) {
                        carrito = new Carrito(codigo, fecha);
                        mapaCarritos.put(codigo, carrito);
                    }

                    // Buscar producto
                    Producto producto = productoDAO.buscarPorCodigo(codProducto);
                    if (producto != null) {
                        carrito.agregarProducto(producto, cantidad);
                    } else {
                        System.out.println("⚠ Producto no encontrado: código " + codProducto);
                    }
                }
            }

            // Añadir todos los carritos al resultado
            carritos.addAll(mapaCarritos.values());

        } catch (IOException | ParseException e) {
            System.err.println("Error al leer carritos: " + e.getMessage());
        }

        return carritos;
    }

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
