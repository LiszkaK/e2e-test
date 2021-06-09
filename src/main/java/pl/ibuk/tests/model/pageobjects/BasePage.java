package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.ibuk.tests.helpers.DriverWait;

public class BasePage {

    protected WebDriver webDriver;
    protected Actions actions;
    protected DriverWait driverWait;

    private static final String COOKIE_BUTTON = "a[class*='cookies-wrapper']";
    private static final String MODAL_POPUP = "div[id*='modal-pop'][style*='display: block']";
    private static final String MODAL_POPUP_CLOSE_ICON = "button[class*='close'] img";

    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(getDriverFactory(webDriver), this);
    }

    private WebDriver getDriverFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        this.driverWait = new DriverWait(webDriver);
        return webDriver;
    }

    protected void waitForPage(WebElement webElement) {
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.visibilityOf(webElement));
    }

    public void closeCookies() {
        driverWait.waitForPageToLoad();
        if(webDriver.findElements(By.cssSelector(COOKIE_BUTTON)).size() > 0) {
            driverWait.waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector(COOKIE_BUTTON)));
            webDriver.findElement(By.cssSelector(COOKIE_BUTTON)).click();
        }
    }

    protected void scrollElementIntoView(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void moveToElement(WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }

    protected void closeModalPopup() {
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(MODAL_POPUP)));
        driverWait.waitUntil(ExpectedConditions.elementToBeClickable(By.cssSelector(MODAL_POPUP_CLOSE_ICON)));
        webDriver.findElement(By.cssSelector(MODAL_POPUP_CLOSE_ICON)).click();
    }
}