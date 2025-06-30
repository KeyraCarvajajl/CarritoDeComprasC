package ec.edu.ups.modelo;

public class Pregunta {
    private String username;
    private String pregunta;
    private String respuesta;

    public Pregunta(String username, String pregunta, String respuesta) {
        this.username = username;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getUsername() {
        return username;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
