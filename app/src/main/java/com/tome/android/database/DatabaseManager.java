package com.tome.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zhangyufei
 */
public class DatabaseManager {
    public static final String TAG = "DatabaseManager";

    private static DatabaseManager sInstance;
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
