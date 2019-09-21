/**
 * 
 */
package top.geomatics.ips.model.wifi;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class FPFloor {

	// [{
	// "floorinfo": 4,
	// "FPfloor info": []
	// "buidinginfo": "LIESMARS"
	// }]
	@JSONField(name = "buidinginfo")
	String buildId;
	@JSONField(name = "floorinfo")
	String floorId;
	@JSONField(name = "FPfloor info")
	List<FPFloorInfo> floorinfo;

	public String getBuildId() {
		return buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public List<FPFloorInfo> getFloorinfo() {
		return floorinfo;
	}

	public void setFloorinfo(List<FPFloorInfo> floorinfo) {
		this.floorinfo = floorinfo;
	}

}
