package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private final static String REGISTER_PAGE_TITLE_SELECTOR = "h1[class*='login__title']";

    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(id = "signupform-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "signupform-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "signupform-username")
    private WebElement userEmailInput;

    @FindBy(id = "signupform-password")
    private WebElement passwordInput;

    @FindBy(id = "signupform-passwordag")
    private WebElement repeatPasswordInput;

    @FindBy(id = "signupform-regulamin")
    private WebElement privacyPolicyCheckbox;

    @FindBy(id = "form-signup")
    private WebElement signupForm;

    public boolean isRegisterPageVisible() {
        driverWait.waitForPageToLoad();
        return webDriver.findElement(By.cssSelector(REGISTER_PAGE_TITLE_SELECTOR)).getText().equalsIgnoreCase("Rejestracja");
    }

    private RegisterPage inputFirstName(String firstName) {
        driverWait.waitForPageToLoad();
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        return this;
    }

    private RegisterPage inputLastName(String lastName) {
        driverWait.waitForPageToLoad();
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        return this;
    }

    private RegisterPage inputEmailAddress(String email) {
        driverWait.waitForPageToLoad();
        userEmailInput.clear();
        userEmailInput.sendKeys(email);
        return this;
    }

    private RegisterPage inputPassword(String password) {
        driverWait.waitForPageToLoad();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    private RegisterPage repeatInputPassword(String password) {
        driverWait.waitForPageToLoad();
        repeatPasswordInput.clear();
        repeatPasswordInput.sendKeys(password);
        return this;
    }

    private RegisterPage clickPrivacyPolicyCheckbox() {
        driverWait.waitForPageToLoad();
        privacyPolicyCheckbox.click();
        return this;
    }

    private LoginPage clickRegisterButton() {
        driverWait.waitForPageToLoad();
        signupForm.submit();
        driverWait.waitForPageToLoad();
        driverWait.waitUntil(ExpectedConditions.urlContains("logowanie"));
        closeModalPopup();
        return new LoginPage(webDriver);
    }

    public LoginPage registerUser(String firstName, String lastName, String email, String password) {
        return inputFirstName(firstName)
                .inputLastName(lastName)
                .inputEmailAddress(email)
                .inputPassword(password)
                .repeatInputPassword(password)
                .clickPrivacyPolicyCheckbox()
                .clickRegisterButton();
    }
}
