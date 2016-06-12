package com.squareup.okhttp;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbo on 2016/3/31.
 * 用于处理耗时较长的网络请求(文件上传与图片下载)
 */
public class LongTimeAuzHttp extends OkHttp {

    public static final OkHttpClient longTimeOkHttpClient = new OkHttpClient();//用于处理文件上传与图片下载的超时限制
    private static final String FIXED_AUZ = "Basic ";

    static {
        longTimeOkHttpClient.setConnectTimeout(55, TimeUnit.SECONDS);
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
        String auzStr = FIXED_AUZ + AuzHttp.SIG;
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
        String auzStr = FIXED_AUZ + AuzHttp.SIG;
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
        String auzStr = FIXED_AUZ + AuzHttp.SIG;
        Request request = commonHeaderBuilder().addHeader("Authorization", auzStr.replaceAll("\n", "")).url(reqUrl)
                .tag(tag).build();
        enqueue(request, responseCallback);
    }

    public static void enqueue(final Request request, RespondCallBack responseCallback) {
        longTimeOkHttpClient.newCall(request).enqueue(CommonCallback.obtain(responseCallback));
    }



}
