package top.geomatics.ips.server.test;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.model.WifiRoot;
import top.geomatics.ips.server.util.FileUtil;

import java.util.List;

/**
 * @author chenfa
 */
public class FileUtilTest {
    public static void main(String[] args) {
        //读取json文件，获取json字符串
        String filePath = "C:\\Users\\chenfa\\Desktop\\指纹库采集及存储说明\\wifi+蓝牙json格式\\WIFIscan.json";
        String jsonContent = FileUtil.ReadFile(filePath);

        //将json转化为java对象
        List<WifiRoot> wifiroot = JSON.parseArray(jsonContent, WifiRoot.class);
        System.out.println(jsonContent);
    }
}
