package pl.ibuk.tests.core.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static pl.ibuk.tests.driver.config.SystemProperties.TEST_ENVIRONMENT;

public class ReadProperties {
    FileInputStream inputStream;
    Properties prop = null;

    public ReadProperties() throws IOException {
        try {
            prop = new Properties();
            String propFilePath = "src/main/resources/" + TEST_ENVIRONMENT + ".properties";

            inputStream = new FileInputStream(propFilePath);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFilePath + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }
    public Properties getProperties(){
        return prop;
    }
}