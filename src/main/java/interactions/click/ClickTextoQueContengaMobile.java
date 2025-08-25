package interactions.click;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClickTextoQueContengaMobile {

    public static Interaction elTextoContiene(String texto) {
        return new Interaction() {
            @Override
            public <T extends Actor> void performAs(T actor) {
                try {
                    AndroidDriver driver = (AndroidDriver) BrowseTheWeb.as(actor).getDriver();

                    // Buscar elemento que contenga el texto
                    List<WebElement> elementos = driver.findElements(
                            By.xpath("//*[contains(@text,'" + texto + "')]")
                    );

                    if (!elementos.isEmpty()) {
                        elementos.get(0).click();
                        System.out.println("✅ Clic en elemento que contiene: " + texto);
                    } else {
                        throw new RuntimeException("❌ No se encontró elemento con texto: " + texto);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("❌ Error al hacer clic en texto: " + e.getMessage());
                }
            }
        };
    }
}