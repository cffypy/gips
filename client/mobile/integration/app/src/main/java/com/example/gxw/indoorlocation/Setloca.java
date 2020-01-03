package com.example.gxw.indoorlocation;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gxw.indoorlocation.algorithm.Loc;
import com.example.gxw.indoorlocation.step.OrientSensor;
import com.example.gxw.indoorlocation.step.SensorUtil;
import com.example.gxw.indoorlocation.step.StepSensorAcceleration;
import com.example.gxw.indoorlocation.step.StepSensorBase;
import com.example.gxw.indoorlocation.util.ReadHipeFileUtil;
import com.example.gxw.indoorlocation.util.SaveCoordinatesUtil;
import com.example.gxw.indoorlocation.wifi.Hipe;
import com.example.gxw.indoorlocation.wifi.Wifi;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Setloca extends AppCompatActivity implements StepSensorBase.StepCallBack, OrientSensor.OrientCallBack {

    public static final double PI = 3.14159265358979323846;

    private TextView mStepText;
    private TextView mOrientText;
    private StepView mStepView;
    private StepSensorBase mStepSensor; // 计步传感器
    private OrientSensor mOrientSensor; // 方向传感器
    //    private int mStepLen = 50; // 步长
    private Button button5;    //定位按钮
    private WifiManager wm;           //WifiManager

    private int orientP;      //前一步的方向
    private int orientC;      //当前这一步的方向
    private int orient;       //方向

    //    wifi定位的坐标
    private Double wifiXp;
    private Double wifiYp;
    private Double wifiX = 0.0;
    private Double wifiY = 0.0;
    //    pdr定位的坐标
    private Double pdrX;
    private Double pdrY;
    //    融合定位坐标
    private Double wifi_pdrXp;
    private Double wifi_pdrYp;
    private Double wifi_pdrX;
    private Double wifi_pdrY;

    private Loc loc = Loc.getInstance();//定位的方法类
    private List<Hipe> hipeList;
    private ReadHipeFileUtil readHipeFileUtil = ReadHipeFileUtil.getInstance();


    private Array2DRowRealMatrix A; //状态转移矩阵
    private Array2DRowRealMatrix H; //测量矩阵
    private Array2DRowRealMatrix Q; //过程误差矩阵
    private Array2DRowRealMatrix RR; //测量误差矩阵
    private Array2DRowRealMatrix P; //状态量的初始权阵


    @Override
    public void Step(int stepNum, double stepLength) {           //使用接口来定义step方法的具体实现
        //  计步回调 界面显示
        mStepText.setText("步数:" + stepNum);
        mStepView.autoAddPoint((float) (stepLength * 100));
        //一、wifi 先进行定位
        if (stepNum > 1) {
            wifiXp = wifiX;
            wifiYp = wifiY;
        }
        Double[] wifiC = loc.wKNN(scan(), hipeList, 2);
        wifiX = wifiC[0];
        wifiY = wifiC[1];
        try {
            SaveCoordinatesUtil.save(wifiX, wifiY, "wifi", false);//保存wifi定位结果
        } catch (IOException e) {
            e.printStackTrace();
        }
        //二、pdr进行单独定位（第一步需要初始的wifi坐标值不参与pdr定位）
        if (stepNum > 1) {
            Double[] pdrC = loc.pdrLoc(pdrX, pdrY, orient, stepLength);
            pdrX = pdrC[0];
            pdrY = pdrC[1];
        } else {
            pdrX = wifiX;
            pdrY = wifiY;
        }
        try {
            SaveCoordinatesUtil.save(pdrX, pdrY, "pdr", false);//保存wifi定位结果
        } catch (IOException e) {
            e.printStackTrace();
        }
        //三、进行wifi和pdr扩展卡尔曼滤波
        if (stepNum > 1) {
            wifi_pdrXp = wifi_pdrX; //赋值前一步的值
            wifi_pdrYp = wifi_pdrY;
            this.orientP = this.orientC;
            this.orientC = orient;
            Double[] wifi_pdrP = loc.pdrLoc(wifi_pdrXp, wifi_pdrYp, orientC, stepLength);
            wifi_pdrX = wifi_pdrP[0];
            wifi_pdrY = wifi_pdrP[1];
            initMatrix(wifi_pdrXp, wifi_pdrYp, wifi_pdrX, wifi_pdrY, orientP, stepLength);
            RealVector x = new ArrayRealVector(new double[]{wifi_pdrX, wifi_pdrY, orientC});
            RealVector z = new ArrayRealVector(new double[]{wifiX, wifiY,Math.sqrt(Math.pow(wifiX-wifiXp,2)+Math.pow(wifiY-wifiYp,2)),
            getAngle(wifiX,wifiY,wifiXp,wifiYp)});
            Double[] doubles = loc.ekfLoc(A, H, Q, RR, P, x, z);
            wifi_pdrX = doubles[0];
            wifi_pdrY = doubles[1];
        } else {
            wifi_pdrX = wifiX;
            wifi_pdrY = wifiY;
            this.orientC = orient;
        }
        try {
            SaveCoordinatesUtil.save(wifi_pdrX, wifi_pdrY, "ekf", false);//保存wifi定位结果
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 利用wifi观测坐标反推方向角作为观测值
     * @param wifiX
     * @param wifiY
     * @param wifiXp
     * @param wifiYp
     * @return
     */
    private double getAngle(Double wifiX, Double wifiY, Double wifiXp, Double wifiYp) {
        double dx = wifiX - wifiXp;
        double dy = wifiY - wifiYp;
        double t = Math.atan(dx / dy);
        if(dy<0){
            t = t + PI;
        }
        return Math.toDegrees(t);
    }

    //只考虑一步的卡尔曼滤波（初始值一直用的是pdr坐标产生的值，不是卡尔曼滤波之后更新的值加入下一次滤波）
    private void initMatrix(Double Xp, Double Yp, Double X, Double Y, int orientP, Double stepLength) {
        A = new Array2DRowRealMatrix(new double[][]{{1, 0, -stepLength * Math.sin(Math.toRadians(orientP))},
                {0, 1, stepLength * Math.cos(Math.toRadians(orientP))},
                {0, 0, 1}});
        H = new Array2DRowRealMatrix(new double[][]{{1, 0, 0}, {0, 1, 0}, {(X - Xp) / Math.sqrt(Math.pow((X - Xp), 2) + Math.pow((Y - Yp), 2)),
                (Y - Yp) / Math.sqrt(Math.pow((X - Xp), 2) + Math.pow((Y - Yp), 2)), 0}, {0, 0, 1}});
        P = new Array2DRowRealMatrix(new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
        Q = new Array2DRowRealMatrix(new double[][]{{0.1, 0, 0}, {0, 0.1, 0}, {0, 0, 0.1}});
        RR = new Array2DRowRealMatrix(new double[][]{{0.2, 0, 0, 0}, {0, 0.2, 0, 0}, {0, 0, 0.1, 0}, {0, 0, 0, 0.1}});
    }

    @Override
    public void Orient(int orient) {         //使用接口来定义Orient方法的具体实现
        // 方向回调
//        if(this.orient==0){
//            this.orient = orient;
//        }else{
//            if(Math.abs(orient-this.orient)>=5){
//                this.orient = orient;
//            }
//        }
        this.orient = orient;
        mOrientText.setText("方向:" + orient);
//        获取手机转动停止后的方向
//        orient = SensorUtil.getInstance().getRotateEndOrient(orient);
        mStepView.autoDrawArrow(orient);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SensorUtil.getInstance().printAllSensor(this); // 打印所有可用传感器 step_surfaceView
        setContentView(R.layout.setloca);

        mStepText = (TextView) findViewById(R.id.step_text);
        mOrientText = (TextView) findViewById(R.id.orient_text);
        mStepView = (StepView) findViewById(R.id.step_surfaceView);
        button5 = findViewById(R.id.button5);
        wm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        button5.setOnClickListener(new View.OnClickListener() {     //定位实现
            @Override
            public void onClick(View view) {
                if (button5.getText().equals("定位")) {
                    button5.setText("停止");
                    hipeList = readHipeFileUtil.readFile(new File(Environment.getExternalStorageDirectory(), "hipe.txt"));
                    registerSensor();
                    Twaiting();
                } else {
                    button5.setText("定位");
                    unRegisterSensor();
                }
            }

        });

    }


    /**
     * 获取扫描到的Wifi
     *
     * @return
     */
    private List<Wifi> scan() {
        List<Wifi> list = new ArrayList<>();
        wm.startScan();                              //开始扫描AP
        List<ScanResult> results = wm.getScanResults();  //拿到扫描的结果
        for (ScanResult scanResult : results) {
            Wifi wifi = new Wifi(scanResult.BSSID, Math.abs(scanResult.level), new SimpleDateFormat("yyyy年MM月dd日HH时").format(new Date()), null);
            list.add(wifi);
        }
        return list;
    }


    private void delay(int ms) {     //延时函数
        try {
            Thread.currentThread();
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Twaiting() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销传感器监听
        unRegisterSensor();
    }

    /**
     * 用户点击定位时进行注册相应的监听器
     */
    private void registerSensor() {
        // 注册计步监听
        mStepSensor = new StepSensorAcceleration(this, this);
        if (!mStepSensor.registerStep()) {
            Toast.makeText(this, "计步功能不可用！", Toast.LENGTH_SHORT).show();
        }
//        }
        // 注册方向监听
        mOrientSensor = new OrientSensor(this, this);
        if (!mOrientSensor.registerOrient()) {
            Toast.makeText(this, "方向功能不可用！", Toast.LENGTH_SHORT).show();
        }
    }


    private void unRegisterSensor() {
        if (mStepSensor != null) {
            mStepSensor.unregisterStep();
            mStepSensor = null;
        }
        if (mOrientSensor != null) {
            mOrientSensor.unregisterOrient();
            mOrientSensor = null;
        }
    }


}

