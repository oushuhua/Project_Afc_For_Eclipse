package com.afc.db.entity;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class StringEntity implements java.io.Serializable {

    private Long _id;
    private String name;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public StringEntity() {
    }

    public StringEntity(Long _id) {
        this._id = _id;
    }

    public StringEntity(Long _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // KEEP METHODS - put your custom methods here
    @Override
    public int hashCode() {
        if (!TextUtils.isEmpty(name))
            return name.hashCode();
        return super.hashCode();
    }
    // KEEP METHODS END

}
