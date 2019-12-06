package top.geomatics.ips.utils.image;

import org.opencv.core.*;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.xfeatures2d.SURF;
import org.opencv.highgui.HighGui;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math.*;

public class FeatureExtraction {

    private String pathString = "E:\\Projects\\诗琳通视觉定位数据";
    private String rf = pathString + File.separator + "extractorType-OBJ-212.dat";
    private String wf = pathString + File.separator + "extractorType-OBJ-212888.json";


    public MatOfKeyPoint extractFeature(String img_file, Mat descriptors) {


        SURF surf = SURF.create();
        surf.setHessianThreshold(2000);

        MatOfKeyPoint keyPoints = new MatOfKeyPoint();

        //读取图像
        Mat img1 = Imgcodecs.imread(img_file);
        System.out.println("图像的大小："+img1.size());

        //提取特征
        surf.detect(img1, keyPoints);
        //计算描述子
        surf.compute(img1, keyPoints, descriptors);


        System.out.println("descriptors.cols=" + descriptors.cols());
        System.out.println("descriptors.rows=" + descriptors.rows());

        return keyPoints;
    }


    public MatOfDMatch matchFeature(Mat descriptors, Mat descriptors_store, List<Point3> points3){

        FlannBasedMatcher matcher = FlannBasedMatcher.create();
        MatOfDMatch matches = new MatOfDMatch();

        //读取特征库 descriptors_store
        ImageDataReader ir = new ImageDataReader();

        ir.loadOBJ(rf, wf);
        ir.loadDes(descriptors_store,points3);

        //将descriptors_store输出到文件

        //特征匹配 matches
        matcher.match(descriptors,descriptors_store,matches);

        //最大距离和最小距离
        double max_dist = 0; double min_dist = 100;
        for( int i = 0; i < descriptors.rows(); i++ )
        { double dist = matches.toArray()[i].distance;
            if( dist < min_dist ) min_dist = dist;
            if( dist > max_dist ) max_dist = dist;
        }
        System.out.println("max_dist:"+max_dist);
        System.out.println("min_dist:"+min_dist);

        //最佳匹配 good_matches
        MatOfDMatch good_matches = new MatOfDMatch();
        for( int i = 0; i < descriptors.rows(); i++ )
        { if( matches.toArray()[i].distance <= Math.max(2*min_dist, 0.02) )
        { good_matches.push_back( matches.row(i)); }
        }

        System.out.println("筛选出的匹配点good_matches对数为"+good_matches.cols());

        return good_matches;
    }
}
