/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * A type of map used in WLAN Fingerprinting where the map is divided in grids
 * of a certain size.
 * 
 * @author whudyj
 *
 */
public class GridMap extends IndoorMap {

	protected int gridSize;

	public GridMap() {
		super();
	}

	public GridMap(String mapId, String mapFileName, int gridSize) {
		super(mapId, mapFileName);
		this.gridSize = gridSize;
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
}
