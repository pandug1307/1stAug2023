package Dashboard_PageObject;

import CommonPage.BasePage;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) throws FileNotFoundException {
        super(driver);
    }

    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop = new Properties();


    //Locators
//    public By pageDashboard = By.xpath("//*[contains(text(),'Dashboard')]//self::h2");
    //public By pageLoader= By.xpath("//mat-spinner[@role='progressbar']");

    //Methods
    public boolean verifyDashboardPage() throws IOException, InterruptedException {
        try {
            prop.load(fis);

            JavascriptExecutor jse = (JavascriptExecutor)driver;
            WebElement element= driver.findElement(By.xpath(prop.getProperty("pageDashboard")));
            jse.executeScript("return arguments[0].text", element);
            String text= "Dashboard";
            Thread.sleep(2000);
            if (text.contains(element.getText())) {
                System.out.println(element.getText());
                return true;

            } else {
                System.out.println(element.getText());
                return false;
            }

        }catch (NoSuchElementException | InterruptedException e){
            throw e;
        }
    }
}