package com.idroid.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * 与版本有关的方法
 * @author yiyi
 * @date 2015年12月4日
 */
public class VerUtils {

	@SuppressLint("NewApi")
	public static void setBackgroundOfVersion(View view, Drawable drawable) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			// Android系统大于等于API16，使用setBackground
			view.setBackground(drawable);
		} else {
			// Android系统小于API16，使用setBackground
			view.setBackgroundDrawable(drawable);
		}
	}
}
