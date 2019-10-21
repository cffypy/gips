package top.geomatics.ips.model.cf;

/**
 * @author chenfa
 * 扫描得到的wifi信息，用于进行指纹匹配
 *
 */
public class ScanInfo {
    public String apID;
    public String mac;
    public String ssid;
    public String level;

    public String getApID() {
        return apID;
    }

    public void setApID(String apID) {
        this.apID = apID;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ScanInfo(String apID, String mac, String ssid, String level) {
        this.apID = apID;
        this.mac = mac;
        this.ssid = ssid;
        this.level = level;
    }

    public ScanInfo() {
    }
}
