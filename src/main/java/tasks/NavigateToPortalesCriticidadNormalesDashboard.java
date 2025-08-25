package tasks;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static pageobjects.DynatracePage.*;

public class NavigateToPortalesCriticidadNormalesDashboard implements Task {

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
                Enter.theValue("Portales").into(SEARCH_FILTER),
                Hit.the(Keys.ENTER).into(SEARCH_FILTER),
                Click.on(BACK_BUTTON_2),

                WaitUntil.the(PORTALES_CRITICIDAD_NORMALES_DASHBOARD, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),

                // Hacer clic en dashboard
                Click.on(PORTALES_CRITICIDAD_NORMALES_DASHBOARD)
        );
    }

    public static NavigateToPortalesCriticidadNormalesDashboard navigate() {
        return instrumented(NavigateToPortalesCriticidadNormalesDashboard.class);
    }
}