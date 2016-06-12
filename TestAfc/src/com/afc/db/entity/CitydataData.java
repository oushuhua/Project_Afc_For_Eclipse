package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CitydataData implements java.io.Serializable {

    private Long _id;
    private String id;
    private String name;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public CitydataData() {
    }

    public CitydataData(Long _id) {
        this._id = _id;
    }

    public CitydataData(Long _id, String id, String name) {
        this._id = _id;
        this.id = id;
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
