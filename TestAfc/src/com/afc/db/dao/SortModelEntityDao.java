package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.SortModelEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SortModelEntityDao extends AbstractDao<SortModelEntity, Long> {
    public static final String TABLENAME = "SORT_MODEL_ENTITY";

    /**
     * Properties of entity SortModelEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
    };


    public SortModelEntityDao(DaoConfig config) {
        super(config);
    }

    public SortModelEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SORT_MODEL_ENTITY\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" TEXT," + // 1: id
                "\"NAME\" TEXT);"); // 2: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SORT_MODEL_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SortModelEntity entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }

        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public SortModelEntity readEntity(Cursor cursor, int offset) {
        SortModelEntity entity = new SortModelEntity( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // name
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SortModelEntity entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SortModelEntity entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(SortModelEntity entity) {
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
