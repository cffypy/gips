/**
 * 
 */
package top.geomatics.ips.algorithms;

import top.geomatics.ips.model.wlan.Position;

/**
 * 
 * A result that is returned by a positioning algorithm. Contains the calculated
 * position.
 * 
 * @author whudyj
 *
 */
public class PositioningResult {
	Position position;

	int x;
	int y;

	public PositioningResult() {
		super();
	}

	public PositioningResult(Position pos) {
		super();

		this.position = pos;
		this.x = pos.getX();
		this.y = pos.getY();
	}

	public PositioningResult(int x, int y) {
		super();
		this.x = x;
		this.y = y;

		this.position = new Position(x, y);
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
