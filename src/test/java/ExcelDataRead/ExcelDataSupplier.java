package ExcelDataRead;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;

public class ExcelDataSupplier {

    @DataProvider(name="loginData")
    public Object[][] getData() throws Exception {

        File file= new File(System.getProperty("user.dir")+"/src/test/java/ExcelDataRead/LoginTestData.xlsx");
        FileInputStream fis= new FileInputStream(file);
        XSSFWorkbook workbook= new XSSFWorkbook (fis);
        XSSFSheet sheet= workbook.getSheet("Sheet1");

        int noOfRows= sheet.getPhysicalNumberOfRows();
        int noOfCcolumns= sheet.getRow(0).getLastCellNum();

        String[][] data = new String[noOfRows-1][noOfCcolumns];
        for(int i=0; i<noOfRows-1; i++){
            for(int j=0; j<noOfCcolumns; j++){
                DataFormatter df= new DataFormatter();
                data[i][j]= df.formatCellValue(sheet.getRow(i+1).getCell(j));
                //System.out.println(data[i][j]);
            }
        }

        workbook.close();
        fis.close();

//        for (String[] dataArray: data){
//            System.out.println(Arrays.toString(dataArray));
//        }

        return data;

    }
}
