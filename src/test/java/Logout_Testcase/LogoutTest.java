package Logout_Testcase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import CommonPage.BaseTest;
import Logout_PageObject.LogoutPage;

import java.io.FileNotFoundException;

public class LogoutTest extends BaseTest {
    public LogoutTest(){}
    public LogoutTest (WebDriver passdriver){driver=passdriver;}

    @Test
    public void VerifyLogout() throws FileNotFoundException {
        LogoutPage logoutPage= new LogoutPage(driver);
        logoutPage.verifyLogoutPage();

    }
}