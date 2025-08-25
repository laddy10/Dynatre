package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CapturasPantallasWeb {

    private static final Logger logger = Logger.getLogger(CapturasPantallasWeb.class.getName());
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy HH_mm_ss");
    private static int contador = 1;
    private static final String RUTA_CAPTURAS = "Capturas/";
    private static final Map<String, String> titulosCapturas = new HashMap<>();

    public static String capturaPantalla(String nombreCaptura, String titulo) {
        String fecha = dtf.format(LocalDateTime.now());
        String nombreArchivo = nombreCaptura + "_" + contador + "_" + fecha + ".png";
        contador++;

        try {
            Robot robot = new Robot();
            Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage image = robot.createScreenCapture(capture);

            // Agregar borde rojo sin texto
            BufferedImage imageWithBorder = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = imageWithBorder.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(5));
            g.drawRect(0, 0, image.getWidth() - 1, image.getHeight() - 1);
            g.dispose();

            // Guardar imagen en formato PNG
            File output = new File(RUTA_CAPTURAS + nombreArchivo);
            ImageIO.write(imageWithBorder, "png", output);

            // Guardar el título asociado al archivo para uso en WordWeb
            titulosCapturas.put(nombreArchivo, titulo);

            logger.info("Captura guardada: " + nombreArchivo);
            return nombreArchivo;
        } catch (Exception e) {
            logger.severe("Error al capturar pantalla: " + e.getMessage());
            return null;
        }
    }

    public static String obtenerTitulo(String nombreArchivo) {
        return titulosCapturas.getOrDefault(nombreArchivo, "Sin título");
    }
}