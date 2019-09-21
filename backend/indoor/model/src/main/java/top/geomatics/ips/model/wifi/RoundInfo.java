/**
 * 
 */
package top.geomatics.ips.model.wifi;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class RoundInfo {
	// "Round": 1,
	// "Date": "2018-03-04 18:07:44",
	// "WifiScanInfo": []

	@JSONField(name = "Round")
	Integer roundNumber;
	@JSONField(name = "Date")
	Date date;
	@JSONField(name = "WifiScanInfo")
	List<WifiScanInfo> wifiScanInfo;

	public Integer getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(Integer roundNumber) {
		this.roundNumber = roundNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<WifiScanInfo> getWifiScanInfo() {
		return wifiScanInfo;
	}

	public void setWifiScanInfo(List<WifiScanInfo> wifiScanInfo) {
		this.wifiScanInfo = wifiScanInfo;
	}

}
