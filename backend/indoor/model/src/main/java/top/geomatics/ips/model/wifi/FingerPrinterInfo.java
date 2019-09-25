/**
 * 
 */
package top.geomatics.ips.model.wifi;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author whudyj
 *
 */
public class FingerPrinterInfo {
	// "FP info": [{
	// "minRssi": 45,
	// "NumWeakerthanMax": 0,
	// "ZeroProb": 0.005,
	// "NumStrongerthanMin": 0,
	// "maxRange": 100,
	// "meanRssi": 48.099999999999994,
	// "stdRssi": 2.073644135332772,
	// "NumOfSamples": 30,
	// "MAC": "d8:24:bd:76:98:3f",
	// "minRange": 30,
	// "NumOfZeroSamples": 0
	// },
	@JSONField(name = "MAC")
	String mac;
	@JSONField(name = "NumOfSamples")
	Integer samples;
	@JSONField(name = "NumOfZeroSamples")
	Integer zeroSampls;
	@JSONField(name = "minRange")
	Integer minRange;
	@JSONField(name = "maxRange")
	Integer maxRange;
	@JSONField(name = "minRssi")
	Integer minRssi;
	@JSONField(name = "meanRssi")
	Double meanRssi;
	@JSONField(name = "stdRssi")
	Double stdRssi;
	@JSONField(name = "NumWeakerthanMax")
	Integer weakerThanMax;
	@JSONField(name = "NumStrongerthanMin")
	Integer strongerThanMin;
	@JSONField(name = "ZeroProb")
	Double zeroProb;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Integer getSamples() {
		return samples;
	}

	public void setSamples(Integer samples) {
		this.samples = samples;
	}

	public Integer getZeroSampls() {
		return zeroSampls;
	}

	public void setZeroSampls(Integer zeroSampls) {
		this.zeroSampls = zeroSampls;
	}

	public Integer getMinRange() {
		return minRange;
	}

	public void setMinRange(Integer minRange) {
		this.minRange = minRange;
	}

	public Integer getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(Integer maxRange) {
		this.maxRange = maxRange;
	}

	public Integer getMinRssi() {
		return minRssi;
	}

	public void setMinRssi(Integer minRssi) {
		this.minRssi = minRssi;
	}

	public Double getMeanRssi() {
		return meanRssi;
	}

	public void setMeanRssi(Double meanRssi) {
		this.meanRssi = meanRssi;
	}

	public Double getStdRssi() {
		return stdRssi;
	}

	public void setStdRssi(Double stdRssi) {
		this.stdRssi = stdRssi;
	}

	public Integer getWeakerThanMax() {
		return weakerThanMax;
	}

	public void setWeakerThanMax(Integer weakerThanMax) {
		this.weakerThanMax = weakerThanMax;
	}

	public Integer getStrongerThanMin() {
		return strongerThanMin;
	}

	public void setStrongerThanMin(Integer strongerThanMin) {
		this.strongerThanMin = strongerThanMin;
	}

	public Double getZeroProb() {
		return zeroProb;
	}

	public void setZeroProb(Double zeroProb) {
		this.zeroProb = zeroProb;
	}

}
