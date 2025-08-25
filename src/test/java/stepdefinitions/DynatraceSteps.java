package stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import freemarker.log.Logger;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import tasks.*;
import utils.AdjustPageZoom;
import utils.CredentialsReader;

import java.io.File;

import static pageobjects.DynatracePage.*;

public class DynatraceSteps {

    private Actor usuario;
    private EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
    private static final Logger LOGGER = Logger.getLogger(DynatraceSteps.class.getName());


    @Before
    public void setUpActor() {
        OnStage.setTheStage(new OnlineCast());
        usuario = OnStage.theActorCalled("Usuario");
        LOGGER.info("Limpiando carpeta de capturas...");
        File folder = new File("Capturas");

        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    file.delete();
                }
            }
        }
    }

    @Given("^El usuario ingresa a la URL Dynatrace$")
    public void elUsuarioNavegaADynatrace() {
        String dynatraceUrl = environmentVariables.getProperty("dynatrace.url");
        usuario.attemptsTo(
                Open.url(dynatraceUrl)
        );

    }

    @When("^Se loguea con sus credenciales$")
    public void elUsuarioSeLoguea() {
        String email = CredentialsReader.getEmail();
        String password = CredentialsReader.getPassword();

        usuario.attemptsTo(
                LoginToDynatrace.withCredentials(email, password)
        );
    }


    @And("^Navega al dashboard DIME$")
    public void navegaAlDashboardDIME() {
        usuario.attemptsTo(
                NavigateToDimeDashboard.navigate());
    }

    @And("^Valida los elementos del dashboard DIME$")
    public void validaElementosDashboard() {
        usuario.attemptsTo(
                WaitUntil.the(DIME_TITLE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(ESTADO_GENERAL, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(LINK_ACCESO_TEXT, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    @And("^Navega al dashboard Mi claro empresas$")
    public void navegaAlDashboardClaroEmpresas() {
        usuario.attemptsTo(
                NavigateToMiClaroEmpresasDashboard.navigate());
    }

    @And("^Valida los elementos del dashboard Mi claro empresas$")
    public void validaElementosDashboardMiClaroEmpresas() {
        usuario.attemptsTo(
                WaitUntil.the(MI_CLARO_EMPRESAS_TITLE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(APDEX_SECTION, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(METRICAS_DEL_DIA, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    @And("^Tomar captura del dashboard \"([^\"]*)\"$")
    public void tomarCapturaDelDashboard(String dashboardName) {
        int zoom = 46; // Default

        if (dashboardName.equals("DIME")) {
            zoom = 48;
        } else if (dashboardName.equals("Portales Regulatorios")) {
            zoom = 46;
        }

        usuario.attemptsTo(
                AdjustPageZoom.to(zoom),
                TakeFullScreenshot.withTitle("Dashboard " + dashboardName),
                AdjustPageZoom.to(100)
        );
    }

    @And("^Navega al dashboard portales regulatorios$")
    public void navegaAlDashboardportalesregulatorios() {
        usuario.attemptsTo(
                NavigateToPortalesRegulatoriosDashboard.navigate());
    }

    @And("^Valida los elementos del dashboard portales regulatorios$")
    public void validaElementosDashboardPortalesRegulatorios() {
        usuario.attemptsTo(
                WaitUntil.the(PORTALES_REGULATORIOS_TITLE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(INFRAESTRUCTURA_SECTION, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }


    @When("^cambia al contexto móvil$")
    public void cambiaAlContextoMovil() {
        usuario.attemptsTo(
                SwitchToMobile.context());
    }

    @When("^abre WhatsApp$")
    public void abreWhatsApp() {
        usuario.attemptsTo(
                WaitUntil.the(LBL_WHATSAPP, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

    @When("^envía todas las capturas al grupo \"([^\"]*)\"$")
    public void enviaTodasLasCapturas(String groupName) {
        usuario.attemptsTo(
                SendCapturesViaWhatsApp.toGroup(groupName));
    }

    @And("^Navega al dashboard portales criticidad normales$")
    public void navegaAlDashboardPortalesCriticidadNormales() {
        usuario.attemptsTo(
                NavigateToPortalesCriticidadNormalesDashboard.navigate());
    }

    @And("^Valida los elementos del dashboard portales criticidad normales$")
    public void validaElementosDashboardPortalescCriticidadNormales() {
        usuario.attemptsTo(
                WaitUntil.the(PORTALES_CRITICIDAD_NORMALES_TITLE, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds(),
                WaitUntil.the(INFRAESTRUCTURA_SECTION_2, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
        );
    }

}