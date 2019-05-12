package com.yubo.leakcanary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.yubo.log.LogUtils;

import static com.yubo.leakcanary.preconditions.checkNotNull;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 19:36
 */

public class ActivityRefWatcher {
    private static final String TAG = ActivityRefWatcher.class.getSimpleName();

    private Application application;

    private RefWatcher refWatcher;

    public ActivityRefWatcher(Application application, RefWatcher refWatcher) {
        this.application = checkNotNull(application, "application");
        this.refWatcher = checkNotNull(refWatcher, "refWatcher");
    }

    private Application.ActivityLifecycleCallbacks mLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LogUtils.d(TAG, "====>onActivityCreated");
            // 在Activity启动的时候注册fragment的监听
            FragmentRefWatcher.installOnIcsPlus(activity, refWatcher);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogUtils.d(TAG, "====>onActivityStarted");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            LogUtils.d(TAG, "====>onActivityResumed");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            LogUtils.d(TAG, "====>onActivityPaused");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            LogUtils.d(TAG, "====>onActivityStopped");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            LogUtils.d(TAG, "====>onActivitySaveInstanceState");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogUtils.d(TAG, "====>onActivityDestroyed");
            refWatcher.watch(activity);
        }
    };

    public static void installOnIcsPlus(Application application, RefWatcher refWatcher) {
        ActivityRefWatcher activityRefWatcher = new ActivityRefWatcher(application, refWatcher);
        activityRefWatcher.watchAllActivities();
    }

    private void watchAllActivities() {
        stopWatchAllActivities();
        application.registerActivityLifecycleCallbacks(mLifecycleCallbacks);
    }

    private void stopWatchAllActivities() {
        application.unregisterActivityLifecycleCallbacks(mLifecycleCallbacks);
    }


}
