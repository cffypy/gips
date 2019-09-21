/**
 * 
 */
package top.geomatics.ips.model.wifi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class WifiScanInfo {
	// "AP": 0,
	// "BSSID": "d8:24:bd:76:98:3f",
	// "SSID": "liesmars123",
	// "Level": 49
	@JSONField(name = "AP")
	String apID;
	@JSONField(name = "BSSID")
	String mac;
	@JSONField(name = "SSID")
	String ssid;
	@JSONField(name = "Level")
	String level;

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

}
