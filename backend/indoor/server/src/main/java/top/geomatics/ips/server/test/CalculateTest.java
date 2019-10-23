package top.geomatics.ips.server.test;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.entity.FP_info;
import top.geomatics.ips.server.entity.FP_position;
import top.geomatics.ips.server.entity.PositionResult;
import top.geomatics.ips.server.entity.ScanInfo;
import top.geomatics.ips.server.util.FileUtil;

import java.util.*;

public class CalculateTest {
    public static void main(String[] args) {
        String json="[{\n" +
                "\t\"SSID\": \"d8:c7:c8:a7:79:28\",\n" +
                "\t\"Level\": 65.13333333333334,\n" +
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

    public static PositionResult calculate(String json){
        //首先获取到指纹库中的样本数据,目前没有使用数据库，先直接从json文件中获取
        //读取json文件，获取json字符串，将json转化为java对象
        List<ScanInfo> scaninfos = JSON.parseArray(json, ScanInfo.class);
        Map<String, Double> map = new LinkedHashMap<>();//LinkedHashMap是有序的
        for (int i = 0; i < scaninfos.size(); i++) {
            String a_mac = scaninfos.get(i).getSSID();//得到mac地址
            Double a_level = scaninfos.get(i).getLevel();//获取信号强度
            map.put(a_mac, a_level);
        }
        System.out.println("android手机扫描到的wifi信息（mac地址：rssi信号强度）");
        System.out.println(map);

        //读取指纹库数据
        String filepath = "C:\\Users\\chenfa\\Desktop\\HIPE\\WifiFPDB.json";
        String jsonContent = FileUtil.ReadFile(filepath);
        //将json转化为java对象
        List<FP_position> fp_positions = JSON.parseArray(jsonContent, FP_position.class);
        List<String> macdata = new ArrayList<String>();
        HashMap<Integer,Double> endmap=new HashMap<>();

        for (int i = 0; i < fp_positions.size(); i++) {
            List<FP_info> fp_infos = fp_positions.get(i).getFP_info();
            List<Double> results=new ArrayList<Double>();

            for (int j = 0; j < fp_infos.size(); j++) {
                String mac = fp_infos.get(j).getMAC();
                double rssi = fp_infos.get(j).getMeanRssi();
                double cut;
                if(map.containsKey(mac)){
                    cut=Math.pow(Math.abs(rssi-map.get(mac)),2);//差值绝对值的平方
                    results.add(cut);
                }
            }
            System.out.println((i+1)+"号点匹配到"+results.size()+"个wifi");
            System.out.println("对应的信号强度绝对值的平方为"+results);
            double sum=0;
            double distance=0;

            for(int x=0;x<results.size();x++){
                double a=results.get(x);
                sum+=a;
                distance=Math.sqrt(sum);
            }
            System.out.println("knn距离为"+distance);
            System.out.println("     ");
            endmap.put(i+1,distance);

        }
        System.out.println(endmap);
        List<Double> list = new ArrayList<Double>();
        for (Double value : endmap.values()) {
            list.add(value);
        }
        Double min = Collections.min(list);
        System.out.println("最小值为"+min);
        Integer number=getKey(endmap,min);
        System.out.println("对应的点号为"+number);

        //根据点号找到对应的经纬度坐标信息
        PositionResult positionResult=new PositionResult();
        positionResult.setPosLon(fp_positions.get(number).getPosLon());
        positionResult.setPosLat(fp_positions.get(number).getPosLat());
        System.out.println(positionResult);

        return positionResult;
    }

     //根据value值获取到对应的key值
     public static Integer getKey(HashMap<Integer,Double> findmap,Double value){
                  Integer key = null;
                  //Map,HashMap并没有实现Iteratable接口.不能用于增强for循环.
                  for(Integer getKey: findmap.keySet()){
                         if(findmap.get(getKey).equals(value)){
                                 key = getKey;
                            }
                    }
                  return key;
     }
}
