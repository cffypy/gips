/**
 * 
 */
package top.geomatics.ips.model.ibeacon;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class ScanResult {
	// "RSSI info": [],
	// "APaddress": "5E:68:48:D7:57:6A"

	@JSONField(name = "RSSI info")
	List<RSSIInfo> rssiInfo;
	@JSONField(name = "APaddress")
	String mac;

	public List<RSSIInfo> getRssiInfo() {
		return rssiInfo;
	}

	public void setRssiInfo(List<RSSIInfo> rssiInfo) {
		this.rssiInfo = rssiInfo;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
