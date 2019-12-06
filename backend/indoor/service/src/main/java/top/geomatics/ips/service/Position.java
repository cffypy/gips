package top.geomatics.ips.service;

import top.geomatics.ips.model.wifi.WifiScanInfo;

/**
 * @author chenfa
 * 基于wifi室内定位算法接口
 */
public interface Position {

    /**
     * Calculates the positioning using a measurement that was collected by the
     * client.
     * @param wifiinfo Measurement from the client that will be used to calculate itsposition
     * @return
     */
    public PositionResult calculate(WifiScanInfo wifiinfo);

}
