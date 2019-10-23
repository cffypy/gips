package top.geomatics.ips.server.test;

import com.alibaba.fastjson.JSON;
import top.geomatics.ips.server.entity.FP_position;
import top.geomatics.ips.server.entity.PositionResult;
import top.geomatics.ips.server.util.FileUtil;

import java.util.List;

public class PositionTest {
    public static void main(String[] args) {
        Double min=1.0;
        position(min);
    }

    public static PositionResult position(Double min){
        PositionResult positionResult=new PositionResult();
        int num=0;
        //读取指纹库数据
        String filepath = "C:\\Users\\chenfa\\Desktop\\HIPE\\WifiFPDB.json";
        String jsonContent = FileUtil.ReadFile(filepath);
        //将json转化为java对象
        List<FP_position> fp_positions = JSON.parseArray(jsonContent, FP_position.class);
        positionResult.setPosLon(fp_positions.get(num).getPosLon());
        positionResult.setPosLat(fp_positions.get(num).getPosLat());
        return positionResult;
    }
}
