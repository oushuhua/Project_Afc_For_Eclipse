package com.idroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 嵌套时使用
 * 
 * @author yiyi
 * @date 2015年9月9日
 */
public class MeasureListView extends ListView {

	public MeasureListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MeasureListView(Context context) {
		super(context);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
