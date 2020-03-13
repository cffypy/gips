package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.*;
import org.opencv.calib3d.*;
import org.opencv.features2d.BFMatcher;
import org.opencv.features2d.ORB;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.xfeatures2d.SIFT;
import org.opencv.xfeatures2d.SURF;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.opencv.core.Core.NORM_L2;
import static org.opencv.features2d.Features2d.drawKeypoints;
import static org.opencv.features2d.Features2d.drawMatches;

public class FeatureExtractionTest {

    private FeatureExtraction fe = new FeatureExtraction();
    private FeatureDataReader fr = new FeatureDataReader();
    private  FeatureMatcher fm = new FeatureMatcher();

    @Test
    public void testExtractFeature(){

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    //    fr.readJson();
   //     Mat descriptors_store = fr.readDes();
    //    List<Point3> point3List= fr.readPoint3();
        Mat image_descriptors = new Mat();
     //   MatOfDMatch good_matches = new MatOfDMatch();

        String file_path = "E:\\Projects\\pictures\\room1.jpg";

        Mat MatOfKeyPoints = fe.extractFeature(file_path,image_descriptors);

        HighGui.namedWindow("Matcher Image");
    //    fm.matchFeature(image_descriptors, descriptors_store, good_matches);

    }

}
