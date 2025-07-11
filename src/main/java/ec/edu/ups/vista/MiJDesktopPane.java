package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;


public class MiJDesktopPane extends JDesktopPane {

    private MensajeInternacionalizacionHandler mensajeHandler;

    public MiJDesktopPane() {
        this.mensajeHandler = new MensajeInternacionalizacionHandler("es", "EC");
    }


    public void setMensajeHandler(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
        repaint();  // Se actualiza el fondo cuando cambie el idioma
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Llamada al super para asegurar la renderización base

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo claro (esto puede ser opcional si ya tienes la imagen de fondo)
        g2.setColor(new Color(232, 218, 239));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Titulo
        String titulo = mensajeHandler != null
                ? mensajeHandler.get("pantalla.principal.bienvenida")
                : "Bienvenido al Sistema de Compras";  // fallback

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Times New Roman", Font.BOLD, 30));

        FontMetrics fm = g2.getFontMetrics();
        int anchoTexto = fm.stringWidth(titulo);
        int xTexto = (getWidth() - anchoTexto) / 2;
        g2.drawString(titulo, xTexto, 50);

        // Coordenadas base para el carrito
        int cx = getWidth() / 2;
        int cy = getHeight() / 2 + 30;

        // Aumento del tamaño del carrito
        int cartW = 350;  // Ancho más grande
        int cartH = 200;  // Alto más grande

        int cartX = cx - cartW / 2;
        int cartY = cy - cartH / 2;

        // Sombra ovalada del carrito
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillOval(cartX + 20, cartY + cartH + 20, cartW - 40, 20);

        // Mango del carrito
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.BLACK);
        g2.drawLine(cartX, cartY, cartX - 40, cartY - 40);

        // Carrito con degradado amarillo dorado
        GradientPaint grad = new GradientPaint(cartX, cartY, new Color(255, 220, 60), cartX + cartW, cartY + cartH, new Color(255, 200, 0));
        g2.setPaint(grad);
        g2.fillRoundRect(cartX, cartY, cartW, cartH, 12, 12);

        // Borde del carrito
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cartX, cartY, cartW, cartH, 12, 12);

        // Cuadrícula interna del carrito
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(cartX + cartW / 3, cartY, cartX + cartW / 3, cartY + cartH);
        g2.drawLine(cartX + 2 * cartW / 3, cartY, cartX + 2 * cartW / 3, cartY + cartH);
        g2.drawLine(cartX, cartY + cartH / 2, cartX + cartW, cartY + cartH / 2);

        // Cajas dentro del carrito
        g2.setColor(new Color(194, 136, 62));
        g2.fillRoundRect(cartX + 15, cartY + 10, 60, 50, 6, 6);
        g2.fillRoundRect(cartX + 130, cartY + 5, 90, 70, 6, 6);
        g2.fillRoundRect(cartX + 230, cartY + 15, 50, 40, 6, 6);

        g2.setColor(new Color(120, 80, 40));
        g2.drawRoundRect(cartX + 15, cartY + 10, 60, 50, 6, 6);
        g2.drawRoundRect(cartX + 130, cartY + 5, 90, 70, 6, 6);
        g2.drawRoundRect(cartX + 230, cartY + 15, 50, 40, 6, 6);

        // Logo de compra en el centro del carrito
        int circleSize = 60;  // Tamaño aumentado para el logo
        int circleX = cx - circleSize / 2;
        int circleY = cy - circleSize / 2;
        g2.setColor(Color.WHITE);
        g2.fillOval(circleX, circleY, circleSize, circleSize);
        g2.setColor(Color.BLACK);
        g2.drawOval(circleX, circleY, circleSize, circleSize);
        g2.drawLine(cx, circleY + 10, cx, circleY + circleSize - 10);
        g2.drawLine(circleX + 10, cy, circleX + circleSize - 10, cy);

        // Código de barras dentro del carrito
        g2.setStroke(new BasicStroke(1));
        int barX = cartX + cartW - 60;
        int barY = cartY + cartH - 30;
        g2.drawRect(barX, barY, 50, 15);
        for (int i = 2; i < 48; i += 4) {
            g2.drawLine(barX + i, barY + 1, barX + i, barY + 13);
        }

        // Ruedas violetas
        int r = 40;  // Tamaño de las ruedas aumentado
        int yR = cartY + cartH;
        int xR1 = cartX + 40;
        int xR2 = cartX + cartW - 80;
        g2.setColor(new Color(150, 100, 200));
        g2.fillOval(xR1, yR, r, r);
        g2.fillOval(xR2, yR, r, r);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(xR1, yR, r, r);
        g2.drawOval(xR2, yR, r, r);

        // Eje de las ruedas
        g2.fillOval(xR1 + 10, yR + 10, 12, 12);
        g2.fillOval(xR2 + 10, yR + 10, 12, 12);
    }
}
