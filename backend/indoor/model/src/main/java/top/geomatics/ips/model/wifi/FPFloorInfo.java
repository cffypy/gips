/**
 * 
 */
package top.geomatics.ips.model.wifi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class FPFloorInfo {
	// [{
	// "minRssi": 37,
	// "MAC": "d8:24:bd:76:98:3f"
	// }, {
	//
	// }],
	@JSONField(name = "minRssi")
	Integer minRssi;
	@JSONField(name = "MAC")
	String mac;

	public Integer getMinRssi() {
		return minRssi;
	}

	public void setMinRssi(Integer minRssi) {
		this.minRssi = minRssi;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
