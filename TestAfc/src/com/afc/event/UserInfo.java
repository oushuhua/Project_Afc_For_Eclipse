package com.afc.event;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/5.
 */
public class UserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8987671298517305533L;
    private String uname;
    private String balance;
    private String userid;
    private String nickname, headpic;
    private String mobile;

    public UserInfo(String uname, String balance, String userid, String nickname, String headpic,String mobile) {
        this.uname = uname;
        this.balance = balance;
        this.userid = userid;
        this.nickname = nickname;
        this.headpic = headpic;
        this.mobile = mobile;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

	/*
	 * public UserInfo(String uname, String balance) { super(); this.uname =
	 * uname; this.balance = balance; }
	 */

}
