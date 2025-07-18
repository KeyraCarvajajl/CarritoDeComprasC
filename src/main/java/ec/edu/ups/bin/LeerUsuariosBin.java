package ec.edu.ups.bin;

import ec.edu.ups.dao.impl.UsuarioArchivoBinario;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public class LeerUsuariosBin {
    public static void main(String[] args) {
        UsuarioArchivoBinario dao = new UsuarioArchivoBinario();
        List<Usuario> usuarios = dao.listarTodos();

        System.out.println("=== Contenido del archivo usuarios.bin ===");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}
