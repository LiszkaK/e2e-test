package regresiontests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import pl.ibuk.tests.core.properties.PropertiesNames;
import pl.ibuk.tests.core.properties.ReadProperties;
import Listeners.TestListener;
import pl.ibuk.tests.driver.config.WebDriverFactory;
import pl.ibuk.tests.helpers.DriverWait;

import java.io.IOException;
import java.util.Properties;

@Listeners(TestListener.class)
public class BaseTest implements PropertiesNames {
    protected WebDriver webDriver;
    protected DriverWait driverWait;
    protected String applicationUrl, userEmail, userPassword, loginPageUrl, registerPageUrl;

    public void setUpTestConfig(ITestContext iTestContext) throws IOException {
        setupProperties();
        setupWebDriver();
        this.driverWait = new DriverWait(webDriver);
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
        this.registerPageUrl = properties.getProperty(PropertiesNames.URL) + "rejestracja.html";
    }

    public WebDriver getWebDriver() {
        if(webDriver == null) {
            try {
                setupWebDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return webDriver;
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        webDriver.quit();
    }

}
