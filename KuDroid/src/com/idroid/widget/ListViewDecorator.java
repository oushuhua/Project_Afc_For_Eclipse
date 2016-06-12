package com.idroid.widget;

import com.bumptech.glide.Glide;
import com.idroid.R;
import com.idroid.utils.PxUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;

/**
 * LswLoader loader = new LswLoader(mContentLsw);
 * loader.loading(R.drawable.ic_launcher, "正在加载...");
 */
/**
 * 给listview封装加载样式
 * 
 * @author yiyi
 * @date 2015年11月6日
 */
public class ListViewDecorator {
	private ImageView mLoadImage;
	private TextView mLoadText;
	private ListView mLoadLsw;

	public ListViewDecorator(ListView listview) {
		if (listview == null)
			throw new RuntimeException("需要一个ListView对象");
		ViewParent parent = listview.getParent();
		if (parent == null)
			throw new RuntimeException("ListView未加入布局？");
		Context context = listview.getContext();

		View loadView = View.inflate(context, R.layout.load, null);
		mLoadImage = ButterKnife.findById(loadView, R.id.load_image);
		mLoadText = ButterKnife.findById(loadView, R.id.load_text);

		if (parent instanceof ViewGroup) {
			ViewGroup viewGroup = (ViewGroup) parent;
			int index = viewGroup.indexOfChild(listview);
			viewGroup.addView(loadView, ++index);
		} else
			throw new RuntimeException("使用方式不对");
		listview.setEmptyView(loadView);
		mLoadLsw = listview;
	}

	/**
	 * 
	 * @param resId 图片id
	 * @param text 加载文字
	 */
	public void loading(int resId, String text) {
		mLoadImage.setImageResource(resId);
		mLoadText.setText(text);
		Animation loadAnim = AnimationUtils.loadAnimation(mLoadLsw.getContext(), R.anim.load_animation);
		mLoadImage.startAnimation(loadAnim);
		mLoadImage.setOnClickListener(null);
		mLoadText.setOnClickListener(null);
	}

	/**
	 * 使用Glide加载gif loading
	 * 
	 * @param context
	 * @param resId
	 * @param text
	 */
	private void loading(Context context, int resId, String text) {
		Glide.with(context.getApplicationContext()).load(resId)
				.override(PxUtils.dp2px(50, context), PxUtils.dp2px(50, context)).into(mLoadImage);
		mLoadText.setText(text);
		Animation loadAnim = AnimationUtils.loadAnimation(mLoadLsw.getContext(), R.anim.load_animation);
		mLoadImage.startAnimation(loadAnim);
		mLoadImage.setOnClickListener(null);
		mLoadText.setOnClickListener(null);
	}

	/**
	 * 加载为空、加载错误时的提示
	 * 
	 * @param resId 图片id
	 * @param text 加载完成的文字提示
	 */
	public void loadEnd(int resId, String text) {
		mLoadImage.clearAnimation();
		mLoadImage.setImageResource(resId);
		mLoadText.setText(text);
		mLoadImage.setOnClickListener(null);
		mLoadText.setOnClickListener(null);
	}

	/**
	 * 
	 * @param resId 图片id
	 * @param l 点击重新加载的listener
	 */
	public void loadError(int resId, View.OnClickListener l) {
		mLoadImage.clearAnimation();
		mLoadImage.setImageResource(resId);
		mLoadText.setText("加载失败、点击重试");
		mLoadImage.setOnClickListener(l);
		mLoadText.setOnClickListener(l);
	}
}
