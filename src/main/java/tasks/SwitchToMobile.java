package tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SwitchToMobile implements Task {

    private static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Cerrar navegador web actual (método correcto para Serenity 2.0.71)
            try {
                if (Serenity.getWebdriverManager().getCurrentDriver() != null) {
                    Serenity.getWebdriverManager().getCurrentDriver().quit();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
                System.out.println("No había driver web activo o ya estaba cerrado");
            }

            // Configurar capabilities para WhatsApp
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("automationName", "UiAutomator2");
            capabilities.setCapability("appPackage", "com.whatsapp");
            capabilities.setCapability("appActivity", ".Main");
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("fullReset", false);
            capabilities.setCapability("autoGrantPermissions", true);

            // Crear driver de Appium
            String appiumHub = environmentVariables.getProperty("appium.hub", "http://127.0.0.1:4723/wd/hub");
            AndroidDriver driver = new AndroidDriver(new URL(appiumHub), capabilities);

            // Configurar timeouts
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Asignar nueva capability al actor
            actor.whoCan(BrowseTheWeb.with(driver));

            System.out.println("✅ Cambio exitoso a contexto móvil - WhatsApp");
            Thread.sleep(3000);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error en URL de Appium: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error en la espera: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar a contexto móvil: " + e.getMessage());
        }
    }

    public static SwitchToMobile context() {
        return instrumented(SwitchToMobile.class);
    }
}