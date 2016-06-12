package com.idroid.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 禁止child加载时自动滚动到底部
 * @author yiyi
 * @date 2015年9月24日
 */
public class NoAutoScrollView extends ScrollView {

	public NoAutoScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
		return 0;
	}
}
