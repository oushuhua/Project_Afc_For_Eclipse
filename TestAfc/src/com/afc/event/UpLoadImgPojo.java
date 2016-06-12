package com.afc.event;

import java.io.Serializable;

public class UpLoadImgPojo implements Serializable {

    private String pid;// 1
    private boolean isSuccess;// true,
    private String mesg;
    private String name;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullPath() {
        return name;
    }

    public void setFullPath(String fullPath) {
        this.name = fullPath;
    }
}
