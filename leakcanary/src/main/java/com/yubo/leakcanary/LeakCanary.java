package com.yubo.leakcanary;

import android.app.Application;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 16:57
 */

public class LeakCanary {
    private static final String TAG = LeakCanary.class.getSimpleName();
    private static AndroidWatchExecutor watchExecutor;

    public LeakCanary() {

    }

    public static RefWatcher install(Application application) {
        // 在这里构建一个RefWatcher用作判断是否有内存泄露
        RefWatcher refWatcher = build();
        // 这里是用作监听activity销毁的时候
        ActivityRefWatcher.installOnIcsPlus(application, refWatcher);
        return refWatcher;
    }

    private static RefWatcher build() {
        if (watchExecutor != null) {
            watchExecutor = new AndroidWatchExecutor();
        }
        return new RefWatcher(watchExecutor);
    }


}
