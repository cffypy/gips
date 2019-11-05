package top.geomatics.ips.server.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author chenfa
 * 离线采集好的的wifi数据
 */
@Data
public class WifiRoot {

    private int Point_NO;
    private double PosLon;
    private double PosLat;
    private String Building_ID;
    private String Floor_ID;
    private Date Date;
    private List<WIFIscan> WIFIscan;
}