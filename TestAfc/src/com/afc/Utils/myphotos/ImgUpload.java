package com.afc.Utils.myphotos;

import android.content.Context;
import android.graphics.Bitmap;

import com.afc.App.UrlPool;
import com.afc.Utils.AccountMgr;
import com.idroid.async.AsyncWorker;
import com.squareup.okhttp.LongTimeAuzHttp;
import com.squareup.okhttp.Params;
import com.squareup.okhttp.RespondCallBack;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 图片上传,按照高宽来做压缩
 * 
 * @author yiyi
 * @date 2016年3月7日
 */
public class ImgUpload implements UploadUtil {
	private int targetWidth = 450, targetHeight = 450;
	private static final int Max = 5;// 支持同时上传5张图片

	private UpLoadListener mListener;
	private Context mContext;
	private List<String> mPaths;
	private String mTag;
	private String FileMD5;
	private int mCategory;
    private int ProjectNum;
	private String PName;
	/**
	 * 业务类型默认为other = 1
	 */
	private int[] modules = { 1, 1, 1, 1, 1 };

	public ImgUpload(Context context, List<String> imgPaths, UpLoadListener l, String tag,String fileMD5,int categoryNum,int project,String name) {
		mContext = context.getApplicationContext();
		mPaths = imgPaths;
		mListener = l;
		mTag = tag;
		FileMD5=fileMD5;
		mCategory=categoryNum;
		ProjectNum=project;
		PName=name;
	}

	/**
	 * 设置图片类型(目前最多支持5张图片),图片没有设置对应业务int的都取设置第一图片的int
	 * 
	 * @param businessModule
	 * 
	 */
	public UploadUtil setBusinessModule(int... businessModule) {
		if (businessModule == null)
			return this;
		int lenth = businessModule.length;
		for (int i = 0; i < modules.length; i++) {
			if (i < lenth)
				this.modules[i] = businessModule[i];
			else
				this.modules[i] = businessModule[0];
		}
		return this;
	}

	@Override
	public void doUpload() {

		AsyncWorker.execute(new Runnable() {
			@Override
			public void run() {
				doUpLoadInAsyncTask();
			}
		});

	}

	/**
	 * 用异步线程操作图片的压缩与上传
	 */
	private void doUpLoadInAsyncTask(){

		int count = mPaths.size();

		List<Photo> photos = new ArrayList<Photo>();
		for (int i = 0; i < count; i++) {
			String photoPath = mPaths.get(i);

			byte[] photoBytes = Bitmap2Bytes(
					BmpCompressUtil.decodeSampledBitmapFromFile(photoPath, targetWidth, targetHeight));
			if (null != photoBytes) {
				if (i < Max) {
					Photo photo = new Photo(i + 1, photoPath.substring(photoPath.lastIndexOf(".")), photoBytes,
							Filetype.Img, modules[i]);
					photos.add(photo);
				}
			}else{
				if (mListener != null) {
					mListener.onComplete(false, photoPath+"解码失败,请重新选择");
					return;
				}
			}

		}

		/*
		*Content  文件内容(字节)   MD5 文件MD5值(百度可以查到)  Category  Name  文档名称
		* */
		Params params = new Params();
		/*params.add("memberId", AccountMgr.getMemberId(mContext));   aa/ff/oo.jpd
		params.add("files", photos);*/
		params.add("Name",PName);//文档名称     图片的名称
		params.add("Project",ProjectNum);//文档项目   0：会员动态 1：活动 2:图标3:资产图片4:吐槽5:项目6:产品7：会员头像
		params.add("Category",mCategory);//文档类型  0:图片  1:文档  2:视频
		params.add("MD5",FileMD5);
		params.add("Content",photos.get(0).data);//文件内容(字节)

		LongTimeAuzHttp.longTimeOkHttpClient.setConnectTimeout(55, TimeUnit.SECONDS);

		LongTimeAuzHttp.post(UrlPool.UploadFiles, params, new RespondCallBack<String>() {

			@Override
			protected String convert(String json) {
				return json;
			}

			@Override
			public void onSuccess(String data) {
				if (mListener != null) {
					mListener.onComplete(true, data);
				}
			}

			@Override
			public void onFailure(int errorCode, String msg) {
				if (mListener != null) {
					mListener.onComplete(false, msg);
				}
			}
		}, mTag);
	}


	/**
	 * 表示图片的对象
	 */
	class Photo implements Serializable{
		private int Flag;
		private String Extension;
		private byte[] data;
		private int fileType;
		private int businessModule;

		public Photo(int flag, String extension, byte[] data, int fileType, int module) {
			super();
			Flag = flag;
			Extension = extension;
			this.data = data;
			this.fileType = fileType;
			this.businessModule = module;
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
			return data;
		}

		public void setData(byte[] Data) {
			this.data = Data;
		}

		public int getFileType() {
			return fileType;
		}

		public void setFileType(int fileType) {
			this.fileType = fileType;
		}

		public int getBusinessModule() {
			return businessModule;
		}

		public void setBusinessModule(int businessModule) {
			this.businessModule = businessModule;
		}

	}

	/**
	 * 图片转换为字节数组
	 */
	private byte[] Bitmap2Bytes(Bitmap bm) {
		if (bm==null){
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

}
