package com.afc.event;

import java.io.Serializable;

/**
 * 会员注册信息上传数据实体
 * Created by Administrator on 2016/5/15.
 */
public class RegisterEntity implements Serializable {

    private String name;
    private String UserName;
    private String PassWord;
    private String NickName;
    private float Longitude;
    private float Latitude;
    private String RefPerson;
    private String VerifyCode;
    private String HeadPicture;


    public RegisterEntity(){
        this.name="";
        this.UserName="";
        this.PassWord="";
        this.NickName="";
        this.Longitude=0;
        this.Latitude=0;
        this.RefPerson="";
        this.VerifyCode="";
        this.HeadPicture="";
    }

    public String getHeadPicture() {
        return HeadPicture;
    }

    public void setHeadPicture(String headPicture) {
        HeadPicture = headPicture;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        Longitude = longitude;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        Latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }


    public String getRefPerson() {
        return RefPerson;
    }

    public void setRefPerson(String refPerson) {
        RefPerson = refPerson;
    }

    public String getVerifyCode() {
        return VerifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        VerifyCode = verifyCode;
    }
}
