/**
 * 
 */
package top.geomatics.ips.model.magnet;

import java.util.Date;

import top.geomatics.ips.model.base.Location;
import top.geomatics.ips.model.base.Orientation;
import top.geomatics.ips.model.base.Scene;

/**
 * @author whudyj
 *
 */
public class FusionResponse {
	// 请求ID
	String requestID;
	// 定位引擎ID
	String engineID;
	// 子/融合定位引擎名称
	String engineName;
	// 定位结果返回时间戳。当前采样点的世界标准时间(UTC)（毫秒）
	Date timeStamp;
	// 位置
	Location location;
	// 场景
	Scene scene;
	// 运动方向
	Orientation orientation;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getEngineID() {
		return engineID;
	}

	public void setEngineID(String engineID) {
		this.engineID = engineID;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

}
