/**
 * 
 */
package top.geomatics.ips.model.ibeacon;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class IBeaconScan {
	// "TimeStamp": 1522324802741,
	// "ScanResult":[],
	// "PosLon": 114.3548967816156,
	// "PosLat": 30.529409829161356

	@JSONField(name = "TimeStamp")
	Date timeStamp;
	@JSONField(name = "ScanResult")
	List<ScanResult> scanResult;
	@JSONField(name = "PosLon")
	Double x;
	@JSONField(name = "PosLat")
	Double y;

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<ScanResult> getScanResult() {
		return scanResult;
	}

	public void setScanResult(List<ScanResult> scanResult) {
		this.scanResult = scanResult;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

}
