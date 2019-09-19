/**
 * 
 */
package top.geomatics.ips.model.vision;

import java.util.Date;

import top.geomatics.ips.model.base.Attitude;
import top.geomatics.ips.model.base.BuildingLocation;
import top.geomatics.ips.model.base.CoordLocation;
import top.geomatics.ips.model.base.Orientation;
import top.geomatics.ips.model.base.Scene;

/**
 * @author whudyj 定位响应结果
 */
public class VisionResponse {
	// 请求响应标识。
	private String responseID;
	// 请求标识。
	private String requestID;
	// 响应时间戳。单位：毫秒。采用通用协调时（UTC）。
	private Date reponseTime;
	// 请求时间戳。单位：毫秒。采用通用协调时（UTC）。
	private Date requestTime;
	// 定位结果状态。
	private Boolean locationState;
	// 楼宇位置。
	private BuildingLocation buildingLoc;
	// 坐标位置。
	private CoordLocation coordLoc;
	// 姿态。
	private Attitude attitude;
	// 场景，
	private Scene scene;
	// 运动方向
	private Orientation orientation;
	// 图像二维特征点与三维空间点的匹配结果。
	private FeatureMatchData match;

	public String getResponseID() {
		return responseID;
	}

	public void setResponseID(String responseID) {
		this.responseID = responseID;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public Date getReponseTime() {
		return reponseTime;
	}

	public void setReponseTime(Date reponseTime) {
		this.reponseTime = reponseTime;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Boolean getLocationState() {
		return locationState;
	}

	public void setLocationState(Boolean locationState) {
		this.locationState = locationState;
	}

	public BuildingLocation getBuildingLoc() {
		return buildingLoc;
	}

	public void setBuildingLoc(BuildingLocation buildingLoc) {
		this.buildingLoc = buildingLoc;
	}

	public CoordLocation getCoordLoc() {
		return coordLoc;
	}

	public void setCoordLoc(CoordLocation coordLoc) {
		this.coordLoc = coordLoc;
	}

	public Attitude getAttitude() {
		return attitude;
	}

	public void setAttitude(Attitude attitude) {
		this.attitude = attitude;
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

	public FeatureMatchData getMatch() {
		return match;
	}

	public void setMatch(FeatureMatchData match) {
		this.match = match;
	}

}
