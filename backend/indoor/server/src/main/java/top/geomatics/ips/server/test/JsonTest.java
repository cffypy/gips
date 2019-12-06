package top.geomatics.ips.server.test;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.model.FP_info;
import top.geomatics.ips.server.model.FP_position;
import top.geomatics.ips.server.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String filepath="C:\\Users\\chenfa\\Desktop\\HIPE\\WifiFPDB.json";
        String jsonContent= FileUtil.ReadFile(filepath);
        //将json转化为java对象
        List<FP_position> fp_positions = JSON.parseArray(jsonContent, FP_position.class);
//        List<FP_info> fp_infos = JSON.parseArray(jsonContent, FP_info.class);
        List<String> macdata=new ArrayList<String>();

        for(int i=0;i<fp_positions.size();i++){
            List<FP_info> fp_infos=fp_positions.get(i).getFP_info();
            for(int j=0;j<fp_infos.size();j++){
                String mac=fp_infos.get(j).getMAC();
                double rssi=fp_infos.get(j).getMeanRssi();
//                String mac1=fp_positions.get(0).getFP_info().get(1).getMAC();
//                System.out.println(mac);
                macdata.add(mac);
            }
            int point_num=fp_positions.get(i).getPoint_NO();

        }
        System.out.println(macdata.size());
        System.out.println(macdata);
        System.out.println(macdata.get(1));

    }
}
