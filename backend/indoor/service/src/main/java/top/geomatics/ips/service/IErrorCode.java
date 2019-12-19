package top.geomatics.ips.service;

/**
 * 错误代码
 */
public interface IErrorCode {
    /**
     * 定位错误码：定位失败，飞行模式或关闭了传感器
     */
    public static final int ERROR_CODE_AIRPLANEMODE_SENSOROFF = 1;
    /**
     * 定位错误码：KEY错误,没有获得定位应用授权
     */
    public static final int ERROR_CODE_FAILURE_AUTH = 2;
    /**
     * 定位错误码：没有网络或网络连接失败
     */
    public static final int ERROR_CODE_FAILURE_CONNECTION = 3;
    /**
     * 定位错误码：初始化失败
     */
    public static final int ERROR_CODE_FAILURE_INIT = 4;
    /**
     * 定位错误码：定位失败
     */
    public static final int ERROR_CODE_FAILURE_LOCATION = 5;
    /**
     * 定位错误码：没有指定请求参数
     */
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 6;
    /**
     * 定位错误码：缺少定位权限
     */
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 7;
    /**
     * 定位错误码：GPS定位失败，可用卫星数不足
     */
    public static final int ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 8;
    /**
     * 定位错误码：网络定位失败，请检查设备是否插入sim卡、开启移动网络或开启了wifi模块
     */
    public static final int ERROR_CODE_FAILURE_NOWIFIANDAP = 9;
    /**
     * 定位错误码：解析数据出错
     */
    public static final int ERROR_CODE_FAILURE_PARSER = 10;
    /**
     * 定位错误码：定位位置可能被模拟
     */
    public static final int ERROR_CODE_FAILURE_SIMULATION_LOCATION = 11;
    /**
     * 定位错误码：定位失败，由于定位传感器数量不足，不能精准的计算出位置信息。
     */
    public static final int ERROR_CODE_FAILURE_SENSOR_INFO = 12;
    /**
     * 定位错误码：一些重要参数,如context,没有设置
     */
    public static final int ERROR_CODE_INVALID_PARAMETER = 13;
    /**
     * 定位错误码：定位服务启动失败，请检查是否有配置service
     */
    public static final int ERROR_CODE_SERVICE_FAIL = 14;
    /**
     * 定位错误码：其他错误
     */
    public static final int ERROR_CODE_UNKNOWN = 15;


}
