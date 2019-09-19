package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class GPoint {
    private double latitude;
    private double longitude;

    /**
     * 获取坐标点的纬度
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * 设置坐标点的纬度
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *获取坐标点的经度
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * 设置坐标点的经度
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



}
