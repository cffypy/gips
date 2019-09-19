package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class GLocation {
    /**
     *定位错误码：定位失败，飞行模式下关闭了Sensor开关，请关闭飞行模式或者打开Sensor开关
     */
    public static final int	ERROR_CODE_AIRPLANEMODE_SENSOROFF =1;
    /**
     * 定位错误码：KEY错误,可以通过GLocation.getLocationDetail()获取详细信息来跟注册的KEY信息进行对照
     */

    public static final int	ERROR_CODE_FAILURE_AUTH=2;
    /**
     *定位错误码：网络连接异常,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_FAILURE_CONNECTION=3;
    /**
     *定位错误码：初始化异常,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_FAILURE_INIT=4;
    /**
     *定位错误码：定位结果错误,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_FAILURE_LOCATION=5;
    /**
     *定位错误码：获取到的请求参数为空，可能获取过程中出现异常,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_FAILURE_LOCATION_PARAMETER=6;
    /**
     *定位错误码：缺少定位权限,请检查是否配置定位权限,并在安全软件和设置中给应用打开定位权限
     */

    public static final int	ERROR_CODE_FAILURE_LOCATION_PERMISSION=7;
    /**
     *定位错误码：GPS定位失败，可用卫星数不足
     */

    public static final int	ERROR_CODE_FAILURE_NOENOUGHSATELLITES=8;
    /**
     *定位错误码：网络定位失败，请检查设备是否插入sim卡、开启移动网络或开启了wifi模块
     */

    public static final int	ERROR_CODE_FAILURE_NOWIFIANDAP=9;
    /**
     *定位错误码：解析数据出错,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_FAILURE_PARSER=10;
    /**
     *定位错误码：定位位置可能被模拟
     */

    public static final int	ERROR_CODE_FAILURE_SIMULATION_LOCATION=11;
    /**
     *定位错误码：定位失败，由于定位传感器数量不足，不能精准的计算出位置信息。
     */

    public static final int	ERROR_CODE_FAILURE_SENSOR_INFO=12;
    /**
     *定位错误码：一些重要参数为空,如context,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_INVALID_PARAMETER=13;
    /**
     *定位错误码：定位服务启动失败，请检查是否配置service并且manifest中service标签是否配置在application标签内
     */

    public static final int	ERROR_CODE_SERVICE_FAIL=14;
    /**
     *定位错误码：其他错误,可以通过GLocation.getLocationDetail()获取详细信息
     */

    public static final int	ERROR_CODE_UNKNOWN=15;
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
    /**
     *定位错误码：定位成功
     */

    public static final int	LOCATION_SUCCESS=19;
    /**
     *定位结果类型：基站定位结果 属于网络定位
     */

    public static final int	LOCATION_TYPE_CELL=20;
    /**
     *定位结果类型：缓存定位结果 返回一段时间前设备在相同的环境中缓存下来的网络定位结果，节省无必要的设备定位消耗
     */

    public static final int	LOCATION_TYPE_FIX_CACHE=21;
    /**
     *定位结果类型：基站定位结果 属于高精度定位
     */

    public static final int	LOCATION_TYPE_HIGHACCLOC=22;
    /**
     *定位结果类型：基站定位结果 属于混合定位
     */

    public static final int	LOCATION_TYPE_MIXED=23;
    /**
     *定位结果类型：GPS定位结果 通过设备GPS定位模块返回的定位结果
     */

    public static final int	LOCATION_TYPE_GPS=24;
    /**
     *定位结果类型： 最后位置缓存
     */

    public static final int	LOCATION_TYPE_LAST_LOCATION_CACHE=25;
    /**
     *定位结果类型： 离线定位结果
     */

    public static final int	LOCATION_TYPE_OFFLINE=26;
    /**
     *定位结果类型：前次定位结果 网络定位请求低于1秒、或两次定位之间设备位置变化非常小时返回，设备位移通过传感器感知
     */

    public static final int	LOCATION_TYPE_SAME_REQ=27;
    /**
     *定位结果类型：SENSOR定位结果，属于网络定位，定位精度相对基站定位会更好
     */

    public static final int	LOCATION_TYPE_SENSOR=28;
    /**
     *定位结果所处场景：室内场景
     */
    public static final int	LOCATION_SCENE_INDOOR=29;
    /**
     * 定位结果所处场景：室外场景
     */
    public static final int	LOCATION_SCENE_OUTDOOR=30;
    /**
     * 定位结果所处场景：未知场景
     */
    public static final int	LOCATION_SCENE_UNKNOWN=31;


    private float	mAccuracy;
    private double	mAltitude;
    private float	mBearing;
    private String	mBuildingId;
    private String	mCityName;
    private String	mCityCode;
    private String	mCountryName;
    private String	mDescription;
    private int	mErrorCode;
    private String	mErrorInfo;
    private String	mFloor;
    private int	mGpsAccuracyStatus;


    private double	mLatitude;


    private String	mLocationDetail;


    private GLocationQualityReport	mLocationQualityReport;


    private int	mLocationType;


    private double	mLongitude;


    private String	mProvider;


    private int	mSatellites;


    private float	mSpeed;

    /**
     * 获取定位结果所处的场景类型
     */
    public int getLocationScene() {
        return LOCATION_SCENE_INDOOR;
    }


    /**
     *获取经度
     */
    public double getLongitude() {
        return mLongitude;
    }
    /**
     *获取纬度
     */
    public double getLatitude() {
        return mLatitude;
    }
    /**
     *获取室内定位的楼层信息
     */
    public String getFloor() {
        return mFloor;
    }
    /**
     *获取海拔高度(单位：米) 默认值：0.0
     */
    public double getAltitude() {
        return mAltitude;
    }
    /**
     *获取当前速度(单位：米/秒) 默认值：0.0
     */
    public float getSpeed() {
        return mSpeed;
    }
    /**
     *获取方向角(单位：度） 默认值：0.0            取值范围：【0，360】，其中0度表示正北方向
     */
    public float getBearing() {
        return mBearing;
    }
    /**
     *获取定位精度 单位:米
     */
    public float getAccuracy() {
        return mAccuracy;
    }
    /**
     *获取室内定位的建筑物ID信息
     */
    public String getBuildingId() {
        return mBuildingId;
    }
    /**
     *获取城市名称
     */
    public String getCityName() {
        return mCityName;
    }
    /**
     *获取城市编码
     */
    public String getCityCode() {
        return mCityCode;
    }
    /**
     *获取国家名称
     */
    public String getCountryName() {
        return mCountryName;
    }
    /**
     *获取位置语义信息
     */
    public String getDescription() {
        return mDescription;
    }
    /**
     *获取错误码
     */
    public int getErrorCode() {
        return mErrorCode;
    }
    /**
     *获取错误信息
     */
    public String getErrorInfo() {
        return mErrorInfo;
    }

    /**
     *获取卫星信号强度，仅在gps定位时有效
     */
    public int getGpsAccuracyStatus() {
        return mGpsAccuracyStatus;
    }

    /**
     *获取定位信息描述
     */
    public String getLocationDetail() {
        return mLocationDetail;
    }
    /**
     *获取定位质量
     */
    public GLocationQualityReport getLocationQualityReport() {
        return mLocationQualityReport;
    }
    /**
     *获取定位结果来源
     */
    public int getLocationType() {
        return mLocationType;
    }

    /**
     *获取定位提供者
     */
    public String getProvider() {
        return mProvider;
    }
    /**
     *获取当前可用卫星数量, 仅在GPS定位时有效
     */
    public int getSatellites() {
        return mSatellites;
    }

}
