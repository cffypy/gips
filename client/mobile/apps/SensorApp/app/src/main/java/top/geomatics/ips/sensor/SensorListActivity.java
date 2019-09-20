package top.geomatics.ips.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private TextView mSensorList;
    private String mInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorlist);

        //从系统服务中获得传感器管理器
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        //准备显示信息的UI组件
        mSensorList = findViewById(R.id.text_view_sensor_list);

        /*从传感器管理器中获得全部的传感器列表   */
        List<Sensor> allSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mInfo = "经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n";

        //显示每个传感器的具体信息
        for (Sensor s : allSensors) {

            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" + "  设备版本：" + s.getVersion() + "\n" + "  供应商："
                    + s.getVendor() + "\n";

            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    mInfo = mInfo + s.getType() + " 加速度传感器accelerometer" + tempString;
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    mInfo = mInfo + s.getType() + " 线性加速度传感器linear acceleration" + tempString;
                    break;
                case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                    mInfo = mInfo + s.getType() + " 未校准的加速度传感器uncalibrated accelerometer" + tempString;
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    mInfo = mInfo + s.getType() + " 陀螺仪传感器gyroscope" + tempString;
                    break;
                case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                    mInfo = mInfo + s.getType() + " 未校准的陀螺仪传感器uncalibrated gyroscope" + tempString;
                    break;
                case Sensor.TYPE_LIGHT:
                    mInfo = mInfo + s.getType() + " 环境光线传感器light" + tempString;
                    break;
                case Sensor.TYPE_HEART_BEAT:
                    mInfo = mInfo + s.getType() + " 心跳传感器motion detect" + tempString;
                    break;
                case Sensor.TYPE_HEART_RATE:
                    mInfo = mInfo + s.getType() + " 心率传感器heart rate" + tempString;
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    mInfo = mInfo + s.getType() + " 电磁场传感器magnetic field" + tempString;
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    mInfo = mInfo + s.getType() + " 未校准的电磁场传感器uncalibrated magnetic field" + tempString;
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    mInfo = mInfo + s.getType() + " 地磁旋转向量geo-magnetic rotation" + tempString;
                    break;
                case Sensor.TYPE_ORIENTATION:
                    mInfo = mInfo + s.getType() + " 方向传感器orientation" + tempString;
                     break;
                case Sensor.TYPE_MOTION_DETECT:
                    mInfo = mInfo + s.getType() + " 运动探测传感器motion detect" + tempString;
                    break;
                case Sensor.TYPE_SIGNIFICANT_MOTION :
                    mInfo = mInfo + s.getType() + " 明显的运动探测传感器significant motion trigger" + tempString;
                    break;
                case Sensor.TYPE_POSE_6DOF:
                    mInfo = mInfo + s.getType() + " 姿态传感器pose" + tempString;
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    mInfo = mInfo + s.getType() + " 未校准的游戏旋转向量uncalibrated rotation vector" + tempString;
                    break;
                case Sensor.TYPE_PRESSURE:
                    mInfo = mInfo + s.getType() + " 压力传感器pressure" + tempString;
                    break;
                case Sensor.TYPE_GRAVITY:
                    mInfo = mInfo + s.getType() + " 重力传感器gravity" + tempString;
                    break;
                case Sensor.TYPE_PROXIMITY:
                    mInfo = mInfo + s.getType() + " 距离传感器proximity" + tempString;
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE :
                    mInfo = mInfo + s.getType() + " 温度传感器temperature" + tempString;
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY :
                    mInfo = mInfo + s.getType() + " 相对湿度传感器relative humidity" + tempString;
                    break;
                case Sensor.TYPE_ROTATION_VECTOR :
                    mInfo = mInfo + s.getType() + " 旋转向量传感器rotation vector" + tempString;
                    break;

                case Sensor.TYPE_STATIONARY_DETECT :
                    mInfo = mInfo + s.getType() + " 静止探测传感器stationary detect" + tempString;
                    break;
                case Sensor.TYPE_STEP_COUNTER :
                    mInfo = mInfo + s.getType() + " 计步传感器step counter" + tempString;
                    break;
                case Sensor.TYPE_STEP_DETECTOR :
                    mInfo = mInfo + s.getType() + " 计步探测传感器step detector" + tempString;
                    break;
                default:
                    mInfo = mInfo + s.getType() + " 未知传感器" + tempString;
                    break;
            }
        }
        mSensorList.setText(mInfo);
        mSensorList.setMovementMethod(ScrollingMovementMethod.getInstance());


    }
}
