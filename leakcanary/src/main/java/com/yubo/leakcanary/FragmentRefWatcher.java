package com.yubo.leakcanary;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.yubo.log.LogUtils;

import static com.yubo.leakcanary.preconditions.checkNotNull;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 20:02
 */

public class FragmentRefWatcher {
    private static final String TAG = FragmentRefWatcher.class.getSimpleName();

    private Activity activity;

    private RefWatcher refWatcher;

    public FragmentRefWatcher(Activity activity, RefWatcher refWatcher) {
        this.activity = checkNotNull(activity, "activity");
        this.refWatcher = checkNotNull(refWatcher, "refWatcher");
    }

    public static void installOnIcsPlus(Activity activity, RefWatcher refWatcher) {
        FragmentRefWatcher fragmentRefWatcher = new FragmentRefWatcher(activity, refWatcher);
        fragmentRefWatcher.watchAllFragments();
    }

    private FragmentManager.FragmentLifecycleCallbacks mLifecycleCallbacks
            = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentPreAttached(fm, f, context);
            LogUtils.d(TAG, "====>onFragmentPreAttached");
        }

        @Override
        public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentAttached(fm, f, context);
            LogUtils.d(TAG, "====>onFragmentAttached");
        }

        @Override
        public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentPreCreated(fm, f, savedInstanceState);
            LogUtils.d(TAG, "====>onFragmentPreCreated");
        }

        @Override
        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentCreated(fm, f, savedInstanceState);
            LogUtils.d(TAG, "====>onFragmentCreated");
        }

        @Override
        public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentActivityCreated(fm, f, savedInstanceState);
            LogUtils.d(TAG, "====>onFragmentActivityCreated");
        }

        @Override
        public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            LogUtils.d(TAG, "====>onFragmentViewCreated");
        }

        @Override
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            super.onFragmentStarted(fm, f);
            LogUtils.d(TAG, "====>onFragmentStarted");
        }

        @Override
        public void onFragmentResumed(FragmentManager fm, Fragment f) {
            super.onFragmentResumed(fm, f);
            LogUtils.d(TAG, "====>onFragmentResumed");
        }

        @Override
        public void onFragmentPaused(FragmentManager fm, Fragment f) {
            super.onFragmentPaused(fm, f);
            LogUtils.d(TAG, "====>onFragmentPaused");
        }

        @Override
        public void onFragmentStopped(FragmentManager fm, Fragment f) {
            super.onFragmentStopped(fm, f);
            LogUtils.d(TAG, "====>onFragmentStopped");
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
            super.onFragmentSaveInstanceState(fm, f, outState);
            LogUtils.d(TAG, "====>onFragmentSaveInstanceState");
        }

        @Override
        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
            LogUtils.d(TAG, "====>onFragmentViewDestroyed");
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentDestroyed(fm, f);
            LogUtils.d(TAG, "====>onFragmentDestroyed");
            refWatcher.watch(this);
        }

        @Override
        public void onFragmentDetached(FragmentManager fm, Fragment f) {
            super.onFragmentDetached(fm, f);
            LogUtils.d(TAG, "====>onFragmentDetached");
        }
    };

    private void watchAllFragments() {
        stopWatchAllFragments();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.getFragmentManager().registerFragmentLifecycleCallbacks(mLifecycleCallbacks,
                    false);
        }
    }

    private void stopWatchAllFragments() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.getFragmentManager().unregisterFragmentLifecycleCallbacks(mLifecycleCallbacks);
        }
    }
}
