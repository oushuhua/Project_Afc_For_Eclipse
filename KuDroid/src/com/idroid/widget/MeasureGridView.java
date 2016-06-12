package com.idroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 嵌套时使用
 * @author yiyi
 * @date 2015年8月19日
 */
public class MeasureGridView extends GridView {
	
	public MeasureGridView(Context context) {
		super(context);
	}

	public MeasureGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
