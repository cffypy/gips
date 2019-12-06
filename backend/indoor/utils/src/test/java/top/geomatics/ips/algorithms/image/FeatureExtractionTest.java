package top.geomatics.ips.algorithms.image;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.Point3;
import top.geomatics.ips.utils.image.FeatureExtraction;

import java.util.ArrayList;
import java.util.List;

public class FeatureExtractionTest {

    private FeatureExtraction fe = new FeatureExtraction();
    @Test
    public void testExtractFeature(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat descriptors_store = new Mat();
        Mat image_descriptors = new Mat();
        List<Point3> point3List= new ArrayList<Point3>();
        String file_path = "E:\\Projects\\pictures\\T2.jpg";

        Mat MatOfKeyPoints = fe.extractFeature(file_path,image_descriptors);
        MatOfDMatch matches = fe.matchFeature(image_descriptors, descriptors_store, point3List);

    }

}
