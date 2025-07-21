/**
 * Este paquete contiene las vistas gráficas relacionadas con la recuperación de contraseñas
 * mediante preguntas de seguridad en el sistema de carrito de compras.
 * <p>
 * Las clases que conforman este paquete permiten a los usuarios recuperar el acceso a su cuenta
 * respondiendo correctamente a preguntas de seguridad previamente registradas.
 * <p>
 * Funcionalidades incluidas:
 * <ul>
 *   <li>Validación de preguntas y respuestas asociadas al usuario</li>
 *   <li>Cambio de contraseña posterior a la validación exitosa</li>
 *   <li>Internacionalización dinámica de componentes</li>
 *   <li>Íconos personalizados para mejorar la experiencia de usuario</li>
 * </ul>
 *
 * <p>
 * Todas las ventanas implementan {@link javax.swing.JInternalFrame} para integrarse con la interfaz MDI.
 * Además, utilizan {@link ec.edu.ups.util.MensajeInternacionalizacionHandler} para soportar múltiples idiomas.
 * </p>
 *
 * @author Keyra
 * @version 1.0
 */
package ec.edu.ups.vista.preguntas;
