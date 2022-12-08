Feature: Prueba para comparar laptops en www.gadgetsnow.com

  Scenario: Comparar laptos y exportar a excel
    Given Ingreso al portal "https://www.gadgetsnow.com/compare-laptops"
    When Ingreso la laptop "Lenovo E41-80" y la laptop "Lenovo B40-80"
    And Hago click en el boton comparar
    And Visualizo el detalle de la  y genero reporte excel