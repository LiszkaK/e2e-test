package pl.ibuk.tests.model.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterConfirmationPage extends BasePage {

    private final static String REGISTER_CONFIRMATION_MESSAGE = "//b[contains(text(), '%s')]";

    public RegisterConfirmationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isRegisterConfirmationPageVisible(String confirmationMessage) {
        driverWait.waitForPageToLoad();
        return webDriver.findElement(By.xpath(String.format(REGISTER_CONFIRMATION_MESSAGE, confirmationMessage)))
                .getText().equalsIgnoreCase(confirmationMessage);
    }

}
