package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementación de la interfaz {@link CarritoDAO} que utiliza almacenamiento
 * en archivo binario para gestionar objetos de tipo {@link Carrito}.
 * <p>
 * Los datos se almacenan y recuperan del archivo {@code carritos.bin} utilizando
 * la serialización estándar de Java.
 * </p>
 *
 * @author Keyra
 * @version 1.0
 */
public class CarritoDAOArchivoBinario implements CarritoDAO {

    /** Nombre del archivo donde se guardan los carritos en formato binario. */
    public static final String ARCHIVO = "carritos.bin";

    /** Lista interna que almacena los carritos en memoria. */
    private List<Carrito> carritos;

    /**
     * Constructor que inicializa la lista de carritos cargando los datos desde el archivo binario.
     */
    public CarritoDAOArchivoBinario() {
        carritos = cargarDesdeArchivo();
    }

    /**
     * Guarda un nuevo carrito en la lista y en el archivo.
     *
     * @param carrito Carrito a guardar.
     */
    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
        guardarEnArchivo();
    }

    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito a buscar.
     * @return Carrito encontrado o {@code null} si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
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
     * @param fecha  Fecha de creación del carrito.
     * @return Carrito que coincide con el código y la fecha, o {@code null} si no se encuentra.
     */
    @Override
    public Carrito buscarPorCodigoYFecha(int codigo, Date fecha) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo && c.getFechaCreacion().equals(fecha)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito existente en la lista y en el archivo.
     *
     * @param carritoActualizado Carrito con los nuevos datos.
     */
    @Override
    public void actualizar(Carrito carritoActualizado) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carritoActualizado.getCodigo()) {
                carritos.set(i, carritoActualizado);
                guardarEnArchivo();
                return;
            }
        }
    }

    /**
     * Elimina un carrito de la lista por su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarEnArchivo();
    }

    /**
     * Retorna una lista con todos los carritos almacenados.
     *
     * @return Lista de carritos.
     */
    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }

    /**
     * Alias del método {@link #crear(Carrito)}. Guarda un nuevo carrito.
     *
     * @param carrito Carrito a guardar.
     */
    @Override
    public void guardar(Carrito carrito) {
        crear(carrito); // alias
    }

    /**
     * Guarda la lista de carritos en el archivo binario.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            System.err.println("Error al guardar carritos: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de carritos desde el archivo binario.
     *
     * @return Lista de carritos leída desde el archivo, o lista vacía si no existe o hay error.
     */
    private List<Carrito> cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (List<Carrito>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar carritos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
