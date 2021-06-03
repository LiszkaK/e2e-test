package pl.ibuk.tests.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


public class DriverWait {

    private final static int DRIVER_WAIT_TIMEOUT = 30;
    private WebDriver webDriver;
    private JavascriptExecutor javascriptExecutor;

    public DriverWait(WebDriver webDriver) {
        this.webDriver = webDriver;
        javascriptExecutor = ((JavascriptExecutor) webDriver);
    }

    public void waitUntil(ExpectedCondition<?> expectedCondition) { waitUntil(expectedCondition, DRIVER_WAIT_TIMEOUT); }

    public void waitUntil(ExpectedCondition<?> expectedCondition, int timeoutSeconds) {
        new WebDriverWait(webDriver, timeoutSeconds).until(expectedCondition);
    }

    public void strictWaitInSec(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> pageFullyLoadCondition = webDriver1 -> javascriptExecutor.executeScript("return document.readyState").equals("complete");
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
        webDriverWait.until(pageFullyLoadCondition);
    }
}
