package regresiontests;

import com.mailslurp.client.ApiException;
import com.mailslurp.models.Inbox;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.ibuk.tests.dataStructure.User;
import pl.ibuk.tests.helpers.MailSlurpHelper;
import pl.ibuk.tests.model.pageobjects.RegisterConfirmationPage;
import pl.ibuk.tests.model.pageobjects.RegisterPage;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest extends BaseTest {

    private MailSlurpHelper mailSlurpHelper;
    private User testUser;
    private Inbox userInbox;
    private final static String CONFIRMATION_MESSAGE = "Dziękujemy za rejestrację konta";

    @BeforeTest
    public void setup(ITestContext iTestContext) throws IOException, ApiException {
        super.setUpTestConfig(iTestContext);
        mailSlurpHelper = new MailSlurpHelper();
        userInbox = mailSlurpHelper.getInbox();
        testUser = new User.UserBuilder(userInbox.getEmailAddress()).build();
    }

    @Test
    public void registerTest() throws ApiException {
        webDriver.get(registerPageUrl);
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.closeCookies();
        assertThat(registerPage.isRegisterPageVisible()).isTrue();
        registerPage.registerUser(
                testUser.getFirstName(),
                testUser.getLastName(),
                testUser.getEmailAddress(),
                userPassword);

        confirmRegisterByOpeningConfirmationLink();
        assertThatRegisterConfirmationMessageIsVisible();
    }

    private void confirmRegisterByOpeningConfirmationLink() throws ApiException {
        String confirmationLink = mailSlurpHelper.getConfirmationLink(userInbox);
        webDriver.get(confirmationLink);
    }

    private void assertThatRegisterConfirmationMessageIsVisible() {
        RegisterConfirmationPage registerConfirmationPage = new RegisterConfirmationPage(webDriver);
        assertThat(registerConfirmationPage.isRegisterConfirmationPageVisible(CONFIRMATION_MESSAGE)).isTrue();
    }
}
