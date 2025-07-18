package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarritoDAOArchivoBinario implements CarritoDAO {

    public static final String ARCHIVO = "carritos.bin";
    private List<Carrito> carritos;

    public CarritoDAOArchivoBinario() {
        carritos = cargarDesdeArchivo();
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
        guardarEnArchivo();
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Carrito buscarPorCodigoYFecha(int codigo, Date fecha) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo && c.getFechaCreacion().equals(fecha)) {
                return c;
            }
        }
        return null;
    }

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

    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarEnArchivo();
    }

    @Override
    public List<Carrito> listarTodos() {
        return new ArrayList<>(carritos);
    }

    @Override
    public void guardar(Carrito carrito) {
        crear(carrito); // alias
    }

    // Métodos auxiliares

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            System.err.println("Error al guardar carritos: " + e.getMessage());
        }
    }

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
