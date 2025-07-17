/**
 * Este paquete contiene todas las clases relacionadas con la interfaz gráfica
 * de usuario (GUI) para la gestión de productos dentro del sistema de carrito de compras.
 *
 * <p>Incluye las siguientes ventanas (vistas):</p>
 * <ul>
 *   <li><b>ProductoAnadirView</b>: Permite registrar nuevos productos.</li>
 *   <li><b>ProductoEliminarView</b>: Facilita la eliminación de productos existentes.</li>
 *   <li><b>ProductoModificarView</b>: Permite modificar los datos de un producto.</li>
 *   <li><b>ProductoListaView</b>: Muestra una tabla con todos los productos y permite filtrarlos.</li>
 * </ul>
 *
 * <p>Cada clase de este paquete implementa una interfaz con soporte para internacionalización
 * mediante la clase {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}.</p>
 *
 * <p>Estas vistas siguen el patrón de diseño MVC (Modelo-Vista-Controlador), en el que las
 * clases de este paquete actúan como las 'Vistas' que se comunican con sus respectivos
 * controladores para la lógica del negocio.</p>
 *
 * @author Keyra
 */
package ec.edu.ups.vista.producto;
