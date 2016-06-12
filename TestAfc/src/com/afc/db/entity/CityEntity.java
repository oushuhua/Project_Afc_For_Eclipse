package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class CityEntity implements java.io.Serializable {

    private Long _id;
    private Integer id;
    private Integer bid;
    private String name;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public CityEntity() {
    }

    public CityEntity(Long _id) {
        this._id = _id;
    }

    public CityEntity(Long _id, Integer id, Integer bid, String name) {
        this._id = _id;
        this.id = id;
        this.bid = bid;
        this.name = name;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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
