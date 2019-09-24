package top.geomatics.ips;

import java.top.geomatics.ips.model.PositionResult;
import java.top.geomatics.ips.model.ScanResults;

/**
 * @author chenfa
 * Wifi定位算法接口
 */
public interface WifiPositioningAlgorithm {

    public PositionResult calculatePosition(ScanResults scanResults);
}
