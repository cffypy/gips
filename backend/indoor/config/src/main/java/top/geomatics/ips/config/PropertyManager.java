package top.geomatics.ips.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

/**
 * <em>配置文件管理</em>
 *
 * @author Yuejin Deng
 * @version 1.0
 */
public class PropertyManager {
    /**
     * gips全局配置文件路径
     */
    private static final String USER_HOME = "user.home";
    private static final String GIPS = ".gips";
    private static final String GIPS_PROPERTIES = "gips.properties";
    private static final String CONFIG_DIR = System.getProperty(USER_HOME) + File.separator + GIPS;
    private static final String CONFIG_FILE = CONFIG_DIR + File.separator + GIPS_PROPERTIES;
    /**
     * 如果配置文件不存在，则创建一个文件，写入默认值
     */
    private static final String DEFAULT_DIR_BASE_KEY = "gips_base_dir";
    private static final String DEFAULT_DIR_BASE_VALUE = "d:" + File.separator + "ips";
    private static final String DEFAULT_COMMENTS = "GIPS global settings " ;

    static {
        File dir = new File(CONFIG_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        OutputStream outputStream = null;
        File f = new File(CONFIG_FILE);
        if (!f.exists()) {
            try {
                boolean s = f.createNewFile();
                if (s) {
                    /**
                     * 写入默认的属性
                     */
                    Properties properties = new Properties();
                    outputStream = new FileOutputStream(CONFIG_FILE);
                    properties.setProperty(DEFAULT_DIR_BASE_KEY, DEFAULT_DIR_BASE_VALUE);
                    /**
                     * 保存文件
                     */
                    properties.store(outputStream, DEFAULT_COMMENTS);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (null != outputStream)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得系统属性
     */
    public static String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    public static Properties getSystemProperties() {
        return System.getProperties();
    }
}
