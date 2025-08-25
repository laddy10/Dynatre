package utils;

import net.serenitybdd.screenplay.Actor;
import java.io.File;

public class CopyCapturesToDevice {

    public static void copyImagesToDevice(Actor actor) {
        try {
            File capturesFolder = new File("Capturas/");
            File[] images = capturesFolder.listFiles((dir, name) -> name.endsWith(".png"));

            if (images != null) {
                for (File image : images) {
                    // Copiar a DCIM/Camera (donde aparecen en Recientes)
                    String devicePath = "/sdcard/DCIM/Camera/" + image.getName();

                    ProcessBuilder pb = new ProcessBuilder(
                            "adb", "push",
                            image.getAbsolutePath(),
                            devicePath
                    );

                    Process process = pb.start();
                    process.waitFor();

                    System.out.println("📷 Imagen copiada a Camera: " + image.getName());
                }

                // Refrescar la base de datos de medios
                ProcessBuilder refresh = new ProcessBuilder(
                        "adb", "shell",
                        "am", "broadcast",
                        "-a", "android.intent.action.MEDIA_SCANNER_SCAN_FILE",
                        "-d", "file:///sdcard/DCIM/Camera/"
                );
                refresh.start().waitFor();

                System.out.println("🔄 Galería actualizada - las imágenes aparecerán en Recientes");
                Thread.sleep(5000);
            }

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}