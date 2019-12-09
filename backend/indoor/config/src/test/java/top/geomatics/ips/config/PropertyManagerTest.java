package top.geomatics.ips.config;

import org.junit.Before;
import org.junit.Test;

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
}