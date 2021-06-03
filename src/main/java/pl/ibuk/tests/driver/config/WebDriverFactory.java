package pl.ibuk.tests.driver.config;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory implements SystemProperties {

    private static WebDriver webDriver;
    public static BrowserName browserName;


    public static WebDriver getWebBrowserDriver() {
        browserName = BrowserName.valueOf(BROWSER);

        if(IS_REMOTE) {
            MutableCapabilities options = null;
            switch (browserName) {
                case CHROME:
                    options  = new ChromeOptions();
                    ((ChromeOptions) options).setHeadless(true);
                    break;
                case FIREFOX:
                    options = new FirefoxOptions();
                    ((FirefoxOptions) options).setHeadless(true);
                    break;
            }

            try {
                System.out.println(REMOTE_HUB_URL);
                webDriver = new RemoteWebDriver(new URL(REMOTE_HUB_URL), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        webDriver.manage().window().setSize(new Dimension(1024,768));
        return webDriver;
    }

    public enum BrowserName {
        FIREFOX, CHROME
    }
}