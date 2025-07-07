package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.Date;
import java.util.List;

public interface CarritoDAO {

    void crear(Carrito carrito);

    Carrito buscarPorCodigo(int codigo);

    Carrito buscarPorCodigoYFecha(int codigo, Date fecha);

    void actualizar(Carrito carrito);

    void eliminar(int codigo);

    List<Carrito> listarTodos();
}
