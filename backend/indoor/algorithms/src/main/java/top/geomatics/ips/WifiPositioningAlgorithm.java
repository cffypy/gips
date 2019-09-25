package top.geomatics.ips;

import top.geomatics.ips.model.PositionResult;
import top.geomatics.ips.model.ScanResults;

/**
 * @author chenfa
 * Wifi定位算法接口
 */
public interface WifiPositioningAlgorithm {

    public PositionResult calculatePosition(ScanResults scanResults);
}
