package com.example.gxw.indoorlocation;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gxw.indoorlocation.wifi.Hipe;
import com.example.gxw.indoorlocation.wifi.Wifi;
import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Collect extends AppCompatActivity {


    private Integer posNum;
    private Button col;
    private Button next;
    private Button save;
    private Button clear;
    private WifiManager wifiManager;
    private List<ScanResult> scanResults;
    private EditText X;
    private EditText Y;
    private EditText direction;
    private EditText posN;
    private List<Wifi> list;
    private List<Hipe> L;
    private File file;
    private String x;
    private String y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        X = findViewById(R.id.X);
        Y = findViewById(R.id.Y);
        posN = findViewById(R.id.posN);
        posNum = Integer.valueOf(String.valueOf(posN.getText()));
        File f = Environment.getExternalStorageDirectory();
        file = new File(f, "hipe.txt");
        direction = findViewById(R.id.e);
        list = new ArrayList<>();
        L = new ArrayList<>();
        col = findViewById(R.id.col);
        next = findViewById(R.id.next);
        save = findViewById(R.id.save);
        clear = findViewById(R.id.button4);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = String.valueOf(X.getText());
                y = String.valueOf(Y.getText());
                posNum = Integer.valueOf(String.valueOf(posN.getText()));
                if (x.equals("11111") || y.equals("22222")) {
                    Toast.makeText(Collect.this, "修改一下坐标哦！！！", Toast.LENGTH_SHORT).show();
                } else {
                    next.setEnabled(true);
                    for (int i = 9; i > 0; i--) {
                        wifiManager.startScan();
                        wifiManager.setWifiEnabled(true);
                        scanResults = wifiManager.getScanResults();
                        for (ScanResult scanResult : scanResults) {
                            Wifi wifi = new Wifi(scanResult.BSSID, Math.abs(scanResult.level), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), parseDirection(Integer.valueOf(String.valueOf(direction.getText()))));
                            list.add(wifi);
                        }
                        try {
                            Thread.currentThread().sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Toast.makeText(Collect.this, posNum + "本方向采集完毕，换个方向...", Toast.LENGTH_LONG).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
//                posNum++;
//                posN.setText(String.valueOf(posNum));
//                posNum = Integer.valueOf(String.valueOf(posN.getText()));
                X.setText("11111");
                Y.setText("22222");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (L != null) {
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Gson gson = new Gson();
                    String s = gson.toJson(L);
                    try {
                        FileUtils.write(file, s, "UTF-8", true);
                        Toast.makeText(Collect.this, "hipe.txt 保存成功！！！", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    list = new ArrayList<>();
                    L = new ArrayList<>();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.exists()) {
                    try {
                        FileUtils.forceDelete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Collect.this, "hipe.txt 已删除！！！", Toast.LENGTH_SHORT).show();
                }
                list = new ArrayList<>();
                L = new ArrayList<>();
            }
        });

    }

    private void save() {
        Hipe hipe = new Hipe();
        col.setText("开始采集");
        posNum = Integer.valueOf(String.valueOf(posN.getText()));
        hipe.setPOSNum(posNum);
        hipe.setPOSX(Double.valueOf(x));
        hipe.setPOSY(Double.valueOf(y));
        hipe.setFPinfo(list);
        L.add(hipe);
        list = new ArrayList<>();
    }

    private String parseDirection(Integer value) {
        String s = "北";
        if (value == 1) {
            s = "东";
        } else if (value == 2) {
            s = "南";
        } else if (value == 3) {
            s = "西";
        }
        return s;
    }


}
