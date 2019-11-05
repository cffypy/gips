package top.geomatics.ips.server.service;


import top.geomatics.ips.server.model.PositionResult;

/**
 * @author chenfa
 * 基于wifi的室内定位接口
 */
public interface PositionService {

    //android传递过来的json数据
    public PositionResult calculatePosition(String json);
}
