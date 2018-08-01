package com.tome.android.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by zhangyufei
 */
public class QDApplication extends Application{
    public static final String TAG = "QDApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

    }
}
