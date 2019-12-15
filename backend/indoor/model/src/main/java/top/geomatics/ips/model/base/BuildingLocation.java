/**
 * 
 */
package top.geomatics.ips.model.base;

import lombok.Data;

/**
 * @author whudyj
 *楼层定位及精度
 */
@Data
public class BuildingLocation {
	//楼宇ID
	private String buildingID;
	//楼宇名称
	private String buildingName;
	//楼宇定位精度
	private Double buildingPrecision;
	//楼层号
	private Double floor;
	//楼层定位精度
	private Double floorPrecision;

}
