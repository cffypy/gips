package top.geomatics.ips.utils.image;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;
import java.nio.ByteBuffer;

import static org.opencv.calib3d.Calib3d.Rodrigues;

public class PositionSolver {

    public Mat rvec = new Mat(3,1,5);
    public Mat tvec = new Mat(3,1,5);
    public  Mat cameraParameter;
    public MatOfDouble distCoeffs;

    public static byte[] DoubleToBytes(double d){
        //根据 IEEE 754 浮点“双精度格式”位布局，返回指定浮点值的表示形式，并保留 NaN 值。
        Long value = Double.doubleToRawLongBits(d);
        byte[] b = new byte[8];
        for(int i = 0 ; i<8;i++){
            b[i] = (byte)((value>>8*i)&0xff);
        }
        return b;
    }


    public Mat getCameraMatrix() {

        double[] camD = new double[]{
                3298.97227445320, 0, 0,
                0, 3295.20216582325, 0,
                2016.00568791970, 1498.43250950467, 1
        };

        ByteBuffer buf = ByteBuffer.allocate(800);
        for( double d: camD){
            byte[] b = DoubleToBytes(d);
            buf.put(b,0, b.length);
        }
        Mat cameraParameter = new Mat(3,3,5, buf);

        return cameraParameter;
    }

    //获取畸变系数矩阵
    public MatOfDouble getDisCoeffs(){

        double[] disC = new double[]{
                0.3855 , -2.8081 , 0.0017 ,-0.0040, 7.2458
        };
        ByteBuffer buff = ByteBuffer.allocate(800);
        for( double d: disC){
            byte[] c = DoubleToBytes(d);
            buff.put(c,0, c.length);
        }

        Mat disMat = new Mat(5,1,5,buff);
        MatOfDouble disCoeffs = new MatOfDouble(disMat);

        return disCoeffs;

    }
    //解算位姿
    // params：世界坐标点，图像坐标点，内参矩阵，畸变参数
    public void slove(MatOfPoint3f objectPoints, MatOfPoint2f imagePoints) {



        int iterationsCount = 500;
        float reprojectionError = 0.7f;
        double confidence = 0.9;

        Mat inliers = new Mat();

        cameraParameter = getCameraMatrix();
        distCoeffs = getDisCoeffs();

        //PNP解算位姿函数的参数
        Calib3d.solvePnPRansac(objectPoints, imagePoints,cameraParameter, distCoeffs, rvec,  tvec,
                true,iterationsCount,reprojectionError,confidence,inliers,0);

        System.out.println("旋转矩阵："+rvec+"/n");
        System.out.println("平移矩阵："+tvec);

    }

    public Point3 getWorldPoint (Point inPoints, Mat rvec1, Mat tvec1, Mat cameraMatrix){
        Mat rotationMatrix = new Mat(3,3,5);//3*3
        Rodrigues(rvec1,rotationMatrix);
        double zConst = 0;
        double s;

        Mat imagePoint = Mat.ones(3,1,6);
        imagePoint.get(0,0)[0]= inPoints.x;
        imagePoint.get(1, 0)[0]= inPoints.y;

        //计算
        Mat tempMat = new Mat();
        Mat tempMat2 = new Mat();
        tempMat = rotationMatrix.inv().mul(cameraMatrix.inv().mul(imagePoint));
        tempMat2 = rotationMatrix.inv().mul( tvec1 );
        s = zConst + tempMat2.get(2, 0)[0];
        s /= tempMat.get(2,0)[0];

        //计算世界坐标
        Mat wcPoint = rotationMatrix.inv().mul ((cameraMatrix.inv().mul(imagePoint))*s - tvec);
        Point3 worldPoint = new Point3(wcPoint.get(0,0)[0], wcPoint.get(0,1)[0], wcPoint.get(2, 0)[0]);
        return worldPoint;

    }
}

