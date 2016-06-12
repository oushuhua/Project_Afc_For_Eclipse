package com.afc.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/22.
 */
public class productidEntity implements Serializable{

    private String proID;

    public productidEntity(){
        this.proID="";
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }
}
