package top.geomatics.ips.server.service;


import top.geomatics.ips.model.cf.ScanInfo;
import top.geomatics.ips.server.entity.PositionResult;

/**
 * @author chenfa
 * 基于wifi的室内定位接口
 */
public interface PositionService {

    //android传递过来的json数据
    public void calculatePosition(String json);
}
