package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link ProductoDAO} que utiliza un archivo de texto
 * para almacenar y recuperar productos del sistema.
 * <p>
 * Cada producto se guarda en una línea del archivo {@code productos.txt}, usando el formato:
 * {@code codigo;nombre;precio}.
 * </p>
 * <p>
 * Este enfoque permite una persistencia simple y legible por humanos, útil para pruebas
 * o sistemas con bajo volumen de datos.
 * </p>
 *
 * @author Keyra
 * @version 1.0
 */
public class ProductoDAOArchivoTexto implements ProductoDAO {

    /** Ruta del archivo donde se almacenan los productos en formato de texto. */
    private static String ARCHIVO = "data/productos.txt";

    static {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona la carpeta para productos.txt");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int opcion = fileChooser.showOpenDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File carpeta = fileChooser.getSelectedFile();
            ARCHIVO = carpeta.getAbsolutePath() + File.separator + "productos.txt";
            System.out.println("📄 Archivo productos.txt estará en: " + ARCHIVO);

            // Crear archivo si no existe
            try {
                File archivo = new File(ARCHIVO);
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
            } catch (IOException e) {
                System.err.println("❌ Error al crear productos.txt: " + e.getMessage());
            }
        }
    }
    /**
     * Guarda un nuevo producto en el archivo de texto.
     *
     * @param producto Producto a guardar.
     */
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

    /**
     * Busca un producto por su código.
     *
     * @param codigo Código del producto.
     * @return El producto correspondiente o {@code null} si no se encuentra.
     */
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

    /**
     * Busca productos cuyo nombre contenga la cadena dada (sin distinción de mayúsculas).
     *
     * @param nombre Nombre o parte del nombre a buscar.
     * @return Lista de productos que coincidan parcialmente con el nombre.
     */
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

    /**
     * Actualiza un producto existente sobrescribiendo su información en el archivo.
     *
     * @param producto Producto con los datos actualizados.
     */
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

    /**
     * Elimina un producto del archivo por su código.
     *
     * @param codigo Código del producto a eliminar.
     */
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

    /**
     * Modifica un producto ya existente en el archivo.
     *
     * @param producto Producto con los datos nuevos.
     * @return {@code true} si se modificó correctamente, {@code false} si no se encontró.
     */
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

    /**
     * Retorna la lista completa de productos almacenados en el archivo.
     *
     * @return Lista de productos existentes.
     */
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
