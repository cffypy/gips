/**
 * 
 */
package top.geomatics.ips.model.base;

/**
 * @author whudyj
 *
 */
public class Orientation {
	// 运动方向角，以正北为0度，顺时针为正。取值区间[0,360)。
	Double orientation;
	// 运动方向估计精密度
	Double orientationPrecision;

	public Double getOrientation() {
		return orientation;
	}

	public void setOrientation(Double orientation) {
		this.orientation = orientation;
	}

	public Double getOrientationPrecision() {
		return orientationPrecision;
	}

	public void setOrientationPrecision(Double orientationPrecision) {
		this.orientationPrecision = orientationPrecision;
	}

}
