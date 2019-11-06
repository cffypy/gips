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

    public MatOfKeyPoint extractFeature(String img_file) {

        //提取特征 descriptors
        SURF surf = SURF.create();
        surf.setHessianThreshold(1000);
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        Mat descriptors = new Mat();
        Mat img1 = Imgcodecs.imread(img_file);
        surf.compute(img1, keyPoints, descriptors);
        System.out.println("descriptors.cols=" + descriptors.cols());
        System.out.println("descriptors.rows=" + descriptors.rows());
        return keyPoints;
    }
    public MatOfDMatch matchFeature(Mat descriptors, Mat descriptors_store, List<Point3> points3){


        FlannBasedMatcher matcher = new FlannBasedMatcher();
        MatOfDMatch matches = new MatOfDMatch();

        //读取特征库 descriptors_store
        ImageDataReader ir = new ImageDataReader();

        ir.run(descriptors_store,points3);

        //特征匹配 matches
        matcher.match(descriptors,descriptors_store,matches);

        //最大距离和最小距离
        double max_dist = 0; double min_dist = 100;
        for( int i = 0; i < descriptors.rows(); i++ )
        { double dist = matches.toArray()[i].distance;
            if( dist < min_dist ) min_dist = dist;
            if( dist > max_dist ) max_dist = dist;
        }
        System.out.println(max_dist);
        System.out.println(min_dist);

        //最佳匹配 good_matches
        MatOfDMatch good_matches = new MatOfDMatch();
        for( int i = 0; i < descriptors.rows(); i++ )
        { if( matches.toArray()[i].distance <= Math.max(2*min_dist, 0.02) )
        { good_matches.push_back( matches.row(i)); }
        }

        return good_matches;


    }
}
