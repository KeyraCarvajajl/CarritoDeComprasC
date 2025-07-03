package ec.edu.ups.modelo;

public class Preguntas {
    private String username;
    private String pregunta;
    private String respuesta;

    public Preguntas(String username, String pregunta) {
        this.username = username;
        this.pregunta = pregunta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
