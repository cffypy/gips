package java.top.geomatics.ips.model;

/**
 * @author chenfa
 * 将扫描到的WiFi信息封装成一个对象
 */
public class ScanResults {
    public String SSID;//AP的名字
    public String mac;//AP的mac地址
    public String level;//AP的信号强度

    public ScanResults() {
    }

    public ScanResults(String SSID, String mac, String level) {
        this.SSID = SSID;
        this.mac = mac;
        this.level = level;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
