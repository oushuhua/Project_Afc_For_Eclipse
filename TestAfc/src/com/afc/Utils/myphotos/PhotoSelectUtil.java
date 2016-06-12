package com.afc.Utils.myphotos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.idroid.widget.Toaster;
import com.afc.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * 图片选择界面通用类(最多选择3张图片)
 * @author zhangbo
 *
 */
public class PhotoSelectUtil {

	private PopupWindow photoCheckWindow;
	private Context mContext;
	private List<String> photoPathList;  //图片路径集合
	private int maxPhotoAmount;
	

	public PhotoSelectUtil(Activity mContext) {
		super();
		this.mContext = mContext;
		this.photoPathList = new ArrayList<String>();
		initPopupWindows();
	}
	
	
	/**
	 * 弹出选择图片窗口(拍照或相册选取)
	 */
	public void getPhoto(int maxPhotoAmount){
		this.maxPhotoAmount = maxPhotoAmount;
		
		View rootView = ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
		photoCheckWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
	}
	


	/**
	 * 打开本地相册选择图片上传
	 */
	private void openLocalPhoto(int maxSize) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			Toaster.show(mContext, "外部储存不可用！");
			return;
		}

		Intent intent = new Intent();
		intent.setClass(mContext, PhotoSelectActivity.class);
		intent.putExtra("SurplusSize", maxSize);
		mContext.startActivity(intent);

		// Intent intent = new Intent(Intent.ACTION_PICK, null);
		// intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
		// "image/*");
	}

	/**
	 * 打开照相机进行拍照后上传
	 */
	private File photoFile;
	public void takePhoto() {
		photoFile = null;
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
			Toaster.show(mContext, "外部储存不可用！");
			return;
		}

		 
		//调用系统相机拍照并将完成后的照片保存至自定义的目录下(经试验,若保存至系统公有的图片目录下部分手机拍照完成无法返回,坑爹啊)
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String path = System.currentTimeMillis() + ".jpg";
		File dir = new File(Environment.getExternalStorageDirectory() + "/KUPARTS");
		if(!dir.exists()){
			dir.mkdirs();
		}
		
		photoFile = new File(dir,path);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
		((Activity)mContext).startActivityForResult(intent, 0x201);

	}

	/**
	 *  获取拍照后的图片目录
	 *  may be null
	 */
	public  File getPhotoFile(){
		return photoFile;
	}


	/**
	 *  初始化图片选择时弹窗
	 */
	private void initPopupWindows() {
		photoCheckWindow = new PopupWindow(mContext);
		View view = LayoutInflater.from(mContext).inflate(R.layout.popup_check_photos, new LinearLayout(mContext),false);
		photoCheckWindow.setWidth(LayoutParams.MATCH_PARENT);
		photoCheckWindow.setHeight(LayoutParams.MATCH_PARENT);
		photoCheckWindow.setBackgroundDrawable(new BitmapDrawable());
		photoCheckWindow.setFocusable(true);
		photoCheckWindow.setOutsideTouchable(true);
		photoCheckWindow.setContentView(view);

		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
		view.findViewById(R.id.pop_topView).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				photoCheckWindow.dismiss();
			}
		});

		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				takePhoto();
				photoCheckWindow.dismiss();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				openLocalPhoto(maxPhotoAmount);
				photoCheckWindow.dismiss();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				photoCheckWindow.dismiss();
			}
		});

		

	}
	
	
}
