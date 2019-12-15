/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

/**
 * @author whudyj
 *总位置描述
 */
@Data
public class Location {
	//无法计算出位置
	Boolean noLocation;
	//楼宇定位
	BuildingLocation buildingLoc;
	//坐标位置
	CoordLocation coordLoc;

}
