package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.CompararLaptosPage;

import java.io.IOException;

public class CompararLaptosSteps {

    CompararLaptosPage compararLaptosPage = new CompararLaptosPage();

    @Given("Ingreso al portal {string}")
    public void ingresoAlPortal(String urlPortal){
        compararLaptosPage.navegarAlPortal();
        compararLaptosPage.esperaImplicita();
    }

    @When("Ingreso la laptop {string} y la laptop {string}")
    public void ingresoLaLaptopYLaLaptop(String laptopUno, String laptopDos) {
        //selecciono laptop uno
        compararLaptosPage.clickBtnLaptop();
        compararLaptosPage.sendKeysInputCompararLaptop(laptopUno);
        compararLaptosPage.esperaImplicita();
        compararLaptosPage.clickSpanSelectLaptop();
        //selecciono laptop dos
        compararLaptosPage.clickBtnLaptop();
        compararLaptosPage.sendKeysInputCompararLaptop(laptopDos);
        compararLaptosPage.esperaImplicita();
        compararLaptosPage.clickSpanSelectLaptop();
        compararLaptosPage.esperaImplicita();
    }

    @And("Hago click en el boton comparar")
    public void hagoClickEnElBotonComparar() {
        compararLaptosPage.clickBtnComparar();
        compararLaptosPage.esperaImplicita();
    }

    @And("Visualizo el detalle de la  y genero reporte excel")
    public void visualizoElDetalleDeLaYGeneroReporteExcel() throws IOException, InterruptedException {
        //compararLaptosPage.wait(5000);
        Assert.assertTrue(compararLaptosPage.isVisibleLableTitleSummary());
        compararLaptosPage.generoExcel();

    }
}
