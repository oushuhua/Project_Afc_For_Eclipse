package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.ChepaiData;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ChepaiDataDao extends AbstractDao<ChepaiData, Long> {

    public static final String TABLENAME = "CHEPAI_DATA";

    /**
     * Properties of entity ChepaiData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property ProvinceName = new Property(1, String.class, "provinceName", false, "PROVINCE_NAME");
        public final static Property ListCount = new Property(2, String.class, "listCount", false, "LIST_COUNT");
    };

    private DaoSession daoSession;


    public ChepaiDataDao(DaoConfig config) {
        super(config);
    }

    public ChepaiDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHEPAI_DATA\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"PROVINCE_NAME\" TEXT," + // 1: provinceName
                "\"LIST_COUNT\" TEXT);"); // 2: listCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHEPAI_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ChepaiData entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }

        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(2, provinceName);
        }

        String listCount = entity.getListCount();
        if (listCount != null) {
            stmt.bindString(3, listCount);
        }
    }

    @Override
    protected void attachEntity(ChepaiData entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public ChepaiData readEntity(Cursor cursor, int offset) {
        ChepaiData entity = new ChepaiData( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // provinceName
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // listCount
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ChepaiData entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProvinceName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setListCount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ChepaiData entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(ChepaiData entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}
