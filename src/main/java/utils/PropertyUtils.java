package utils;

import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils
{
    private static Properties props = new Properties();

    static
    {
        try {
            loadPropertyFile(".properties");
        } catch (FileNotFoundException realCause)
        {
            Assert.fail("Unable to load file!", realCause);
        } catch (IOException realCause)
        {
            Assert.fail("Unable to load file!", realCause);
        }
    }

    public static void loadPropertyFile(String propertyFileName) throws IOException
    {
        props.load(new FileInputStream(propertyFileName));
    }

    public static String get(String propertyKey)
    {
        String propertyValue = props.getProperty(propertyKey.trim());

        if (propertyValue == null || propertyValue.trim().length() == 0)
        {
            System.err.println("No such property key!");
        }

        return propertyValue;
    }

    public static void setProperty(String propertyKey, String value)
    {
        props.setProperty(propertyKey, value);
    }
}
