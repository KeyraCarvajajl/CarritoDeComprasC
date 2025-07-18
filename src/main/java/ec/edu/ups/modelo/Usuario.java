package ec.edu.ups.modelo;

import ec.edu.ups.excepciones.*;
import java.io.Serializable;
import java.time.LocalDate;

public class Usuario implements Serializable {

    private String username;
    private String contrasenia;
    private Rol rol;
    private int codigo;
    private String nombre;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
    private String pregunta1;
    private String respuesta1;
    private String pregunta2;
    private String respuesta2;
    private String pregunta3;
    private String respuesta3;

    public Usuario() {
        this.rol = Rol.USUARIO;
    }

    public Usuario(String username, String contrasenia, Rol rol) throws CedulaException, ContraseniaException {
        setUsername(username);
        setContrasenia(contrasenia);
        this.rol = rol;
    }

    public Usuario(String username, String contrasenia) throws CedulaException, ContraseniaException {
        setUsername(username);
        setContrasenia(contrasenia);
        this.rol = Rol.USUARIO;
    }

    public void setUsername(String username) throws CedulaException {
        if (!validarCedulaEcuatoriana(username)) {
            throw new CedulaException("Cédula inválida. Debe tener 10 dígitos válidos según módulo 10.");
        }
        this.username = username;
    }

    public void setContrasenia(String contrasenia) throws ContraseniaException {
        if (!validarContrasenaSegura(contrasenia)) {
            throw new ContraseniaException("Contraseña insegura. Debe tener al menos 6 caracteres, una mayúscula, una minúscula y un símbolo (@ _ -).");
        }
        this.contrasenia = contrasenia;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) throws FechaException {
        if (fechaNacimiento == null) {
            throw new FechaException("La fecha de nacimiento no puede estar vacía.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) throws CamposException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CamposException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setNombreCompleto(String nombreCompleto) throws CamposException {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new CamposException("El nombre completo no puede estar vacío.");
        }
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) throws CorreoException {
        if (correo == null || !correo.contains("@")) {
            throw new CorreoException("Correo electrónico inválido.");
        }
        this.correo = correo;
    }

    public void setTelefono(String telefono) throws CamposException {
        if (telefono == null || !telefono.matches("\\d{7,10}")) {
            throw new CamposException("Número de teléfono inválido.");
        }
        this.telefono = telefono;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public void setRespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public void setPregunta3(String pregunta3) {
        this.pregunta3 = pregunta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public String getRespuesta1() {
        return respuesta1;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public String getRespuesta2() {
        return respuesta2;
    }

    public String getPregunta3() {
        return pregunta3;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

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

    private boolean validarContrasenaSegura(String contrasenia) {
        return contrasenia != null &&
                contrasenia.length() >= 6 &&
                contrasenia.matches(".*[A-Z].*") &&
                contrasenia.matches(".*[a-z].*") &&
                contrasenia.matches(".*[@_-].*");
    }

    @Override
    public String toString() {
        return "Usuario: " + username +
                " | Contraseña: " + contrasenia +
                " | Rol: " + rol +
                " | Nombre: " + nombreCompleto +
                " | Fecha: " + fechaNacimiento +
                " | Correo: " + correo +
                " | Teléfono: " + telefono;
    }


}
