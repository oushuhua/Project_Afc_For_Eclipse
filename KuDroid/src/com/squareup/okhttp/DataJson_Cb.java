package com.squareup.okhttp;

import android.text.TextUtils;

/**
 * 直接返回returnData部分
 * 
 * @author yiyi
 * @date 2015年8月29日
 */
public abstract class DataJson_Cb extends RespondCallBack<String> {
	@Override
	protected String convert(String json) {
		if (TextUtils.isEmpty(json))
			return null;
		return json;
	}
}
