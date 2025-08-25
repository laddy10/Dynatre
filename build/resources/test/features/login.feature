Feature: Ingresar a Dynatrace y tomar capturas de Dashboards

  @ValidarURL
  Scenario: Ingresar a Dynatrace y capturar Dashboards
    Given El usuario ingresa a la URL Dynatrace
    When  Se loguea con sus credenciales
    And   Navega al dashboard DIME
    And   Valida los elementos del dashboard DIME
    And   Tomar captura del dashboard "DIME"
    And   Navega al dashboard Mi claro empresas
    And   Valida los elementos del dashboard Mi claro empresas
    And   Tomar captura del dashboard "Mi Claro Empresas"
    And   Navega al dashboard portales regulatorios
    And   Valida los elementos del dashboard portales regulatorios
    And   Tomar captura del dashboard "Portales Regulatorios"
    And   Navega al dashboard portales criticidad normales
    And   Valida los elementos del dashboard portales criticidad normales
    And   Tomar captura del dashboard "Portales Criticidad Normales"
    And   cambia al contexto móvil
    And   abre WhatsApp
    And   envía todas las capturas al grupo "Pruebas Dynatrice"


