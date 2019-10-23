package top.geomatics.ips.server.entity;


import lombok.Data;

/**
 * @author chenfa
 * 离线采集时候扫描获取的wifi数据
 */
@Data
public class WifiScanInfo {

    private int AP;//ap自增
    private String BSSID;//mac地址
    private String SSID;//wifi名称
    private int Level;//信号强度

}
