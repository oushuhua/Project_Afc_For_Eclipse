package com.idroid.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 
 * @author yiyi
 * @date 2015年8月19日
 */
public class Toaster {
	private static Toast mToast;
	private static Handler mHandler;
	private static Context mContext;

	public static void show(Context context, final String msg) {
		if (mHandler == null) {
			mHandler = new Handler(Looper.getMainLooper());
			mContext = context.getApplicationContext();
		}
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mToast == null) {

					mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
					mToast.setGravity(Gravity.CENTER, 0, 0);
				} else {
					mToast.setText(msg);
					mToast.setDuration(Toast.LENGTH_SHORT);
				}
				mToast.show();

			}
		});

		// TextView text = new TextView(context);
		// text.setText(msg);
		// text.setBackgroundColor(Color.parseColor("#7f000000"));
		// text.setGravity(Gravity.CENTER);
		// text.setPadding(100, 40, 100, 40);
		// text.setTextColor(context.getResources().getColor(android.R.color.white));
		//
		// Toast toast = new Toast(context);
		// toast.setView(text);
		// toast.setDuration(Toast.LENGTH_SHORT);
		// toast.setMargin(0, 0.35f);
		// toast.setGravity(Gravity.CENTER, 0, 0);
		// toast.show();
	}

}
