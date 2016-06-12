package com.afc.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ResultLogin implements Serializable {

    private String pid;
    private String CreateTime;
    private String ModifyTime;


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }
}
