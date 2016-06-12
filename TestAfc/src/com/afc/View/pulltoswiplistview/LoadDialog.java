package com.afc.View.pulltoswiplistview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afc.R;


/**
 * 
 * @author yiyi
 * @date 2015年11月19日
 */
public class LoadDialog extends Dialog {

	private ImageView spaceshipImage;

	@SuppressLint("InflateParams")
	public LoadDialog(Context context, String msg) {
		super(context, R.style.loading_dialog);

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
		spaceshipImage = (ImageView) v.findViewById(R.id.img);
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		spaceshipImage.setImageResource(R.mipmap.load);
		// Glide.with(context.getApplicationContext()).load(R.drawable.loading)
		// .override(PxUtils.dp2px(50, context), PxUtils.dp2px(50,
		// context)).into(spaceshipImage);
		// tipTextView.setText(msg);// 设置加载信息
		tipTextView.setText(null);

		setCancelable(true);// 不可以用“返回键”取消
		setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局

	}

	@Override
	public void show() {
		spaceshipImage.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.load_animation));
		super.show();
	}

}
