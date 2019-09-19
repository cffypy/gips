/**
 * 
 */
package top.geomatics.ips.model.base;

/**
 * @author whudyj
 *
 */
public class BuildingLocation {
	//楼宇ID
	private String buildingID;
	//楼宇名称
	private String buildingName;
	//楼宇定位精密度
	private Double buildingPrecision;
	//楼层号
	private Double floor;
	//楼层定位精密度
	private Double floorPrecision;
	
	public String getBuildingID() {
		return buildingID;
	}
	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public Double getBuildingPrecision() {
		return buildingPrecision;
	}
	public void setBuildingPrecision(Double buildingPrecision) {
		this.buildingPrecision = buildingPrecision;
	}
	public Double getFloor() {
		return floor;
	}
	public void setFloor(Double floor) {
		this.floor = floor;
	}
	public Double getFloorPrecision() {
		return floorPrecision;
	}
	public void setFloorPrecision(Double floorPrecision) {
		this.floorPrecision = floorPrecision;
	}

	

}
