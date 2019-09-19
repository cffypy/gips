/**
 * 
 */
package top.geomatics.ips.model.base;

import java.util.Date;

/**
 * @author whudyj
 *
 *
 *         <i>定位请求基本信息</i>
 */
public class RequestBase {
	// 定位请求标识。
	private String requestID;
	// 终端唯一标识
	private String deviceID;
	// 定位请求的时间。单位：毫秒。采用通用协调时（UTC）。
	private Date timeStamp;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
