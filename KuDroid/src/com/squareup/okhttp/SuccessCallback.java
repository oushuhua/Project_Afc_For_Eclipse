package com.squareup.okhttp;

/**
 * 只是表示成功与否，不需要获取数据的接口返回
 * 
 * @author yiyi
 * @date 2015年8月19日
 */
public abstract class SuccessCallback extends RespondCallBack<Boolean> {

	@Override
	protected Boolean convert(String json) {
		return true;
	}
}
