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
public class Fingerprinter {
	// [{
	// "Point NO": 1,
	// "PosLon": 114.35489935727901,
	// "FP info": [
	// .......
	// }, {.....

	// }],
	// "NumOfStrongerthan70": 25,
	// "NumOfSamples": 30,
	// "PosLat": 30.529410302175254
	// }, {
	// "Point NO": 2,

	@JSONField(name = "Point NO")
	String pointNumber;
	@JSONField(name = "PosLon")
	Double x;
	@JSONField(name = "PosLat")
	Double y;
	@JSONField(name = "NumOfSamples")
	Integer samples;
	@JSONField(name = "NumOfStrongerthan70")
	Integer above70;
	@JSONField(name = "FP info")
	String fpInfos;

	public String getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(String pointNumber) {
		this.pointNumber = pointNumber;
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

	public Integer getSamples() {
		return samples;
	}

	public void setSamples(Integer samples) {
		this.samples = samples;
	}

	public Integer getAbove70() {
		return above70;
	}

	public void setAbove70(Integer above70) {
		this.above70 = above70;
	}

	public String getFpInfos() {
		return fpInfos;
	}

	public void setFpInfos(String fpInfos) {
		this.fpInfos = fpInfos;
	}

}
