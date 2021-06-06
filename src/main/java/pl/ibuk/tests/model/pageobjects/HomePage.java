package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(css = "button[class*='logout']")
    private WebElement logoutButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        waitForPage(logoutButton);
    }

    public boolean isLogoutButtonVisible() {
        driverWait.waitForPageToLoad();
        return logoutButton.isDisplayed();
    }
}
