/**
 * Este paquete contiene los controladores de la aplicación del sistema de carrito de compras.
 *
 * Los controladores tienen como responsabilidad principal coordinar la interacción entre las vistas
 * (paquete {@code vista}), los modelos (paquete {@code modelo}) y los DAOs (paquete {@code dao}).
 *
 * <p>Siguiendo el patrón de arquitectura MVC, estos controladores:
 * <ul>
 *   <li>Gestionan los eventos de la interfaz gráfica de usuario.</li>
 *   <li>Validan los datos ingresados por el usuario antes de enviarlos al modelo o DAO.</li>
 *   <li>Actualizan las vistas según las acciones realizadas en el modelo o persistencia.</li>
 *   <li>Controlan el flujo de navegación entre las diferentes ventanas (login, menú, formularios).</li>
 * </ul>
 *
 * <p>Ejemplos de clases contenidas:
 * <ul>
 *   <li>{@code UsuarioController}: controla el flujo de autenticación, registro y mantenimiento de usuarios.</li>
 *   <li>{@code CarritoController}: maneja la lógica relacionada con la gestión de carritos de compras.</li>
 *   <li>{@code ProductoController}: gestiona el CRUD de productos en el sistema.</li>
 * </ul>
 *
 * @author Keyra
 */

package ec.edu.ups.controlador;