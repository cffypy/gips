package top.geomatics.ips.algorithms.image;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import top.geomatics.ips.utils.image.FeatureExtraction;

public class FeatureExtractionTest {

    private FeatureExtraction fe = new FeatureExtraction();
    @Test
    public void testExtractFeature(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String file_path = "E:\\Projects\\214房间照片\\T2.jpg";
        Mat image_descriptors = fe.extractFeature(file_path);
        MatOfDMatch matches = fe.matchFeature(image_descriptors);

    }

}
