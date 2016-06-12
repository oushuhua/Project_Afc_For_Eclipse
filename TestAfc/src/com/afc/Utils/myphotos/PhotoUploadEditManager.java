package com.afc.Utils.myphotos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;


import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lisi on 2016/4/14.
 */
public class PhotoUploadEditManager {
    private Context mContext;
    private LruCache mBitmapCache;
    private PhotoSelectUtil mPhotoSelectUtil;
    private List<String> mListPath = new ArrayList<>();
    private PhotoUploadEditView mPhotoView;
    private boolean isBinded = false;

    public PhotoUploadEditManager(Activity activity) {
        mContext = activity;
        mBitmapCache = new LruCache(5 * 1024);
        mPhotoSelectUtil = new PhotoSelectUtil(activity);
        EventBus.getDefault().register(this);
    }

    public void bindPhotoView(PhotoUploadEditView view) {
        if (null == view || isBinded) {
            //Log.i(ServiceConst.TAG, "bind failed!!! PhotoSelectorView is null or PhotoUploadEditManager has bind other view!!!");
            return;
        }
        mPhotoView = view;
        view.bindManager(this);
        isBinded = true;
    }

    public void unBindManager() {
        if (null != mPhotoView) {
            mPhotoView.unBindManager();
            mPhotoView = null;
            isBinded = false;
        }
    }

    public void unRegisterEventbus() {
        EventBus.getDefault().unregister(this);
    }

    /**
     * 启动图片预览界面
     */
    public void startGalerry(int currentIndex) {
        // 进入图片预览界面
        Intent intent = new Intent();
        intent.setClass(mContext, GalleryActivity.class);
        intent.setFlags(1);
        intent.putExtra("NewMaintenance", 0);
        intent.putExtra("PhotoIndex", currentIndex);
        intent.putStringArrayListExtra("photoPathList", (ArrayList<String>) mListPath);
        mContext.startActivity(intent);
    }

    /**
     * 启动图片选择界面
     */
    public void startSelectPhoto() {
        if (null != mPhotoSelectUtil) {
            int remainNum = mPhotoView.getMaxImageNum() - mListPath.size();
            if (remainNum >= 5) {
                mPhotoSelectUtil.getPhoto(5);
            } else {
                mPhotoSelectUtil.getPhoto(remainNum);
            }
        }
    }

    @Subscriber(tag = "OnPhotoCheckOK")
    private void OnPhotoCheckOK(List<String> list) {
        if (null != mPhotoView && null != list) {
            mListPath.addAll(list);
            mPhotoView.requestLayout();
            mPhotoView.invalidate();
        }
    }

    /**
     * 添加拍照成功后的图片path
     */
    public void addCameraImage(int reqCode) {
        if (0x201 != reqCode || null == mPhotoView || null == mPhotoSelectUtil) {
            return;
        }

        File photoFile = mPhotoSelectUtil.getPhotoFile();
        if (null != photoFile) {
            Bitmap bm = getBitmapFromPath(photoFile.getAbsolutePath(), mPhotoView.getImageLenth());
            if (null != bm) {
                mListPath.add(photoFile.getAbsolutePath());
                mPhotoView.requestLayout();
                mPhotoView.invalidate();
            }
        }
    }

    /**
     * 获取图片path总数
     */
    public int getImageNums() {
        return mListPath.size();
    }

    /**
     * 获取指定位置的图片path
     */
    public String getImagePath(int index) {
        if (index >= 0 && index < mListPath.size()) {
            return mListPath.get(index);
        } else {
            return null;
        }
    }

    /**
     * 移除图片path
     */
    public void removeImage(int index) {
        if (index >= 0 && index < mListPath.size()) {
            mListPath.remove(index);
        }
    }

    /**
     * 获取图片path集合
     */
    public List<String> getImageList() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(mListPath);
        return list;
    }

    /**
     * 获取压缩后的bitmap
     */
    public Bitmap getBitmapFromPath(String path, int viewWid) {
        if (null == path) {
            return null;
        }

        Bitmap bm = (Bitmap) mBitmapCache.get(path);
        if (null != bm) {
            return bm;
        }

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, newOpts);
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;
        int bei = 1;
        if (height > width && height > viewWid) {
            bei = height / viewWid;
        } else if (width > height && width > viewWid) {
            bei = width / viewWid;
        }
        newOpts.inSampleSize = bei;
        newOpts.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, newOpts);

        if (null != bm) {
            mBitmapCache.put(path, bm);
        }
        return bm;
    }
}
