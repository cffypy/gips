package ips.geomatics.top.indoormapdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.w);
        webView.loadUrl("https://www.seniorland.cn/gips/");
//        webView.loadUrl("https://www.baidu.com");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true); //可任意比例缩放
        webView.getSettings().setLoadWithOverviewMode(true); //设置加载内容自适应屏幕
    }
}
