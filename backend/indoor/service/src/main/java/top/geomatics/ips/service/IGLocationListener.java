package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public interface IGLocationListener {
    /**
     * 定位回调监听，当定位完成后调用此方法
     */
    public void onLocationChanged(GLocation location);

}
