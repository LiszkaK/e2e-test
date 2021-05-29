package pl.ibuk.tests.model.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;

    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(getDriverFactory(webDriver), this);
    }

    private WebDriver getDriverFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 5);
        this.actions = new Actions(webDriver);
        return webDriver;
    }
}
