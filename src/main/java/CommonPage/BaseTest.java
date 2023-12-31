package CommonPage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseTest {

    static final Logger logger = Logger.getLogger("BaseTest.class");
    public WebDriver driver;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ExtentSparkReporter sparkReporter;

    @Parameters({"browsername", "localDriverpath", "url"})

    @BeforeTest
    public void Setup(String browsername, String localDriverpath, String url){

        extentReports= new ExtentReports();
        //sparkReporter= new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/Report.html"));
//        sparkReporter= new ExtentSparkReporter(new File(("http://localhost:8080/job/AlliedMavenJobPipeline/ExtentReport/")));
        sparkReporter= new ExtentSparkReporter(new File(("Report.html")));
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment","QA");

//        ChromeDriverManager.chromedriver().setup();
//        driver= new ChromeDriver();
        logger.info("Launch the chrome browser");
        if (browsername.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", localDriverpath);
            driver= new ChromeDriver();
        }else if(browsername.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", localDriverpath);
            driver= new FirefoxDriver();
        }else {
            System.out.println("Do not found any browser.");
        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        logger.info("URL: http://168.61.189.83/#/login");
        driver.get(url);
    }

    @AfterTest
    public void Terminate(){
        if (driver!=null) {
            //System.out.println("Browser closed successfully.");
            driver.quit();
        }else {
            System.out.println("Browser unable to close.");
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException , InterruptedException{
        if(result.getStatus() == ITestResult.FAILURE){
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " Test Case Failed", ExtentColor.RED));
            String screenshotPath = getSreencapture(driver, result.getName());
            extentTest.fail("Test Case Failed" + extentTest.addScreenCaptureFromPath(screenshotPath));
        }
//        else if(result.getStatus() == ITestResult.SKIP){
//            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case Skipped", ExtentColor.ORANGE));
//        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getThrowable() + "Test Case PASSED", ExtentColor.GREEN));
        }
        extentReports.flush();
        //Thread.sleep(1000);
        //Desktop.getDesktop().browse(new File("Report.html").toURI());
    }

    public static String getSreencapture(WebDriver driver, String screenShort) throws IOException {
        String date= new SimpleDateFormat("MM-dd-yyyy-hhmmss").format(new Date());
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File srcFile= ts.getScreenshotAs(OutputType.FILE);
        String destination= System.getProperty("user.dir")+ "/Screenshot/" +screenShort+ "_" +date+ ".png";
        File file= new File(destination);
        FileUtils.copyFile(srcFile, file);

        //Path for Jenkins
        String jenkinsImagePath= "http://localhost:8080/job/AlliedMavenJobPipeline/ws/Screenshot/" +screenShort+ "_" +date+ ".png";
        return destination;
    }

//    public static String getCurrentDateTime(){
//        String currentdate= new SimpleDateFormat("MM-dd-yyyy-hhmmss").format(new Date());
//        return currentdate;
//    }
}