/**
 * 
 */
package top.geomatics.ips.model.fusion;

import java.util.List;

import top.geomatics.ips.model.base.RSS;
import top.geomatics.ips.model.base.RequestBase;
import top.geomatics.ips.model.base.SensorSampleData;

/**
 * @author whudyj
 * 
 *         <i>融合定位请求</i>
 */
public class FusionRequest {
	// 基本请求信息
	private RequestBase requestBase;
	// 传感器采样数据
	private SensorSampleData sampleData;

	// WiFi列表
	private List<RSS> wifiList;
	// BLE列表
	private List<RSS> bleList;

	public RequestBase getRequestBase() {
		return requestBase;
	}

	public void setRequestBase(RequestBase requestBase) {
		this.requestBase = requestBase;
	}

	public SensorSampleData getSampleData() {
		return sampleData;
	}

	public void setSampleData(SensorSampleData sampleData) {
		this.sampleData = sampleData;
	}

	public List<RSS> getWifiList() {
		return wifiList;
	}

	public void setWifiList(List<RSS> wifiList) {
		this.wifiList = wifiList;
	}

	public List<RSS> getBleList() {
		return bleList;
	}

	public void setBleList(List<RSS> bleList) {
		this.bleList = bleList;
	}

}
