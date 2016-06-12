package com.afc.db.entity;

import com.afc.db.dao.ChepaiDataDao;
import com.afc.db.dao.CityItemDao;
import com.afc.db.dao.DaoSession;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoException;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityItem implements java.io.Serializable {

    private Long _cid;
    private String cityName;
    private String no;
    private String code;
    private long _id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient CityItemDao myDao;

    private ChepaiData chepaiData;
    private Long chepaiData__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public CityItem() {
    }

    public CityItem(Long _cid) {
        this._cid = _cid;
    }

    public CityItem(Long _cid, String cityName, String no, String code, long _id) {
        this._cid = _cid;
        this.cityName = cityName;
        this.no = no;
        this.code = code;
        this._id = _id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityItemDao() : null;
    }

    public Long get_cid() {
        return _cid;
    }

    public void set_cid(Long _cid) {
        this._cid = _cid;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    /** To-one relationship, resolved on first access. */
    public ChepaiData getChepaiData() {
        long __key = this._id;
        if (chepaiData__resolvedKey == null || !chepaiData__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChepaiDataDao targetDao = daoSession.getChepaiDataDao();
            ChepaiData chepaiDataNew = targetDao.load(__key);
            synchronized (this) {
                chepaiData = chepaiDataNew;
                chepaiData__resolvedKey = __key;
            }
        }
        return chepaiData;
    }

    public void setChepaiData(ChepaiData chepaiData) {
        if (chepaiData == null) {
            throw new DaoException("To-one property '_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.chepaiData = chepaiData;
            _id = chepaiData.get_id();
            chepaiData__resolvedKey = _id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
