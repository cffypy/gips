package top.geomatics.ips.service;

import java.util.Random;

/**
 * Created by whudyj on 2017/11/2.
 * 位置监听器
 */

public class GLocationListener implements IGLocationListener {

    @Override
    public void onLocationChanged(GLocation location) {
        double maxLon = 180.0;
        double minLon = 60.0;
        double maxLat = 80.0;
        double minLat = 10.0;
        Random random = new Random();
        /**
         * 判断位置是否改变
         */
        double longitude = random.nextDouble()*(maxLon-minLon);
        double latitude = random.nextDouble()*(maxLat-minLat);
        if (location != null){
            location.setmLongitude(longitude);
            location.setmLatitude(latitude);
        }
    }
}
