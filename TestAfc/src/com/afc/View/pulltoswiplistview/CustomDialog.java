package com.afc.View.pulltoswiplistview;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.afc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 统一对话框
 * @author yiyi
 * @date 2015年10月13日
 */
public class CustomDialog {
	@Bind(R.id.msg_text)
	TextView mMsgText;
	@Bind(R.id.cancel_btn)
	TextView mCancelBtn;
	@Bind(R.id.ok_btn)
	TextView mOkBtn;
	@Bind(R.id.msg_title)
	TextView mTitleBtn;
	private AlertDialog dialog;

	/**
	 * 
	 * @param context
	 * @param title
	 *            对话框内容
	 * @param l
	 *            确定键点击事件
	 * @return
	 */
	public CustomDialog create(Context context, String title, View.OnClickListener l) {
		dialog = new AlertDialog.Builder(context).create();
		View root = View.inflate(context, R.layout.alert_main, null);
		ButterKnife.bind(this, root);
		dialog.show();
		dialog.getWindow().setContentView(root);
		mMsgText.setText(title);
		mOkBtn.setOnClickListener(l);
		mCancelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});
		return this;
	}

	public void show() {
		if (dialog != null)
			dialog.show();
	}

	public void dismiss() {
		if (dialog != null)
			dialog.dismiss();
	}

	/**
	 * 设置为底部单按钮样式
	 * @param isSingleBottom 为true时生效
	 * @param titleText
	 * @param bottomText
	 * @return
	 */
	public CustomDialog setSingleBtnBottom(boolean isSingleBottom,String titleText,String bottomText){
		if(isSingleBottom){
			mTitleBtn.setVisibility(View.VISIBLE);
			mCancelBtn.setVisibility(View.GONE);
			mTitleBtn.setText(titleText);
			mOkBtn.setText(bottomText);
		}
		return this;
	}

}
