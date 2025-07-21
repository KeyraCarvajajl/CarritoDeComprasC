package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.RutaArchivo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ProductoDAO} que utiliza archivos binarios
 * para almacenar y recuperar productos del sistema.
 * <p>
 * Los productos se serializan usando {@code ObjectOutputStream} y se guardan en el archivo
 * {@code productos.bin}. La información se mantiene persistente entre ejecuciones.
 * </p>
 *
 * <p>Esta clase permite la gestión completa de productos: crear, buscar, actualizar,
 * eliminar y listar, todo a través de operaciones sobre el archivo binario.</p>
 *
 * @author Keyra
 */
public class ProductoDAOArchivoBinario implements ProductoDAO {

    /** Ruta del archivo binario donde se almacenan los productos. */
    private String archivoRuta;

    /** Lista interna de productos cargados desde el archivo. */
    private List<Producto> productos;

    public ProductoDAOArchivoBinario(String rutaArchivo) {
        this.archivoRuta = rutaArchivo; // ✔️ Usa la ruta que le pasan
        new File("bin").mkdirs(); // Crea la carpeta si no existe
        this.productos = cargarDesdeArchivo();  // Carga desde esa ruta
    }



    /**
     * Constructor que carga los productos desde el archivo binario al iniciar la clase.
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
        guardarEnArchivo();
    }

    /**
     * Crea un nuevo producto y lo guarda tanto en memoria como en el archivo.
     *
     * @paramproducto Producto a guardar.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre contenga la cadena dada (sin distinción de mayúsculas).
     *
     * @param nombre Nombre o fragmento del nombre a buscar.
     * @return Lista de productos que coincidan parcialmente con el nombre.
     */
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

    /**
     * Actualiza un producto existente en la lista, identificándolo por su código.
     *
     * @param productoActualizado Producto con datos nuevos.
     */
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

    /**
     * Elimina un producto por su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        productos.removeIf(p -> p.getCodigo() == codigo);
        guardarEnArchivo();
    }

    /**
     * Modifica un producto existente (alias de actualizar).
     *
     * @param productoModificado Producto con datos nuevos.
     * @return {@code true} si se modificó exitosamente, {@code false} si no se encontró.
     */
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

    /**
     * Devuelve una copia de la lista de todos los productos almacenados.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    /**
     * Guarda la lista actual de productos en el archivo binario.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoRuta))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
    }

    /**
     * Carga los productos desde el archivo binario.
     *
     * @return Lista de productos leídos o una lista vacía si el archivo no existe o hay error.
     */
    private List<Producto> cargarDesdeArchivo() {
        File archivo = new File(archivoRuta);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoRuta))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
