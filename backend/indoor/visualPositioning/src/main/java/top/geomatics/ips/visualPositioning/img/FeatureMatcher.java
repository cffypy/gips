package top.geomatics.ips.visualPositioning.img;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FlannBasedMatcher;
import top.geomatics.ips.model.vision.ImageData;


import java.util.ArrayList;
import java.util.List;


public class FeatureMatcher {


    QuickSort qs;
    //匹配特征点对：descriptors 和 descriptors_store
    //返回：good_matches
    public void matchFeature(Mat descriptors_query, Mat des_train, MatOfDMatch good_matches) {


        FlannBasedMatcher matcher = FlannBasedMatcher.create();
        MatOfDMatch matches = new MatOfDMatch();

        //读取特征库 descriptors_store

        //  ImageDataReader ir = new ImageDataReader();
   //     FeatureDataReader fr = new FeatureDataReader();


        matcher.match(descriptors_query, des_train, matches);


        //最大距离和最小距离
        double max_dist = 0;
        double min_dist = 100;
        for (int i = 0; i < matches.toArray().length; i++) {
            double dist = matches.toArray()[i].distance;
            if (dist < min_dist) min_dist = dist;
            if (dist > max_dist) max_dist = dist;
        }
        System.out.println("max_dist:" + max_dist);
        System.out.println("min_dist:" + min_dist);

        //最佳匹配 good_matches

        for (int m = 0; m< matches.rows(); m++) {
            if (matches.toArray()[m].distance <= Math.max(2* min_dist, 0.2)) {
                good_matches.push_back(matches.row(m));

            }

        }

        System.out.println("匹配点对数"+good_matches.toArray().length);

    }








    //对匹配结果进行排序
    public MatOfDMatch sortMatches(MatOfDMatch good_matches){

        //对匹配点对按照距离排序
        if (good_matches!=null) {

            qs.quickSort(good_matches);

            System.out.println("筛选出的匹配点good_matches对数为" + good_matches.rows());

            return good_matches;
        }

        else  {
            System.out.println("good_matches为空");
            return null;
        }
    }


    public MatOfDMatch matchFeatureByHNSW (Mat descriptors, Mat descriptors_store, MatOfDMatch matches_result){


        return matches_result;
    }

    public class QuickSort {

        public  int partition( MatOfDMatch good_matches, int low, int high) {
            //指定左指针i和右指针j
            int i = low;
            int j = high;

            //将第一个数作为基准值。挖坑
            float x = good_matches.toArray()[low].distance;

            //使用循环实现分区操作
            while(i<j){//5  8
                //1.从右向左移动j，找到第一个小于基准值的值 arr[j]
                while(good_matches.toArray()[j].distance>=x && i<j){
                    j--;
                }
                //2.将右侧找到小于基准数的值加入到左边的（坑）位置， 左指针想中间移动一个位置i++
                if(i<j){
                    good_matches.toArray()[i] = good_matches.toArray()[j];
                    i++;
                }
                //3.从左向右移动i，找到第一个大于等于基准值的值 arr[i]
                while(good_matches.toArray()[j].distance<x && i<j){
                    i++;
                }
                //4.将左侧找到的大于等于基准值的值加入到右边的坑中，右指针向中间移动一个位置 j--
                if(i<j){
                    good_matches.toArray()[j] = good_matches.toArray()[i];
                    j--;
                }
            }

            //使用基准值填坑，这就是基准值的最终位置
            good_matches.toArray()[i].distance = x;//arr[j] = y;
            //返回基准值的位置索引
            return i; //return j;
        }
        public  void quickSort(MatOfDMatch good_matches, int low, int high) {//???递归何时结束
            if(low < high){
                //分区操作，将一个数组分成两个分区，返回分区界限索引
                int index = partition(good_matches,low,high);
                //对左分区进行快排
                quickSort(good_matches,low,index-1);
                //对右分区进行快排
                quickSort(good_matches,index+1,high);
            }

        }

        public  void quickSort(MatOfDMatch good_matches) {
            int low = 0;
            int high = good_matches.toArray().length-1;
            quickSort(good_matches,low,high);
        }


        }


    }






