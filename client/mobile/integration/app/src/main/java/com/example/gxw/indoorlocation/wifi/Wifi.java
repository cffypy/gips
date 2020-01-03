package com.example.gxw.indoorlocation.wifi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lombok.Data;

/**
 * Created by peiyuyu on 2019/11/4.
 */

@Data
public class Wifi {
    private String Bssid;
    private Integer RSSI;
    private String Dater;
    private String Direction;

    public Wifi(String bssid, Integer RSSI, String dater, String Direction) {
        Bssid = bssid;
        this.RSSI = RSSI;
        Dater = dater;
        this.Direction = Direction;
    }

  



}

