package top.geomatics.ips.algorithms.image;

import org.junit.Test;
import org.opencv.core.*;
import org.opencv.features2d.FlannBasedMatcher;
import top.geomatics.ips.utils.image.FeatureExtraction;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.opencv.core.Scalar.all;
import static org.opencv.features2d.Features2d.NOT_DRAW_SINGLE_POINTS;
import static org.opencv.features2d.Features2d.drawMatches;

public class FeatureMatchTest {

    private String file_path1 = "E:\\Projects\\pictures\\T4.jpg";
    private String file_path2 = "E:\\Projects\\pictures\\T4-2.jpg";

    @Test
    public void TestFeatureMatch()  {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat descriptors_query = new Mat();
        Mat descriptors_train = new Mat();

        FlannBasedMatcher matcher = FlannBasedMatcher.create();
        MatOfDMatch matches = new MatOfDMatch();

        //p1的特征点数多于p2，p1为训练点集，p2为查询点集
        Mat p1 = Imgcodecs.imread(file_path1);
        Mat p2 = Imgcodecs.imread(file_path2);


        FeatureExtraction fe = new FeatureExtraction();
        MatOfKeyPoint keyPoints1 = fe.extractFeature(file_path1, descriptors_train);
        MatOfKeyPoint keyPoints2 = fe.extractFeature(file_path2, descriptors_query);

        matcher.match(descriptors_query, descriptors_train, matches);

        Mat MatchesImage = new Mat();
        drawMatches(p2, keyPoints2, p1, keyPoints1, matches, MatchesImage);
        HighGui.namedWindow("Matcher Image");
        Imgcodecs.imwrite("T4-matches.jpg", MatchesImage);
        HighGui.imshow("Matcher Image", MatchesImage);

        HighGui.waitKey();

        System.out.println("matches.cols:"+matches.cols()+",matches.rows:"+matches.rows());

    }

}
