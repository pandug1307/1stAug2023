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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ExtentSparkReporter sparkReporter;

    @Parameters({"browsername", "localDriverpath", "url"})

    @BeforeClass
    public void Setup(String browsername, String localDriverpath, String url){

        extentReports= new ExtentReports();
        //sparkReporter= new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/Report.html"));
        sparkReporter= new ExtentSparkReporter(new File(("http://localhost:8080/job/AlliedMavenJobPipeline/ExtentReport")));
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Environment","QA");

//        ChromeDriverManager.chromedriver().setup();
//        driver= new ChromeDriver();
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
        driver.get(url);
    }

    @AfterClass
    public void Terminate(){
        System.out.println("Browser closed successfully.");
        driver.quit();
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException , InterruptedException{
        if(result.getStatus() == ITestResult.FAILURE){
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
//            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " Test Case Failed", ExtentColor.RED));
            String screenshotPath = getSreencapture(driver, result.getName());
            extentTest.fail("Test Case Failed" + extentTest.addScreenCaptureFromPath(screenshotPath));
        }
//        else if(result.getStatus() == ITestResult.SKIP){
//            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case Skipped", ExtentColor.ORANGE));
//        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        extentReports.flush();
        //Thread.sleep(1000);
        //Desktop.getDesktop().browse(new File("Report.html").toURI());
    }

    public static String getSreencapture(WebDriver driver, String screenShort) throws IOException {
        TakesScreenshot ts = ((TakesScreenshot) driver);
        File srcFile= ts.getScreenshotAs(OutputType.FILE);
        String destination= System.getProperty("user.dir")+ "/Screenshot/" +screenShort+ ".png";
        File file= new File(destination);
        FileUtils.copyFile(srcFile, file);
        return destination;
    }
}