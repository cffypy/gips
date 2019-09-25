package top.geomatics.ips.algorithms.image;

import java.io.File;

import org.junit.Test;

import top.geomatics.ips.utils.image.ImageDataReader;

public class ImageDataReaderTest {

	private ImageDataReader ir = new ImageDataReader();

	@Test
	public void testLoadOBJ() {
		String pathString = "D:\\ips\\data\\database\\诗琳通视觉定位数据";
		String rf = pathString + File.separator + "extractorType-OBJ-212.dat";
		String wf = pathString + File.separator + "extractorType-OBJ-212.txt";
		ir.loadOBJ(rf,wf);
	}

}
