package top.geomatics.ips.visualPositioning.img;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfDMatch;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.xfeatures2d.SURF;

import java.io.File;

public class FeatureExtraction {

    private String pathString = "E:\\Projects\\诗琳通视觉定位数据";
    private String rf = pathString + File.separator + "extractorType-OBJ-212.dat";
    private String wf = pathString + File.separator + "extractorType-OBJ-212888.json";


    //提取单张图像特征点
    public MatOfKeyPoint extractFeature(String img_file, Mat descriptors) {


        SURF surf = SURF.create();
        surf.setHessianThreshold(2000);

        MatOfKeyPoint keyPoints = new MatOfKeyPoint();

        //读取图像
        Mat img1 = Imgcodecs.imread(img_file);
        System.out.println("图像的大小："+img1.size());

        //提取特征
        surf.detect(img1, keyPoints);
        //计算描述子
        surf.compute(img1, keyPoints, descriptors);


        System.out.println("descriptors.cols=" + descriptors.cols());
        System.out.println("descriptors.rows=" + descriptors.rows());

        return keyPoints;
    }


}
