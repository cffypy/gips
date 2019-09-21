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
public class WiFiSacn {
	// "Point NO": 0,
	// "PosLon": 114.35489935727901,
	// "PosLat": 30.529410302175254,
	// "Building ID": "LIESMARS",
	// "Floor ID": "4",
	// "Date": "2018-03-04 18:07:44",
	// "WIFIscan":[],

	@JSONField(name = "Point NO")
	String pointNumber;
	@JSONField(name = "PosLon")
	Double x;
	@JSONField(name = "PosLat")
	Double y;
	@JSONField(name = "Building ID")
	String buildingId;
	@JSONField(name = "Floor ID")
	String floorId;
	@JSONField(name = "Date")
	Date date;
	@JSONField(name = "WIFIscan")
	List<RoundInfo> wifiScan;

	public String getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(String pointNumber) {
		this.pointNumber = pointNumber;
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

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<RoundInfo> getWifiScan() {
		return wifiScan;
	}

	public void setWifiScan(List<RoundInfo> wifiScan) {
		this.wifiScan = wifiScan;
	}

}
