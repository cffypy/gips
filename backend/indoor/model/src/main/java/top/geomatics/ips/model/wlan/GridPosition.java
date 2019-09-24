/**
 * 
 */
package top.geomatics.ips.model.wlan;

/**
 * A single grid position on a grid map. The position is defined with the top
 * left and bottom right coordinates of the grid.
 * 
 * 
 * @author whudyj
 *
 */
public class GridPosition extends Position {

	protected int leftX;
	protected int topY;
	protected int rightX;
	protected int bottomY;

	public GridPosition() {

		super();
	}

	public GridPosition(int leftX, int topY, int rightX, int bottomY) {

		super((leftX + rightX) / 2, (topY + bottomY) / 2);
		this.leftX = leftX;
		this.topY = topY;
		this.rightX = rightX;
		this.bottomY = bottomY;
	}

	public GridPosition(GridMap map, int leftX, int topY, int rightX, int bottomY) {

		super((leftX + rightX) / 2, (topY + bottomY) / 2);

		this.map = map;

		this.leftX = leftX;
		this.topY = topY;
		this.rightX = rightX;
		this.bottomY = bottomY;
	}

	public int getLeftX() {
		return leftX;
	}

	public void setLeftX(int leftX) {
		this.leftX = leftX;
	}

	public int getTopY() {
		return topY;
	}

	public void setTopY(int topY) {
		this.topY = topY;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}

	public int getBottomY() {
		return bottomY;
	}

	public void setBottomY(int bottomY) {
		this.bottomY = bottomY;
	}

}
