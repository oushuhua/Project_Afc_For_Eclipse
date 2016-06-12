package com.afc.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.afc.db.entity.ChepaiData;
import com.afc.db.entity.CityItem;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityItemDao extends AbstractDao<CityItem, Long> {

    public static final String TABLENAME = "CITY_ITEM";

    /**
     * Properties of entity CityItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _cid = new Property(0, Long.class, "_cid", true, "_CID");
        public final static Property CityName = new Property(1, String.class, "cityName", false, "CITY_NAME");
        public final static Property No = new Property(2, String.class, "no", false, "NO");
        public final static Property Code = new Property(3, String.class, "code", false, "CODE");
        public final static Property _id = new Property(4, long.class, "_id", false, "_ID");
    };

    private DaoSession daoSession;

    private Query<CityItem> chepaiData_ItemsQuery;

    public CityItemDao(DaoConfig config) {
        super(config);
    }

    public CityItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY_ITEM\" (" + //
                "\"_CID\" INTEGER PRIMARY KEY ," + // 0: _cid
                "\"CITY_NAME\" TEXT," + // 1: cityName
                "\"NO\" TEXT," + // 2: no
                "\"CODE\" TEXT," + // 3: code
                "\"_ID\" INTEGER NOT NULL );"); // 4: _id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY_ITEM\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CityItem entity) {
        stmt.clearBindings();

        Long _cid = entity.get_cid();
        if (_cid != null) {
            stmt.bindLong(1, _cid);
        }

        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(2, cityName);
        }

        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(3, no);
        }

        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(4, code);
        }
        stmt.bindLong(5, entity.get_id());
    }

    @Override
    protected void attachEntity(CityItem entity) {
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
    public CityItem readEntity(Cursor cursor, int offset) {
        CityItem entity = new CityItem( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _cid
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // cityName
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // no
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // code
                cursor.getLong(offset + 4) // _id
        );
        return entity;
    }

    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CityItem entity, int offset) {
        entity.set_cid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCityName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.set_id(cursor.getLong(offset + 4));
    }

    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CityItem entity, long rowId) {
        entity.set_cid(rowId);
        return rowId;
    }

    /** @inheritdoc */
    @Override
    public Long getKey(CityItem entity) {
        if(entity != null) {
            return entity.get_cid();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    /** Internal query to resolve the "items" to-many relationship of ChepaiData. */
    public List<CityItem> _queryChepaiData_Items(long _id) {
        synchronized (this) {
            if (chepaiData_ItemsQuery == null) {
                QueryBuilder<CityItem> queryBuilder = queryBuilder();
                queryBuilder.where(Properties._id.eq(null));
                chepaiData_ItemsQuery = queryBuilder.build();
            }
        }
        Query<CityItem> query = chepaiData_ItemsQuery.forCurrentThread();
        query.setParameter(0, _id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getChepaiDataDao().getAllColumns());
            builder.append(" FROM CITY_ITEM T");
            builder.append(" LEFT JOIN CHEPAI_DATA T0 ON T.\"_ID\"=T0.\"_ID\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected CityItem loadCurrentDeep(Cursor cursor, boolean lock) {
        CityItem entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ChepaiData chepaiData = loadCurrentOther(daoSession.getChepaiDataDao(), cursor, offset);
        if(chepaiData != null) {
            entity.setChepaiData(chepaiData);
        }

        return entity;
    }

    public CityItem loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<CityItem> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<CityItem> list = new ArrayList<CityItem>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    protected List<CityItem> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<CityItem> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

}
