package tasks;

import interactions.click.ClickTextoQueContengaMobile;
import interactions.waits.WaitFor;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CopyCapturesToDevice;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static pageobjects.DynatracePage.*;

public class SendCapturesViaWhatsApp implements Task {

    private String groupName;


    public SendCapturesViaWhatsApp(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            File capturesFolder = new File("Capturas/");
            File[] images = capturesFolder.listFiles((dir, name) -> name.endsWith(".png"));

            if (images == null || images.length == 0) {
                System.out.println("❌ No se encontraron capturas para enviar");
                return;
            }

            System.out.println("📱 Copiando " + images.length + " capturas al dispositivo...");
            CopyCapturesToDevice.copyImagesToDevice(actor);

            System.out.println("📱 Enviando " + images.length + " capturas via WhatsApp...");

            actor.attemptsTo(
                    WaitFor.aTime(1000),
                    searchForGroup(),
                    WaitFor.aTime(1000),
                    // UNA SOLA LLAMADA - envía las 4 imágenes juntas
                    sendAllImagesAtOnce()
            );

            System.out.println("✅ Todas las capturas enviadas exitosamente");

        } catch (Exception e) {
            System.err.println("❌ Error enviando capturas: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private Task searchForGroup() {
        // Crear target dinámico para el grupo específico
        return Task.where("{0} busca el grupo " + groupName,
                Click.on(BTN_ENVIAR_MENSAJE),
                Click.on(BTN_LUPA),
                Enter.theValue("Pruebas").into(TXT_BUSCAR_TEXTO),
                ClickTextoQueContengaMobile.elTextoContiene(groupName)
        );
    }

    private Task sendAllImagesAtOnce() {
        return Task.where("{0} selecciona las 4 imágenes Dashboard",
                Click.on(ATTACH_BUTTON),
                Click.on(GALLERY_OPTION),

                Task.where("Selección con timeouts optimizados",
                        actor -> {
                            try {
                                AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();

                                // REDUCIR TIMEOUT TEMPORAL
                                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                                System.out.println("🔍 INICIANDO SELECCIÓN - " + java.time.LocalTime.now());

                                String fechaHoy = java.time.LocalDate.now()
                                        .format(java.time.format.DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy",
                                                new java.util.Locale("es", "ES")));

                                String baseXpath = "(//android.widget.ImageView[@content-desc=\"‎Foto, fecha " + fechaHoy + "\"])";

                                for (int i = 1; i <= 4; i++) {
                                    System.out.println("🔍 Buscando imagen " + i + " - " + java.time.LocalTime.now());

                                    WebElement imagen = driver.findElement(By.xpath(baseXpath + "[" + i + "]"));
                                    System.out.println("✅ Imagen " + i + " encontrada - " + java.time.LocalTime.now());

                                    imagen.click();
                                    System.out.println("👆 Click en imagen " + i + " completado - " + java.time.LocalTime.now());
                                }

                                System.out.println("🔍 Buscando botón enviar - " + java.time.LocalTime.now());

                                actor.attemptsTo(
                                        Click.on(BTN_SEND)
                                );

                                System.out.println("📤 ENVÍO COMPLETADO - " + java.time.LocalTime.now());

                                // RESTAURAR TIMEOUT ORIGINAL
                                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

                            } catch (Exception e) {
                                System.err.println("❌ Error: " + e.getMessage());
                            }
                        }
                ),

                WaitFor.aTime(2000)
        );
    }

    public static SendCapturesViaWhatsApp toGroup(String groupName) {
        return instrumented(SendCapturesViaWhatsApp.class, groupName);
    }
}