package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOArchivoTexto implements ProductoDAO {

    private static final String ARCHIVO = "data/productos.txt";

    @Override
    public void crear(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            String linea = producto.getCodigo() + ";" +
                    producto.getNombre() + ";" +
                    producto.getPrecio();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (Integer.parseInt(datos[0]) == codigo) {
                    return new Producto(codigo, datos[1],
                            Double.parseDouble(datos[2]));

                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al buscar producto por código: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[1].toLowerCase().contains(nombre.toLowerCase())) {
                    encontrados.add(new Producto(Integer.parseInt(datos[0]), datos[1],
                            Double.parseDouble(datos[2])));

                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar productos por nombre: " + e.getMessage());
        }
        return encontrados;
    }

    @Override
    public void actualizar(Producto producto) {
        List<Producto> lista = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : lista) {
                if (p.getCodigo() == producto.getCodigo()) {
                    p = producto;
                }
                writer.write(p.getCodigo() + ";" + p.getNombre() + ";" +
                        p.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int codigo) {
        List<Producto> lista = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : lista) {
                if (p.getCodigo() != codigo) {
                    writer.write(p.getCodigo() + ";" + p.getNombre() + ";" +
                            p.getPrecio());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public boolean modificar(Producto producto) {
        List<Producto> lista = listarTodos();
        boolean modificado = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : lista) {
                if (p.getCodigo() == producto.getCodigo()) {
                    p = producto;
                    modificado = true;
                }
                writer.write(p.getCodigo() + ";" + p.getNombre() + ";" +
                        p.getPrecio());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al modificar producto: " + e.getMessage());
        }
        return modificado;
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");
                lista.add(new Producto(Integer.parseInt(datos[0]), datos[1],
                        Double.parseDouble(datos[2])));

            }
        } catch (IOException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }
}
