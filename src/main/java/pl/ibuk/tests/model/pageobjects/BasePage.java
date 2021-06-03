package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pl.ibuk.tests.helpers.DriverWait;

public class BasePage {
    public WebDriver webDriver;
    protected Actions actions;
    protected DriverWait driverWait;

    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(getDriverFactory(webDriver), this);
    }

    private WebDriver getDriverFactory(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        this.driverWait = new DriverWait(webDriver);
        return webDriver;
    }
}