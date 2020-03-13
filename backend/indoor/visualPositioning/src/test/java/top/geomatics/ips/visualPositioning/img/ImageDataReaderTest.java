package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageDataReaderTest {

	private ImageDataReader ir = new ImageDataReader();


//	@Test
//	public void testLoadOBJ() {
//		String pathString = "E:\\毕设加油\\诗琳通视觉定位数据";
//		String rf = pathString + File.separator + "extractorType-OBJ-212.dat";
//		String wf = pathString + File.separator + "extractorType-OBJ-21222.json";
//		ir.loadOBJ(rf,wf);
//	}
	@Test
    public  void testLoadDes() throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat descriptors = new Mat(1,64,5);
        List<Point3> point3s = new ArrayList<Point3>();
        String pathString = "E:\\Projects\\诗琳通视觉定位数据";
        String rf = pathString + File.separator + "extractorType-OBJ-212.dat";
        String wf = pathString + File.separator + "extractorType-OBJ-212545.json";
        ir.loadOBJ(rf,wf);
//	    ir.loadDes(descriptors, point3s);

    }

}
