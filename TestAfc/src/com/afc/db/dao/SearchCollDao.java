package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.SearchColl;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SearchCollDao extends AbstractDao<SearchColl, Long> {

    public static final String TABLENAME = "SEARCH_COLL";

    /**
     * Properties of entity SearchColl.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Item = new Property(0, String.class, "item", false, "ITEM");
        public final static Property Type = new Property(1, String.class, "type", false, "TYPE");
        public final static Property Id = new Property(2, Long.class, "id", true, "ID");
    };


    public SearchCollDao(DaoConfig config) {
        super(config);
    }

    public SearchCollDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEARCH_COLL\" (" + //
                "\"ITEM\" TEXT UNIQUE ," + // 0: item
                "\"TYPE\" TEXT," + // 1: type
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT );"); // 2: id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEARCH_COLL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SearchColl entity) {
        stmt.clearBindings();

        String item = entity.getItem();
        if (item != null) {
            stmt.bindString(1, item);
        }

        String type = entity.getType();
        if (type != null) {
            stmt.bindString(2, type);
        }

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(3, id);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2);
    }

    /** @inheritdoc */
    @Override
    public SearchColl readEntity(Cursor cursor, int offset) {
        SearchColl entity = new SearchColl( //
                cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // item
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // type
                cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // id
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SearchColl entity, int offset) {
        entity.setItem(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setType(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SearchColl entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(SearchColl entity) {
        if(entity != null) {
            return entity.getId();
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
