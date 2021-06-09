package pl.ibuk.tests.helpers;

import com.mailslurp.api.api.InboxControllerApi;
import com.mailslurp.api.api.WaitForControllerApi;
import com.mailslurp.client.ApiClient;
import com.mailslurp.client.ApiException;
import com.mailslurp.client.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.Inbox;
import org.openqa.selenium.WebDriver;
import pl.ibuk.tests.core.properties.PropertiesNames;
import pl.ibuk.tests.core.properties.ReadProperties;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class MailSlurpHelper {

    private static ApiClient mailslurpClient;
    private static WebDriver driver;
    private static Inbox inbox;
    private static Email email;
    private static String confirmationLink;
    private static final Long TIMEOUT_MILLIS = 30000L;
    private static String apiKey;
    private InboxControllerApi inboxControllerApi;
    private static final Boolean UNREAD_ONLY = true;

    public MailSlurpHelper() throws IOException {
        setupProperties();
        mailslurpClient = Configuration.getDefaultApiClient();
        mailslurpClient.setApiKey(apiKey);
        mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
        inboxControllerApi = new InboxControllerApi(mailslurpClient);
    }

    public Inbox getInbox() throws ApiException {
        inbox = inboxControllerApi.createInbox(null,null,null,null,null,null);
        return inbox;
    }

    private void setupProperties() throws IOException {
        Properties properties = new ReadProperties().getProperties();
        this.apiKey = properties.getProperty(PropertiesNames.API_KEY);
    }

    public String getConfirmationLink(Inbox inbox) throws ApiException {
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
        email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, UNREAD_ONLY);
        assertThat(email.getSubject()).containsIgnoringCase("Aktywuj konto");
        return extractLinkFromEmailBody(email.getBody());
    }

    private String extractLinkFromEmailBody(String emailBody) {
        Pattern p = Pattern.compile("https:\\/\\/sso\\.pwn\\.pl[\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-]?");
        Matcher matcher = p.matcher(emailBody);
        matcher.find();
        return matcher.group();
    }
}
