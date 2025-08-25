package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsReader {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/test/resources/credentials.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getEmail() {
        return properties.getProperty("dynatrace.email");
    }

    public static String getPassword() {
        return properties.getProperty("dynatrace.password");
    }
}