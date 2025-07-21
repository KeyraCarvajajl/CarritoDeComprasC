package ec.edu.ups.modelo;

/**
 * Enumeración que representa los roles posibles que puede tener un usuario dentro del sistema.
 *
 * <p>Los roles definen el nivel de acceso y las funcionalidades disponibles para el usuario:</p>
 * <ul>
 *     <li>{@code ADMINISTRADOR}: Tiene acceso total al sistema, incluyendo la gestión de usuarios, productos y carritos.</li>
 *     <li>{@code USUARIO}: Tiene acceso restringido, solo puede gestionar su cuenta y realizar compras.</li>
 * </ul>
 *
 * <p>Esta enumeración se utiliza en la clase {@link ec.edu.ups.modelo.Usuario} para asignar roles a los usuarios.</p>
 *
 * @author Keyra
 */
public enum Rol {
    /**
     * Rol de administrador del sistema.
     */
    ADMINISTRADOR,

    /**
     * Rol de usuario común.
     */
    USUARIO,
}
