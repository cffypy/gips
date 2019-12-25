package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point3;

import java.util.List;

public class FeatureDataReaderTest {

    FeatureDataReader fr = new FeatureDataReader();

    @Test
    public void testFeatureReader(){

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        fr.readJson();
        Mat des_store = fr.readDes();
        List<Point3> point3List = fr.readPoint3();
    }
}
