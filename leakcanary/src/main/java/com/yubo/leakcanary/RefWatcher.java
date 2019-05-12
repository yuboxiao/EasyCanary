package com.yubo.leakcanary;

import com.yubo.log.LogUtils;

import static com.yubo.leakcanary.preconditions.checkNotNull;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 17:06
 */

public class RefWatcher {
    private static final String TAG = RefWatcher.class.getSimpleName();

    private AndroidWatchExecutor watchExecutor;

    public RefWatcher(AndroidWatchExecutor watchExecutor) {
        this.watchExecutor = checkNotNull(watchExecutor, "watchExecutor");
    }

    public void watch(Object watchedReference) {
        LogUtils.e(TAG, "====>watch when activity destroyed");
    }
}