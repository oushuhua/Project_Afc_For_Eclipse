package com.squareup.okhttp;

import java.io.Serializable;

/**
 * 接口返回json实体
 * 
 * @author yiyi
 * @date 2015年8月17日
 */
public class Respond implements Serializable {

	private static final long serialVersionUID = -4613682933060337877L;

	private int code = -1;

	private String msg;

	private String IsSuccess;

	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIsSuccess() {
		return IsSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		IsSuccess = isSuccess;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isCorrect() {
		return code == 0 ? true : false;
	}

}
