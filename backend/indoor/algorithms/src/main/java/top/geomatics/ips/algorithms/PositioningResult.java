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

	double x;
	double y;

	public PositioningResult() {
		super();
	}

	public PositioningResult(Position pos) {
		super();

		this.position = pos;
		this.x = pos.getX();
		this.y = pos.getY();
	}

	public PositioningResult(double x, double y) {
		super();
		this.x = x;
		this.y = y;

		this.position = new Position(x, y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
