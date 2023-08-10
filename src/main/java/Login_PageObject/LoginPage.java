package Login_PageObject;

import CommonPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) throws FileNotFoundException {super(driver);}

    static final Logger logger = Logger.getLogger("LoginPage.class");

    //Read the file
    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop= new Properties();

    //Methods
    public boolean verifyLogin(String email, String passwd) throws IOException, InterruptedException {
        try {

            prop.load(fis);

            logger.info("Enter username");
            WebElement emailaddress = driver.findElement(By.cssSelector(prop.getProperty("txtemailAddress")));
            emailaddress.sendKeys(email);

            logger.info("Enter password");
            WebElement Passwd = driver.findElement(By.cssSelector(prop.getProperty("txtPasswrd")));
            Passwd.sendKeys(passwd);

            logger.info("Click SignIn button");
            WebElement btnSignin = driver.findElement(By.xpath(prop.getProperty("btnSignIn")));
            btnSignin.click();

            logger.info("get successful message");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement successMsg = driver.findElement(By.xpath(prop.getProperty("loginMsg")));
            jse.executeScript("return arguments[0].text", successMsg);
            String toastMsg= "Login Successful.";
            Thread.sleep(3000);
            if (toastMsg.contains(successMsg.getText())) {
                //Assert.assertTrue(successMsg.isDisplayed(), "Login Failed");
                System.out.println(successMsg.getText());
                return true;

            }else {
                System.out.println(successMsg.getText());
                return false;
            }

        }catch (IOException  e){
            throw e;
        }
    }
}