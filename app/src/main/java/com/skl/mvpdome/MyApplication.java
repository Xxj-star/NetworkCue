package com.skl.mvpdome;

import android.app.Application;


/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getContext() {
        return mApplication;
    }
}
