package top.geomatics.ips.service;

import top.geomatics.ips.model.wifi.WifiScanInfo;

/**
 * @author chenfa
 * 基于wifi的室内定位算法实现
 */
public class PositionImpl implements Position {

    @Override
    public PositionResult calculate(WifiScanInfo wifiinfo) {

        PositionResult positionResult=new PositionResult();
        return positionResult;
    }
}
