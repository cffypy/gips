/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author whudyj
 * 
 *         <i>定位请求传感器数据</i>
 */
@Data
public class SensorSampleData {
	// 设备识别码
	private String deviceID;
	// 采样结束时间戳。当前采样点的世界标准时间(UTC)（毫秒）
	private Date timeStamp;
	// 采样开始至结束的持续时间
	private Long sampleDuration;
	// 采样频率。
	private Double sampleFrequency;

	// 传感器数据序列
	private List<SensorData> sensorData;

}
