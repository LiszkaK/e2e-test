package regresiontests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import pl.ibuk.tests.core.properties.PropertiesNames;
import pl.ibuk.tests.core.properties.ReadProperties;
import pl.ibuk.tests.driver.config.WebDriverFactory;

import java.io.IOException;
import java.util.Properties;


public class BaseTest implements PropertiesNames {
    public WebDriver webDriver;
    public String applicationUrl, userEmail, userPassword, loginPageUrl;

    public void setUpTestConfig(ITestContext iTestContext) throws IOException {
        setupProperties();
        setupWebDriver();
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        webDriver.quit();
    }


    private void setupWebDriver() throws IOException {
        webDriver = WebDriverFactory.getWebBrowserDriver();
    }

    private void setupProperties() throws IOException {
        Properties properties = new ReadProperties().getProperties();

        this.applicationUrl = properties.getProperty(PropertiesNames.URL);
        this.userEmail = properties.getProperty(PropertiesNames.USER_EMAIL);
        this.userPassword = properties.getProperty(PropertiesNames.USER_PASSWORD);
        this.loginPageUrl = properties.getProperty(PropertiesNames.URL) + "logowanie.html";
    }

}
