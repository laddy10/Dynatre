package utils;

import net.serenitybdd.core.Serenity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.image.BufferedImage;


public class DashboardCapture {

    public static String captureFullDashboard(String title) {
        try {
            WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Maximizar ventana
            driver.manage().window().maximize();
            Thread.sleep(1000);

            // Scroll inicial
            js.executeScript("window.scrollTo(0, 0)");
            Thread.sleep(1000);

            // Obtener dimensiones reales del contenido
            Long contentHeight = (Long) js.executeScript(
                    "return Math.max(document.querySelector('[data-testid=\"dashboard-content\"]')?.scrollHeight || 0, " +
                            "document.querySelector('.dashboard-container')?.scrollHeight || 0, " +
                            "document.documentElement.scrollHeight)"
            );

            // Ajustar altura de la ventana para evitar espacio negro
            js.executeScript("document.body.style.height = '" + contentHeight + "px'");
            Thread.sleep(2000);

            // Usar CapturasPantallasWeb para captura completa del escritorio
            String filename = CapturasPantallasWeb.capturaPantalla("Dashboard", title);

            // Restaurar altura
            js.executeScript("document.body.style.height = 'auto'");

            return filename;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static BufferedImage addTitleToImage(BufferedImage original, String title) {
        int titleHeight = 60;
        BufferedImage titled = new BufferedImage(
                original.getWidth(),
                original.getHeight() + titleHeight,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = titled.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo blanco para título
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, original.getWidth(), titleHeight);

        // Título en negro
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g.getFontMetrics();
        int x = (original.getWidth() - fm.stringWidth(title)) / 2;
        g.drawString(title, x, 35);

        // Línea separadora
        g.setColor(Color.GRAY);
        g.setStroke(new BasicStroke(2));
        g.drawLine(0, titleHeight - 2, original.getWidth(), titleHeight - 2);

        // Imagen original
        g.drawImage(original, 0, titleHeight, null);
        g.dispose();

        return titled;
    }

    private static String sanitizeTitle(String title) {
        return title.replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s+", "_")
                .substring(0, Math.min(title.length(), 30));
    }
}