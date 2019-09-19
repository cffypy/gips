/**
 * 
 */
package top.geomatics.ips.model.base;

import top.geomatics.ips.common.base.Double3D;

/**
 * @author whudyj
 *
 */
public class Attitude {
	// 姿态。单位：弧度。
	private Double3D attitude;
	// 姿态估计精度。单位：弧度。
	private Double3D attitudeAccuracy;

	public Double3D getAttitude() {
		return attitude;
	}

	public void setAttitude(Double3D attitude) {
		this.attitude = attitude;
	}

	public Double3D getAttitudeAccuracy() {
		return attitudeAccuracy;
	}

	public void setAttitudeAccuracy(Double3D attitudeAccuracy) {
		this.attitudeAccuracy = attitudeAccuracy;
	}

}
