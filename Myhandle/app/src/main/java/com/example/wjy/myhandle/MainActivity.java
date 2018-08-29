package com.example.wjy.myhandle;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Button btnShowInfo;
    private JSKit js;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        mWebView = (WebView) findViewById(R.id.wv_test);
        btnShowInfo = (Button) findViewById(R.id.btn_showmsg);
        //实例化js对象
        js = new JSKit(this);
        //设置参数
        mWebView.getSettings().setBuiltInZoomControls(true);
        //内容的渲染需要webviewChromClient去实现，设置webviewChromClient基类，解决js中alert不弹出的问题和其他内容渲染问题
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //把js绑定到全局的myjs上，myjs的作用域是全局的，初始化后可随处使用
        mWebView.addJavascriptInterface(js, "myjs");

        mWebView.loadUrl("file:///android_asset/jstest.html");

        btnShowInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //调用 HTML 中的javaScript 函数
                        mWebView.loadUrl("javascript:showMsg()");
                    }
                });
            }
        });
    }

}

//    class JavaScriptinterface {
//        Context context;
//
//        public JavaScriptinterface(Context c) {
//            context = c;
//        }
//
//        /**
//         * 与js交互时用到的方法，在js里直接调用的
//         */
//        @JavascriptInterface
//        public void showToast(String ssss) {
//
//            Toast.makeText(context, ssss, Toast.LENGTH_LONG).show();
//        }
//
//        @JavascriptInterface
//        public void showMsg(String msg) {
//            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//
//
//    class JSKit {
//        private MainActivity ma;
//
//        public JSKit(MainActivity context) {
//            this.ma = context;
//        }
//
//        public void showMsg(String msg) {
//            Toast.makeText(ma, msg, Toast.LENGTH_SHORT).show();
//        }
//    }

//}
