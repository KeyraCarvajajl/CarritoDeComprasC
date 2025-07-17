package ec.edu.ups.modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa a un usuario del sistema de carrito de compras.
 *
 * <p>Los usuarios pueden tener distintos roles como {@link Rol#ADMINISTRADOR} o {@link Rol#USUARIO},
 * y están identificados por su número de cédula ecuatoriana (username).</p>
 *
 * <p>Incluye información personal, credenciales de acceso, y un conjunto de preguntas de seguridad
 * para recuperación de contraseña.</p>
 *
 * <p>Implementa {@code Serializable} para permitir su persistencia en archivos binarios.</p>
 *
 * @author Keyra
 */
public class Usuario implements Serializable {

    /** Identificador único del usuario (cédula ecuatoriana). */
    private String username;

    /** Contraseña del usuario, validada como segura. */
    private String contrasenia;

    /** Rol del usuario dentro del sistema. */
    private Rol rol;

    /** Código interno del sistema para el usuario. */
    private int codigo;

    /** Nombre corto o alias. */
    private String nombre;

    /** Nombre completo del usuario. */
    private String nombreCompleto;

    /** Fecha de nacimiento del usuario. */
    private LocalDate fechaNacimiento;

    /** Correo electrónico del usuario. */
    private String correo;

    /** Número de teléfono del usuario. */
    private String telefono;

    /** Pregunta de seguridad 1. */
    private String pregunta1;

    /** Respuesta de seguridad 1. */
    private String respuesta1;

    /** Pregunta de seguridad 2. */
    private String pregunta2;

    /** Respuesta de seguridad 2. */
    private String respuesta2;

    /** Pregunta de seguridad 3. */
    private String pregunta3;

    /** Respuesta de seguridad 3. */
    private String respuesta3;

    /**
     * Constructor por defecto. Crea un usuario con rol USUARIO.
     */
    public Usuario() {
        this.rol = Rol.USUARIO;
    }

    /**
     * Constructor con parámetros principales.
     *
     * @param username    Cédula ecuatoriana válida.
     * @param contrasenia Contraseña segura.
     * @param rol         Rol del usuario.
     */
    public Usuario(String username, String contrasenia, Rol rol) {
        setUsername(username);
        setContrasenia(contrasenia);
        this.rol = rol;
    }

    /**
     * Constructor para rol USUARIO por defecto.
     *
     * @param username    Cédula ecuatoriana válida.
     * @param contrasenia Contraseña segura.
     */
    public Usuario(String username, String contrasenia) {
        setUsername(username);
        setContrasenia(contrasenia);
        this.rol = Rol.USUARIO;
    }

    // ========================= Setters con validación =========================

    /**
     * Establece el username como cédula ecuatoriana.
     *
     * @param username Cédula válida de 10 dígitos.
     * @throws IllegalArgumentException si la cédula no es válida.
     */
    public void setUsername(String username) {
        if (!validarCedulaEcuatoriana(username)) {
            throw new IllegalArgumentException("Cédula inválida. Debe tener 10 dígitos válidos según módulo 10.");
        }
        this.username = username;
    }

    /**
     * Establece la contraseña validando su seguridad.
     *
     * @param contrasenia Contraseña con mínimo 6 caracteres, una mayúscula, una minúscula y un símbolo.
     * @throws IllegalArgumentException si la contraseña no es segura.
     */
    public void setContrasenia(String contrasenia) {
        if (!validarContrasenaSegura(contrasenia)) {
            throw new IllegalArgumentException("Contraseña insegura. Debe tener al menos 6 caracteres, una mayúscula, una minúscula y un símbolo (@ _ -).");
        }
        this.contrasenia = contrasenia;
    }

    /**
     * Establece el código interno del usuario.
     *
     * @param codigo Código numérico único asignado al usuario.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento Fecha válida (no nula).
     * @throws IllegalArgumentException si la fecha es nula.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede estar vacía.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Establece el nombre corto o alias del usuario.
     *
     * @param nombre Nombre no vacío.
     * @throws IllegalArgumentException si el nombre está vacío o es nulo.
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     *
     * @param nombreCompleto Nombre completo no vacío.
     * @throws IllegalArgumentException si el nombre está vacío o es nulo.
     */
    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo no puede estar vacío.");
        }
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo Correo con formato válido que contenga '@'.
     * @throws IllegalArgumentException si el correo es inválido.
     */

    public void setCorreo(String correo) {
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo electrónico inválido.");
        }
        this.correo = correo;
    }

    /**
     * Establece el número de teléfono del usuario.
     *
     * @param telefono Número de teléfono de 7 a 10 dígitos.
     * @throws IllegalArgumentException si el formato del teléfono es inválido.
     */
    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{7,10}")) {
            throw new IllegalArgumentException("Número de teléfono inválido.");
        }
        this.telefono = telefono;
    }

    /**
     * Establece la primera pregunta de seguridad.
     *
     * @param pregunta1 Pregunta de seguridad 1.
     */
    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    /**
     * Establece la respuesta a la primera pregunta de seguridad.
     *
     * @param respuesta1 Respuesta correspondiente.
     */
    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    /**
     * Establece la segunda pregunta de seguridad.
     *
     * @param pregunta2 Pregunta de seguridad 2.
     */
    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    /**
     * Establece la respuesta a la segunda pregunta de seguridad.
     *
     * @param respuesta2 Respuesta correspondiente.
     */
    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    /**
     * Establece la tercera pregunta de seguridad.
     *
     * @param pregunta3 Pregunta de seguridad 3.
     */
    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    /**
     * Establece la respuesta a la tercera pregunta de seguridad.
     *
     * @param respuesta3 Respuesta correspondiente.
     */
    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    /**
     * Establece el rol del usuario (ADMINISTRADOR o USUARIO).
     *
     * @param rol Rol asignado.
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // ========================= Getters =========================

    /**
     * Obtiene el nombre de usuario (cédula).
     *
     * @return Nombre de usuario (cédula ecuatoriana válida).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return Rol asignado al usuario (ADMINISTRADOR o USUARIO).
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Obtiene el código único del usuario.
     *
     * @return Código numérico asignado.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre corto del usuario.
     *
     * @return Nombre o alias.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return Nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return Fecha de nacimiento en formato LocalDate.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     *
     * @return Teléfono en formato de 7 a 10 dígitos.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Obtiene la primera pregunta de seguridad.
     *
     * @return Pregunta 1.
     */
    public String getPregunta1() {
        return pregunta1;
    }

    /**
     * Obtiene la respuesta a la primera pregunta de seguridad.
     *
     * @return Respuesta 1.
     */
    public String getRespuesta1() {
        return respuesta1;
    }

    /**
     * Obtiene la segunda pregunta de seguridad.
     *
     * @return Pregunta 2.
     */
    public String getPregunta2() {
        return pregunta2;
    }

    /**
     * Obtiene la respuesta a la segunda pregunta de seguridad.
     *
     * @return Respuesta 2.
     */
    public String getRespuesta2() {
        return respuesta2;
    }

    /**
     * Obtiene la tercera pregunta de seguridad.
     *
     * @return Pregunta 3.
     */
    public String getPregunta3() {
        return pregunta3;
    }

    /**
     * Obtiene la respuesta a la tercera pregunta de seguridad.
     *
     * @return Respuesta 3.
     */
    public String getRespuesta3() {
        return respuesta3;
    }


    // ========================= Validadores =========================

    /**
     * Valida una cédula ecuatoriana utilizando el algoritmo del dígito verificador (módulo 10).
     *
     * @param cedula Cédula de 10 dígitos que se desea validar.
     * @return true si la cédula es válida, false en caso contrario.
     */
    private boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) return false;
        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) return false;
        int[] coef = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int prod = Character.getNumericValue(cedula.charAt(i)) * coef[i];
            if (prod >= 10) prod -= 9;
            suma += prod;
        }
        int verificador = Character.getNumericValue(cedula.charAt(9));
        int decena = ((suma + 9) / 10) * 10;
        int digito = decena - suma;
        if (digito == 10) digito = 0;
        return digito == verificador;
    }

    /**
     * Verifica si una contraseña es segura.
     * La contraseña debe tener al menos 6 caracteres, contener una letra mayúscula,
     * una letra minúscula y al menos un símbolo especial (@, _ o -).
     *
     * @param contrasenia Contraseña que se desea validar.
     * @return true si la contraseña cumple con los criterios de seguridad, false en caso contrario.
     */
    private boolean validarContrasenaSegura(String contrasenia) {
        return contrasenia != null &&
                contrasenia.length() >= 6 &&
                contrasenia.matches(".*[A-Z].*") &&
                contrasenia.matches(".*[a-z].*") &&
                contrasenia.matches(".*[@_-].*");
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Usuario.
     *
     * @return Cadena con los principales atributos del usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", rol=" + rol +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", preguntas=[1, 2, 3]" +
                '}';
    }
}
