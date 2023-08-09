package Login_Testcase;

import CommonPage.BaseTest;
import ExcelDataRead.ExcelDataSupplier;
import Login_PageObject.LoginPage;
import Logout_PageObject.LogoutPage;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginWithMultipleSetsOfData extends BaseTest {
    public LoginWithMultipleSetsOfData(){}
    public LoginWithMultipleSetsOfData(WebDriver passdriver){ driver=passdriver;}


    @Test(dataProvider = "loginData", dataProviderClass = ExcelDataSupplier.class)
    public void verifyLoginCredentials(String email, String passwd) throws IOException, InterruptedException {

        extentTest= extentReports.createTest("Verify the Login with Different Sets of Data");
        LoginPage loginPage= new LoginPage(driver);

        boolean result= loginPage.verifyLogin(email, passwd);
        if (result){
            extentTest.log(Status.PASS, "Login successful");
        }else {
            extentTest.log(Status.FAIL, "Login failed");
        }
        Thread.sleep(2000);
        driver.navigate().refresh();

        Thread.sleep(2000);
        LogoutPage logoutPage= new LogoutPage(driver);

            logoutPage.verifyLogoutPage();
            extentTest.log(Status.PASS, "Logout success");

//        DashboardPage dashboardPage= new DashboardPage(driver);
//        boolean result1= dashboardPage.verifyDashboardPage();
//        if (result1){
//            extentTest.log(Status.PASS, "Dashboard page is display");
//        }else {
//            extentTest.log(Status.FAIL, "Dashboard page do not display");
//        }

//        Thread.sleep(2000);
//        LogoutPage logoutPage= new LogoutPage(driver);
//        boolean result2= logoutPage.verifyLogoutPage();
//        if (result2){
//            extentTest.log(Status.PASS, "Logout success");
//        }else {
//            extentTest.log(Status.FAIL, "Logout failed");
//        }

        //driver.navigate().refresh();

    }

}