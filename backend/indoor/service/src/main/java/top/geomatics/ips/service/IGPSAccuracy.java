package top.geomatics.ips.service;

/**
 * GPS卫星状态
 */
public interface IGPSAccuracy {
    /**
     *卫星信号弱
     */
    public static final int	GPS_ACCURACY_BAD=16;
    /**
     *卫星信号强
     */
    public static final int	GPS_ACCURACY_GOOD=17;
    /**
     *卫星状态未知
     */
    public static final int	GPS_ACCURACY_UNKNOWN=18;
}
