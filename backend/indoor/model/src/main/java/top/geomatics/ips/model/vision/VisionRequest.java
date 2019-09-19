/**
 * 
 */
package top.geomatics.ips.model.vision;

import java.util.List;

import top.geomatics.ips.common.base.Double2D;
import top.geomatics.ips.model.base.RequestBase;
import top.geomatics.ips.model.base.SensorData;

/**
 * @author whudyj
 * 
 *         <i>视觉定位请求数据</i>
 */
public class VisionRequest {
	// 定位请求基本信息。
	private RequestBase requestBase;
	// 传感器数据。
	private SensorData sensorData;
	// 传感器采集图像帧。
	private ImageData frame;

	// 相机焦距。单位：像素。
	private Double focalLength;
	// 相机主点坐标。单位：像素。
	private Double2D principlePoint;
	// 当前图像提取的特征点的数量。
	private Integer featurePointsNumber;
	// 当前图像提取的特征点。
	private List<FeaturePoint2D> FeaturePoints;

	public RequestBase getRequestBase() {
		return requestBase;
	}

	public void setRequestBase(RequestBase requestBase) {
		this.requestBase = requestBase;
	}

	public SensorData getSensorData() {
		return sensorData;
	}

	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

	public ImageData getFrame() {
		return frame;
	}

	public void setFrame(ImageData frame) {
		this.frame = frame;
	}

	public Double getFocalLength() {
		return focalLength;
	}

	public void setFocalLength(Double focalLength) {
		this.focalLength = focalLength;
	}

	public Double2D getPrinciplePoint() {
		return principlePoint;
	}

	public void setPrinciplePoint(Double2D principlePoint) {
		this.principlePoint = principlePoint;
	}

	public Integer getFeaturePointsNumber() {
		return featurePointsNumber;
	}

	public void setFeaturePointsNumber(Integer featurePointsNumber) {
		this.featurePointsNumber = featurePointsNumber;
	}

	public List<FeaturePoint2D> getFeaturePoints() {
		return FeaturePoints;
	}

	public void setFeaturePoints(List<FeaturePoint2D> featurePoints) {
		FeaturePoints = featurePoints;
	}

}
