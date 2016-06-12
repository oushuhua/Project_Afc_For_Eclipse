package com.afc.Utils.myphotos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afc.event.ETag;
import com.idroid.async.WeakHandler;
import com.afc.R;

import org.simple.eventbus.EventBus;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class PhotoSelectActivity extends Activity implements ListImageDirPopupWindow.OnImageDirSelected {

	private ProgressDialog mProgressDialog;

	/**
	 * 存储文件夹中的图片数量
	 */
	private int mPicsSize;
	/**
	 * 图片数量最多的文件夹
	 */
	private File mImgDir;
	/**
	 * 所有的图片
	 */
	private List<String> mImgs;

	private GridView mGirdView;
	private MyAdapter mAdapter;
	/**
	 * 临时的辅助类，用于防止同一个文件夹的多次扫描
	 */
	private HashSet<String> mDirPaths = new HashSet<String>();

	/**
	 * 扫描拿到所有的图片文件夹
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	private RelativeLayout mBottomLy;

	private TextView mChooseDir;
	public TextView preview; // 预览按钮
	public TextView rightTextView; // 完成按钮

	int totalCount = 0;

	int surplusSize = 1; // 剩余可选图片的张数
	private ArrayList<String> photoPathList = new ArrayList<String>();// 已选图片的路径集合

	private int mScreenHeight;

	private ListImageDirPopupWindow mListImageDirPopupWindow;

	private WeakHandler mHandler = new WeakHandler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {

			mProgressDialog.dismiss();
			// 为View绑定数据
			data2View();
			// 初始化展示文件夹的popupWindw
			initListDirPopupWindw();

			return false;
		}
	});

	/**
	 * 为View绑定数据
	 */
	private void data2View() {

		if (mImgDir == null) {
			// Toast.makeText(getApplicationContext(), "擦，一张图片没扫描到",
			// Toast.LENGTH_SHORT).show();
			return;
		}

		mImgs = Arrays.asList(mImgDir.list());

		Collections.reverse(mImgs);
		/**
		 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
		 */
		mAdapter = new MyAdapter(this, mImgs, photoPathList, R.layout.grid_item, mImgDir.getAbsolutePath(), surplusSize);
		mGirdView.setAdapter(mAdapter);
		// mImageCount.setText("预览");
	};

	/**
	 * 初始化展示文件夹的popupWindw
	 */
	private void initListDirPopupWindw() {

		mListImageDirPopupWindow = new ListImageDirPopupWindow(LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7), mImageFloders, LayoutInflater
				.from(getApplicationContext()).inflate(R.layout.list_dir, new LinearLayout(this), false));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {

				// 设置背景颜色变暗
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		// 设置选择文件夹的回调
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myphotos);
		Intent intent = getIntent();
		surplusSize = intent.getIntExtra("SurplusSize", 1);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;

		initHeadLayout();
		initView();
		getImages();
		initEvent();

		IntentFilter filter = new IntentFilter();
		filter.addAction("PhotoChecked");
		registerReceiver(photoReceiver, filter);

	}

	/**
	 * 初始化标题栏的布局状态
	 */
	private void initHeadLayout() {

		RelativeLayout headLayout = (RelativeLayout) findViewById(R.id.headLayout);

		RelativeLayout leftLayout = (RelativeLayout) headLayout.findViewById(R.id.leftLayout);
		RelativeLayout rightLayout = (RelativeLayout) headLayout.findViewById(R.id.rightLayout);
		ImageView leftImageView = (ImageView) headLayout.findViewById(R.id.leftImageView);
		TextView leftTextView = (TextView) headLayout.findViewById(R.id.leftTextView);
		rightTextView = (TextView) headLayout.findViewById(R.id.rightTextView);
		TextView titleTextView = (TextView) headLayout.findViewById(R.id.titleTextView);

		titleTextView.setVisibility(View.GONE);
		leftImageView.setImageResource(R.mipmap.ic_k2_back_btn);
		leftTextView.setText("  图片");
		rightTextView.setText("完成(" + photoPathList.size() + "/" + surplusSize + ")");
		rightLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (photoPathList.size() == 0)
					return;
				Log.w("SelectPhotos", photoPathList.toString());
				onCheckOk();
			}
		});
		leftLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
			}
		});
	}

	/**
	 * 完成选择返回结果
	 */
	public void onCheckOk() {

		Intent intent = new Intent();
		intent.putExtra("photoPathList", photoPathList);
		setResult(101, intent);
		EventBus.getDefault().post(photoPathList, "OnPhotoCheckOK");
		finish();
	}

	/**
	 * 注册广播以监听预览界面的完成选择事件后返回结果
	 */
	private BroadcastReceiver photoReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			EventBus.getDefault().post(photoPathList, ETag.CheckPhotosOK);
			PhotoSelectActivity.this.setResult(101, intent);
			finish();
		}
	};

	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
	 */
	private void getImages() {

		if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		new Thread(new Runnable() {

			@Override
			public void run() {

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = PhotoSelectActivity.this.getContentResolver();

				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or "
						+ MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]
				{ "image/jpeg", "image/jpg", "image/png" }, MediaStore.Images.Media.DATE_MODIFIED);

				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));

					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = path;
					// 获取该图片的父路径名
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					// 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
					if (mDirPaths.contains(dirPath)) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						// 初始化imageFloder
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImagePath(path);
					}

					String[] parentFileStr = parentFile.list(new FilenameFilter() {

						@Override
						public boolean accept(File dir, String filename) {

							if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					});
					if (parentFileStr != null) {
						int picSize = parentFileStr.length;
						totalCount += picSize;

						imageFloder.setCount(picSize);
						mImageFloders.add(imageFloder);

						if (picSize > mPicsSize) {
							mPicsSize = picSize;
							mImgDir = parentFile;
						}
					}

				}
				mCursor.close();

				// 扫描完成，辅助的HashSet也就可以释放内存了
				mDirPaths = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();

	}

	/**
	 * 初始化View
	 */
	private void initView() {

		mGirdView = (GridView) findViewById(R.id.id_gridView);
		mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
		preview = (TextView) findViewById(R.id.id_total_count);

		mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);
		preview.setText("预览");
	}

	private void initEvent() {

		/**
		 * 为底部的布局设置点击事件，弹出popupWindow
		 */
		mChooseDir.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// mListImageDirPopupWindow
				// .setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);
			}
		});

		preview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (photoPathList.size() == 0)
					return;
				Intent intent = new Intent();
				intent.putExtra("position", "1");
				intent.putStringArrayListExtra("photoPathList", photoPathList);
				intent.setClass(PhotoSelectActivity.this, GalleryActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void selected(ImageFloder floder) {

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {

				if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		Collections.reverse(mImgs);
		/**
		 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
		 */
		mAdapter = new MyAdapter(this, mImgs, photoPathList, R.layout.grid_item, mImgDir.getAbsolutePath(), surplusSize);
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		// mImageCount.setText(floder.getCount() + "张");
		// mImageCount.setText("预览");
		mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		unregisterReceiver(photoReceiver);
	}

}
