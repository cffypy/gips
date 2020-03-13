package top.geomatics.ips.visualPositioning.img;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import static org.opencv.features2d.Features2d.drawMatches;

public class FeatureMatchTest {

    private String file_path1 = "E:\\Projects\\pictures\\T4.jpg";
    private String file_path2 = "E:\\Projects\\pictures\\T4-2.jpg";

    private FeatureMatcher fm = new FeatureMatcher();

    @Test
  public void TestFeatureMatch()  {
//
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        Mat descriptors_query = new Mat();
//        Mat descriptors_train = new Mat();
//
//        FlannBasedMatcher matcher = FlannBasedMatcher.create();
//        MatOfDMatch matches = new MatOfDMatch();
//
//        //p1的特征点数多于p2，p1为训练点集，p2为查询点集
//        Mat p1 = Imgcodecs.imread(file_path1);
//        Mat p2 = Imgcodecs.imread(file_path2);
//
//        //缩小图片尺寸
//
//            Mat dst1 = p1.clone();
//            Mat dst2 = p2.clone();
//            float scale = 0.3f;
//            Imgproc.resize(p1, dst1, new Size(p1.width()*scale, p1.height()*scale));
//            Imgcodecs.imwrite("E:\\Projects\\pictures\\resizedT4.jpg",dst1);
//            Imgproc.resize(p2, dst2, new Size(p2.width()*scale, p2.height()*scale));
//            Imgcodecs.imwrite("E:\\Projects\\pictures\\resizedT4-2.jpg",dst2);
//
//
//
//
//        FeatureExtraction fe = new FeatureExtraction();
//        MatOfKeyPoint keyPoints1 = fe.extractFeature("E:\\Projects\\pictures\\resizedT4.jpg", descriptors_train);
//        MatOfKeyPoint keyPoints2 = fe.extractFeature("E:\\Projects\\pictures\\resizedT4-2.jpg", descriptors_query);
//
//       // matcher.match(descriptors_query, descriptors_train, matches);
//      //  fm.matchFeature(descriptors_query, descriptors_train , matches);
//
//        Mat MatchesImage = new Mat();
//        drawMatches(dst2, keyPoints2, dst1, keyPoints1, matches, MatchesImage);
//        HighGui.namedWindow("Matcher Image");
//        Imgcodecs.imwrite("T4-matches.jpg", MatchesImage);
//        HighGui.imshow("Matcher Image", MatchesImage);
//
//        HighGui.waitKey();
//
//        System.out.println("matches.cols:"+matches.cols()+",matches.rows:"+matches.rows());
//
    }

}
