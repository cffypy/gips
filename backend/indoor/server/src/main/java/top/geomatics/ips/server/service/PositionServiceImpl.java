package top.geomatics.ips.server.service;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.entity.FP_info;
import top.geomatics.ips.server.entity.FP_position;
import top.geomatics.ips.server.entity.PositionResult;
import top.geomatics.ips.server.entity.ScanInfo;
import top.geomatics.ips.server.util.FileUtil;

import java.util.*;


/**
 * @author chenfa
 * 接口实现类，定位算法实现
 */
public class PositionServiceImpl implements PositionService {

    @Override
    public void calculatePosition(String json) {
        //首先获取到指纹库中的样本数据,目前没有使用数据库，先直接从json文件中获取
        //读取json文件，获取json字符串，将json转化为java对象
        List<ScanInfo> scaninfos = JSON.parseArray(json, ScanInfo.class);
        Map<String, Double> map = new LinkedHashMap<>();//LinkedHashMap是有序的
        for (int i = 0; i < scaninfos.size(); i++) {
            String a_mac = scaninfos.get(i).getSSID();//得到mac地址
            Double a_level = scaninfos.get(i).getLevel();//获取信号强度
            map.put(a_mac, a_level);
        }
        System.out.println(map);

        //读取指纹库数据
        String filepath = "C:\\Users\\chenfa\\Desktop\\HIPE\\WifiFPDB.json";
        String jsonContent = FileUtil.ReadFile(filepath);
        //将json转化为java对象
        List<FP_position> fp_positions = JSON.parseArray(jsonContent, FP_position.class);

        List<String> macdata = new ArrayList<String>();

        for (int i = 0; i < fp_positions.size(); i++) {
            List<FP_info> fp_infos = fp_positions.get(i).getFP_info();
            for (int j = 0; j < fp_infos.size(); j++) {
                String mac = fp_infos.get(j).getMAC();
                double rssi = fp_infos.get(j).getMeanRssi();
//                String mac1=fp_positions.get(0).getFP_info().get(1).getMAC();
//                System.out.println(mac);
                macdata.add(mac);
            }
            int point_num = fp_positions.get(i).getPoint_NO();

        }


    }
}