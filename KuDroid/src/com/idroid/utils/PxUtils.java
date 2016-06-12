package com.idroid.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 
 * @author yiyi
 * @date 2015年11月26日
 */
public class PxUtils {
	public static int dp2px(int dp, Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

	public static int px2dp(int px, Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px,
				context.getResources().getDisplayMetrics());
	}
}
