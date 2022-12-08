package pages;

import gherkin.ast.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import constantes.SeleniumConstantes;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CompararLaptosPage extends BasePage{

    public CompararLaptosPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "(//ul[@class='grid_wrapper']//span[@class='_1WJ4c']//input)[1]")
    WebElement inputCompararLaptopUno;

    @FindBy(how = How.XPATH, using = "(//mark/parent::span)[1]")
    WebElement spanSelectPrimerLaptop;

    @FindBy(how = How.XPATH, using = "(//ul[@class='grid_wrapper']//div[@class='_3--CS'])[1]")
    WebElement btnPrimerLaptop;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(),'Summary')]")
    WebElement labelTitleSummary;

    @FindBy(how = How.XPATH, using = "//button[text()='compare']")
    WebElement btnComparar;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr/th)[1]")
    WebElement thDisplay;

    @FindBy(how = How.XPATH, using = "(//table/tbody//td)[1]")
    WebElement tdDisplay;

    @FindBy(how = How.XPATH, using = "(//table/tbody//th)[2]")
    WebElement thpulgadas;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[2]/td)[1]")
    WebElement tdStorage;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[1]/td)[2]")
    WebElement tdDisplay2;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[2]/td)[2]")
    WebElement tdStorage2;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[1]/td)[3]")
    WebElement tdDisplay3;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[2]/td)[3]")
    WebElement tdStorage3;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[1]/td)[4]")
    WebElement tdDisplay4;

    @FindBy(how = How.XPATH, using = "(//table/tbody/tr[2]/td)[4]")
    WebElement tdStorage4;

    public void generoExcel() throws IOException {
        Map<String, String> dataMap = new LinkedHashMap<>();

        dataMap.put(thDisplay.getText(), thpulgadas.getText());
        dataMap.put(tdDisplay.getText(), tdStorage.getText());
        dataMap.put(tdDisplay2.getText(), tdStorage2.getText());
        dataMap.put(tdDisplay3.getText(), tdStorage3.getText());
        dataMap.put(tdDisplay4.getText(), tdStorage4.getText());

        writeToExcel(dataMap, SeleniumConstantes.filePath, SeleniumConstantes.fileName);

    }

    public boolean isVisibleLableTitleSummary(){
        return isVisible(labelTitleSummary);
    }

    public void clickBtnComparar(){
        btnComparar.click();
    }

    public void clickBtnLaptop(){
        btnPrimerLaptop.click();
    }

    public void clickSpanSelectLaptop(){
        spanSelectPrimerLaptop.click();
    }

    public void sendKeysInputCompararLaptop(String laptopUno){
        inputCompararLaptopUno.sendKeys(laptopUno);
    }

    public void navegarAlPortal(){
        navegarAlPortal(SeleniumConstantes.portalGadgetsNow);
    }
}
