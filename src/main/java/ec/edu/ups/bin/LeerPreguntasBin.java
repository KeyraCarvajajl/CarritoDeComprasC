package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.PreguntasArchivoBinario;
import ec.edu.ups.modelo.Preguntas;

import java.util.List;

public class LeerPreguntasBin {
    public static void main(String[] args) {
        PreguntasArchivoBinario dao = new PreguntasArchivoBinario();
        List<Preguntas> preguntas = dao.obtenerTodas();

        System.out.println("=== Contenido del archivo preguntas.bin ===");
        for (Preguntas p : preguntas) {
            System.out.println(p);
        }
    }
}
