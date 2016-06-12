package com.afc.App;

import android.content.Context;

import com.afc.db.dao.DaoMaster;
import com.afc.db.dao.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/5.
 */
public class DbMgr {

    private static final String DEFAULT_dbName = "kucar.db";
    private static DaoMaster daoMaster;
    public static DaoSession daoSession;

    /**
     * 在Application类中初始化
     *
     * @param context
     */
    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        init(context, DEFAULT_dbName);
        enableQueryBuilderLog();
    }

    private static void init(Context context, String dbName) {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    /**
     * DB操作日志打印的开关
     */
    static void enableQueryBuilderLog() {
        if (Host.DEBUG) {
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }
}
