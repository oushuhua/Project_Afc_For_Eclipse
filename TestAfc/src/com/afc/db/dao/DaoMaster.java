package com.afc.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

/**
 * Created by Administrator on 2016/5/5.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 9;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        KuMessageDao.createTable(db, ifNotExists);
        SortModelEntityDao.createTable(db, ifNotExists);
        BindingModelsJiLuDao.createTable(db, ifNotExists);
        CityEntityDao.createTable(db, ifNotExists);
        StringEntityDao.createTable(db, ifNotExists);
        SearchCollDao.createTable(db, ifNotExists);
        FootPrintDao.createTable(db, ifNotExists);
        SercodeDataDao.createTable(db, ifNotExists);
        WeatherDataDao.createTable(db, ifNotExists);
        ChepaiDataDao.createTable(db, ifNotExists);
        CityItemDao.createTable(db, ifNotExists);
        CitydataDataDao.createTable(db, ifNotExists);
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        KuMessageDao.dropTable(db, ifExists);
        SortModelEntityDao.dropTable(db, ifExists);
        BindingModelsJiLuDao.dropTable(db, ifExists);
        CityEntityDao.dropTable(db, ifExists);
        StringEntityDao.dropTable(db, ifExists);
        SearchCollDao.dropTable(db, ifExists);
        FootPrintDao.dropTable(db, ifExists);
        SercodeDataDao.dropTable(db, ifExists);
        WeatherDataDao.dropTable(db, ifExists);
        ChepaiDataDao.dropTable(db, ifExists);
        CityItemDao.dropTable(db, ifExists);
        CitydataDataDao.dropTable(db, ifExists);
    }

    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(KuMessageDao.class);
        registerDaoClass(SortModelEntityDao.class);
        registerDaoClass(BindingModelsJiLuDao.class);
        registerDaoClass(CityEntityDao.class);
        registerDaoClass(StringEntityDao.class);
        registerDaoClass(SearchCollDao.class);
        registerDaoClass(FootPrintDao.class);
        registerDaoClass(SercodeDataDao.class);
        registerDaoClass(WeatherDataDao.class);
        registerDaoClass(ChepaiDataDao.class);
        registerDaoClass(CityItemDao.class);
        registerDaoClass(CitydataDataDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

}
