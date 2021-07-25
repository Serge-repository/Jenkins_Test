package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private Properties environmentPropertiesLoader() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Property File '" + propFileName + "' not found in the classpath");
        }
        prop.load(inputStream);
        inputStream.close();
        return prop;
    }

    public String getUrl(String propertyName) throws IOException {
        return environmentPropertiesLoader().getProperty(propertyName);
    }
}
