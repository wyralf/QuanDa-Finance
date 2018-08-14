package com.tome.android.database;

import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by zhangyufei
 */
public class DaoSession extends AbstractDaoSession {
    public DaoSession(Database db) {
        super(db);
    }
}
