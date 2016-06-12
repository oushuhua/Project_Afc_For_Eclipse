package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.CityEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityEntityDao extends AbstractDao<CityEntity, Long> {

    public static final String TABLENAME = "CITY_ENTITY";

    /**
     * Properties of entity CityEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_ID");
        public final static Property Id = new Property(1, Integer.class, "id", false, "ID");
        public final static Property Bid = new Property(2, Integer.class, "bid", false, "BID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
    };


    public CityEntityDao(DaoConfig config) {
        super(config);
    }

    public CityEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY_ENTITY\" (" + //
                "\"_ID\" INTEGER PRIMARY KEY ," + // 0: _id
                "\"ID\" INTEGER," + // 1: id
                "\"BID\" INTEGER," + // 2: bid
                "\"NAME\" TEXT);"); // 3: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CityEntity entity) {
        stmt.clearBindings();

        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }

        Integer id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }

        Integer bid = entity.getBid();
        if (bid != null) {
            stmt.bindLong(3, bid);
        }

        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /** @inheritdoc */
    @Override
    public CityEntity readEntity(Cursor cursor, int offset) {
        CityEntity entity = new CityEntity( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
                cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // id
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // bid
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // name
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CityEntity entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setBid(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CityEntity entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(CityEntity entity) {
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
