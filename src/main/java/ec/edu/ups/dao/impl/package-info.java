/**
 * Este paquete contiene implementaciones concretas de las interfaces DAO del sistema de carrito de compras.
 *
 * Cada clase implementa una interfaz del paquete {@code dao} y proporciona una lógica específica
 * de almacenamiento, como almacenamiento en memoria o en archivos.
 *
 * <p>Estas implementaciones permiten ejecutar operaciones CRUD sobre colecciones temporales o persistentes,
 * simulando una base de datos.
 *
 * <p>Ejemplos de clases implementadas:
 * <ul>
 *   <li>{@code UsuarioDAOMemoria}: implementación de {@code UsuarioDAO} usando listas en memoria.</li>
 *   <li>{@code ProductoDAOMemoria}: manejo de productos sin base de datos.</li>
 *   <li>{@code CarritoDAOMemoria}: gestión de carritos en tiempo de ejecución.</li>
 * </ul>
 *
 * @author Keyra
 */

package ec.edu.ups.dao.impl;