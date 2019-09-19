package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class GLocationClientOption {
	/**
	 * 定位模式，目前支持三种定位模式 高精度定位模式： 在这种定位模式下，将同时使用所有定位源,优先返回精度高的定位 低功耗定位模式：
	 * 在这种模式下，将只使用功耗最低定位模式 仅设备定位模式： 在这种模式下，将在功耗和定位间取得均衡
	 */
	public static class GLocationMode {
		/**
		 * "低功耗模式"
		 */
		public static final int Battery_Saving = 1;
		/**
		 * 仅设备模式
		 */
		public static final int Device_Sensors = 2;
		/**
		 * 高精度模式
		 */
		public static final int High_Accuracy = 3;

	}

	/**
	 * 定位协议，目前支持二种定位协议 http协议： 在这种定位协议下，会使用http请求定位 https协议： 在这种定位协议下，会使用https请求定位
	 */
	public static class GLocationProtocol {
		/**
		 * http协议
		 */
		public static final int HTTP = 1;
		/**
		 * https协议
		 */
		public static final int HTTPS = 2;
	}

	private long mHttpTimeOut = 30000;

	private long mInterval = 2000;

	private int mLocationMode = GLocationMode.High_Accuracy;

	private int mLocationProtocol = GLocationProtocol.HTTP;

	private boolean mGpsFirst = false;

	private boolean mKillProcess = false;

	private boolean mLocationCacheEnable = true;

	private boolean mOnceLocationLatest = true;

	private boolean mSensorEnable = false;

	private boolean mSensorPassiveScan = false;

	/**
	 * 获取联网超时时间, 单位：毫秒,默认值：30000毫秒
	 */
	public long getHttpTimeOut() {
		return mHttpTimeOut;
	}

	/**
	 * 设置联网超时时间 单位：毫秒 默认值：30000毫秒
	 * 
	 * @param httpTimeOut
	 * @return
	 */
	public GLocationClientOption setHttpTimeOut(long httpTimeOut) {
		mHttpTimeOut = httpTimeOut;
		return this;
	}

	/**
	 * 获取发起定位请求的时间间隔, 默认值：2000毫秒
	 */
	public long getInterval() {
		return mInterval;
	}

	/**
	 * 设置发起定位请求的时间间隔 单位：毫秒 默认值：2000毫秒
	 * 
	 * @param interval
	 * @return
	 */
	public GLocationClientOption setInterval(long interval) {
		mInterval = interval;
		return this;
	}

	/**
	 * 获取定位模式 默认值：Hight_Accuracy 高精度模式
	 */
	public int getLocationMode() {
		return mLocationMode;
	}

	/**
	 * 设置定位模式
	 * 
	 * @param locationMode
	 * @return
	 */
	public GLocationClientOption setLocationMode(GLocationClientOption.GLocationMode locationMode) {
		return this;
	}

	/**
	 * 获取定位协议 默认值：HTTP http协议
	 */
	public int getLocationProtocol() {
		return mLocationProtocol;
	}

	/**
	 * 设置定位协议
	 * 
	 * @param locationProtocol
	 */
	public static void setLocationProtocol(GLocationClientOption.GLocationProtocol locationProtocol) {

	}

	/**
	 * 高精度模式下单次定位是否优先返回GPS定位信息, 默认值：false
	 */
	public boolean isGpsFirst() {
		return mGpsFirst;
	}

	/**
	 * 设置首次定位是否等待GPS定位结果 默认值：false, 只有在单次定位高精度定位模式下有效
	 * 
	 * @param gpsFirst
	 * @return
	 */
	public GLocationClientOption setGpsFirst(boolean gpsFirst) {
		mGpsFirst = gpsFirst;
		return this;
	}

	/**
	 * 退出时是否杀死进程, 默认值:false, 不杀死
	 */
	public boolean isKillProcess() {
		return mKillProcess;
	}

	/**
	 * 设置退出时是否杀死进程 默认值:false, 不杀死
	 * 
	 * @param killProcess
	 * @return
	 */
	public GLocationClientOption setKillProcess(boolean killProcess) {
		mKillProcess = killProcess;
		return this;
	}

	/**
	 * 设置是否使用缓存策略, 默认为true 使用缓存策略
	 */
	public GLocationClientOption setLocationCacheEnable(boolean locationCacheEnable) {
		mLocationCacheEnable = locationCacheEnable;
		return this;
	}

	/**
	 * 设置单次定位是否等待SENSOR列表刷新 仅适用于单次定位，当设置为true时，连续定位会自动变为单次定位,定位精度会更高，但是定位速度会变慢1-3秒
	 */
	public GLocationClientOption setOnceLocationLatest(boolean onceLocationLatest) {
		mOnceLocationLatest = onceLocationLatest;
		return this;
	}

	/**
	 * 设置是否使用设备传感器 默认值：false 不使用设备传感器
	 */
	public GLocationClientOption setSensorEnable(boolean sensorEnable) {
		mSensorEnable = sensorEnable;
		return this;
	}

	/**
	 * 设置是否允许调用传感器刷新 默认值为true，当设置为false时会停止主动调用传感器刷新，将会极大程度影响定位精度，但可以有效的降低定位耗电
	 */
	public GLocationClientOption setSensorPassiveScan(boolean sensorPassiveScan) {
		mSensorPassiveScan = sensorPassiveScan;
		return this;
	}

	public long getmHttpTimeOut() {
		return mHttpTimeOut;
	}

	public void setmHttpTimeOut(long mHttpTimeOut) {
		this.mHttpTimeOut = mHttpTimeOut;
	}

	public long getmInterval() {
		return mInterval;
	}

	public void setmInterval(long mInterval) {
		this.mInterval = mInterval;
	}

	public int getmLocationMode() {
		return mLocationMode;
	}

	public void setmLocationMode(int mLocationMode) {
		this.mLocationMode = mLocationMode;
	}

	public int getmLocationProtocol() {
		return mLocationProtocol;
	}

	public void setmLocationProtocol(int mLocationProtocol) {
		this.mLocationProtocol = mLocationProtocol;
	}

	public boolean ismGpsFirst() {
		return mGpsFirst;
	}

	public void setmGpsFirst(boolean mGpsFirst) {
		this.mGpsFirst = mGpsFirst;
	}

	public boolean ismKillProcess() {
		return mKillProcess;
	}

	public void setmKillProcess(boolean mKillProcess) {
		this.mKillProcess = mKillProcess;
	}

	public boolean ismLocationCacheEnable() {
		return mLocationCacheEnable;
	}

	public void setmLocationCacheEnable(boolean mLocationCacheEnable) {
		this.mLocationCacheEnable = mLocationCacheEnable;
	}

	public boolean ismOnceLocationLatest() {
		return mOnceLocationLatest;
	}

	public void setmOnceLocationLatest(boolean mOnceLocationLatest) {
		this.mOnceLocationLatest = mOnceLocationLatest;
	}

	public boolean ismSensorEnable() {
		return mSensorEnable;
	}

	public void setmSensorEnable(boolean mSensorEnable) {
		this.mSensorEnable = mSensorEnable;
	}

	public boolean ismSensorPassiveScan() {
		return mSensorPassiveScan;
	}

	public void setmSensorPassiveScan(boolean mSensorPassiveScan) {
		this.mSensorPassiveScan = mSensorPassiveScan;
	}

}
