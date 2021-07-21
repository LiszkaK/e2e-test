package pl.ibuk.tests.driver.config;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pl.ibuk.tests.core.properties.PropertiesNames;
import pl.ibuk.tests.core.properties.ReadProperties;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory implements SystemProperties {

    private static WebDriver webDriver;
    public static BrowserName browserName;
    private static String firefoxLocalPath, chromeLocalPath;
    private static int PAGE_LOAD_TIMEOUT = 20;
    private static int IMPLICIT_WAIT = 30;


    public static WebDriver getWebBrowserDriver() throws IOException {
        browserName = BrowserName.valueOf(BROWSER);
        setupProperties();

        if(IS_REMOTE) {
            MutableCapabilities options;
            switch (browserName) {
                case CHROME:
                    options  = new ChromeOptions();
                    ((ChromeOptions) options).setHeadless(true);
                    break;
                case FIREFOX:
                    options = new FirefoxOptions();
                    ((FirefoxOptions) options).setHeadless(true);
                    break;
                default:
                    throw new IllegalArgumentException("Browser: " + browserName + "does not  exist");
            }

            try {
                webDriver = new RemoteWebDriver(new URL(REMOTE_HUB_URL), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        else {
            switch (browserName) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", chromeLocalPath);
                    webDriver = new ChromeDriver();
                    break;
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", firefoxLocalPath);
                    webDriver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser: " + browserName + "does not  exist");
            }
        }
        webDriver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT,TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(webDriver);
        EventListenerWrapper eventListener = new EventListenerWrapper();
        eventFiringDriver.register(eventListener);
        return eventFiringDriver;
    }

    private static void setupProperties() throws IOException {
        Properties properties = new ReadProperties().getProperties();

        firefoxLocalPath = properties.getProperty(PropertiesNames.FIREFOX_DRIVER);
        chromeLocalPath = properties.getProperty(PropertiesNames.CHROME_DRIVER);
    }

    public enum BrowserName {
        FIREFOX, CHROME
    }
}