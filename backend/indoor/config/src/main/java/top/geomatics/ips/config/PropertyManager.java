package top.geomatics.ips.config;

/**
 * <em>配置文件管理</em>
 * @author Yuejin Deng
 * @version 1.0
 *
 */
public class PropertyManager {
    /**
     * 获得系统属性
     */

    public static String getSystemProperty(String key){
        return  System.getProperty(key);
    }
}
