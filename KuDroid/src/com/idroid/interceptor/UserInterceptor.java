package com.idroid.interceptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public abstract class UserInterceptor implements Interceptor {

	public void jumpToActivity(Context ctx, String target) {
		jumpToActivity(ctx, target, null, new Intent(ctx, getLoginClass()));
	}

	public void jumpToActivity(Context ctx, String target, Bundle params) {
		jumpToActivity(ctx, target, params, new Intent(ctx, getLoginClass()));
	}

	/**
	 * 
	 * @param ctx
	 * @param target
	 *            目标activity的action
	 * @param params
	 *            需要传给目标activity的参数
	 * @param loginIntent
	 *            带参数的指向登录页的intent
	 */
	public void jumpToActivity(Context ctx, String target, Bundle params, Intent loginIntent) {
		if (TextUtils.isEmpty(target) || loginIntent == null)
			throw new RuntimeException("No target activity.");

		JumpInvoker invoker = new JumpInvoker(target, params);
		if (logon(ctx)) {
			invoker.invoke(ctx);
		} else {
			loginIntent.putExtra(INVOKER, invoker);
			loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ctx.startActivity(loginIntent);
		}
	}

	/**
	 * @return 是否登录
	 */
	public abstract boolean logon(Context context);

	/**
	 * @return 登录Activity.class
	 */
	public abstract Class getLoginClass();

}