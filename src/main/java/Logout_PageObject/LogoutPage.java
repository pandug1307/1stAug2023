package Logout_PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import CommonPage.BasePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) throws FileNotFoundException {super(driver);}
    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop= new Properties();

    //Locators
//    public By Menudropdown= By.xpath("//span[@class='hidden-xs']");
//    public By btnSignout= By.xpath("//a[@href='javascript:void(0)' and text()=' Sign Out']");

    //Locators Method
    public boolean verifyLogoutPage() {
        try{
            prop.load(fis);

            WebElement menu = driver.findElement(By.xpath(prop.getProperty("Menudropdown")));
            menu.click();

            WebElement btnsignout= driver.findElement(By.xpath(prop.getProperty("btnSignout")));
            btnsignout.click();
            System.out.println("Logout success");

            return true;

        }catch (Exception e){
            return false;

        }

    }
}