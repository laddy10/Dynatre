package tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.Keys;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static pageobjects.DynatracePage.*;

public class NavigateToDimeDashboard implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        List<WebElementFacade> menuhamburguesa = MENU_BUTTON.resolveAllFor(actor);
        if (!menuhamburguesa.isEmpty()) {
            actor.attemptsTo(
                    Click.on(MENU_BUTTON)
            );
        }

        actor.attemptsTo(
                // Ir a Dashboards
                Click.on(DASHBOARDS_MENU),
                WaitUntil.the(SEARCH_FILTER, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),

                // Filtrar por DIME
                Enter.theValue("DIME").into(SEARCH_FILTER),
                Hit.the(Keys.ENTER).into(SEARCH_FILTER),
                WaitUntil.the(DIME_DASHBOARD, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),

                // Hacer clic en dashboard DIME
                Click.on(DIME_DASHBOARD),
                WaitUntil.the(DIME_TITLE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(15).seconds(),

                Click.on(BACK_BUTTON)
        );
    }

    public static NavigateToDimeDashboard navigate() {
        return instrumented(NavigateToDimeDashboard.class);
    }
}