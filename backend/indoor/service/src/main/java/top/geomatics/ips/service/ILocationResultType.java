package top.geomatics.ips.service;

/**
 * 定位结果类型
 */
public interface ILocationResultType {
    /**
     * 定位结果类型：基站定位结果 属于网络定位
     */
    public static final int LOCATION_TYPE_CELL = 20;
    /**
     * 定位结果类型：缓存定位结果 返回一段时间前设备在相同的环境中缓存下来的网络定位结果，节省无必要的设备定位消耗
     */
    public static final int LOCATION_TYPE_FIX_CACHE = 21;
    /**
     * 定位结果类型：基站定位结果 属于高精度定位
     */
    public static final int LOCATION_TYPE_HIGHACCLOC = 22;
    /**
     * 定位结果类型：基站定位结果 属于混合定位
     */
    public static final int LOCATION_TYPE_MIXED = 23;
    /**
     * 定位结果类型：GPS定位结果 通过设备GPS定位模块返回的定位结果
     */
    public static final int LOCATION_TYPE_GPS = 24;
    /**
     * 定位结果类型： 最后位置缓存
     */
    public static final int LOCATION_TYPE_LAST_LOCATION_CACHE = 25;
    /**
     * 定位结果类型： 离线定位结果
     */
    public static final int LOCATION_TYPE_OFFLINE = 26;
    /**
     * 定位结果类型：前次定位结果 网络定位请求低于1秒、或两次定位之间设备位置变化非常小时返回，设备位移通过传感器感知
     */
    public static final int LOCATION_TYPE_SAME_REQ = 27;
    /**
     * 定位结果类型：SENSOR定位结果，属于网络定位，定位精度相对基站定位会更好
     */
    public static final int LOCATION_TYPE_SENSOR = 28;
}
