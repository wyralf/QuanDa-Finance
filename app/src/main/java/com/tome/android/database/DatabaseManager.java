package com.tome.android.database;

import android.content.Context;

/**
 * Created by zhangyufei
 */
public class DatabaseManager {
    public static final String TAG = "DatabaseManager";

    private static DatabaseManager sInstance;
    private DaoSession mDaoSession;
    public static DatabaseManager getInstance() {
        if (sInstance == null) {
            synchronized (DatabaseManager.class) {
                if (sInstance == null) {
                    sInstance = new DatabaseManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {

    }
}
