package com.squareup.okhttp;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author yiyi
 * @date 2015年11月2日
 */
public class Params {

	private JSONObject bodyParams;

	public Params() {
		this.bodyParams = new JSONObject();
	}

	public void add(String name, Object value) {
		bodyParams.put(name, value);
	}

	public JSONObject list() {
		return bodyParams;
	}

}
