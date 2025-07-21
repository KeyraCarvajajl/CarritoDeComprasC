package ec.edu.ups.vista.preguntas;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.usuario.LoginView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Vista interna que forma parte del módulo de recuperación de contraseña.
 * Permite validar la identidad del usuario mediante preguntas de seguridad.
 * <p>
 * El usuario debe ingresar su nombre de usuario, seleccionar una pregunta aleatoria
 * (de las 3 registradas en su cuenta) y proporcionar la respuesta correcta.
 * Si la validación es exitosa, se le permite cambiar su contraseña.
 * <p>
 * Esta clase incorpora soporte para internacionalización dinámica a través de
 * {@link ec.edu.ups.util.MensajeInternacionalizacionHandler}, e íconos personalizados
 * en los botones.
 *
 * <p><b>Características:</b></p>
 * <ul>
 *   <li>Validación de usuario y respuestas a preguntas de seguridad</li>
 *   <li>Carga aleatoria de preguntas asociadas al usuario</li>
 *   <li>Internacionalización y diseño visual personalizado</li>
 * </ul>
 *
 * @author Keyra
 */

public class CuestionarioView extends JInternalFrame {

    /**
     * Panel principal que contiene todos los componentes visuales de la ventana.
     * Utiliza un diseño personalizado para organizar los elementos.
     */
    private JPanel panelPrincipal;

    /**
     * Etiqueta que indica el campo donde se debe ingresar el nombre de usuario.
     */
    private JLabel lblUsuario;

    /**
     * Campo de texto donde el usuario debe ingresar su nombre de usuario
     * para cargar las preguntas asociadas a su cuenta.
     */
    private JTextField txtUsuario;

    /**
     * Etiqueta que indica el combo box de selección de preguntas de seguridad.
     */
    private JLabel lblPreguntas;

    /**
     * Combo box que contiene tres preguntas de seguridad aleatorias asociadas al usuario.
     */
    private JComboBox<String> cbxPreguntas;

    /**
     * Etiqueta que indica el campo para ingresar la respuesta a la pregunta seleccionada.
     */
    private JLabel lblRespuesta;

    /**
     * Campo de texto donde el usuario debe escribir la respuesta a la pregunta de seguridad seleccionada.
     */
    private JTextField txtRespuesta;

    /**
     * Botón que permite validar la respuesta ingresada y confirmar la identidad del usuario.
     */
    private JButton btnValidar;

    /**
     * Botón que permite cancelar la validación y cerrar la ventana.
     */
    private JButton btnCancelar;

    /**
     * Manejador de internacionalización encargado de proporcionar los textos
     * traducidos de los componentes gráficos, según el idioma seleccionado.
     * Es una dependencia inmutable de la clase.
     */
    private final MensajeInternacionalizacionHandler mensajeHandler;

    /**
     * Objeto DAO que permite acceder a los datos del usuario para recuperar sus preguntas
     * y validar las respuestas durante el proceso de recuperación de contraseña.
     * Es una dependencia inmutable de la clase.
     */
    private final UsuarioDAO usuarioDAO;

    /**
     * Referencia al usuario actualmente identificado por su nombre de usuario,
     * cuyo cuestionario está siendo validado.
     */
    private Usuario usuarioActual;

    /**
     * Constructor que inicializa la ventana de recuperación de contraseña mediante preguntas de seguridad.
     * <p>
     * Configura la interfaz gráfica, establece el panel principal, tamaño de la ventana,
     * carga los íconos personalizados y aplica los textos internacionalizados a los componentes.
     * <p>
     * También registra los listeners para los botones "Validar" y "Cancelar", y carga
     * preguntas aleatorias al ingresar un nombre de usuario válido.
     *
     * @param mensajeHandler Manejador de internacionalización que proporciona los textos traducidos.
     * @param usuarioDAO DAO para acceder a los datos del usuario y sus preguntas de seguridad.
     */
    public CuestionarioView(MensajeInternacionalizacionHandler mensajeHandler, UsuarioDAO usuarioDAO) {
        this.mensajeHandler = mensajeHandler;
        this.usuarioDAO = usuarioDAO;

        setContentPane(panelPrincipal);
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);

        imagenIcon();
        actualizarTextos(mensajeHandler);

        btnValidar.addActionListener(e -> validarRespuesta());
        btnCancelar.addActionListener(e -> dispose());

