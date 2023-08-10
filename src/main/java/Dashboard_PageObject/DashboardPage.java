package Dashboard_PageObject;

import CommonPage.BasePage;
import org.openqa.selenium.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) throws FileNotFoundException {
        super(driver);
    }

    static final Logger logger = Logger.getLogger("DashboardPage.class");

    FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\PageElements.properties");
    Properties prop = new Properties();

    //Methods
    public boolean verifyDashboardPage() throws IOException, InterruptedException {
        try {
            prop.load(fis);

            logger.info("verify the Dashboard page being displayed or not");
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