package top.geomatics.ips.server.model;

/**
 * Copyright 2019 bejson.com
 */

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author chenfa
 * 采集辅助信息
 */

@Data
public class WIFIscan {

    private int Round;//采集次数
    private Date Date;//数据采集时间
    private List<top.geomatics.ips.model.wifi.WifiScanInfo> WifiScanInfo;//采集时候扫描获取的wifi信息

}
