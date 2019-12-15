/**
 * 
 */
package top.geomatics.ips.common.base;

import lombok.Data;

/**
 * @author whudyj
 *三维坐标及精度
 */
@Data
public class Double6D {
	private Double3D point;
	private Double3D accuracy;

}
