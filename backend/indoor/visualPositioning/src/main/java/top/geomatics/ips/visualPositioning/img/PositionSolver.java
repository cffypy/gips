package top.geomatics.ips.visualPositioning.img;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.*;

import java.nio.ByteBuffer;

import static org.opencv.calib3d.Calib3d.*;

public class PositionSolver {



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


        Mat cameraParameter = new Mat(3,3,6);
        for(int cc =0;cc<3;cc++) {
            for(int mm = 0 ; mm<3;mm++) {
                cameraParameter.put(cc,mm,camD[cc*3+mm]);
            }
        }



        return cameraParameter;
    }

    //获取畸变系数矩阵
    public MatOfDouble getDisCoeffs(){

        double[] disC = new double[]{
                0.3855 , -2.8081 , 0.0017 ,-0.0040, 7.2458
        };

        Mat disMat = new Mat(5,1,6);
        for(int vv = 0 ; vv<5; vv++){
            disMat.put(vv , 0 , disC[vv] );
        }
        MatOfDouble disCoeffs = new MatOfDouble(disMat);

        return disCoeffs;

    }
    //解算位姿
    // params：世界坐标点，图像坐标点，内参矩阵，畸变参数
    public Point3 slove(MatOfPoint3f objectPoints, MatOfPoint2f imagePoints) {

        int iterationsCount = 1000;
        float reprojectionError = 0.7f;
        double confidence = 0.95;

        Mat inliers = new Mat();

        Mat cameraParameter = getCameraMatrix();
        MatOfDouble  distCoeffs = getDisCoeffs();

        Mat rvec = new Mat();
        Mat tvec = new Mat();

        //PNP解算位姿函数的参数
        Calib3d.solvePnPRansac(objectPoints, imagePoints,cameraParameter, distCoeffs, rvec,  tvec,
                true,iterationsCount,reprojectionError,confidence,inliers,SOLVEPNP_P3P);

        //求解出相机坐标系变换到世界坐标系下的旋转与平移矩阵
      //  System.out.println("旋转矩阵："+rvec+"/n");
      //  System.out.println("平移矩阵："+tvec);

        Mat rotationMatrix = new Mat(3,3,6);//3*3
        Rodrigues(rvec,rotationMatrix);


        Mat w =rotationMatrix.inv();
        System.out.println("旋转矩阵");
        for(int dd =0 ;dd<3 ; dd++){
            for(int jj =0 ;jj<3 ; jj++){
                System.out.println(rotationMatrix.get(dd,jj)[0]);
            }
        }
        System.out.println("旋转矩阵的逆");
        for(int dd =0 ;dd<3 ; dd++){
            for(int jj =0 ;jj<3 ; jj++){
                System.out.println(w.get(dd,jj)[0]);
            }
        }
        System.out.println("矩阵tvec");
        for(int pp =0 ;pp<3 ; pp++){

                System.out.println(w.get(0,pp)[0]);

        }

        Mat wcPoint = multipl(w,tvec);
        Point3 CameraCoordinate = new Point3(wcPoint.get(0,0)[0], wcPoint.get(1,0)[0], wcPoint.get(2, 0)[0]);

        return   CameraCoordinate;

    }

    public Mat multipl(Mat matrix_a, Mat matrix_b){
        Mat result3 = new Mat(matrix_a.rows(),matrix_b.cols(),6);

            for(int i = 0; i < matrix_a.rows(); i++){
                              for(int j = 0;j < matrix_b.cols(); j++){
                                        result3.put(i,j,calculateSingleResult(matrix_a, matrix_b, i, j));
                                     }
                            }
                       return result3;

                   }

    public double calculateSingleResult(Mat matrix_a, Mat matrix_b, int row, int col){
               double result = 0.0;
                for(int i = 0; i < matrix_a.rows(); i++){

                       result += matrix_a.get(row,i)[0] * matrix_b.get(i,col)[0];

                   }
               return result;
            }


//    public Point3 getWorldPoint (Point inPoints, Mat rvec1, Mat tvec1, Mat cameraMatrix){
//        Mat rotationMatrix = new Mat(3,3,5);//3*3
//        Rodrigues(rvec1,rotationMatrix);
//        double zConst = 0;
//        double s;
//
//        Mat imagePoint = Mat.ones(3,1,6);
//        imagePoint.get(0,0)[0]= inPoints.x;
//        imagePoint.get(1, 0)[0]= inPoints.y;
//
//        //计算系数
//        Mat tempMat = rotationMatrix.inv().mul(cameraMatrix.inv().mul(imagePoint));
//        Mat tempMat2 = rotationMatrix.inv().mul( tvec1 );
//        s = zConst + tempMat2.get(2, 0)[0];
//        s /= tempMat.get(2,0)[0];
//
//        //计算世界坐标
//        Mat m_sub = new Mat();
//        Mat m_scale = new Mat();
//        Mat m = cameraMatrix.inv().mul(imagePoint);
//        //乘以系数s
//        //Core.convertScaleAbs(m , m_scale, s);
//        Core.subtract(m_scale,tvec1, m_sub);
//        Mat wcPoint = rotationMatrix.inv().mul (m_sub);
//        Point3 worldPoint = new Point3(wcPoint.get(0,0)[0], wcPoint.get(0,1)[0], wcPoint.get(2, 0)[0]);
//        return worldPoint;

//    }

}

