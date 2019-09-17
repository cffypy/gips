package com.example.peiyuyu.geoindoorpos.engine;

import android.location.Location;

import com.amap.api.location.AMapLocation;

/**
 * Created by peiyuyu on 2019/9/16.
 */

public class GeoIndoorLocation extends AMapLocation{
    public GeoIndoorLocation(Location location) {
        super(location);
    }
}
