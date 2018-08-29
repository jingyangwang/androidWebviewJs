package com.example.wjy.myhandle;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by wjy on 2018/5/4.
 */
public class JSKit {
    private MainActivity ma;

    public JSKit(MainActivity context) {
        this.ma = context;
    }
@JavascriptInterface
    public void showMsg(String msg) {
        Toast.makeText(ma, msg+"    王景阳", Toast.LENGTH_SHORT).show();
    }


    @JavascriptInterface
    public void xiaojing(String msg) {
        Toast.makeText(ma, msg+"    王景阳   再次测试", Toast.LENGTH_SHORT).show();
    }
}
