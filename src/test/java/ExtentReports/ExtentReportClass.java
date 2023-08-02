package ExtentReports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

//public class ExtentReportClass {
//    public ExtentReports extentReports;
//    public ExtentTest extentTest;
//    public ExtentSparkReporter sparkReporter;
//
//    @BeforeClass
//    public void startReport() throws IOException{
//        extentReports= new ExtentReports();
//        sparkReporter= new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/Reports.html"));
//        extentReports.attachReporter(sparkReporter);
//        extentReports.addSystemInfo("Environment","QA");
//        extentReports.addSystemInfo("Host","Allied");
//        extentReports.addSystemInfo("Owner","Umesh Pandey");
//
//    }
//
//    public static void getSreencapture(WebDriver driver, String screenShort) throws IOException {
//        TakesScreenshot ts = ((TakesScreenshot) driver);
//        File srcFile= ts.getScreenshotAs(OutputType.FILE);
//        File destination= new File(screenShort);
//        FileUtils.copyFile(srcFile, destination);
//
//    }
//
//    @AfterClass()
//    public void tearDown() throws IOException{
//        extentReports.flush();
//    }
//}