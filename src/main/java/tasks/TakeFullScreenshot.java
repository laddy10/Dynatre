package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import utils.DashboardCapture;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class TakeFullScreenshot implements Task {

    private final String title;

    public TakeFullScreenshot(String title) {
        this.title = title;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Esperar que el dashboard se cargue completamente
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        DashboardCapture.captureFullDashboard(title);
    }

    public static TakeFullScreenshot withTitle(String title) {
        return instrumented(TakeFullScreenshot.class, title);
    }
}