package com.example.gxw.indoorlocation.algorithm;

import com.example.gxw.indoorlocation.wifi.Hipe;
import com.example.gxw.indoorlocation.wifi.WKNN;
import com.example.gxw.indoorlocation.wifi.Wifi;

import org.apache.commons.math3.filter.DefaultMeasurementModel;
import org.apache.commons.math3.filter.DefaultProcessModel;
import org.apache.commons.math3.filter.KalmanFilter;
import org.apache.commons.math3.filter.MeasurementModel;
import org.apache.commons.math3.filter.ProcessModel;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by peiyuyu on 2019/12/25.
 */

public class Loc {
    private static Loc loc = new Loc();

    private Loc() {

    }

    public static Loc getInstance() {
        return loc;
    }

    /**
     * WKNN算法
     *
     * @param wifiList 定位时扫描到的wifi集合
     * @param hipeList 指纹库对应的指纹点的WiFi信息
     * @param k        选取距离最小的k个进行加权求取定位的坐标
     * @return 定位出来的坐标
     */
    public Double[] wKNN(List<Wifi> wifiList, List<Hipe> hipeList, Integer k) {
        if (wifiList == null || hipeList == null) {
            throw new RuntimeException("arguements is null....");
        }
        if (k > hipeList.size()) {
            throw new RuntimeException("this k is so big...");
        }
        WKNN[] wknns = new WKNN[hipeList.size()];
        for (Hipe hipe : hipeList) {
            WKNN wknn = new WKNN();
            wknn.setPOSX(hipe.getPOSX());
            wknn.setPOSY(hipe.getPOSY());
            Double distance = 0.0;
            int i = 0;
            int j = 0;
            for (Wifi wifi : wifiList) {
                for (Wifi w : hipe.getFPinfo()) {
                    if (wifi.getBssid().equals(w.getBssid())) {
                        distance += Math.pow((wifi.getRSSI() - w.getRSSI()), 2);
                        i++;
                        break;
                    }
                }
            }
            if (distance == 0.0) {
                distance = 10000.0;
            } else {
                distance = Math.sqrt(distance) / i;
            }
            wknn.setDistance(distance);
            wknns[j] = wknn;
        }
        Arrays.sort(wknns, new Comparator<WKNN>() {
            @Override
            public int compare(WKNN o1, WKNN o2) {
                return (int) (o1.getDistance() - o2.getDistance());
            }
        });
        Double[] coordinate = new Double[2];
        Double pSum = 0.0;
        for (int m = 0; m < k; m++) {
            Double p = 1 / wknns[m].getDistance();
            coordinate[0] = coordinate[0] + p * wknns[m].getPOSX();
            coordinate[1] = coordinate[1] + p * wknns[m].getPOSY();
            pSum += p;
        }
        coordinate[0] = coordinate[0] / pSum;
        coordinate[1] = coordinate[1] / pSum;
        return coordinate;
    }

    /**
     * PDR定位得出坐标
     *
     * @param x      上一步的位置X
     * @param y      上一步的位置Y
     * @param orient 这一步期间的方向
     */
    public Double[] pdrLoc(Double x, Double y, int orient, Double stepLength) {
        Double[] pdr = new Double[2];
        pdr[0] = x + stepLength * Math.cos(Math.toRadians(orient));
        pdr[1] = y + stepLength * Math.sin(Math.toRadians(orient));
        return pdr;
    }

    /**
     * 卡尔曼滤波
     * @param A 状态转移矩阵
     * @param H 观测矩阵
     * @param Q 过程噪声
     * @param R 测量噪声
     * @param P 变量初始权阵
     * @param x 上一时刻纠正的值
     * @param z 观测值
     * @return 纠正后的坐标值
     */
    public Double[] ekfLoc(RealMatrix A, RealMatrix H, RealMatrix Q, RealMatrix R, RealMatrix P, RealVector x,RealVector z){
        ProcessModel pm = new DefaultProcessModel(A, null, Q, x, P);
        MeasurementModel mm = new DefaultMeasurementModel(H, R);
        KalmanFilter filter = new KalmanFilter(pm, mm);
        filter.predict();
        filter.correct(z);
        Double[] ekf = new Double[2];
        ekf[0] = filter.getStateEstimation()[0];
        ekf[1] = filter.getStateEstimation()[1];
        return ekf;
    }


    public static void main(String[] args) {
//        Double[] doubles = Loc.getInstance().ekfLoc(new Array2DRowRealMatrix(new double[][]{{1d,0},{0,1}}), new Array2DRowRealMatrix(new double[][]{{1d,0},{0,1d}}),
//                new Array2DRowRealMatrix(new double[][]{{0.0001,0},{0,0.0001}}), new Array2DRowRealMatrix(new double[][]{{0.1d,0},{0,0.1d}}),
//                new Array2DRowRealMatrix(new double[][]{{1d,0},{0,1d}}), new ArrayRealVector(new double[]{5d,10d}),
//                new ArrayRealVector(new double[]{4.5d,10.5d}));
//        System.out.println(doubles[0]+","+doubles[1]);

        System.out.println(Math.cos(Math.toRadians(10.0)));
    }





}
