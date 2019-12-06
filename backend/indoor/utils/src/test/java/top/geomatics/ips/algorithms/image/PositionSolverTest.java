package top.geomatics.ips.algorithms.image;


import org.junit.Test;
import org.opencv.core.*;
import top.geomatics.ips.utils.image.FeatureExtraction;
import top.geomatics.ips.utils.image.PositionSolver;

import java.util.ArrayList;
import java.util.List;


public class PositionSolverTest {

    PositionSolver ps;
    FeatureExtraction fe;
    Point3 worldPoint;

    @Test
    public void testPositionSolver(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //提取特征点
        String file_path = "E:\\Projects\\214房间照片\\T2.jpg";
        Mat descriptors_query = new Mat();
        MatOfKeyPoint image_keyPoints = fe.extractFeature(file_path, descriptors_query);

        Mat descriptors_store = new Mat(1,64,5);
        List<Point3> point3s = new ArrayList<Point3>();

        //找出匹配点对matches
        MatOfDMatch good_matches = fe.matchFeature(descriptors_query,descriptors_store,point3s);

        MatOfPoint2f image_points = new MatOfPoint2f();
        MatOfPoint3f object_points = new MatOfPoint3f();

        List<Point> best_image_points = new ArrayList<Point>();
        List<Point3> best_object_points = new ArrayList<Point3>();

        //挑选最佳匹配的2D-3D点对（未完成）
        for(int i = 0; i<good_matches.rows(); i++){
            int imageKPID = good_matches.toArray()[i].queryIdx;
            int objectKPID = good_matches.toArray()[i].trainIdx;
            Point point2f = image_keyPoints.toArray()[imageKPID].pt;
            Point3 point3f = point3s.get(objectKPID);
            best_image_points.add(point2f);
            best_object_points.add(point3f);

        }

        object_points.fromList(best_object_points);
        image_points.fromList(best_image_points);

        //解算位姿
        ps.slove(object_points, image_points);

        System.out.println("旋转矩阵："+ps.rvec);
        System.out.println("平移矩阵："+ps.tvec);

        //worldPoint = ps.getWorldPoint( best_image_points.get(1), ps.rvec, ps.tvec, ps.cameraParameter);


    }
}
