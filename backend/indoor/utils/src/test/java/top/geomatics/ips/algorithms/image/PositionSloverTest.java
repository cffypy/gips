package top.geomatics.ips.algorithms.image;


import org.junit.Test;
import org.opencv.core.*;
import top.geomatics.ips.utils.image.FeatureExtraction;
import top.geomatics.ips.utils.image.PositionSlover;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class PositionSloverTest {

    PositionSlover ps;
    FeatureExtraction fe;

    @Test
    public void testPositionSlover(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //获取特征匹配点对
        String file_path = "E:\\Projects\\214房间照片\\T2.jpg";
        MatOfKeyPoint image_keyPoints = fe.extractFeature(file_path);

        Mat descriptors_store = new Mat(1,64,5);
        List<Point3> point3s = new ArrayList<Point3>();
        MatOfDMatch good_matches = fe.matchFeature(image_keyPoints,descriptors_store,point3s);

        MatOfPoint2f image_points = new MatOfPoint2f();
        MatOfPoint3f object_points = new MatOfPoint3f();

        List<Point> best_image_points = new ArrayList<Point>();
        List<Point3> best_object_points = new ArrayList<Point3>();

        //挑选最佳匹配的2D-3D点对（未完成）
        for(int i = 0; i<4; i++){
            int imageKPId = good_matches.toArray()[i].queryIdx;
            Point point2f = image_keyPoints.toArray()[imageKPId].pt;
            Point3 point3f = point3s.get(imageKPId);
            best_image_points.add(point2f);
            best_object_points.add(point3f);

    }

        object_points.fromList(best_object_points);
        image_points.fromList(best_image_points);

        //解算位姿
        ps.slove(object_points, image_points);


    }
}
