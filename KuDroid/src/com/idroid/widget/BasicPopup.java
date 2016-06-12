package com.idroid.widget;

import android.R.color;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * 
 * @author yiyi
 * @date 2015年8月20日
 */
public abstract class BasicPopup extends PopupWindow {
	private Context mContext;

	public BasicPopup(Context context) {
		this(context, ViewGroup.LayoutParams.MATCH_PARENT);
	}

	public BasicPopup(Context context, int height) {
		super(context);
		mContext = context;
		setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		setHeight(height);
		setTouchable(true);
		setOutsideTouchable(true);
		setFocusable(true);
		ColorDrawable cd = new ColorDrawable(color.transparent);
		setBackgroundDrawable(cd);
		setContentView();
	}

	private void setContentView() {
		setContentView(getView());
	}

	/**
	 * 类似于Adapter.getView,返回的view自动添加为ContentView
	 * 
	 * @return
	 */
	public abstract View getView();

	public Context getContext() {
		return mContext;
	}

}
