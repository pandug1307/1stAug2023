package Logout_PageObject;

import CommonPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) throws FileNotFoundException {super(driver);}
    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop= new Properties();

    //Methods
    public boolean verifyLogoutPage() throws IOException {
        try{
            prop.load(fis);

            WebElement wclUser= driver.findElement(By.cssSelector(prop.getProperty("username")));
            Assert.assertTrue(wclUser.isDisplayed(), "User do not found");
            System.out.println(wclUser.getText());

            WebDriverWait wait= new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Menudropdown"))));
            WebElement menu = driver.findElement(By.xpath(prop.getProperty("Menudropdown")));
            menu.click();

            WebElement btnsignout= driver.findElement(By.xpath(prop.getProperty("btnSignout")));
            btnsignout.click();

            return true;

        }catch (Exception e){
            throw e;

        }

    }
}