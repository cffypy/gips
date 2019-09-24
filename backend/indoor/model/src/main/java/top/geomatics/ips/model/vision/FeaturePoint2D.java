/**
 * 
 */

package top.geomatics.ips.model.vision;

import org.opencv.core.Mat;

/**
 * @author whudyj
 *
 *	图像特征点在图像上的位置坐标
 *	坐标原点（0,0）点表示图像左上角的像素点。图像X轴为从原点向左，图像Y轴为从原点向下。
 */
public class FeaturePoint2D {
	//特征点在X轴上的数值。单位：像素。
	private Double x;
	//特征点在Y轴上的数值。单位：像素。
	private Double y;
	
	private Mat descriptor;
	
	
	public FeaturePoint2D() {
		super();
	}


	public FeaturePoint2D(Double x, Double y, Mat descriptor) {
		super();
		this.x = x;
		this.y = y;
		this.descriptor = descriptor;
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
	
	public Mat getDescriptor() {
		return descriptor;
	}


	public void setDescriptor(Mat descriptor) {
		this.descriptor = descriptor;
	}

	
}
