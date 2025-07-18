package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.RespuestasArchivoBinario;
import ec.edu.ups.modelo.Respuesta;

import java.util.List;

public class LeerRespuestasBin {
    public static void main(String[] args) {
        RespuestasArchivoBinario dao = new RespuestasArchivoBinario();
        List<Respuesta> respuestas = dao.listarTodos();

        System.out.println("=== Contenido del archivo respuestas.bin ===");
        for (Respuesta r : respuestas) {
            System.out.println(r);
        }
    }
}