package com.afc.Utils.myphotos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.afc.App.BasicActivity;
import com.afc.App.BasicFragmentActivity;
import com.afc.App.UrlPool;
import com.afc.Utils.AccountMgr;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.Params;
import com.squareup.okhttp.RespondCallBack;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 图片上传相关工具类
 * 
 * @author zhangbo
 *
 */
public class PhotoUpUtils {

	public final static class PicType {
		// 图片类型，0其他，1服务商门店照，2产品图片，3用户头像
		public static final int Other = 0;
		public static final int Service = 1;
		public static final int Product = 2;
		public static final int Head = 3;
	}

	// 上传完成的回调
	private OnUpLoadCompleteListener l;

	public void setListener(OnUpLoadCompleteListener l) {
		this.l = l;
	}

	private Activity mActivity;
	private List<String> photoPathList;
	private String tag;

	public PhotoUpUtils(Activity mActivity, List<String> photoPathList, OnUpLoadCompleteListener l) {
		super();
		this.mActivity = mActivity;
		this.photoPathList = photoPathList;
		this.l = l;

		if (mActivity instanceof BasicActivity) {
			tag = ((BasicActivity) mActivity).TAG;
		} else if (mActivity instanceof BasicFragmentActivity) {
			tag = ((BasicFragmentActivity) mActivity).TAG;
		} else {
			String className = mActivity.getLocalClassName();
			tag = className.substring(className.indexOf("."));
		}
	}

	/**
	 * 照片类型
	 */
	private int[] photoTypes = { 0, 0, 0 };

	public void setPhotoTypes(int... types) {
		if (types == null)
			return;
		if (types.length > 3)
			return;

		for (int i = 0; i < types.length; i++) {
			photoTypes[i] = types[i];
		}
	}

	public void doUploadPhotos() {
		doUploadPhotos(1024);
	}

	/**
	 * 上传图片到服务器
	 */
	public void doUploadPhotos(int maxSize) {

		int photoCount = photoPathList.size();

		List<Photo> photos = new ArrayList<Photo>();
		for (int i = 0; i < photoCount; i++) {
			String photoPath = photoPathList.get(i);
			byte[] photoBytes = Bitmap2Bytes(BmpCompressUtil.decodeSampledBitmapFromFile(photoPath, 200, 200));
			Photo photo = new Photo(i + 1, photoPath.substring(photoPath.lastIndexOf(".")), photoBytes, photoTypes[i]);
			photos.add(photo);
		}

		Params params = new Params();
		params.add("memberId",  AccountMgr.getMemberId(mActivity));
		params.add("pictures", photos);

		// AuzHttp.SIG = PreferencesUtils.getString(mActivity, Const.SIG);

		// String newUrl =
		// "http://172.16.13.32:8091/api/Uploadpic/UploadPictures";
		// AuzHttp.post(newUrl, params, new RespondCallBack<String>() {
		AuzHttp.sOkHttpClient.setConnectTimeout(55, TimeUnit.SECONDS);
		AuzHttp.post(UrlPool.UploadFiles, params, new RespondCallBack<String>() {

			@Override
			protected String convert(String json) {
				return json;
			}

			@Override
			public void onSuccess(String data) {
				if (l != null) {
					l.onComplete(true, data);
				}
				AuzHttp.sOkHttpClient.setConnectTimeout(25, TimeUnit.SECONDS);
			}

			@Override
			public void onFailure(int errorCode, String msg) {
				if (l != null) {
					l.onComplete(false, msg);
				}
				AuzHttp.sOkHttpClient.setConnectTimeout(25, TimeUnit.SECONDS);
			}
		}, tag);

	}

	/**
	 * 很据路径获取图片
	 * 
	 */
	private Bitmap getImage(Activity avtivity, String imagePath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(imagePath, options);
		int width = options.outWidth;
		options.inSampleSize = width / avtivity.getWindowManager().getDefaultDisplay().getWidth();
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(imagePath, options);
	}

	/**
	 * 图片转换为字节数组
	 */
	private byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * 表示图片的对象
	 */
	class Photo {
		private int Flag;
		private String Extension;
		private byte[] Data;
		private int picType;

		public Photo(int flag, String extension, byte[] data, int picType) {
			super();
			Flag = flag;
			Extension = extension;
			Data = data;
			this.picType = picType;
		}

		public int getPicType() {
			return picType;
		}

		public void setPicType(int picType) {
			this.picType = picType;
		}

		public int getFlag() {
			return Flag;
		}

		public void setFlag(int Flag) {
			this.Flag = Flag;
		}

		public String getExtension() {
			return Extension;
		}

		public void setExtension(String Extension) {
			this.Extension = Extension;
		}

		public byte[] getData() {
			return Data;
		}

		public void setData(byte[] Data) {
			this.Data = Data;
		}

	}

	/**
	 * 图片上传完成的回调
	 *
	 */
	public interface OnUpLoadCompleteListener {
		void onComplete(boolean isSuccess, String response);
	}

}
