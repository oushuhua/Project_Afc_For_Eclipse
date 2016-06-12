package com.afc.db.entity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class SearchColl implements java.io.Serializable {

    private String item;
    private String type;
    private Long id;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public SearchColl() {
    }

    public SearchColl(Long id) {
        this.id = id;
    }

    public SearchColl(String item, String type, Long id) {
        this.item = item;
        this.type = type;
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
