package regresiontests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.ibuk.tests.model.pageobjects.HomePage;
import pl.ibuk.tests.model.pageobjects.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest{

    @BeforeTest
    public void setup(ITestContext iTestContext) throws IOException {
        super.setUpTestConfig(iTestContext);
    }

    @Test
    public void loginPositiveTest() {
        webDriver.get(loginPageUrl);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.closeCookies();
        HomePage homePage = loginPage.loginToIbuk(userEmail, userPassword);
        Assert.assertTrue(homePage.isLogoutButtonVisible());
    }
}
