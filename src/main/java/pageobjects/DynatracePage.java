package pageobjects;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DynatracePage {


    //Login Dynatrice
    public static final Target EMAIL_FIELD = Target.the("Campo email")
            .located(By.xpath("//*[@id=':r0:']"));
    public static final Target NEXT_BUTTON = Target.the("Botón Next")
            .located(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div/div[1]/div[1]/div/form/div/div[2]/button/div/div[1]"));
    public static final Target PASSWORD_FIELD = Target.the("Campo password")
            .located(By.xpath("//*[@id=\":rb:\"]"));
    public static final Target SIGN_IN_BUTTON = Target.the("Botón Sign in")
            .located(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div/div[1]/div[1]/div/form/div/div[4]/button[2]/div/div[1]"));


    // Navegación a Dashboards
    public static final Target DASHBOARDS_MENU = Target.the("Menú Dashboards")
            .located(By.xpath("/html/body/div[4]/div[1]/div/div[2]/div/div/div[3]/div[1]/div/div/a[1]/div"));

    // Filtro de búsqueda
    public static final Target SEARCH_FILTER = Target.the("Filtro de búsqueda")
            .located(By.xpath("//*[@id=\"dt-filter-field-0\"]"));


    // Validaciones dentro del dashboard
    public static final Target DIME_TITLE = Target.the("Título DIME")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[3]/div[1]/div/div/div"));

    public static final Target ESTADO_GENERAL = Target.the("Estado General")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[18]"));

    public static final Target LINK_ACCESO_TEXT = Target.the("Texto Link de acceso")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[10]/div[2]/div/div[1]/div/p"));
    public static final Target BACK_BUTTON = Target.the("Botón esconder menu")
            .located(By.xpath("/html/body/div[4]/div[1]/div/div[1]/span"));

    public static final Target MENU_BUTTON = Target.the("Botón menú hamburguesa")
            .located(By.xpath("/html/body/div[4]/div[2]/div/div[1]/span"));

    // Dashboard DIME
    public static final Target DIME_DASHBOARD = Target.the("Dashboard DIME")
            .located(By.xpath("/html/body/app-root/ng-component/app-shell/main/ng-component/db-dashboard-list-overview/page-content/div/dt-quick-filter/dt-drawer-container/div/div/div/dt-card/div[2]/div/div/db-dashboard-table/dt-table/dt-row[1]/dt-cell[3]/div/div/a"));

    // Dashboard CLARO EMPRESAS
    public static final Target CLARO_EMPRESAS_DASHBOARD = Target.the("Dashboard Claro Empresas")
            .located(By.xpath("/html/body/app-root/ng-component/app-shell/main/ng-component/db-dashboard-list-overview/page-content/div/dt-quick-filter/dt-drawer-container/div/div/div/dt-card/div[2]/div/div/db-dashboard-table/dt-table/dt-row/dt-cell[3]/div/div/a"));

    // Dashboard PORTALES REGULATORIOS
    public static final Target PORTALES_REGULATORIOS_DASHBOARD = Target.the("Dashboard Portales regulatorios")
            .located(By.xpath("/html/body/app-root/ng-component/app-shell/main/ng-component/db-dashboard-list-overview/page-content/div/dt-quick-filter/dt-drawer-container/div/div/div/dt-card/div[2]/div/div/db-dashboard-table/dt-table/dt-row[1]/dt-cell[3]/div/div/a"));

    // Dashboard PORTALES CRITICIDAD NORMALES
    public static final Target PORTALES_CRITICIDAD_NORMALES_DASHBOARD = Target.the("Dashboard Portales criticidad normales")
            .located(By.xpath("/html/body/app-root/ng-component/app-shell/main/ng-component/db-dashboard-list-overview/page-content/div/dt-quick-filter/dt-drawer-container/div/div/div/dt-card/div[2]/div/div/db-dashboard-table/dt-table/dt-row[2]/dt-cell[3]/div/div/a"));

    // Elementos dashboard Mi Claro Empresas
    public static final Target MI_CLARO_EMPRESAS_TITLE = Target.the("Título Mi Claro Empresas")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[3]/div[1]/div/div/div"));

    public static final Target APDEX_SECTION = Target.the("Sección Apdex")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[18]"));

    public static final Target METRICAS_DEL_DIA = Target.the("Métricas del día")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[12]"));

    public static final Target BACK_BUTTON_2 = Target.the("Botón esconder menu")
            .located(By.xpath("/html/body/app-root/ng-component/app-shell/nav/button/dt-icon"));


    // Elementos dashboard Portales Regulatorios
    public static final Target PORTALES_REGULATORIOS_TITLE = Target.the("Título Portales Regulatorios")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[3]/div[1]/div/div/div"));

    public static final Target INFRAESTRUCTURA_SECTION = Target.the("Sección Infraestructura")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[62]"));


    public static final Target PORTALES_CRITICIDAD_NORMALES_TITLE = Target.the("Título Portales criticidad normales")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[3]/div[1]/div/div/div"));

    public static final Target INFRAESTRUCTURA_SECTION_2 = Target.the("Sección Infraestructura")
            .located(By.xpath("/html/body/div[4]/div[6]/div/div[6]/div[1]/div[42]"));


    // Elementos de WhatsApp
    public static final Target LBL_WHATSAPP =
            Target.the("Texto de WhatsApp")
                    .located(By.id("toolbar_logo"));
    public static final Target BTN_ENVIAR_MENSAJE =
            Target.the("Boton enviar mensaje")
                    .located(By.id("fab"));
    public static final Target BTN_LUPA =
            Target.the("Boton lupa")
                    .located(By.id("menuitem_search"));
    public static final Target TXT_BUSCAR_TEXTO =
            Target.the("Caja de texto buscar")
                    .located(By.id("search_src_text"));
    public static final Target ATTACH_BUTTON = Target.the("Botón adjuntar")
            .located(By.xpath("//android.widget.ImageButton[@content-desc='Adjuntar']"));
    public static final Target GALLERY_OPTION = Target.the("Opción galería")
            .located(By.xpath("//android.widget.TextView[@text='Galería']"));
    public static final Target BTN_SEND = Target.the("Boton enviar")
            .located(By.id("com.whatsapp:id/send_media_btn"));


}