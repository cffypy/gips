package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class GLocationQualityReport {
    /**
     * GPS定位状态--选择的定位模式中不包含GPS定位 Android 4.4以上的手机设置中开启了定位（位置）服务，但是选择的模式为省电模式，不包含GPS定位
     * 建议选择包含gps定位的模式（例如：高精度、仅设备）
     */
    public static final int	GPS_STATUS_MODE_SAVING = 1;
    /**
     * GPS定位状态--没有GPS定位权限 如果没有GPS定位权限无法进行GPS定位, 建议在安全软件中授予GPS定位权限
     */
    public static final int	GPS_STATUS_NOGPSPERMISSION =2;
    /**
     * 定位状态--没有音频定位权限 如果没有音频定位权限无法进行音频定位, 建议在安全软件中授予音频读取权限
     */
    public static final int	GPS_STATUS_NOAUDIOPERMISSION =3;
    /**
     * 定位状态--没有摄像头定位权限 如果没有摄像头定位权限无法进行摄像头S定位, 建议在安全软件中授予摄像头读取权限
     */
    public static final int	GPS_STATUS_NOCAMERAPERMISSION =4;
    /**
     * GPS定位状态--手机中没有GPS Provider，无法进行GPS定位
     */
    public static final int	GPS_STATUS_NOGPSPROVIDER=5;
    /**
     * GPS定位状态--GPS关闭 建议开启GPS，提高定位质量
     */
    public static final int	GPS_STATUS_OFF=6;
    /**
     * 定位状态—相机关闭 建议开启相机，提高定位质量
     */
    public static final int	CAMERA_STATUS_OFF=7;
    /**
     * 定位状态—蓝牙关闭 建议开启蓝牙，提高定位质量
     */
    public static final int	BLE_STATUS_OFF=8;
    /**
     * GPS定位状态--正常
     */
    public static final int	GPS_STATUS_OK=9;


    /**
     * 获取当前的卫星数
     * @return
     */
    public int	getGPSSatellites(){
        return 0;
    }

    /**
     * 获取GPS状态信息
     * @return
     */
    public int	getGPSStatus(){
        return  GPS_STATUS_OFF;
    }

    /**
     * wifi开关是否打开 如果wifi关闭建议打开wifi开关，提高定位质量
     * @return
     */
    public boolean	isWifiAble(){
        return false;
    }

    /**
     * Camera开关是否打开 如果Camera关闭建议打开Camera开关，提高定位质量
     * @return
     */
    public boolean	isCameraAble(){
        return false;
    }

    /**
     * 蓝牙开关是否打开 如果蓝牙关闭建议打开蓝牙开关，提高定位质量
     * @return
     */
    public boolean	isBLEAble(){
        return false;
    }

    /**
     * 获取定位Sensor状态信息
     * @return
     */
    public int	getSensorStatus() {
        return CAMERA_STATUS_OFF;
    }

}
