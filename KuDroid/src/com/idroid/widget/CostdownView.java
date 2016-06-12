package com.idroid.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/**
 * 带下划线的价格view
 * 
 * @author yiyi
 * @date 2015年8月18日
 */
public class CostdownView extends View {
	private final int Color = android.graphics.Color.parseColor("#999999");
	private final String Dollar = "\u00a5";
	private String price = "";
	private Paint mPaint;
	private Rect mTextBound;

	public CostdownView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color);
		mPaint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTextBound = new Rect();
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setTextSize(int size) {
		mPaint.setTextSize(size);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int textWidth = (int) mPaint.measureText(Dollar + " " + price);
		if (!TextUtils.isEmpty(price))
			mPaint.getTextBounds(price, 0, price.length(), mTextBound);
		setMeasuredDimension(textWidth, mTextBound.height());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawText(Dollar + "" + price, 0, getMeasuredHeight(), mPaint);
		// canvas.drawText(Dollar, 0, getMeasuredHeight(), mPaint);
		// canvas.drawText(price, mPaint.getTextSize() * 3 / 4,
		// getMeasuredHeight(), mPaint);

	}

}
