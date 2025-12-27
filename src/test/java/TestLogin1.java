import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserHomePage;

import java.time.Duration;

public class TestLogin1 extends TestBase {

    LoginPage loginPage;
    String userName,password;

    @Test(priority = 0)
    public void setup1(){
        System.out.println("Starting login");
        String webUrl = properties.getProperty("launchUrl");
        loginPage = new LoginPage(driver,webUrl);
        loginPage.get();
        userName = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    @Test(priority = 1)
    public void login1() throws InterruptedException {
        loginPage.enterUserName(userName).enterUserPassword(password).clickSubmitButton();
        Thread.sleep(Duration.ofSeconds(10));
    }

    @Test(priority = 2)
    public void checkIfLoggedIn1(){
        UserHomePage userHomePage = new UserHomePage(driver);
        Assert.assertTrue(userHomePage.checkIfLoggedInTitleIsThere(),"The User is not logged in");
    }
}
