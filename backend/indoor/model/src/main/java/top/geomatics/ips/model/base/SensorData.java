/**
 * 
 */
package top.geomatics.ips.model.base;

import java.util.Date;
import java.util.Map;

import top.geomatics.ips.common.base.Double3D;
import top.geomatics.ips.common.base.Double6D;

/**
 * @author whudyj
 * 
 *         <i>定位请求传感器数据</i>
 */
public class SensorData {
	// 设备识别码
	private String deviceID;
	// 采样时间戳。当前采样点的世界标准时间(UTC)（毫秒）
	private Date timeStamp;
	// 加速度序列
	private Double3D acceleration;
	// 磁场强度序列
	private Double3D magnetism;
	// 角速度序列
	private Double3D gyroscope;
	// 姿态
	private Double3D attitude;
	// 重力加速度
	private Double3D gravity;
	// 线性加速度序列
	private Double3D linearAcceleration;
	// 旋转向量序列
	private Double3D rotationVector;
	// 光强序列
	private Double3D light;
	// 气压序列
	private Double3D pressure;
	// 温度序列
	private Double3D temperature;
	// 相对湿度序列
	private Double3D humidty;
	// GNSS定位
	private Double6D gnssPos;
	// 可见卫星数
	private Integer statNumber;
	// 网络定位
	private Double6D networkPos;
	// 扩展数据
	private Map<String, Object> extendedData;

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

	public Double3D getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Double3D acceleration) {
		this.acceleration = acceleration;
	}

	public Double3D getMagnetism() {
		return magnetism;
	}

	public void setMagnetism(Double3D magnetism) {
		this.magnetism = magnetism;
	}

	public Double3D getGyroscope() {
		return gyroscope;
	}

	public void setGyroscope(Double3D gyroscope) {
		this.gyroscope = gyroscope;
	}

	public Double3D getAttitude() {
		return attitude;
	}

	public void setAttitude(Double3D attitude) {
		this.attitude = attitude;
	}

	public Double3D getGravity() {
		return gravity;
	}

	public void setGravity(Double3D gravity) {
		this.gravity = gravity;
	}

	public Double3D getLinearAcceleration() {
		return linearAcceleration;
	}

	public void setLinearAcceleration(Double3D linearAcceleration) {
		this.linearAcceleration = linearAcceleration;
	}

	public Double3D getRotationVector() {
		return rotationVector;
	}

	public void setRotationVector(Double3D rotationVector) {
		this.rotationVector = rotationVector;
	}

	public Double3D getLight() {
		return light;
	}

	public void setLight(Double3D light) {
		this.light = light;
	}

	public Double3D getPressure() {
		return pressure;
	}

	public void setPressure(Double3D pressure) {
		this.pressure = pressure;
	}

	public Double3D getTemperature() {
		return temperature;
	}

	public void setTemperature(Double3D temperature) {
		this.temperature = temperature;
	}

	public Double3D getHumidty() {
		return humidty;
	}

	public void setHumidty(Double3D humidty) {
		this.humidty = humidty;
	}

	public Double6D getGnssPos() {
		return gnssPos;
	}

	public void setGnssPos(Double6D gnssPos) {
		this.gnssPos = gnssPos;
	}

	public Integer getStatNumber() {
		return statNumber;
	}

	public void setStatNumber(Integer statNumber) {
		this.statNumber = statNumber;
	}

	public Double6D getNetworkPos() {
		return networkPos;
	}

	public void setNetworkPos(Double6D networkPos) {
		this.networkPos = networkPos;
	}

	public Map<String, Object> getExtendedData() {
		return extendedData;
	}

	public void setExtendedData(Map<String, Object> extendedData) {
		this.extendedData = extendedData;
	}

}
