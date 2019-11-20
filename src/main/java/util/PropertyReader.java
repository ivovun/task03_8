package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PropertyReader {
    private  static Properties properties;
    static {
        try {
            properties = new Properties();
            URL url1 = Thread.currentThread().getContextClassLoader().getResource("config.properties");
            if (url1 != null) {
                properties.load(new FileInputStream(url1.getPath()));
            }
        } catch (IOException | NullPointerException e) {
            System.out.println( "Error: config.properties is absent!!");
        }
    }
    public static String getProperty(String prop) {
        return properties.getProperty(prop);
    }
 }
