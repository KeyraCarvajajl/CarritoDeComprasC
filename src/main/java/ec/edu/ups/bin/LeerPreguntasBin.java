package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.PreguntasArchivoBinario;
import ec.edu.ups.modelo.Preguntas;

import java.io.File;
import java.util.List;

/**
 * Clase de utilidad para leer y mostrar preguntas de seguridad desde un archivo binario.
 * Si no existen preguntas, se cargan 10 preguntas base automáticamente.
 *
 * @author Keyra
 */
public class LeerPreguntasBin {

    public static void main(String[] args) {
        String ruta = "bin/preguntas.bin";
        PreguntasArchivoBinario dao = new PreguntasArchivoBinario(ruta);

        File archivo = new File(ruta);
        if (!archivo.exists() || dao.obtenerTodas().isEmpty()) {
            cargarPreguntasBase(dao);
        }

        List<Preguntas> preguntas = dao.obtenerTodas();
        System.out.println("=== Preguntas de seguridad registradas ===");
        for (Preguntas p : preguntas) {
            System.out.println(p);
        }
    }

    /**
     * Inserta 10 preguntas predeterminadas con username "base".
     */
    private static void cargarPreguntasBase(PreguntasArchivoBinario dao) {
        dao.guardar(new Preguntas("base", "¿Cuál es tu color favorito?"));
        dao.guardar(new Preguntas("base", "¿Cuál es el nombre de tu primer mascota?"));
        dao.guardar(new Preguntas("base", "¿En qué ciudad naciste?"));
        dao.guardar(new Preguntas("base", "¿Cuál es tu comida favorita?"));
        dao.guardar(new Preguntas("base", "¿Cuál es tu película favorita?"));
        dao.guardar(new Preguntas("base", "¿Cuál es tu canción favorita?"));
        dao.guardar(new Preguntas("base", "¿Qué deporte practicaste en la infancia?"));
        dao.guardar(new Preguntas("base", "¿Cuál es tu libro favorito?"));
        dao.guardar(new Preguntas("base", "¿A qué escuela primaria asististe?"));
        dao.guardar(new Preguntas("base", "¿Cuál es el segundo nombre de tu madre?"));
    }
}
