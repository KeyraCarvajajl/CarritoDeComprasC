package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CarritoDAOArchivoTexto implements CarritoDAO {

    private static final String ARCHIVO = "data/carritos.txt";
    private ProductoDAO productoDAO;
    private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public CarritoDAOArchivoTexto() {
        this.productoDAO = productoDAO;
    }

    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            for (ItemCarrito item : carrito.obtenerItems()) {
                writer.write(carrito.getCodigo() + ";" + formato.format(carrito.getFechaCreacion()) + ";" +
                        item.getProducto().getCodigo() + ";" + item.getCantidad());
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
        Map<String, Carrito> mapa = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                int codigo = Integer.parseInt(datos[0]);
                Date fecha = formato.parse(datos[1]);
                int codProducto = Integer.parseInt(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);

                Producto p = productoDAO.buscarPorCodigo(codProducto);
                if (p != null) {
                    String clave = codigo + "-" + datos[1];
                    Carrito c = mapa.getOrDefault(clave, new Carrito(codigo, fecha));
                    c.obtenerItems().add(new ItemCarrito(p, cantidad));
                    mapa.put(clave, c);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al listar carritos: " + e.getMessage());
        }
        return new ArrayList<>(mapa.values());
    }
}
