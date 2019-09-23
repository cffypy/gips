package com.example.peiyuyu.accelerator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
private SensorManager sensorManager;
private final Double ACC_MAX=12.00;
private final Double ACC_MIN=8.00;
private Double a_min;
private Double a_max;
private Long start_time=0l;
private Long end_time=0l;
private  Boolean flag_min=false;
private  Boolean flag_max=false;
private final int threshold_time=100;
private int step_num=0;
private Double distance=0.00;
private int i=0;
private TextView t;
private HSSFWorkbook workbook;
private HSSFSheet sheet;
private  HSSFRow row;
private TextView tc;
private TextView tt;
private TextView t2;
private TextView step;
private TextView stepLength;
private TextView length;
private TextView pos;
private Button stop;
private Button start;
private File file;
private float[] acc;
private  float[]mag;
private Double oldX;
private Double oldY;
private Double nowX=0.0;
private  Double nowY=0.0;
private Double direct;
private FileOutputStream fos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stop=findViewById(R.id.stop);
        start=findViewById(R.id.start);
        step=findViewById(R.id.step);
        pos=findViewById(R.id.pos);
        stepLength=findViewById(R.id.steplength);
        length=findViewById(R.id.length);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time=0l;
                end_time=0l;
                step_num=0;
                a_max=ACC_MAX;
                a_min=ACC_MIN;
                file=new File(Environment.getExternalStorageDirectory(),"a.xls");
                i=0;
                try {
                    if(file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    workbook=new HSSFWorkbook();
                    sheet=workbook.createSheet("sheet1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
                sensorManager.registerListener(MainActivity.this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_FASTEST);
                sensorManager.registerListener(MainActivity.this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
                sensorManager.registerListener(MainActivity.this,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_GAME);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sensorManager!=null){
                    sensorManager.unregisterListener(MainActivity.this);
                    if(file!=null) try {
                        workbook.write(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            workbook.close();
                            workbook=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t=findViewById(R.id.t);
        tc=findViewById(R.id.tc);
        tt=findViewById(R.id.tt);
        t2=findViewById(R.id.t2);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager!=null){
        sensorManager.unregisterListener(this);}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(sensorManager!=null){
            sensorManager.unregisterListener(this);}
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
     /*      if(i==0){start_time=System.currentTimeMillis();}
            else if(i==1){end_time=System.currentTimeMillis();}
            else{
                step.setText(String.valueOf(end_time-start_time));
            }
            i++;*/
           float[] a = event.values;
            acc=event.values.clone();
            DecimalFormat df = new DecimalFormat("#.00");
            Double accelerater = Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[2] * a[2]);
//            判断加速度的最小值
            if(flag_min&&flag_max){
                if(Math.abs(end_time-start_time)>threshold_time){
                    step_num++;
                    oldX=nowX;
                    oldY=nowY;
                    step.setText(String.valueOf(step_num));
                    Double s=0.28*Math.pow((a_max-a_min),0.25);
                    stepLength.setText(s+"");
                    distance+=s;
                    if(direct<0)direct+=6.28;
                    df = new DecimalFormat("#0.00");
                    nowX=oldX+distance*Math.cos(direct);
                    nowY=oldY+distance*Math.sin(direct);
                    pos.setText("X: "+df.format(nowX)+"Y: "+df.format(nowY));
                    length.setText(distance+"");
                }
                    a_max=ACC_MAX;
                    a_min=ACC_MIN;
                    flag_min=false;
                    flag_max=false;

            }
//            控制低谷的阀门
            if(accelerater<ACC_MIN){
                if(accelerater<a_min)
                {
                    a_min=accelerater;
                }else if(accelerater>a_min){
                    start_time=System.currentTimeMillis();
                    flag_min=true;
                }
            }
//            控制谷峰的阀门
            if(accelerater>ACC_MAX) {
                if(accelerater>a_max){
                    a_max=accelerater;
                }else if(accelerater<a_max){
                    end_time = System.currentTimeMillis();
                    flag_max = true;
                }
            }

            t.setText(df.format(accelerater));
            if(sheet.getRow(i)!=null){sheet.getRow(i).getCell(0).setCellValue(accelerater);}
            else{row = sheet.createRow(i);
            row.createCell(0).setCellValue(accelerater);}
            i++;
        }else if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            tc.setText("X轴的磁场强度："+String.valueOf(event.values[0])+"\n"+"Y轴的磁场强度："+String.valueOf(event.values[1])+"\n"+"Z轴的磁场强度："+String.valueOf(event.values[2]));
            mag=event.values.clone();
        }else if(event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
            direct=0.0+event.values[0];
            tt.setText("X轴的旋转速度："+String.valueOf(event.values[0])+"\n"+"Y轴的旋转速度："+String.valueOf(event.values[1])+"\n"+"Z轴的旋转速度："+String.valueOf(event.values[2]));
        }
        float[] R=new float[9];
        float[] values=new float[3];
        if(mag!=null&&acc!=null) {
            SensorManager.getRotationMatrix(R, null, acc, mag);
            SensorManager.getOrientation(R, values);
            t2.setText("X轴的旋转角度：" + String.valueOf(values[0]) + "\n" + "Y轴的旋转角度：" + String.valueOf(values[1]) + "\n" + "Z轴的旋转角度：" + String.valueOf(values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
