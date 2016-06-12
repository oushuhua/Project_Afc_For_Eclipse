package com.afc.db.entity;

import com.afc.db.dao.ChepaiDataDao;
import com.afc.db.dao.CityItemDao;
import com.afc.db.dao.DaoSession;

import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ChepaiData implements java.io.Serializable {

    private Long _id;
    private String provinceName;
    private String listCount;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ChepaiDataDao myDao;

    private List<CityItem> items;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public ChepaiData() {
    }

    public ChepaiData(Long _id) {
        this._id = _id;
    }

    public ChepaiData(Long _id, String provinceName, String listCount) {
        this._id = _id;
        this.provinceName = provinceName;
        this.listCount = listCount;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChepaiDataDao() : null;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getListCount() {
        return listCount;
    }

    public void setListCount(String listCount) {
        this.listCount = listCount;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<CityItem> getItems() {
        if (items == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CityItemDao targetDao = daoSession.getCityItemDao();
            List<CityItem> itemsNew = targetDao._queryChepaiData_Items(_id);
            synchronized (this) {
                if(items == null) {
                    items = itemsNew;
                }
            }
        }
        return items;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetItems() {
        items = null;
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

    public void setItems(List<CityItem> items) {
        this.items = items;
    }
}
