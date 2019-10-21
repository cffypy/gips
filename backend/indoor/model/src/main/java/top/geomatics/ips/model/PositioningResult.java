package top.geomatics.ips.model;

/**
 * @author chenfa
 *
 */
public class PositioningResult {
    public double x;
    public double y;

    public PositioningResult() {
    }

    public PositioningResult(double x, double y) {
        this.x = x;
        this.y = y;
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
}
