package com.squareup.okhttp;

/**
 * 账号异常的消息通知
 * Created by yi on 2016/4/5.
 */
public class AuzEvent {
    private int code;//异常代码
    private String msg;

    public AuzEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
