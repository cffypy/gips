package top.geomatics.ips.config;

import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Properties;

public class PropertyManagerTest {
    PropertyManager pm = null;

    @Before
    public void setup() {
        pm = new PropertyManager();
    }

    @Test
    public void getSystemProperty() {
        System.out.println(PropertyManager.getSystemProperty("user.dir"));
    }
    @Test
    public void getSystemProperties(){
        Properties properties = PropertyManager.getSystemProperties();
        Enumeration en = properties.propertyNames();
        while (en.hasMoreElements()){
            String key = (String) en.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + ": " + value);
        }
    }
}