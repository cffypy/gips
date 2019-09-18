package top.geomatics.ips.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    private final SensorManager mSensorManager;
    private TextView mSensorList;
    private String mInfo;

    public SensorListActivity() {
        //从系统服务中获得传感器管理器
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorlist);

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
                case Sensor.TYPE_GYROSCOPE:
                    mInfo = mInfo + s.getType() + " 陀螺仪传感器gyroscope" + tempString;
                    break;
                case Sensor.TYPE_LIGHT:
                    mInfo = mInfo + s.getType() + " 环境光线传感器light" + tempString;
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    mInfo = mInfo + s.getType() + " 电磁场传感器magnetic field" + tempString;
                    break;
                case Sensor.TYPE_ORIENTATION:
                    mInfo = mInfo + s.getType() + " 方向传感器orientation" + tempString;
                     break;
                case Sensor.TYPE_PRESSURE:
                    mInfo = mInfo + s.getType() + " 压力传感器pressure" + tempString;
                    break;
                case Sensor.TYPE_PROXIMITY:
                    mInfo = mInfo + s.getType() + " 距离传感器proximity" + tempString;
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE :
                    mInfo = mInfo + s.getType() + " 温度传感器temperature" + tempString;
                    break;
                default:
                    mInfo = mInfo + s.getType() + " 未知传感器" + tempString;
                    break;
            }
        }


    }
}
