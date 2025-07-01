package ec.edu.ups.modelo;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private Rol rol;

    // Nuevos campos solicitados:
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String correo;
    private String telefono;
    private List<Pregunta> preguntasDeSeguridad;

    // Constructor original
    public Usuario(String username, String password, Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Pregunta> getPreguntasDeSeguridad() {
        return preguntasDeSeguridad;
    }

    public void setPreguntasDeSeguridad(List<Pregunta> preguntasDeSeguridad) {
        this.preguntasDeSeguridad = preguntasDeSeguridad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", rol=" + rol +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", preguntas=" + (preguntasDeSeguridad != null ? preguntasDeSeguridad.size() : 0) +
                '}';
    }
}
