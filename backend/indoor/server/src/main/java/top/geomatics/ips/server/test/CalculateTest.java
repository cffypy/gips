package top.geomatics.ips.server.test;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.entity.FP_info;
import top.geomatics.ips.server.entity.FP_position;
import top.geomatics.ips.server.entity.ScanInfo;
import top.geomatics.ips.server.util.FileUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalculateTest {
    public static void main(String[] args) {
        String json="[{\n" +
                "\t\"SSID\": \"d8:c7:c8:a7:79:28\",\n" +
                "\t\"Level\": 64.13333333333334,\n" +
                "\t\"BSSID\": \"hhh\"\n" +
                "}, {\n" +
                "\t\"SSID\": \"24:69:68:8a:29:c6\",\n" +
                "\t\"Level\": 42.63333333333333,\n" +
                "\t\"BSSID\": \"wuhan\"\n" +
                "}, {\n" +
                "\t\"SSID\": \"d8:24:bd:78:45:b4\",\n" +
                "\t\"Level\": 50.066666666666656,\n" +
                "\t\"BSSID\": \"chenfa\"\n" +
                "}]";
        calculate(json);
    }

    public static void calculate(String json){
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
            List<Double> results=new ArrayList<Double>();

            for (int j = 0; j < fp_infos.size(); j++) {
                String mac = fp_infos.get(j).getMAC();
                double rssi = fp_infos.get(j).getMeanRssi();
                double cut;
                if(map.containsKey(mac)){
                    cut=Math.abs(rssi-map.get(mac));
                    results.add(cut);
                }

            }
            System.out.println(results);
            double sum=0;
            for(int x=0;x<=results.size();x++){
                double a=results.get(i);
                sum+=a;
            }
            System.out.println(sum);
//            double b=Math.pow(a,2);
//            for (int x = 0; x < results.size(); x++) {
//               a=a+results.get(i);
//            }
//            System.out.println(a);
//            System.out.println(Math.sqrt(b));
        }

    }
}
