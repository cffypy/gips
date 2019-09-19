/**
 * 
 */
package top.geomatics.ips.common.base;

/**
 * @author whudyj
 *
 */
public class Double6D {
	private Double3D point;
	private Double3D accuracy;
	
	
	public Double6D() {
		super();
	}


	public Double3D getPoint() {
		return point;
	}


	public void setPoint(Double3D point) {
		this.point = point;
	}


	public Double3D getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(Double3D accuracy) {
		this.accuracy = accuracy;
	}



}
