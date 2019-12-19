package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 * 位置信息
 */

public class GLocation {

    //经纬度
    private double	longitude;
    private double	latitude;

    public GLocation() {
    }

    public GLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
