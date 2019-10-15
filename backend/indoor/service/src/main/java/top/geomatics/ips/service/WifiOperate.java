package top.geomatics.ips.service;

import top.geomatics.ips.model.cf.ScanInfo;

/**
 * @author chenfa
 * 保存wifi信息
 */
public interface WifiOperate {

    public boolean save(ScanInfo scanInfo);

}
