/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * 
 *  A simple 2-D position on a map where a Measurement can take place or that is
 * returned as a position estimate by a positioning algorithm. It is defined by
 * its X and Y coordinate on a map of an indoor location.
 * 
 * @author whudyj
 *
 */
public class Position {
	protected long id;

	protected IndoorMap map;

	protected int x;
	protected int y;

	public Position() {
		super();
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Position(IndoorMap map, int x, int y) {
		super();
		this.map = map;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IndoorMap getMap() {
		return map;
	}

	public void setMap(IndoorMap map) {
		this.map = map;
	}
}
