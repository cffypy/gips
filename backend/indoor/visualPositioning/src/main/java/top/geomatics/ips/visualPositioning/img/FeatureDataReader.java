package top.geomatics.ips.visualPositioning.img;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Point3;
import top.geomatics.ips.utils.file.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

public class FeatureDataReader {

    List<FeaturePoint3> featurePoint3List = new ArrayList<>();


    public Mat readDesMat() throws IOException {
        File file = new File("E:\\Projects\\descriptors.dat");
        FileInputStream fileInput = new FileInputStream(file);
        DataInputStream dataI = new DataInputStream(fileInput);
        Mat desMat = new Mat(66979,64,5);
        int size = 0;

        while (dataI.available()!=0) {

            for (int i = 0; i < 64; i++) {
                float descriptor_m= dataI.readFloat();
                desMat.put(size, i , descriptor_m);

            }
            size++;
        }

//        for(int j = 0 ; j <10 ; j++){
//            for (int ee = 0 ; ee<10 ; ee++){
//                System.out.println(desMat.get(j,ee)[0]);
//            }
//        }
        return desMat;

    }

    public List<Point3> readPoint3() throws IOException {
        List<Point3> pointsList = new ArrayList<Point3>();

        File file2 = new File("E:\\Projects\\coordinates.dat");
        FileInputStream fileInput2 = new FileInputStream(file2);
        DataInputStream dataI2 = new DataInputStream(fileInput2);
        int ss = 0;
        Point3 p = new Point3();
        while (dataI2.available()!=0) {

            p.x = dataI2.readDouble();
            p.y = dataI2.readDouble();
            p.z = dataI2.readDouble();
            pointsList.add(p);

        }

//        System.out.println("三维点个数："+pointsList.size());
        return pointsList;
    }


        public void readJson() throws FileNotFoundException {

        //从json文件中读取特征点
        String jsonPath = "E:\\Projects\\诗琳通视觉定位数据\\extractorType-OBJList.json";
        String s = FileUtils.ReadFile(jsonPath);

        File target = new File("E:\\Projects\\诗琳通视觉定位数据\\des.dat");
        JSONArray jsonArray = JSON.parseArray(s);
        FeaturePoint3 fp3 = new FeaturePoint3();
        fp3.point3 = new Point3();
        fp3.desc = new float[64];
        int length = 6979*64;
        List desList = new ArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {


            JSONObject feature_i = jsonArray.getJSONObject(i);

            JSONArray arr = feature_i.getJSONArray("descriptors");


            //读取一个特征向量


            byte[] b =new byte[4];
            for(int j = 0 ; j < arr.size() ; j++){
               //fp3.desc[j] = arr.getFloat(j);

                float f = arr.getFloat(j);
                b = float2byte(f);

            }


            fp3.f_id = feature_i.getInteger("id");
            JSONObject obj = feature_i.getJSONObject("pt3");

            fp3.point3.x = obj.getFloat("x");
            fp3.point3.y = obj.getFloat("y");
            fp3.point3.z = obj.getFloat("z");


            featurePoint3List.add(fp3);

        }




        FileOutputStream Fos =new FileOutputStream("E:\\Projects\\诗琳通视觉定位数据\\des.dat");
        DataOutputStream ss = new DataOutputStream(Fos);



        System.out.println("已读取"+featurePoint3List.size()+"个特征点");
        for(int x =0;x<20;x++){
            System.out.println(featurePoint3List.get(x).desc[0]+"/n");
        }
    }

    //读取描述子
//    public Mat readDes(){
//        int rows = featurePoint3List.size();
//        Mat des = new Mat(rows, 64 , 5);
//        //构建待匹配的特征点描述子库descriptor_store，用于特征点匹配
//        for (int m = 0 ; m < rows  ; m++){
//            for (int n = 0 ; n < 64 ; n++) {
//                des.get(m, n)[0] = (float)featurePoint3List.get(m).desc[n];
//            }
//        }
//
//        return des;
//
//    }

    //读取空间点
//    public List<Point3> readPoint3(){
//        int row = featurePoint3List.size();
//       List<Point3> pointsList = new ArrayList<Point3>();
//        //构建待匹配的特征点描述子库descriptor_store，用于特征点匹配
//        for (int m = 0 ; m < row  ; m++){
//
//                pointsList.add( featurePoint3List.get(m).point3);
//        }
//
//        return pointsList;
//
//    }

    public class FeaturePoint3{
        public int getF_id() {
            return f_id;
        }

        public Point3 getPoint3() {
            return point3;
        }

        public float[] getDesc() {
            return desc;
        }

        int f_id = 0;
        Point3 point3;
        float[] desc;
    }


    public  byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }
        return b;
    }
}
