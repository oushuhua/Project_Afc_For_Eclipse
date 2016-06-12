package com.squareup.okhttp;

import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.idroid.BuildConfig;
import com.lidroid.util.Logger;

import android.text.TextUtils;

/**
 * 新的请求接口返回请继承该类
 *
 * @author yiyi
 * @date 2015年8月11日
 */
public abstract class RespondCallBack<T> {
    // json code != 0
    public static final int CODE_FALSE = -999;
    // json 不是规范的，参考Respond
    public static final int CODE_JSON_NOT_STANDARD = -998;
    // json 返回的实体数据部分解析出错
    public static final int CODE_JSON_RETURNDATA_PARSEERROR = -997;

    final void onResponse(String result) {
        if (BuildConfig.DEBUG) {
            Logger.d(result);
        }

        if (result == null) {
            onFailure(CODE_JSON_NOT_STANDARD, "数据返回不符合规范");
            return;
        }

        T data = null;
        try {
            Respond respond = JSON.parseObject(result, Respond.class);
            if (respond == null) {
                onFailure(CODE_JSON_NOT_STANDARD, "数据返回不符合规范");
            } else if (!respond.isCorrect()) {
                int code = respond.getCode();
                switch (code) {
                    case 10000:
                        Logger.e(respond.getMsg() + "\n" + result);
                        onFailure(code, "请求失败，接口异常");
                        break;
                    case 1006:
                        String msg = "你在别处修改了密码，请用新密码登录";
                        EventBus.getDefault().post(new AuzEvent(code, msg));
                        onFailure(code, msg);
                        break;
                    default:
                        onFailure(code, respond.getMsg());
                        break;
                }

            } else {
                data = convert(respond.getData());
                if (data == null) {
                    onFailure(CODE_JSON_RETURNDATA_PARSEERROR, "Json解析失败");
                } else {
                    try {
                        onSuccess(data);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            onFailure(CODE_JSON_RETURNDATA_PARSEERROR, "请求失败,请确保网络畅通");
            e.printStackTrace();
        }

    }

    protected abstract T convert(String json);

    public abstract void onSuccess(T data);

    public abstract void onFailure(int errorCode, String msg);

    /**
     * 获取特定部分的数据，比如"list"下的数据
     *
     * @param json
     * @param key
     * @return
     */
    public static final String get(String json, String key) {
        if (TextUtils.isEmpty(json))
            return null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