        txtUsuario.addActionListener(e -> cargarPreguntasAleatorias(txtUsuario.getText().trim()));
    }

    /**
     * Actualiza los textos visibles de la interfaz gráfica utilizando el manejador de internacionalización.
     * <p>
     * Se modifican las etiquetas, los botones y el título de la ventana para reflejar
     * el idioma seleccionado por el usuario.
     *
     * @param mensajeHandler Manejador que proporciona los textos traducidos desde los archivos de propiedades.
     */
    public void actualizarTextos(MensajeInternacionalizacionHandler mensajeHandler) {
        lblUsuario.setText(mensajeHandler.get("recuperar.usuario"));
        lblPreguntas.setText(mensajeHandler.get("recuperar.pregunta"));
        lblRespuesta.setText(mensajeHandler.get("recuperar.respuesta"));

        btnValidar.setText(mensajeHandler.get("boton.validar"));
        btnCancelar.setText(mensajeHandler.get("boton.cancelar"));

        setTitle(mensajeHandler.get("recuperar.titulo"));
    }

    /**
     * Carga y asigna íconos personalizados a los botones "Validar" y "Cancelar".
     * <p>
     * Las imágenes se obtienen desde la carpeta de recursos del proyecto y se redimensionan
     * a 30x30 píxeles para mantener consistencia visual con el diseño del sistema.
     * <p>
     * Si los íconos no se encuentran, se muestra un mensaje de error en la consola.
     */
    private void imagenIcon(){
        URL btValidar = LoginView.class.getClassLoader().getResource("imagenes/validar.png");
        if (btValidar != null) {
            ImageIcon iconBtnValidar = new ImageIcon(btValidar);
            Image imgValidar = iconBtnValidar.getImage();  // Convierte ImageIcon a Image
            Image newImgValidar = imgValidar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnValidar = new ImageIcon(newImgValidar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnValidar.setIcon(iconBtnValidar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Validar");
        }

        URL btCancelar = LoginView.class.getClassLoader().getResource("imagenes/cancelar.png");
        if (btCancelar != null) {
            ImageIcon iconBtnCancelar = new ImageIcon(btCancelar);
            Image imgCancelar = iconBtnCancelar.getImage();  // Convierte ImageIcon a Image
            Image newImgCancelar = imgCancelar.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensionar la imagen
            iconBtnCancelar = new ImageIcon(newImgCancelar);  // Crea un nuevo ImageIcon con la imagen redimensionada
            btnCancelar.setIcon(iconBtnCancelar);  // Establecer el icono en el botón
        } else {
            System.err.println("Error: No se ha cargado el icono de Cancelar");
        }

    }

    /**
     * Carga tres preguntas de seguridad asociadas al usuario cuyo nombre de usuario se ha ingresado.
     * <p>
     * Las preguntas se obtienen desde el DAO, se almacenan en una lista y se mezclan aleatoriamente
     * antes de mostrarse en el combo box.
     * <p>
     * Si el usuario no existe, se muestra un mensaje de advertencia.
     *
     * @param username Nombre de usuario ingresado para recuperar sus preguntas de seguridad.
     */
    private void cargarPreguntasAleatorias(String username) {
        usuarioActual = usuarioDAO.buscarPorUsername(username);
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.usuario.noencontrado"));
            return;
        }

        List<String> preguntas = new ArrayList<>();
        preguntas.add(usuarioActual.getPregunta1());
        preguntas.add(usuarioActual.getPregunta2());
        preguntas.add(usuarioActual.getPregunta3());
        Collections.shuffle(preguntas);

        cbxPreguntas.removeAllItems();
        for (String pregunta : preguntas) {
            cbxPreguntas.addItem(pregunta);
        }
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje informativo o de error.
     *
     * @param mensaje Texto que se mostrará al usuario en una ventana emergente.
     */

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Valida si la respuesta ingresada por el usuario coincide con alguna de las respuestas
     * asociadas a las preguntas de seguridad registradas en su cuenta.
     * <p>
     * Si la validación es correcta, se muestra un mensaje de éxito y puede continuar con el cambio de contraseña.
     * Si la respuesta es incorrecta o el campo está vacío, se muestra un mensaje de advertencia.
     */
    private void validarRespuesta() {
        String preguntaSeleccionada = (String) cbxPreguntas.getSelectedItem();
        String respuestaIngresada = txtRespuesta.getText().trim();

        if (preguntaSeleccionada == null || respuestaIngresada.isEmpty()) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.completar"));
            return;
        }

        boolean esCorrecta = false;
        if (preguntaSeleccionada.equals(usuarioActual.getPregunta1()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta1())) {
            esCorrecta = true;
        } else if (preguntaSeleccionada.equals(usuarioActual.getPregunta2()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta2())) {
            esCorrecta = true;
        } else if (preguntaSeleccionada.equals(usuarioActual.getPregunta3()) &&
                respuestaIngresada.equalsIgnoreCase(usuarioActual.getRespuesta3())) {
            esCorrecta = true;
        }

        if (esCorrecta) {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.correcto"));
            // Aquí puedes abrir la ventana CambiarContraseniaView
        } else {
            JOptionPane.showMessageDialog(this, mensajeHandler.get("recuperar.incorrecto"));
        }
    }

    /**
     * Retorna el panel principal de la vista.
     * @return Panel principal {@code JPanel}.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Retorna la etiqueta del campo de usuario.
     * @return Etiqueta {@code JLabel} del usuario.
     */
    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    /**
     * Retorna el campo de texto donde se ingresa el nombre de usuario.
     * @return Campo de texto {@code JTextField}.
     */
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    /**
     * Retorna la etiqueta del campo de preguntas.
     * @return Etiqueta {@code JLabel} para preguntas.
     */
    public JLabel getLblPreguntas() {
        return lblPreguntas;
    }

    /**
     * Retorna la etiqueta del campo de respuesta.
     * @return Etiqueta {@code JLabel} para la respuesta.
     */
    public JLabel getLblRespuesta() {
        return lblRespuesta;
    }

    /**
     * Retorna el campo de texto donde el usuario escribe la respuesta.
     * @return Campo de texto {@code JTextField} para respuesta.
     */
    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    /**
     * Retorna el botón para validar la respuesta ingresada.
     * @return Botón {@code JButton} validar.
     */
    public JButton getBtnValidar() {
        return btnValidar;
    }

    /**
     * Retorna el botón para cancelar la operación.
     * @return Botón {@code JButton} cancelar.
     */
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * Retorna el manejador de mensajes para la internacionalización.
     * @return Objeto {@code MensajeInternacionalizacionHandler}.
     */
    public MensajeInternacionalizacionHandler getMensajeHandler() {
        return mensajeHandler;
    }

    /**
     * Retorna el DAO que permite buscar y recuperar datos de usuario.
     * @return Objeto {@code UsuarioDAO}.
     */
    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    /**
     * Retorna el usuario actualmente cargado en el cuestionario.
     * @return Objeto {@code Usuario} actual.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Retorna el combo box que contiene las preguntas de seguridad.
     * @return {@code JComboBox<String>} con las preguntas.
     */
    public JComboBox<String> getCbxPreguntas() {
        return cbxPreguntas;
    }

    /**
     * Establece el panel principal de la vista.
     * @param panelPrincipal Panel a asignar.
     */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /**
     * Establece la etiqueta del campo de usuario.
     * @param lblUsuario Etiqueta a asignar.
     */
    public void setLblUsuario(JLabel lblUsuario) {
        this.lblUsuario = lblUsuario;
    }

    /**
     * Establece el campo de texto para el nombre de usuario.
     * @param txtUsuario Campo de texto a asignar.
     */
    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    /**
     * Establece la etiqueta del combo box de preguntas.
     * @param lblPreguntas Etiqueta a asignar.
     */
    public void setLblPreguntas(JLabel lblPreguntas) {
        this.lblPreguntas = lblPreguntas;
    }

    /**
     * Establece el combo box de preguntas aleatorias.
     * @param cbxPreguntas Combo box a asignar.
     */
    public void setCbxPreguntas(JComboBox<String> cbxPreguntas) {
        this.cbxPreguntas = cbxPreguntas;
    }

    /**
     * Establece la etiqueta del campo de respuesta.
     * @param lblRespuesta Etiqueta a asignar.
     */
    public void setLblRespuesta(JLabel lblRespuesta) {
        this.lblRespuesta = lblRespuesta;
    }

    /**
     * Establece el campo de texto para ingresar la respuesta.
     * @param txtRespuesta Campo de texto a asignar.
     */
    public void setTxtRespuesta(JTextField txtRespuesta) {
        this.txtRespuesta = txtRespuesta;
    }

    /**
     * Establece el botón de validación de la respuesta.
     * @param btnValidar Botón a asignar.
     */
    public void setBtnValidar(JButton btnValidar) {
        this.btnValidar = btnValidar;
    }

    /**
     * Establece el botón para cancelar la operación.
     * @param btnCancelar Botón a asignar.
     */
    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    /**
     * Establece el usuario actualmente cargado en el cuestionario.
     * @param usuarioActual Usuario a asignar.
     */
    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

}
