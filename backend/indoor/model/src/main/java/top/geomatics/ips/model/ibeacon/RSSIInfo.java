/**
 * 
 */
package top.geomatics.ips.model.ibeacon;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class RSSIInfo {
	// RSSI": -46,
	// "APaddress": "12:3B:6A:1A:8F:83"

	@JSONField(name = "RSSI")
	Integer rssi;
	@JSONField(name = "APaddress")
	String mac;

	public Integer getRssi() {
		return rssi;
	}

	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
