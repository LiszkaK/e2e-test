package pl.ibuk.tests.driver.config;

public interface SystemProperties {

    boolean IS_REMOTE = System.getProperty("remote").toUpperCase().equals("YES");
    String TEST_ENVIRONMENT = System.getProperty("environment");
    String BROWSER = System.getProperty("browser").toUpperCase();
    String REMOTE_HUB_URL = System.getProperty("selenoidhub");

}
