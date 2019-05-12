package com.yubo.easycanary;

import android.app.Application;

import com.yubo.leakcanary.LeakCanary;
import com.yubo.log.LogUtils;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 17:07
 */

public class MyApplication extends Application{
    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "====>onCreate");
        LeakCanary.install(this);
    }
}
