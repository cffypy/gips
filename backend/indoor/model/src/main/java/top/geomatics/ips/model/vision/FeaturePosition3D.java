/**
 * 
 */
package top.geomatics.ips.model.vision;

/**
 * @author whudyj
 *	图像特征点在三维空间的位置坐标
 */
public class FeaturePosition3D {
	//特征点相对于参考原点的X坐标。单位：米。
	private Double X;
	//特征点相对于参考原点的Y坐标。单位：米。
	private Double Y;
	//特征点相对于参考原点的Z坐标。单位：米。
	private Double Z;
	
	
	public FeaturePosition3D() {
		super();
	}


	public FeaturePosition3D(Double x, Double y, Double z) {
		super();
		X = x;
		Y = y;
		Z = z;
	}


	public Double getX() {
		return X;
	}


	public void setX(Double x) {
		X = x;
	}


	public Double getY() {
		return Y;
	}


	public void setY(Double y) {
		Y = y;
	}


	public Double getZ() {
		return Z;
	}


	public void setZ(Double z) {
		Z = z;
	}
	
	

}
