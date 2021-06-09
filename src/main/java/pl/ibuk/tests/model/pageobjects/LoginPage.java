package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "h1[class*='login__title']")
    private WebElement heading;

    @FindBy(id = "loginform-username")
    private WebElement emailInput;

    @FindBy(id = "loginform-password")
    private WebElement passwordInput;

    @FindBy(css = "button[name*='login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        waitForPage(heading);
    }

    private LoginPage inputEmail(String email) {
        driverWait.waitForPageToLoad();
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    private LoginPage inputPassword(String password) {
        driverWait.waitForPageToLoad();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    private HomePage clickLoginButton() {
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new HomePage(webDriver);
    }

    public HomePage loginToIbuk(String email, String password) {
        return inputEmail(email)
                .inputPassword(password)
                .clickLoginButton();
    }
}
