package com.yubo.leakcanary;

/**
 * 作者: yubo.xiaoyubo
 * 日期: 2019/3/10 19:44
 */

public class preconditions {

    public static <T> T checkNotNull(T instance, String name) {
        if (instance == null) {
            throw new NullPointerException(name + " must not be null");
        }
        return instance;
    }
}