package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point3;

import java.io.IOException;
import java.util.List;

public class FeatureDataReaderTest {

    FeatureDataReader fr = new FeatureDataReader();

    @Test
    public void testFeatureReader() throws IOException {

       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

       Mat des = fr.readDesMat();
       List p = fr.readPoint3();
    }
}
