package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
    private Properties properties = new Properties();

    public TestConfig() {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public String getname() {
        return properties.getProperty("name");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getemail() {
        return properties.getProperty("email");
    }

    public String getJob() {
        return properties.getProperty("job");
    }

    public String getspecificUserID() {
        return properties.getProperty("specificUserID");
    }

    public String getSingleUserNotFound() {
        return properties.getProperty("singleUserNotFound");
    }

    public String getSpecificPage() {
        return properties.getProperty("specificPage");
    }

    public String getDelay() {
        return properties.getProperty("delay");
    }

}
