package top.geomatics.ips.visualPositioning.img;


import org.junit.Test;
import org.opencv.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PositionSolverTest {


    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); //
    }

    PositionSolver ps = new PositionSolver();
    FeatureExtraction fe = new FeatureExtraction();
    FeatureMatcher fm = new FeatureMatcher();
    Point3 worldPoint =new Point3();

    private FeatureDataReader fr = new FeatureDataReader();

    @Test
    public void testPositionSolver() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //提取特征点
        String file_path = "E:\\Projects\\pictures\\T2.jpg";
        Mat descriptors_query = new Mat();
        //提取拍摄图像的特征点
        MatOfKeyPoint image_keyPoints = fe.extractFeature(file_path, descriptors_query);

        //读取空间点的json文件（坐标+描述子）
      //  fr.readJson();
        //读取所有特征描述子，用于特征匹配
        long start=System.currentTimeMillis();   //获取开始时间
        Mat descriptors_store = fr.readDesMat();
        long end=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(end-start)/1000+"s");
        //读取所有三维点，用于计算位姿
        List<Point3> point3List= fr.readPoint3();

        //找出匹配点对matches
        MatOfDMatch good_matches =new MatOfDMatch();
        fm.matchFeature( descriptors_query, descriptors_store,good_matches);
     //   fm.sortMatches(good_matches);

        MatOfPoint2f image_points = new MatOfPoint2f();
        MatOfPoint3f object_points = new MatOfPoint3f();

        List<Point> best_image_points = new ArrayList<Point>();
        List<Point3> best_object_points = new ArrayList<Point3>();

        // 对用于解算位姿的输入点对进行赋值，根据匹配点的id（分queryIdx和trainIdx）去 image_keyPoints 和 point3List中找到对应的点
        for(int i = 0; i<good_matches.rows(); i++){
            int imageKPID = good_matches.toArray()[i].queryIdx;
            int objectKPID = good_matches.toArray()[i].trainIdx;
            Point point2f = image_keyPoints.toArray()[imageKPID].pt;
            Point3 point3f = point3List.get(objectKPID);

            best_image_points.add(point2f);
            best_object_points.add(point3f);

        }

        object_points.fromList(best_object_points);
        image_points.fromList(best_image_points);

        //解算位姿
        worldPoint = ps.slove(object_points, image_points);


        System.out.println("相机坐标:"+ worldPoint);


    }
}
