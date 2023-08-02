package Login_PageObject;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import CommonPage.BasePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) throws FileNotFoundException {super(driver);}
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop= new Properties();

    //Locators
//    public By Msg= By.xpath("//body//app-root//h2[1]");
//    public By txtemailAddress= By.xpath("//input[@id='mat-input-0']");
//    public By txtPasswrd= By.xpath("//input[@id='mat-input-1']");
//    public By chckbxRememberMe= By.xpath("//span[@class='mat-checkbox-inner-container']");
//    public By btnSignIn= By.xpath("//span[@class='mat-button-wrapper' and text()='Sign In']");
//    public By loginMsg= By.xpath("//div[contains(@role,'alertdialog')]");
    //public By loggedinUser= By.xpath("//span[@class='hidden-xs']");
    //public By pageLoader= By.xpath("//mat-spinner[@role='progressbar']");

    //Locators Method
//    public boolean verifyInvalidLogin(By username, String email, By pass, String passwd){
//        try{
//
//            WebElement welcmeTxtmsg= (new WebDriverWait(driver, 10))
//                    .until(ExpectedConditions.presenceOfElementLocated(Msg));
//            System.out.println(welcmeTxtmsg.getText());
//
//            WebElement emailaddress= (new WebDriverWait(driver,10))
//                    .until(ExpectedConditions.presenceOfElementLocated(username));
//            emailaddress.sendKeys(email);
//
//            WebElement Passwd= (new WebDriverWait(driver,10))
//                    .until(ExpectedConditions.presenceOfElementLocated(pass));
//            Passwd.sendKeys(passwd);
//
//            WebElement btnSignin= (new WebDriverWait(driver, 10))
//                    .until(ExpectedConditions.presenceOfElementLocated(btnSignIn));
//            btnSignin.click();
//
//            JavascriptExecutor jse= (JavascriptExecutor)driver;
//            WebElement failMsg= driver.findElement(By.xpath("//div[contains(@id, 'toast-container')]"));
//            jse.executeScript("return arguments[0].text", failMsg);
//            String toastMsg= "Email or Password is not valid";
//
//            if (toastMsg.contains(failMsg.getText())){
//                System.out.println(failMsg.getText());
//                return true;
//            }else {
//                System.out.println(failMsg.getText());
//                return false;
//            }
//
//        }catch (NoSuchElementException e){
//            throw e;
//
//        }
//    }

    public boolean verifyValidLogin(String email, String passwd) throws IOException, FileNotFoundException, InterruptedException {
        try{

            prop.load(fis);

            WebElement welcmeTxtmsg= driver.findElement(By.xpath(prop.getProperty("Msg")));
            System.out.println(welcmeTxtmsg.getText());

            WebElement emailaddress = driver.findElement(By.xpath(prop.getProperty("txtemailAddress")));
            emailaddress.sendKeys(email);

            WebElement Passwd = driver.findElement(By.xpath(prop.getProperty("txtPasswrd")));
            Passwd.sendKeys(passwd);

//            WebElement RememberMe= driver.findElement(By.xpath(prop.getProperty("chckbxRememberMe")));
//            RememberMe.click();

            WebElement btnSignin= driver.findElement(By.xpath(prop.getProperty("btnSignIn")));
            btnSignin.click();


            JavascriptExecutor jse = (JavascriptExecutor)driver;
            WebElement successMsg= driver.findElement(By.xpath(prop.getProperty("loginMsg")));
//            WebElement successMsg= driver.findElement(loginMsg);
            jse.executeScript("return arguments[0].text", successMsg);
            String toastMsg= "Login Successful.";
            Thread.sleep(3000);
            if (toastMsg.contains(successMsg.getText())) {
                System.out.println(successMsg.getText());
                return true;

            }else {
                System.out.println(successMsg.getText());
                return false;
            }

        }catch (IOException | InterruptedException e){
            throw e;
        }
    }
}