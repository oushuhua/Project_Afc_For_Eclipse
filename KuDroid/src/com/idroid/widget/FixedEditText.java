package com.idroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * 左边有固定文字edittext
 * 
 * @author yiyi
 * @date 2015年8月21日
 */
public class FixedEditText extends EditText {
	private String fixedText;
	private View.OnClickListener mListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			setText(null);
		}
	};

	public FixedEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setFixedText(String text) {
		fixedText = text;
		int left = (int) getPaint().measureText(fixedText) + getPaddingLeft();
		setPadding(left, getPaddingTop(), getPaddingBottom(), getPaddingRight());
		invalidate();
	}

	public void setDrawableClk(View.OnClickListener listener) {
		mListener = listener;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!TextUtils.isEmpty(fixedText)) {
			canvas.drawText(fixedText, 10, (getMeasuredHeight() - getTextSize()) / 2 + getTextSize(), getPaint());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (mListener != null && getCompoundDrawables()[2] != null) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				int i = getMeasuredWidth() - getCompoundDrawables()[2].getIntrinsicWidth()
						- getCompoundDrawablePadding();
				if (event.getX() > i) {
					mListener.onClick(this);
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:

				break;
			default:
				break;
			}

		}

		return super.onTouchEvent(event);
	}

}
