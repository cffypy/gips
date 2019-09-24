/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * 
 * An indoor map with an ID to identify it.
 * 
 * @author whudyj
 *
 */
public class IndoorMap {
	protected String mapId;

	protected String mapFileName;

	public IndoorMap() {
		super();
	}

	public IndoorMap(String mapId, String mapFileName) {
		super();
		this.mapId = mapId;
		this.mapFileName = mapFileName;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}

	public String getMapFileName() {
		return mapFileName;
	}

	public void setMapFileName(String mapFileName) {
		this.mapFileName = mapFileName;
	}
}
