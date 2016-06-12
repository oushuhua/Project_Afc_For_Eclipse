package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.FootPrint;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class FootPrintDao extends AbstractDao<FootPrint, Long> {

    public static final String TABLENAME = "FOOT_PRINT";

    /**
     * Properties of entity FootPrint.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Pid = new Property(1, String.class, "pid", false, "PID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Img = new Property(3, String.class, "img", false, "IMG");
        public final static Property Price = new Property(4, String.class, "price", false, "PRICE");
    };


    public FootPrintDao(DaoConfig config) {
        super(config);
    }

    public FootPrintDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FOOT_PRINT\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"PID\" TEXT UNIQUE ," + // 1: pid
                "\"NAME\" TEXT," + // 2: name
                "\"IMG\" TEXT," + // 3: img
                "\"PRICE\" TEXT);"); // 4: price
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FOOT_PRINT\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FootPrint entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }

        String pid = entity.getPid();
        if (pid != null) {
            stmt.bindString(2, pid);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }

        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(4, img);
        }

        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(5, price);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public FootPrint readEntity(Cursor cursor, int offset) {
        FootPrint entity = new FootPrint( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // pid
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // img
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // price
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FootPrint entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setImg(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(FootPrint entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(FootPrint entity) {
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
