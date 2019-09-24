package top.geomatics.ips.utils.vision;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

/**
 * Created by gj
 */


public class FeatureExtractor {
	
	/**
	 * 提取每张图像的surf特征描述子
	 * @param pic
	 * @return Mat
	 */
	
	
	public static Mat FeatureSurf(Mat pic){
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.SURF);
		DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SURF);
		//DescriptorMatcher Matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
		//DescriptorMatcher Matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
		
		MatOfKeyPoint mkp = new MatOfKeyPoint();
		fd.detect(pic, mkp);
		Mat desc = new Mat();
		de.compute(pic, mkp, desc);
		//Features2d.drawKeypoints(src, mkp, src);
		
		return desc;
		
	}
	
	


}
