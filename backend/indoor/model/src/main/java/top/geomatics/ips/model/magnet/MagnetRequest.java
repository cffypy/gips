/**
 * 
 */
package top.geomatics.ips.model.magnet;

import top.geomatics.ips.model.base.RequestBase;
import top.geomatics.ips.model.base.SensorData;

/**
 * @author whudyj
 *
 */
public class MagnetRequest {
	// 定位请求基本信息。
	private RequestBase requestBase;
	// 传感器数据。
	private SensorData sensorData;

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

}
