package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.Point3;

import java.util.List;

public class FeatureExtractionTest {

    private FeatureExtraction fe = new FeatureExtraction();
    private FeatureDataReader fr = new FeatureDataReader();

    @Test
    public void testExtractFeature(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        fr.readJson();
        Mat descriptors_store = fr.readDes();
        List<Point3> point3List= fr.readPoint3();
        Mat image_descriptors = new Mat();

        String file_path = "E:\\Projects\\pictures\\T2.jpg";

        Mat MatOfKeyPoints = fe.extractFeature(file_path,image_descriptors);
        MatOfDMatch matches = fe.matchFeature(image_descriptors, descriptors_store);

    }

}
