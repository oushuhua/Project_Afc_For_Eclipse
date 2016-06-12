package com.squareup.okhttp;

import android.text.TextUtils;

/**
 * 
 * @author yiyi
 * @date 2015年11月16日
 */
public class AuzHttp extends OkHttp {
	private static final String FIXED_AUZ = "Basic ";
	public static String SIG = "";

    /**
     * 直接封装好的json做为参数
     *
     * @param url
     * @param json
     * @param responseCallback
     * @param tag
     */
    public static void post(String url, String json, RespondCallBack responseCallback, String tag) {
        if (TextUtils.isEmpty(json))
            return;
        String auzStr = FIXED_AUZ + SIG;
        RequestBody body = RequestBody.create(ContentType, json);
        Request request = commonHeaderBuilder().addHeader("Authorization", auzStr.replaceAll("\n", "")).url(url).tag(tag).post(body).build();
        enqueue(request, responseCallback);
    }


    /**
	 * Post方式发起请求
	 * 
	 * @param url
	 * @param params
	 * @param responseCallback
	 */
	public static void post(String url, Params params, RespondCallBack responseCallback, String tag) {
		if (params == null) {
			params = new Params();
		}
		String auzStr = FIXED_AUZ + SIG;
		String toJson = buildBodyJson(url, params);
		RequestBody body = RequestBody.create(ContentType, toJson);
		Request request = commonHeaderBuilder().addHeader("Authorization", auzStr.replaceAll("\n", "")).url(url)
				.tag(tag).post(body).build();
		enqueue(request, responseCallback);
	}

	/**
	 * Get方式发送请求
	 * 
	 * @param url
	 * @param params
	 * @param responseCallback
	 */
	public static void get(String url, Params params, RespondCallBack responseCallback, String tag) {
		String reqUrl = paramsUrl(url, params);
		String auzStr = FIXED_AUZ + SIG;
		Request request = commonHeaderBuilder().addHeader("Authorization", auzStr.replaceAll("\n", "")).url(reqUrl)
				.tag(tag).build();
		enqueue(request, responseCallback);
	}

}
