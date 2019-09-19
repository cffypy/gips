package top.geomatics.ips.service;

/**
 * Created by whudyj on 2017/11/2.
 */

public class CoordinateConverter {
    /**
     * 坐标类型
     */
    public  static class CoordType {
        /**
         * 高德坐标系
         */
        public static final int AMAP =1;
        /**
         * GPS坐标系
         */
        public static final int GPS =2;
        /**
         * 天地图CGCS2000坐标系
         */
        public static final int CGCS2000 =3;
    }

    /**
     * 计算两点间距离 单位：米
     * @param startLatLon
     * @param endLatLon
     * @return
     */
    public static  float calculateLineDistance(GPoint startLatLon, GPoint endLatLon){
        return 0.0f;
    }

    /**
     * 进行坐标转换
     * @return
     */
    public GPoint convert(){
        return new GPoint();
    }

    /**
     * 设置偏转数据源
     * @param latLon
     * @return
     */
    public CoordinateConverter coord(GPoint latLon) {
        return this;
    }

    /**
     * 设置偏转源类型
     * @param type
     * @return
     */
    public CoordinateConverter from(CoordinateConverter.CoordType type){
        return this;
    }
}
