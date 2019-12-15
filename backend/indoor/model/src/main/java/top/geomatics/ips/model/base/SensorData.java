/**
 * 
 */
package top.geomatics.ips.model.base;

import java.util.Date;
import java.util.Map;

import lombok.Data;
import top.geomatics.ips.common.base.Double3D;
import top.geomatics.ips.common.base.Double6D;

/**
 * @author whudyj
 * 
 *         <i>定位请求传感器数据</i>
 */
@Data
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

}
