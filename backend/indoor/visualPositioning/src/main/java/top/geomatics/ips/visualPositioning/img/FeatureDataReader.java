package top.geomatics.ips.visualPositioning.img;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.opencv.core.Mat;
import org.opencv.core.Point3;
import top.geomatics.ips.utils.file.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class FeatureDataReader {

    List<FeaturePoint3> featurePoint3List = new ArrayList<>();

    public void readJson() {

        //从json文件中读取特征点
        String jsonPath = "E:\\Projects\\诗琳通视觉定位数据\\extractorType-OBJList.json";
        String s = FileUtils.ReadFile(jsonPath);

        JSONArray jsonArray = JSON.parseArray(s);

        for (int i = 0; i < jsonArray.size(); i++) {

            FeaturePoint3 fp3 = new FeaturePoint3();

            JSONObject feature_i = jsonArray.getJSONObject(i);

            JSONArray arr = feature_i.getJSONArray("descriptors");
            fp3.desc = new float[arr.size()];
            for(int j = 0 ; j < arr.size() ; j++){
                fp3.desc[j] = arr.getFloat(j);
            }
            fp3.f_id = feature_i.getInteger("id");
            JSONObject obj = feature_i.getJSONObject("pt3");
            fp3.point3 = new Point3();
            fp3.point3.x = obj.getFloat("x");
            fp3.point3.y = obj.getFloat("y");
            fp3.point3.z = obj.getFloat("z");

            featurePoint3List.add(fp3);

        }


        System.out.println("已读取"+featurePoint3List.size()+"个特征点");
    }

    //读取描述子
    public Mat readDes(){
        int rows = featurePoint3List.size();
        Mat des = new Mat(rows, 64 , 5);
        //构建待匹配的特征点描述子库descriptor_store，用于特征点匹配
        for (int m = 0 ; m < rows  ; m++){
            for (int n = 0 ; n < 64 ; n++) {
                des.get(m, n)[0] = (double)featurePoint3List.get(m).desc[n];
            }
        }

        return des;

    }

    //读取空间点
    public List<Point3> readPoint3(){
        int row = featurePoint3List.size();
       List<Point3> pointsList = new ArrayList<Point3>();
        //构建待匹配的特征点描述子库descriptor_store，用于特征点匹配
        for (int m = 0 ; m < row  ; m++){

                pointsList.add( featurePoint3List.get(m).point3);
        }

        return pointsList;

    }

    public class FeaturePoint3{
        int f_id = 0;
        Point3 point3;
        float[] desc;
    }


}
