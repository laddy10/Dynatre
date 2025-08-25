package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static pageobjects.DynatracePage.*;


public class LoginToDynatrace implements Task {

    private final String email;
    private final String password;

    public LoginToDynatrace(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(email).into(EMAIL_FIELD),
                Click.on(NEXT_BUTTON),
                Enter.theValue(password).into(PASSWORD_FIELD),
                Click.on(SIGN_IN_BUTTON)
        );
    }

    public static LoginToDynatrace withCredentials(String email, String password) {
        return instrumented(LoginToDynatrace.class, email, password);
    }
}