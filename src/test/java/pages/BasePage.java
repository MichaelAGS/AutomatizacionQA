package pages;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BasePage {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    static {
        System.setProperty("webdriver.chrome.driver", "environment/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    public boolean isVisible(WebElement webElement){
        boolean isVisible;
        try {
            isVisible = webElement.isDisplayed();
        }catch (Exception e){
            isVisible = false;
        }
        return isVisible;
    }

    public static void navegarAlPortal(String url){
        driver.get(url);
    }

    public void esperaImplicita(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void esperaExplicita(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10,100);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void writeToExcel(Map<String, String> dataMap, String filePath, String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName + ".xlsx");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        XSSFWorkbook IRWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = IRWorkbook.createSheet();
        List<String> headers = dataMap.keySet().stream().collect(Collectors.toList());
        List<String> data = new ArrayList<>(dataMap.values());

        setHeadersAndFillData(sheet, headers, data);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            IRWorkbook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setHeadersAndFillData(XSSFSheet sheet, List<String> headers, List<String> data) {
        int headersSize = headers.size();
        int dataSize = headers.size();
        Row headerRow = sheet.createRow(0);
        Row dataRow = sheet.createRow(1);
        setCells(headers, headersSize, headerRow);
        setCells(data, dataSize, dataRow);
    }

    private static void setCells(List<String> cellData, int headersSize, Row row) {
        for (int rn = 0; rn < headersSize; rn++) {
            row.createCell(rn).setCellValue(cellData.get(rn));
        }
    }
}
