/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;
import top.geomatics.ips.common.base.Double3D;

/**
 * @author whudyj
 * 描述设备的姿态及姿态精度
 *
 */
@Data
public class Attitude {
	// 姿态。单位：弧度。
	private Double3D attitude;
	// 姿态估计精度。单位：弧度。
	private Double3D attitudeAccuracy;

}
