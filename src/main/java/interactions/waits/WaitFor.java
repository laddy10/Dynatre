package interactions.waits;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class WaitFor {
    public static Interaction aTime(int milliseconds) {
        return new Interaction() {
            @Override
            public <T extends Actor> void performAs(T actor) {
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
    }
}