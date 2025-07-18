package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoBinario implements ProductoDAO {

    private static final String ARCHIVO = "productos.bin";
    private List<Producto> productos;

    public ProductoDAOArchivoBinario() {
        productos = cargarDesdeArchivo();
    }

    @Override
    public void crear(Producto producto) {
        productos.add(producto);
        guardarEnArchivo();
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    @Override
    public void actualizar(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == productoActualizado.getCodigo()) {
                productos.set(i, productoActualizado);
                guardarEnArchivo();
                return;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarEnArchivo();
    }

    @Override
    public boolean modificar(Producto productoModificado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == productoModificado.getCodigo()) {
                productos.set(i, productoModificado);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }


    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    private List<Producto> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
