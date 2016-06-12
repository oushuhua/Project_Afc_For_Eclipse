package com.afc.Utils;

import android.content.Context;
import android.text.TextUtils;

import com.afc.App.DbMgr;
import com.afc.App.JPushSet;
import com.afc.db.entity.BindingModelsJiLu;
import com.afc.event.ETag;
import com.afc.event.NullEvent;
import com.afc.event.UserInfo;
import com.idroid.async.AsyncWorker;

import org.simple.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import cn.trinea.android.common.util.PreferencesUtils;

/**
 * 账号相关
 * Created by Administrator on 2016/5/5.
 */
public class AccountMgr extends PreferencesUtils {

    /**
     * 用户登陆成功时触发
     *
     * @param context
     */
    public static void loginSuccess(Context context) {
        JPushSet.setAliasAndTags(context);
    }

    /**
     * 是否登录
     *
     * @param context
     * @return
     */
    public static boolean isLogon(Context context) {
        String mid = getMemberId(context);
        if (TextUtils.isEmpty(mid))
            return false;
        return true;
    }

    /**
     * 注销时的操作
     *
     * @param context
     */
    public static void logout(final Context context) {
        PreferencesUtils.putString(context, PrefKey.SelCarId, null);
        JPushSet.reset(context);
        JPushInterface.clearAllNotifications(context.getApplicationContext());
        //PswdEncrypt.remove(context); IM相关代码操作
        removeUser(context);
        EventBus.getDefault().post(new NullEvent(), ETag.ETag_Logout);
        if (DbMgr.daoSession != null)
            DbMgr.daoSession.startAsyncSession().deleteAll(BindingModelsJiLu.class);
        AsyncWorker.execute(new Runnable() {
            @Override
            public void run() {
                JPushInterface.stopPush(context.getApplicationContext());
            }
        });
    }

    /**
     * 获取个人昵称，没有时用账号
     *
     * @param context
     * @return
     */
    public static String getNickName(Context context) {
        String nickname = getString(context, Const.NICKNAME);
        if (TextUtils.isEmpty(nickname))
            nickname = getUName(context);
        return nickname;
    }


    public static String getMobile(Context context) {
        return getString(context, Const.MOBILE);
    }

    public static String getHeadPic(Context context) {
        return getString(context, Const.HEADPIC);
    }

    public static void putUser(Context context, UserInfo us) {
        putString(context, Const.USERNAME, us.getUname());
        putString(context, Const.BALANCE, us.getBalance());
        putString(context, Const.USER_ID, us.getUserid());
        putString(context, Const.NICKNAME, us.getNickname());
        putString(context, Const.HEADPIC, us.getHeadpic());
        putString(context,Const.MOBILE,us.getMobile());
    }

    public static void removeUser(Context context) {
        putString(context, Const.BALANCE, null);
        putString(context, Const.USER_ID, null);
        putString(context, Const.NICKNAME, null);
        putString(context, Const.HEADPIC, null);
        putString(context, Const.AUZ_JOIN, null);
        putString(context,Const.MOBILE,null);
    }

    public static String getUName(Context context) {
        return getString(context, Const.USERNAME);
    }

    public static String getMemberId(Context context) {
        return getString(context, Const.USER_ID);
    }

    public static UserInfo getUser(Context context) {
        String usName = getString(context, Const.USERNAME);
        String usBalance = getString(context, Const.BALANCE);
        String uid = getString(context, Const.USER_ID);
        String usNick = getString(context, Const.NICKNAME);
        String ushead = getString(context, Const.HEADPIC);
        String mobile = getString(context, Const.MOBILE);
        UserInfo us = new UserInfo(usName, usBalance, uid, usNick, ushead, mobile);
        return us;
    }

    public static String getAuzStr(Context context) {
        return getString(context, Const.AUZ_JOIN);
    }

    public static void putAuz(Context context, String value) {
        putString(context, Const.AUZ_JOIN, value);
    }

    public static final class Const {
        // 账号名
        public final static String USERNAME = "uname";

        public final static String BALANCE = "balance";

        // 用户id
        public final static String USER_ID = "uid";

        // 需登录操作的接口的有效凭证
        private final static String AUZ_JOIN = "auz_join";

        // 昵称
        public final static String NICKNAME = "nickname";

        // 头像地址
        public final static String HEADPIC = "headpic";

        //联系电话
        public final static String MOBILE = "mobile";

    }

}
