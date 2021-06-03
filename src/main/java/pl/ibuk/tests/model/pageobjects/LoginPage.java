package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    @FindBy(css = "h1[class*='login__title']")
    private WebElement heading;

    @FindBy(id = "loginform-username")
    private WebElement emailInput;

    @FindBy(id = "loginform-password")
    private WebElement passwordInput;

    @FindBy(css = "button[name*='login-button']")
    private WebElement loginButton;

    @FindBy(css = "a[class*='cookies-wrapper']")
    private WebElement cookieButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isPageOpened() {
        driverWait.waitForPageToLoad();
        return heading.getText().equalsIgnoreCase("Logowanie");
    }

    private void inputEmail(String email) {
        driverWait.waitForPageToLoad();
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    private void inputPassword(String password) {
        driverWait.waitForPageToLoad();
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    private void clickLoginButton() {
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    private void closeCookies() {
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.elementToBeClickable(cookieButton));
        cookieButton.click();
    }

    public HomePage loginToIbuk(String email, String password) {
        driverWait.waitForPageToLoad();
        closeCookies();
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
        driverWait.waitForPageToLoad();
        return new HomePage(webDriver);
    }
}
