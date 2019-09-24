package top.geomatics.ips.model;

/**
 * @author chenfa
 * 返回的位置信息
 */
public class PositionResult {
    public double coordinate_x;//x坐标
    public double coordinate_y;//y坐标

    public PositionResult() {
    }

    public PositionResult(double coordinate_x, double coordinate_y) {
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
    }

    public double getCoordinate_x() {
        return coordinate_x;
    }

    public void setCoordinate_x(double coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public double getCoordinate_y() {
        return coordinate_y;
    }

    public void setCoordinate_y(double coordinate_y) {
        this.coordinate_y = coordinate_y;
    }
}
