package top.geomatics.ips.server.entity;

import lombok.Data;

/**
 * @author chenfa
 * 通过android手机实时扫描得到的wifi信息，用于进行指纹匹配
 */
@Data
public class ScanInfo {

    public String SSID;//mac地址
    public String BSSID;//wifi名称
    public Double Level;//信号强度
}
