package Login_Testcase;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import CommonPage.BaseTest;
import Dashboard_PageObject.DashboardPage;
import Login_PageObject.LoginPage;
import Logout_PageObject.LogoutPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//import Dashboard_PageObject.DashboardPage;

public class LoginTest extends BaseTest {
    public LoginTest(){}
    public LoginTest(WebDriver passdriver){ driver=passdriver;}

//    @Test
//    public void InValidLogin() {
//
//        extentTest= extentReports.createTest("Verify the InValid Login");
//        LoginPage loginPage= new LoginPage(driver);
//        extentTest.info("Verify the page tile");
//        Assert.assertEquals(driver.getTitle(), "Allied Packaging Corporation");
//        extentTest.log(Status.PASS, "Page title is matched");
//
//        //extentTest.info("Verify the InValidLogin");
//        boolean result= loginPage.verifyInvalidLogin(loginPage.txtemailAddress, "allied.packagingqa@mailinator.com", loginPage.txtPasswrd, "Softweb123");
//        if (result==false) {
//            extentTest.log(Status.PASS, "Login succeed");
//        }
//        else {
//            extentTest.log(Status.FAIL, "Login failed");
//        }
//    }

    @Test
    public void ValidLogin() throws FileNotFoundException, IOException, InterruptedException {

        extentTest= extentReports.createTest("Verify the Valid Login");
        LoginPage loginPage= new LoginPage(driver);

        extentTest.info("Verify the page tile");
        Assert.assertEquals(driver.getTitle(), "Allied Packaging Corporation");
        extentTest.log(Status.PASS, "Page title is matched");

        //extentTest.info("Verify the ValidLogin");
        boolean result= loginPage.verifyValidLogin("umesh.pandey@softwebsolutions.com", "Hello@#123");
        Thread.sleep(3000);
        if (result==true) {
            extentTest.log(Status.PASS, "Login succeed");
        }
        else {
            extentTest.log(Status.FAIL, "Login failed");
        }

        DashboardPage dashboardPage= new DashboardPage(driver);
        //Thread.sleep(2000);
        boolean result1= dashboardPage.verifyDashboardPage();

        if (result1==true) {
            extentTest.log(Status.PASS, "Dashboard page is display");
        }else {
            extentTest.log(Status.FAIL, "Dashboard page do not display");
        }
    }

    @Test
    public void verifyLogout() throws FileNotFoundException, InterruptedException {

        extentTest= extentReports.createTest("Verify the Logout");
        //extentTest.info("Verify the Logout");
        LogoutPage logoutPage= new LogoutPage(driver);
        boolean result3= logoutPage.verifyLogoutPage();
        Thread.sleep(3000);
        if (result3==true){
            extentTest.log(Status.PASS, "Logout success");
        }else {
            extentTest.log(Status.FAIL, "Logout failed");
        }


        //extentTest= extentReports.createTest("Verify to open new Tab window");
        ((JavascriptExecutor) driver).executeScript("window.open('https://qa.iotconnect.io/dashboard','_blank')");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        //extentTest.info("new Tab is opened successfully");
//        driver.switchTo().window(tabs.get(0));
//        extentTest.info("back to original window");
    }
}