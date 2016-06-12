package com.idroid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.trinea.android.common.util.TimeUtils;

public class ITimeUtils {

	public static String parse(String timeStr) {
		if (timeStr == null)
			return null;
		try {
			Date yourUtcDate = TimeUtils.DEFAULT_DATE_FORMAT.parse(timeStr);
			return parse(yourUtcDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化时间
	 * 
	 * @param time
	 * @return
	 */
	public static String parse(long time) {
		if (time <= 0) {
			return "";
		}
		Date date = new Date(time);

		Calendar current = Calendar.getInstance();

		Calendar today = Calendar.getInstance(); // 今天

		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		Calendar yesterday = Calendar.getInstance(); // 昨天

		yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
		yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
		yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
		yesterday.set(Calendar.HOUR_OF_DAY, 0);
		yesterday.set(Calendar.MINUTE, 0);
		yesterday.set(Calendar.SECOND, 0);

		current.setTime(date);

		if (current.after(today)) {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
			String date1 = sDateFormat.format(new Date(time));
			return "今天" + date1;
		} else if (current.before(today) && current.after(yesterday)) {
			SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
			String date1 = sDateFormat.format(new Date(time));
			return "昨天" + date1;
		} else {
			if (current.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
				SimpleDateFormat sDateFormat = new SimpleDateFormat("MM/dd HH:mm", Locale.CHINA);
				String date1 = sDateFormat.format(new Date(time));
				return date1;
			}
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CHINA);
			String date1 = sDateFormat.format(new Date(time));
			return date1;
		}
	}

	/**
	 * 转换为：通话109'50"
	 * 
	 * @param time
	 * @return
	 */
	public static final String parseCallTime(long time) {
		long minutes = (time % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (time % (1000 * 60)) / 1000;
		if (minutes == 0)
			return "通话 " + seconds + "\"";
		return "通话 " + minutes + "'" + seconds + "\"";
	}

}
