/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

/**
 * @author whudyj
 *运动方向及精度
 */
@Data
public class Orientation {
	// 运动方向角，以正北为0度，顺时针为正。取值区间[0,360)。
	Double orientation;
	// 运动方向估计精密度
	Double orientationPrecision;

}
