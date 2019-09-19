/**
 * 
 */
package top.geomatics.ips.model.vision;

import java.util.List;

/**
 * @author whudyj
 *	图像特征点匹配结果
 */
public class FeatureMatchData {
	//匹配特征数量。
	private Integer FeatureNumber;
	//特征在图像上的位置坐标集合，数量为FeatureNumber个。
	private List<FeaturePoint2D> Feature2Ds;
	//特征在三维空间的位置坐标集合，数量为FeatureNumber个。
	private List<FeaturePosition3D> Feature3Ds;
	
	
	public Integer getFeatureNumber() {
		return FeatureNumber;
	}
	public void setFeatureNumber(Integer featureNumber) {
		FeatureNumber = featureNumber;
	}
	public List<FeaturePoint2D> getFeature2Ds() {
		return Feature2Ds;
	}
	public void setFeature2Ds(List<FeaturePoint2D> feature2Ds) {
		Feature2Ds = feature2Ds;
	}
	public List<FeaturePosition3D> getFeature3Ds() {
		return Feature3Ds;
	}
	public void setFeature3Ds(List<FeaturePosition3D> feature3Ds) {
		Feature3Ds = feature3Ds;
	}
	

}
