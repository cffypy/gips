package top.geomatics.ips.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;


public class GLocationClientTest {

    private GLocationClient client = null;

    @Before
    public void before() throws Exception {
        client = new GLocationClient();
        client.setLocationListener(new GLocationListener());
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: getLastKnownLocation()
     */
    @Test
    public void testGetLastKnownLocation() throws Exception {
        GLocation location = client.getLastKnownLocation();
        System.out.println("当前位置：经度= " + location.getLongitude() + " 纬度= " + location.getLatitude());
    }

    /**
     * Method: getVersion()
     */
    @Test
    public void testGetVersion() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: isStarted()
     */
    @Test
    public void testIsStarted() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: onDestroy()
     */
    @Test
    public void testOnDestroy() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setApiKey(String apiKey)
     */
    @Test
    public void testSetApiKey() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setLocationOption(GLocationClientOption option)
     */
    @Test
    public void testSetLocationOption() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: startLocation()
     */
    @Test
    public void testStartLocation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: stopAssistantLocation()
     */
    @Test
    public void testStopAssistantLocation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: stopLocation()
     */
    @Test
    public void testStopLocation() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: unRegisterLocationListener(GLocationListener listener)
     */
    @Test
    public void testUnRegisterLocationListener() throws Exception {
//TODO: Test goes here... 
    }


} 
