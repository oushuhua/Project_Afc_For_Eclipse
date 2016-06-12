package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class FootPrint implements java.io.Serializable {

    private Long _id;
    private String pid;
    private String name;
    private String img;
    private String price;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public FootPrint() {
    }

    public FootPrint(String pid, String name, String img, String price) {
        this.pid = pid;
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public FootPrint(Long _id, String pid, String name, String img, String price) {
        this._id = _id;
        this.pid = pid;
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
