package com.squareup.okhttp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.idroid.BuildConfig;
import com.lidroid.util.Logger;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

/**
 * 
 * @author yiyi
 * @date 2015年11月2日
 */

public class OkHttp {
	public static final OkHttpClient sOkHttpClient = new OkHttpClient();       //用于普通请求时的超时限制(默认25s)
	public static final MediaType ContentType = MediaType.parse("application/json; charset=utf-8");
	public static String version = "1";
	static Handler uiHandler;


	static {
		sOkHttpClient.setConnectTimeout(25, TimeUnit.SECONDS);
		uiHandler = new Handler(Looper.getMainLooper());
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
		String toJson = createBodyJson(url, params);
		post(url, toJson, responseCallback, tag);
	}

	/**
	 * 参数转化为json
	 */
	private static String createBodyJson(String url, Params params) {
		String toJson = JSON.toJSONString(params.list());
		if (BuildConfig.DEBUG) {
			Logger.i(url + "\n" + toJson);
		}
		return toJson;
	}

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
		RequestBody body = RequestBody.create(ContentType, json);
		Request request = commonHeaderBuilder().url(url).tag(tag).post(body).build();
		enqueue(request, responseCallback);
	}

	/**
	 * 初始化header
	 * 
	 */
	public static Request.Builder commonHeaderBuilder() {
		return new Request.Builder().header("X-Message-Sender", "Afc-Web-API").addHeader("Terminal", "1")
				.addHeader("AppVersion", version);
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
		Request request = commonHeaderBuilder().url(reqUrl).tag(tag).build();
		enqueue(request, responseCallback);
	}

	public final static String buildBodyJson(String url, Params params) {
		String toJson = createBodyJson(url, params);
		return toJson;
	}

	/**
	 * url添加参数
	 * 
	 */
	public final static String paramsUrl(String url, Params params) {
		String reqUrl = url;
		if (params != null) {
			Set<Entry<String, Object>> tempSet = params.list().entrySet();
			List<BasicNameValuePair> tempList = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, Object> temp : tempSet) {
				if (!TextUtils.isEmpty(temp.getKey()) && temp.getValue() != null)
					tempList.add(new BasicNameValuePair(temp.getKey(), temp.getValue().toString()));
			}
			reqUrl = attachHttpGetParams(url, tempList);

		}
		if (BuildConfig.DEBUG) {
			Logger.i(reqUrl);
		}
		return reqUrl;
	}

	/**
	 * 开启异步线程访问网络
	 * 
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(final Request request, RespondCallBack responseCallback) {
		sOkHttpClient.newCall(request).enqueue(CommonCallback.obtain(responseCallback));
	}

	private static final String CHARSET_NAME = "UTF-8";

	/**
	 * 这里使用了HttpClinet的API。只是为了方便
	 * 
	 * @param params
	 * @return
	 */
	public static String formatParams(List<BasicNameValuePair> params) {
		return URLEncodedUtils.format(params, CHARSET_NAME);
	}

	/**
	 * 为HttpGet 的 url 方便的添加多个name value 参数。
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String attachHttpGetParams(String url, List<BasicNameValuePair> params) {
		return url + "?" + formatParams(params);
	}

	/**
	 * 为HttpGet 的 url 方便的添加1个name value 参数。
	 * 
	 * @param url
	 * @param name
	 * @param value
	 * @return
	 */
	public static String attachHttpGetParam(String url, String name, String value) {
		return url + "?" + name + "=" + value;
	}

}
