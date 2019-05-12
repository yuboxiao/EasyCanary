package com.yubo.easycanary;

import android.app.Activity;
import android.os.Bundle;

import com.yubo.leakcanary.LeakCanary;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 16:57
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
