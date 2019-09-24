package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class GLocationClient  {
	/**
	 * 获取最后位置
	 */
	public GLocation getLastKnownLocation() {
		return new GLocation();
	}

	/**
	 * 获取定位sdk版本信息
	 */
	public String getVersion() {
		return "1.0";
	}

	/**
	 * 本地定位服务是否已经启动，用于用户检查服务是否已经启动
	 */
	public boolean isStarted() {
		return false;
	}

	/**
	 * 销毁定位,释放定位资源, 当不再需要进行定位时调用此方法 该方法会释放所有定位资源，调用后再进行定位需要重新实例化GLocationClient
	 */
	public void onDestroy() {

	}

	/**
	 * 设置apikey 必须在GLocationClient实例化之前调用
	 */
	public static void setApiKey(String apiKey) {

	}

	/**
	 * 设置定位回调监听
	 */
	public void setLocationListener(IGLocationListener listener) {
	}

	/**
	 * 设置定位参数
	 */
	public void setLocationOption(GLocationClientOption option) {

	}

	/**
	 * 开始定位
	 */
	public void startLocation() {

	}

	/**
	 * 停止辅助定位
	 */
	public void stopAssistantLocation() {

	}

	/**
	 * 停止定位
	 */
	public void stopLocation() {

	}

	/**
	 * 移除定位监听
	 */
	public void unRegisterLocationListener(GLocationListener listener) {

	}

}
