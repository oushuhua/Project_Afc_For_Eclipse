package com.afc.Utils.myphotos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import com.afc.R;

/**
 * 这个是用于进行图片浏览时的界面
 * 
 */
public class GalleryActivity extends Activity {

	private Intent intent;
	private int photoIndex = -1;
	private int PhotoMain;

	// 顶部显示预览图片位置的textview
	private TextView positionTextView;
	// 获取前一个activity传过来的position

	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	private Context mContext;
	private ArrayList<String> photoPathList = new ArrayList<String>();// 已选图片的路径集合
	int photoCount; // 预览图片的数量
	boolean[] isCheckFlags;// 记录图片预览时图片选中与否额状态,初始状态全部为选中状态
	int currentIndex; // 记录当前预览的图片索引

	RelativeLayout photo_relativeLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myphoto_gallery);// 切屏到主界面
		mContext = this;

		intent = getIntent();
		photoIndex = intent.getIntExtra("PhotoIndex", -1);
		PhotoMain = intent.getIntExtra("NewMaintenance", -1);
		photoPathList.addAll(intent.getStringArrayListExtra("photoPathList"));
		photoCount = photoPathList.size();
		isCheckFlags = new boolean[photoCount];
		for (int i = 0; i < isCheckFlags.length; i++) {
			isCheckFlags[i] = true;
		}

		initHeadLayout();

		// Bundle bundle = intent.getExtras();
		// position = Integer.parseInt(intent.getStringExtra("position"));
		// isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(R.id.gallery01);
		for (int i = 0; i < photoPathList.size(); i++) {
			initListViews(photoPathList.get(i));
		}

		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin(10);
		pager.setOnPageChangeListener(pageChangeListener);
		if (photoIndex > -1 && photoIndex < photoCount) {
			pager.setCurrentItem(photoIndex);
		}
	}

	/**
	 * 用给定图片生成适配器数据
	 */
	private void initListViews(String imagePath) {

		if (listViews == null)
			listViews = new ArrayList<View>();

		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		Glide.with(mContext).load(imagePath).into(img);

		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}

	/**
	 * 初始化头部状态栏
	 */
	private TextView rightTextView;// 完成按钮
	private TextView leftTextView; // 返回按钮
	private TextView checkView; // 取消选中按钮

	private void initHeadLayout() {

		RelativeLayout headLayout = (RelativeLayout) findViewById(R.id.headLayout);

		RelativeLayout leftLayout = (RelativeLayout) headLayout.findViewById(R.id.leftLayout);
		RelativeLayout rightLayout = (RelativeLayout) headLayout.findViewById(R.id.rightLayout);
		ImageView leftImageView = (ImageView) headLayout.findViewById(R.id.leftImageView);
		leftTextView = (TextView) headLayout.findViewById(R.id.leftTextView);
		rightTextView = (TextView) headLayout.findViewById(R.id.rightTextView);
		TextView titleTextView = (TextView) headLayout.findViewById(R.id.titleTextView);
		checkView = (TextView) findViewById(R.id.send_button);
		if (PhotoMain == 200) {
			checkView.setVisibility(TextView.GONE);
		}
		titleTextView.setVisibility(View.GONE);
		leftImageView.setImageResource(R.mipmap.ic_k2_back_btn);
		leftTextView.setText("1/" + photoCount);
		checkCount = photoCount;
		rightTextView.setText("完成(" + checkCount + "/" + photoCount + ")");

		rightLayout.setOnClickListener(new OkListener());
		leftLayout.setOnClickListener(new BackListener());
		checkView.setOnClickListener(new CheckListener());
	}

	// 返回按钮添加的监听器
	private class BackListener implements OnClickListener {

		public void onClick(View v) {

			finish();
		}
	}

	// 取消选中按钮添加的监听器
	private int checkCount;

	private class CheckListener implements OnClickListener {

		public void onClick(View v) {

			if (isCheckFlags[currentIndex]) {
				checkCount--;
			} else {
				checkCount++;
			}

			isCheckFlags[currentIndex] = !isCheckFlags[currentIndex];
			if (checkCount < 1) {
				rightTextView.setTextColor(Color.parseColor("#999999"));
			} else {
				rightTextView.setTextColor(Color.WHITE);
			}
			rightTextView.setText("完成(" + checkCount + "/" + photoCount + ")");
			refreshCheckState();

			// 以广播的形式通知PhotoSelectActivity取消选择
		}
	}

	// 完成按钮的监听
	private class OkListener implements OnClickListener {

		public void onClick(View v) {

			if (photoCount == 0)
				return;
			if (checkCount < 1)
				return;
			for (int i = isCheckFlags.length - 1; i >= 0; i--) {
				if (!isCheckFlags[i]) {
					photoPathList.remove(i);
				}
			}
			Intent intent = new Intent();
			intent.putExtra("photoPathList", photoPathList);

			if (photoIndex != -1) { // 从相册页面进入图片预览后返回
				setResult(102, intent);
				finish();
			} else { // 从申请退货页面进入图片预览后返回
				intent.setAction("PhotoChecked");
				sendBroadcast(intent);
				finish();
			}

		}

	}

	/**
	 * 实时改变选中图标的状态
	 */
	private void refreshCheckState() {

		Drawable drawable = null;
		if (isCheckFlags[currentIndex]) {
			drawable = getResources().getDrawable(R.mipmap.pictures_selected);
		} else {
			drawable = getResources().getDrawable(R.mipmap.picture_unselected);
		}
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		checkView.setCompoundDrawables(drawable, null, null, null);

	}

	/**
	 * 图片页的滑动事件
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int index) {

			currentIndex = index;
			leftTextView.setText(index + 1 + "/" + photoCount);
			refreshCheckState();
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};

	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		private int size;

		public MyPageAdapter(ArrayList<View> listViews) {

			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public void setListViews(ArrayList<View> listViews) {

			this.listViews = listViews;
			size = listViews == null ? 0 : listViews.size();
		}

		public int getCount() {

			return size;
		}

		public int getItemPosition(Object object) {

			return POSITION_NONE;
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {

			((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
		}

		public void finishUpdate(View arg0) {

		}

		public Object instantiateItem(View arg0, int arg1) {

			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

			}
			catch (Exception e) {}
			return listViews.get(arg1 % size);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

	}

}
