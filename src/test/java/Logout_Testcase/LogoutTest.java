package Logout_Testcase;

import CommonPage.BaseTest;
import Logout_PageObject.LogoutPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogoutTest extends BaseTest {
    public LogoutTest(){}
    public LogoutTest (WebDriver passdriver){driver=passdriver;}

    @Test
    public void VerifyLogout() throws IOException, InterruptedException {
        LogoutPage logoutPage= new LogoutPage(driver);
        logoutPage.verifyLogoutPage();
    }
}