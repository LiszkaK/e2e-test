package regresiontests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.ibuk.tests.model.pageobjects.HomePage;
import pl.ibuk.tests.model.pageobjects.LoginPage;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeTest
    public void setup(ITestContext iTestContext) throws IOException {
        super.setUpTestConfig(iTestContext);
    }

    @Test
    @Description("Test Description: Login test with correct username and correct password.")
    @Severity(SeverityLevel.BLOCKER)
    public void loginPositiveTest() {
        webDriver.get(loginPageUrl);
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.closeCookies();
        HomePage homePage = loginPage.loginToIbuk(userEmail, userPassword);
        Assert.assertTrue(homePage.isLogoutButtonVisible());
    }
}
