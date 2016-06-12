package com.afc.App;

import android.content.Context;
import android.text.TextUtils;

import com.afc.Utils.AccountMgr;
import com.lidroid.util.Logger;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Administrator on 2016/5/5.
 */
public class JPushSet {

    private static boolean bTagSetted = false;

    /**
     * 设置别名与tag，将根据host来判断内网外网处理tag，外网不设置tag
     *
     * @param ctx
     */
    public static void setAliasAndTags(Context ctx) {
        final Context context = ctx.getApplicationContext();
        final String memberId = AccountMgr.getMemberId(ctx);
        if (bTagSetted || !AccountMgr.isLogon(context) || TextUtils.isEmpty(memberId))
            return;

        final String host = Host.HOST;
        if (host.contains("kuparts")) {
            Set<String> set = new HashSet<String>();
            set.add("Production");
            JPushInterface.setAliasAndTags(context, memberId, set, new TagAliasCallback() {

                @Override
                public void gotResult(int arg0, String arg1, Set<String> arg2) {
                    if (arg0 == 0) {
                        bTagSetted = true;
                        Logger.i("别名设置成功：" + arg1);
                    } else {
                        Logger.i("别名设置失败");
                    }

                }
            });
        } else {
            Set<String> set = new HashSet<String>();
            set.add("Development");
            JPushInterface.setAliasAndTags(context, memberId, set, new TagAliasCallback() {

                @Override
                public void gotResult(int arg0, String arg1, Set<String> arg2) {
                    if (arg0 == 0) {
                        bTagSetted = true;
                        Logger.i("别名设置成功：" + arg1);
                    } else {
                        Logger.i("别名设置失败");
                    }

                }
            });
        }

    }

    /**
     * 退出登陆时调用
     * @param context
     */
    public static void reset(Context context) {
        bTagSetted = false;
    }
}
