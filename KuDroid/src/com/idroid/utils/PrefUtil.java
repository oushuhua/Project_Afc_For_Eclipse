package com.idroid.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @author yiyi
 * @date 2015年8月28日
 */
public class PrefUtil {
	private static final String DEFAULT_PREF_NAME = "sharedpref";

	public static String getString(Context context, String name) {

		return context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE).getString(name, "");

	}

	public static void putString(Context context, String name, String value) {
		Editor editor = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE).edit();
		editor.putString(name, value);
		editor.commit();
	}

	public static boolean getBoolean(Context context, String name) {

		return context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE).getBoolean(name, false);

	}

	public static void putBoolean(Context context, String name, boolean value) {
		Editor editor = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE).edit();
		editor.putBoolean(name, value);
		editor.commit();
	}

}
