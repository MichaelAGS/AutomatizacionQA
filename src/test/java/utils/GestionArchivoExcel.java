package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestionArchivoExcel {

    // LinkedHashMap to maintain the insertion order
    public static Map<String, String> dataMap = new LinkedHashMap<>();
    public static String filePath = "C:\\Users\\Administrator\\Desktop";
    public static String fileName = "dataQA";

    /*public static void main(String[] args) throws IOException {

        String AN_Rate = "Data-1";
        String BN_Rate = "Data-2";
        String CN_Rate = "Data-3";

        dataMap.put("AN_Rate", AN_Rate);
        dataMap.put("BN_Rate", BN_Rate);
        dataMap.put("CN_Rate", CN_Rate);

        writeToExcel(dataMap, filePath, fileName);
    }*/

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
